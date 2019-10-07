import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
public class Book {
    @Getter @Setter private UUID id;
    @Getter @Setter private String title;
    @Getter @Setter private String subject;
}
