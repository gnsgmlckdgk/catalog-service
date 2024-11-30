package com.polarbookshop.catalogservice.config;

import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ToString
@ConfigurationProperties(prefix = "polar")
public class PolarProperties {

    private String greeting;

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }
}
