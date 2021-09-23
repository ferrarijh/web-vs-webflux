package com.jonathan;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
public class BackendController {
    @GetMapping(path = "/hello")
    Mono<String> hello(){
        return Mono.just("Hello!")
                .delayElement(Duration.ofMillis(1000));
    }
}
