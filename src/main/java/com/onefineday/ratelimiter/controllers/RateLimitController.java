package com.onefineday.ratelimiter.controllers;

import com.onefineday.ratelimiter.services.RateLimitService;
import com.onefineday.ratelimiter.utilities.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/ratelimiter")
public class RateLimitController {

    @Autowired
    RateLimitService rateLimitService;

    @PostMapping()
    public ResponseEntity<?> ratelimiter() {

        boolean pass = rateLimitService.checkRateLimit();
        if(!pass) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>(Collections.emptyList(), true, Collections.singletonList("Ip Blocked")));
        }
        return ResponseEntity.ok(new ApiResponse<>(Collections.singletonList("Request Submitted Successfully"), true, Collections.emptyList()));
    }
}
