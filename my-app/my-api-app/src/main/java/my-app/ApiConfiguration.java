import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;

public class ApiConfiguration extends Configuration {
    // TODO: implement service configuration

    @NotEmpty
    private String responseTemplate;

    @JsonProperty
    public String getResponseTemplate(){
        return this.responseTemplate;
    }

    @JsonProperty
    public void setResponseTemplate(String responseTemplate){
        this.responseTemplate = responseTemplate;
    }

}
