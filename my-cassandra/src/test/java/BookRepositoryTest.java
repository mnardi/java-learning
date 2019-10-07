import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.utils.UUIDs;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class BookRepositoryTest {
    private static BookRepository repository;
    private static Session session;

    @Before
    public void connect()
    {
        CassandraConnector connector =  new CassandraConnector();
        connector.connect("127.0.0.1", 9042);
        this.session = connector.getSession();
        repository = new BookRepository(session);
    }

    @Test
    public void whenCreatingAKeyspace_thenCreated()
    {
        repository.createKeyspaceLibrary();

        ResultSet rs = session.execute("SELECT * FROM system_schema.keyspaces;");

        List<String> matchedKeyspaces = rs.all().stream()
                .filter(r -> r.getString(0).equals(BookRepository.KEY_SPACE_NAME.toLowerCase()))
                .map(r -> r.getString(0))
                .collect(Collectors.toList());

        assertEquals(matchedKeyspaces.size(),1);
        assertTrue(matchedKeyspaces.get(0).equals(BookRepository.KEY_SPACE_NAME.toLowerCase()));
    }

    @Test
    public void whenCreatingATable_thenCreated()
    {
        repository.createTableBook();

        ResultSet rs = session.execute("SELECT * FROM " + BookRepository.KEY_SPACE_NAME + "." + BookRepository.TABLE_NAME + ";");

        List<String> columnNames = rs.getColumnDefinitions().asList().stream()
                .map(cl -> cl.getName())
                .collect(Collectors.toList());

        assertEquals(columnNames.size(),3);
        assertTrue(columnNames.contains("id"));
        assertTrue(columnNames.contains("title"));
        assertTrue(columnNames.contains("subject"));
    }

    @Test
    public void whenInsertinByTitle_thenBookExists()
    {
        drop();

        repository.createKeyspaceLibrary();

        repository.createTableBook();


        String title = "My Cassandra Application";
       Book book = new Book(UUIDs.timeBased(), title, "Programming");
       repository.insertBook(book);
       Book savedBook = repository.selectByTitle(title);
       assertEquals(title,savedBook.getTitle());

    }


    @AfterClass
    public static void drop()
    {
        repository.dropTableBook();
        repository.dropKeyspaceLibrary();
    }

}
