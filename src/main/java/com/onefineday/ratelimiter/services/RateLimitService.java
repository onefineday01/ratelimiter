package com.onefineday.ratelimiter.services;

import com.onefineday.ratelimiter.models.Ip;
import com.onefineday.ratelimiter.models.IpStatus;
import com.onefineday.ratelimiter.models.Token;
import com.onefineday.ratelimiter.utilities.ClientIpUtil;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class RateLimitService {

    @Autowired
    private RedisTemplate<String, Object> redis;

    @Autowired
    private IpService ipService;

    private final int subWindow = 10;  // Sub-window in seconds (e.g., 10 seconds)

    public boolean checkRateLimit() {
        Token token = (Token) SecurityContextHolder.getContext().getAuthentication().getCredentials();
        String ip = ClientIpUtil.getClientIp();
        return isAllowed(token, ip);
    }
    // Rate-limiter function
    public boolean isAllowed(Token token, String ip) {

        String tokenValue = token.getToken();

        System.out.println(getTokenIpStatus(token, ip));
//        long currentTime = Instant.now().getEpochSecond();
//        long currentSubWindow = currentTime / subWindow; // Current sub-window ID
//        int requestLimit = token.getRequest();
//        int timeWindow = token.getTime();
//        int blockTime = token.getBlockTime();

//        String redisKey = tokenValue + ":" + ip + ":" + currentSubWindow;
//
//        // Increment the counter for the current sub-window
//        redisTemplate.opsForValue().increment(redisKey, 1);
//
//        // Set expiration for the current sub-window key
//        redisTemplate.expire(redisKey, timeWindow, TimeUnit.SECONDS);
//
//        // Sum the counts of recent sub-windows
//        int totalRequests = getRequestsInSlidingWindow(tokenValue, ip, currentSubWindow);
//
//        // Check if total requests exceed the limit
//        if (totalRequests > requestLimit) {
//            return false; // Block the request
//        }

        return true; // Allow the request
    }

    private IpStatus getTokenIpStatus(Token token, String ip) {

        String tokenValue = token.getToken();
        String redisKey = "STATUS:"+tokenValue+":"+ip;

        IpStatus ipStatus = (IpStatus) redis.opsForValue().get(redisKey);

        if(ipStatus == null) {

            Ip ipDetails = ipService.getTokenIpDetails(token, ip);

            if(ipDetails == null) {
                ipDetails = ipService.addIp(ip, token);
            }

            ipStatus = ipDetails.getStatus();
            redis.opsForValue().set(redisKey, ipStatus);
        }

        return ipStatus;
    }



    private int getRequestsInSlidingWindow(String token, String ip, long currentSubWindow) {
//        int totalRequests = 0;
//        // Sum the counts from all sub-windows in the current time window
//        for (int i = 0; i < timeWindow / subWindow; i++) {
//            String subWindowKey = token + ":" + ip + ":" + (currentSubWindow - i);
//            Integer count = redisTemplate.opsForValue().get(subWindowKey);
//            if (count != null) {
//                totalRequests += count;
//            }
//        }
//        return totalRequests;
        return 0;
    }
}
