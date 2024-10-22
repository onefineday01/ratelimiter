package com.onefineday.ratelimiter.requests;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UpdateTokenRequest {

    @Min(value = 1, message = "Rate Limit Request must be at least 1")
    @Max(value = 1000, message = "Rate Limit Request must be less than or equal to 1000")
    private Integer request;

    @Min(value = 10, message = "Rate Limit Time must be at least 10 second")
    @Max(value = 3600, message = "Rate Limit Time must be less than or equal to 3600 seconds")
    private Integer time;

    @Min(value = 10, message = "Block Time must be at least 10 second")
    @Max(value = 86400, message = "Rate Limit Time must be less than or equal to 86400 seconds")
    private Integer blockTime;

    @Pattern(regexp = "INACTIVE|ACTIVE", message = "Invalid Token status")
    private String status;
}