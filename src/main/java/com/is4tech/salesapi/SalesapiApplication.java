package com.is4tech.salesapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration;

@SpringBootApplication
public class SalesapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SalesapiApplication.class, args);
    }

}
