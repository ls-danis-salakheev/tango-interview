package me.tango.interview.second;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Configuration class for creation bean on application startup stage.
 */
@Configuration
public class HitCounterConfig {

    /**
     * Creates a new bean, empty map with the default initial size.
     * It's the bean for storing hits count by userId.
     *
     * @return the new map with key {@link String} and value {@link Long}
     */
    @Bean
    public Map<String, Long> hitsPerUser() {
        return new ConcurrentHashMap<>();
    }
}
