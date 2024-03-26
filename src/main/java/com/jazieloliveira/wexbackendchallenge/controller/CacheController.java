package com.jazieloliveira.wexbackendchallenge.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cache")
public class CacheController {
    private static final Logger logger = LoggerFactory.getLogger(CacheController.class);

    @Autowired
    private CacheManager cacheManager;

    @DeleteMapping()
    //test
    ResponseEntity<Object> cleanCache() {
        try{
            cacheManager.getCache("rates_of_exchange").clear();
            return ResponseEntity.status(204).body(null);
        }
        catch (NullPointerException e){
            logger.error("Error cleaning cache", e);
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
