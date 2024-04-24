package org.example.config;

import io.dropwizard.core.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AppConfig extends Configuration {
    private String mongoHost;
    private int mongoPort;
    private String mongoDB;

    @JsonProperty
    public String getMongoHost() {
        return mongoHost;
    }

    @JsonProperty
    public int getMongoPort() {
        return mongoPort;
    }

    @JsonProperty
    public String getMongoDB() {
        return mongoDB;
    }
}

