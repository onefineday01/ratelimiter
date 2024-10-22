package com.onefineday.ratelimiter.services;

import com.onefineday.ratelimiter.utilities.ClientIpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class RateLimitService {

    public boolean checkRateLimit() {
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getCredentials());
        System.out.println(ClientIpUtil.getClientIp());
        System.out.println("I am Singhai Raj Jain");
        return true;
    }
}
