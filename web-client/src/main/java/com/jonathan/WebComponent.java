package com.jonathan;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.StatefulRedisConnectionImpl;
import io.lettuce.core.api.StatefulRedisConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WebComponent {
    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
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
