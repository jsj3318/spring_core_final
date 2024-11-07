package com.nhnacademy.springcorefinal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class SpringCoreFinalApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCoreFinalApplication.class, args);
    }

}
