package com.jonathan;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.reactive.HttpComponentsClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

@Component
public class WebFluxComponent {
    @Bean
    WebClient webClient(WebClient.Builder builder){
        return builder
                .build();
    }

    @Bean
    public WebClient.Builder webClientBuilder(){
        String connectionProviderName = "1000ConnectionsProvider";
        int maxConnections = 10000;
        HttpClient httpClient = HttpClient.create(
                ConnectionProvider.create(connectionProviderName, maxConnections)
        );
        return WebClient.builder()
                .clientConnector(
                        new ReactorClientHttpConnector(httpClient)
                );
    }

    @Bean
    RedisClient redisClient(){
        return RedisClient.create("redis://redishost:6379/");
    }

    @Bean
    StatefulRedisConnection<String, String> connection(RedisClient redisClient){
        return redisClient.connect();
    }
}
