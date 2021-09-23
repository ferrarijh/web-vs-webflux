package com.jonathan;

import io.lettuce.core.api.StatefulRedisConnection;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@RequiredArgsConstructor
@SpringBootApplication
public class WebApplication implements CommandLineRunner {

    private final StatefulRedisConnection<String, String> connection;

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class);
    }

    @Override
    public void run(String... args) throws Exception {
        connection.sync().set("hello", "Hello Redis!");
    }
}
