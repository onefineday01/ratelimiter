package com.onefineday.ratelimiter.requests;

import com.onefineday.ratelimiter.models.TokenStatus;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.Data;



@Data
public class UpdateTokenRequest {

    @Min(value = 1, message = "Rate Limit Request must be at least 1")
    @Max(value = 1000, message = "Rate Limit Request must be less than or equal to 1000")
    private Integer request; // Default 3 requests

    @Min(value = 1, message = "Rate Limit Time must be at least 1 second")
    @Max(value = 3600, message = "Rate Limit Time must be less than or equal to 3600 seconds")
    private Integer time; // Default 3 requests in 60 seconds

    @Min(value = 1, message = "Block Time must be at least 1 second")
    @Max(value = 86400, message = "Rate Limit Time must be less than or equal to 86400 seconds")
    private Integer blockTime; // // Default 3 requests in 60 seconds else block for 3600 seconds

    @Pattern(regexp = "INACTIVE|ACTIVE", message = "Invalid Token status")
    private String status;
}