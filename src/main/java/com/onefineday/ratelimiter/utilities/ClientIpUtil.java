package com.onefineday.ratelimiter.utilities;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class ClientIpUtil {

    public static String getClientIp() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request =  attributes.getRequest();
            String clientIp = request.getHeader("X-Forwarded-For");

            if (clientIp == null || clientIp.isEmpty() || "unknown".equalsIgnoreCase(clientIp)) {
                clientIp = request.getHeader("Proxy-Client-IP");
            }
            if (clientIp == null || clientIp.isEmpty() || "unknown".equalsIgnoreCase(clientIp)) {
                clientIp = request.getHeader("WL-Proxy-Client-IP");
            }
            if (clientIp == null || clientIp.isEmpty() || "unknown".equalsIgnoreCase(clientIp)) {
                clientIp = request.getHeader("HTTP_CLIENT_IP");
            }
            if (clientIp == null || clientIp.isEmpty() || "unknown".equalsIgnoreCase(clientIp)) {
                clientIp = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (clientIp == null || clientIp.isEmpty() || "unknown".equalsIgnoreCase(clientIp)) {
                clientIp = request.getRemoteAddr();
            }

            if (clientIp != null && clientIp.contains(",")) {
                clientIp = clientIp.split(",")[0]; // Get the first IP in the list
            }

            return clientIp;
        }
        return null;
    }
}