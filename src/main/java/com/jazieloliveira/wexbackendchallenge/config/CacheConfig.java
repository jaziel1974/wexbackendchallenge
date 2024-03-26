package com.jazieloliveira.wexbackendchallenge.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableCaching
@EnableScheduling
public class CacheConfig {
    private static final Logger logger = LoggerFactory.getLogger(CacheConfig.class);

    @Value("${caching.rates-of-exchange.ttl}")
    private Long ratesOfExchangeTTL;

    @CacheEvict(value = "rates-of-exchange", allEntries = true)
    @Scheduled(fixedRateString = "${caching.rates-of-exchange.ttl}")
    public void evictCache() {
        logger.info("emptying cache");
    }
}