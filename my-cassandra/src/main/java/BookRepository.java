import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;


import java.util.ArrayList;
import java.util.List;

public class BookRepository
{
    public static final String KEY_SPACE_NAME = "library";
    public static final String TABLE_NAME = "books";
    private Session session;
    public BookRepository(Session session)
    {
        this.session = session;
    }

    public void createKeyspaceLibrary()
    {
        StringBuilder sb = new StringBuilder("CREATE KEYSPACE IF NOT EXISTS ")
                .append(KEY_SPACE_NAME).append(" WITH replication = {")
                .append("'class':'SimpleStrategy','replication_factor':1};");
        final String query = sb.toString();
        session.execute(query);
    }


    public void dropKeyspaceLibrary()
    {
        StringBuilder sb = new StringBuilder("DROP KEYSPACE IF EXISTS ")
                .append(KEY_SPACE_NAME).append(" ;");
        final String query = sb.toString();
        session.execute(query);
    }

    public void createTableBook()
    {
        StringBuilder sb = new StringBuilder("CREATE TABLE IF NOT EXISTS ")
                .append (KEY_SPACE_NAME).append(".")
                .append (TABLE_NAME).append("(")
                .append ("id uuid, ")
                .append("title text, ")
                .append("subject text, ")
                .append("PRIMARY KEY (title, id));");
        final String query = sb.toString();
        session.execute(query);
    }


    public void dropTableBook()
    {
        StringBuilder sb = new StringBuilder("DROP TABLE IF EXISTS ")
                .append (KEY_SPACE_NAME).append(".")
                .append (TABLE_NAME).append(";");
        final String query = sb.toString();
        session.execute(query);
    }


    public void insertBook(Book book)
    {
        StringBuilder sb = new StringBuilder("INSERT INTO ")
                .append (KEY_SPACE_NAME).append(".")
                .append (TABLE_NAME)
                .append (" (id, title, subject) VALUES (")
                .append(book.getId()).append(", '")
                .append(book.getTitle()).append("', '")
                .append(book.getSubject()).append("');");

        final String query = sb.toString();
        session.execute(query);
    }

    public List<Book> selectAll()
    {
        StringBuilder sb =  new StringBuilder("SELECT * from ")
                .append(KEY_SPACE_NAME).append(".").append(TABLE_NAME);

        final String query = sb.toString();
        ResultSet rs = session.execute(query);

        List<Book> books = new ArrayList<Book>();
        rs.forEach(r-> {
            books.add(new Book(r.getUUID("id"), r.getString("title"), r.getString("subject")));
        });
        return books;
    }

    public void deleteBookByTitle(String title)
    {
        StringBuilder sb =  new StringBuilder("DELETE from ")
                .append(KEY_SPACE_NAME).append(".").append(TABLE_NAME)
                .append(" WHERE title = '").append(title).append("';");

        final String query = sb.toString();
        session.execute(query);
        return ;
    }

}
