package com.me;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;

// When this class is deserialized from the YAML file,
// it will pull two root-level fields from the YAML object:
// 1) template, the template for our Hello World saying, and 2) defaultName, the default name to use. 
public class HelloWorldConfiguration extends Configuration {

    // Both template and defaultName are annotated with @NotEmpty,
    // so if the YAML configuration file has blank values for either or is missing template entirely, 
    // an informative exception will be thrown, and your application won’t start.
    
    @NotEmpty
    private String template;
    // Annoation @NotEmpty validates that the property is not null or empty;

    @NotEmpty
    private String defaultName = "stranger";

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
    public void setDefaultName(String name) {
        this.defaultName = name;
    }

    // Both the getters and setters for template and defaultName are annotated with @JsonProperty,
    // which allows Jackson to both deserialize the properties from a YAML file but also to serialize it.
}

// The mapping from YAML to your application’s Configuration instance is done by Jackson. This means your Configuration class can use all of Jackson’s object-mapping annotations.
// The validation of @NotEmpty is handled by Hibernate Validator, which has a wide range of built-in constraints for you to use.
