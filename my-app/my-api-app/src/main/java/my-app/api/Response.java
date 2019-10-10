import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;


public class Response {
    private long id;

    @Length(max = 10)
    private String response;

    public Response() {
    }

    public Response(long id, String response) {
        this.id=id;
        this.response=response;
    }

    @JsonProperty
    public long getId()
    {
        return this.id;
    }

    @JsonProperty
    public String getResponse()
    {
        return this.response;
    }

}
