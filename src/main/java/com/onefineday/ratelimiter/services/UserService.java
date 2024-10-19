package com.onefineday.ratelimiter.services;

import com.onefineday.ratelimiter.models.User;
import com.onefineday.ratelimiter.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User registerUser(User user) {
        return userRepository.save(user);
    }

    public User getUserDetails(String username) {
        return userRepository.findByUsername(username);
    }

    public User getCurrentUserDetails() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return this.getUserDetails(username);
    }
}
