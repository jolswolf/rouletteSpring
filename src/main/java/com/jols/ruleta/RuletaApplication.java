package com.jols.ruleta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.jols.ruleta"})
@SpringBootApplication

public class RuletaApplication {

    public static void main(String[] args) {
        SpringApplication.run(RuletaApplication.class, args);
    }

}
