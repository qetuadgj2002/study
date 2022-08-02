package com.example.redis.config;

import com.example.redis.domain.Item;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfig {
    @Bean
    public Cache<Integer, Item> cache() {
        Cache<Integer, Item> cache = Caffeine.newBuilder().build();
        cache.put(10001,new Item(10001,"加油"));
        return cache;
    }

    @Bean
    public CacheManager cacheManager() {
        Caffeine caffeine = Caffeine.newBuilder();
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCaffeine(caffeine);
        cacheManager.setAllowNullValues(true);
        return cacheManager;
    }

}
