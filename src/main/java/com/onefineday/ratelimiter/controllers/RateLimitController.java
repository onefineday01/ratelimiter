package com.onefineday.ratelimiter.controllers;

import com.onefineday.ratelimiter.services.RateLimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ratelimiter")
public class RateLimitController {

    @Autowired
    RateLimitService rateLimitService;

    @PostMapping()
    public void ratelimiter() {

        boolean pass = rateLimitService.checkRateLimit();

    }
}
