package com.shop.config;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.List;

@Configuration
public class RedisConfig {
    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory){
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(10));  //設置緩存過期時間
        return RedisCacheManager.builder(redisConnectionFactory)
                .cacheDefaults(config)
                .build();
    }

    @Bean("productKeyGenerator")
    public KeyGenerator productKeyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                List<String> type = (List<String>) params[0];  //第一個參數是type
                Integer priceRange= (Integer) params[1];  //第二個參數是priceRange
                Integer currentPage = (Integer) params[2];  // 第三個參數是currentPage
                String s = "";
                for(String t : type){
                    s = s + t + ",";
                }
                return "currentPage=" + currentPage +
                        "&type=" + s +
                        "&priceRange=" + priceRange;
            }
        };
    }
}
