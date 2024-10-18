package com.onefineday.ratelimiter.services;

import com.onefineday.ratelimiter.models.User;
import com.onefineday.ratelimiter.repositories.UserRepository;
import com.onefineday.ratelimiter.utilities.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

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
}
