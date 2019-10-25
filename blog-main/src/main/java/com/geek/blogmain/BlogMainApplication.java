package com.geek.blogmain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication(scanBasePackages = {"com.geek"})
public class BlogMainApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogMainApplication.class, args);
    }

}
