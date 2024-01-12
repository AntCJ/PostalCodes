package com.anthonylim.postalcodes;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.anthonylim.postalcodes"})
public class PostalCodesApplication {
    public static void main(String[] args) throws IOException, InterruptedException {
        SpringApplication.run(PostalCodesApplication.class, args);
    }
}
