package io.github.dev_alan87.sales;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SalesApplication
        extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SalesApplication.class, args);
    }

}
