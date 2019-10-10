import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class InsertBook {
    private String title;
    private String subject;
}