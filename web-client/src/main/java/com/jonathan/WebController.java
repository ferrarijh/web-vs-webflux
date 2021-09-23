package com.jonathan;

import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;
import io.lettuce.core.api.sync.RedisCommands;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@RequiredArgsConstructor
@RestController
public class WebController {
    private final RestTemplate template;
    private final URI veryFastServerUri = URI.create("http://veryfastserverhost:8080/hello");
    private final StatefulRedisConnection<String, String> connection;

    @GetMapping(path = "/hello")
    ResponseEntity<String> hello(){
        return template.getForEntity(veryFastServerUri, String.class);
    }

    @GetMapping(path = "/redis")
    ResponseEntity<String> redis(){
        RedisCommands<String, String> commands = connection.sync();
        return ResponseEntity.ok(commands.get("hello"));
    }
}
