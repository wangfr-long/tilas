package com.ithbu.tilas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class TilasApplication {

    public static void main(String[] args) {
        SpringApplication.run(TilasApplication.class, args);
    }

}
