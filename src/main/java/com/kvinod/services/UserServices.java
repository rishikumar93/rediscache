package com.kvinod.services;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kvinod.repository.UserRepository;


import com.kvinod.entity.User;

@Service
public class UserServices {

    @Autowired
    private UserRepository UserRepository;

    @Autowired
    private RedisService redisService;

    // Method to fetch User either from Redis or MySQL
    public User getUser(int UserId) {
        // Check if User exists in Redis
        User User = redisService.getFromCache(UserId);
        if (User != null) {
            return User;  // Return User from cache
        }

        String userId= String. valueOf(UserId);
        // If not in cache, fetch from MySQL
        User = UserRepository.findById(userId).orElse(null);
        if (User != null) {
            // Cache User for future use
            redisService.putToCache(User);
        }
        return User;
    }
}
