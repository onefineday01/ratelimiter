package com.onefineday.ratelimiter.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ratelimiter")
public class RateLimitController {

    @PostMapping()
    public void raj() {
        System.out.println("i am singhai raj jain");
    }
}
