package com.kvinod.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.kvinod.entity.User;

//Redis Service: Implement a service that will handle Redis cache operations.

@Service
public class RedisService {

    @Autowired
    private RedisTemplate<String, User> redisTemplate;

    // Fetch from Redis cache
    public User getFromCache(int UserId) {
        return redisTemplate.opsForValue().get("User:" + UserId);
    }

    // Put into Redis cache
    public void putToCache(User User) {
        redisTemplate.opsForValue().set("User:" + User.getId(), User);
    }

    // Remove from Redis cache
    public void removeFromCache(int UserId) {
        redisTemplate.delete("User:" + UserId);
    }
}
