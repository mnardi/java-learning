package com.mnardi.javalearning;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;

public class HelloWorldConfiguration extends Configuration {
    // TODO: implement service configuration
    @NotEmpty
    private String template;

    @NotEmpty
    private String defaultName;

    @JsonProperty
    public String getTemplate() {
        return this.template;
    }
    @JsonProperty
    public void setTemplate(String template) {
        this.template = template;
    }

    @JsonProperty
    public String getDefaultName() {
        return this.defaultName;
    }
    @JsonProperty
    public void setDefaultName(String defaultName) {
        this.defaultName = defaultName;
    }

}
