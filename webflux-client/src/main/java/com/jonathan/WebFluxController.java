package com.jonathan;

import io.lettuce.core.api.StatefulRedisConnection;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@RequiredArgsConstructor
@RestController
public class WebFluxController {
    private final WebClient webClient;
    private final URI veryFastServerUri = URI.create("http://veryfastserverhost:8080/hello");
    private final StatefulRedisConnection<String, String> connection;

    @GetMapping(path = "/hello")
    Flux<String> hello(){
        return webClient.get()
                .uri(veryFastServerUri)
                .retrieve()
                .bodyToFlux(String.class);
    }

    @GetMapping(path = "/redis")
    Mono<String> redis(){
        return connection.reactive()
                .get("hello");
    }
}
