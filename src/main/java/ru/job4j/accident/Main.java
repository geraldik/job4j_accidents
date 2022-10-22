package ru.job4j.accident;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication(scanBasePackages = "ru.job4j.accident")
public class Main {

    private static final Logger LOG = LoggerFactory.getLogger(Main.class.getName());

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        LOG.info("App started at: " + LocalDateTime.now());
        System.out.println("Go to http://localhost:8080/index");
    }
}
