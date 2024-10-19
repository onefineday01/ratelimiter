package com.onefineday.ratelimiter.requests;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateTokenRequest {

    @NotEmpty(message = "Name is mandatory")
    @Size(min = 3, max = 20, message = "Name must be between 3 and 50 characters")
    private String name;

    @Min(value = 1, message = "Rate Limit Request must be at least 1")
    @Max(value = 1000, message = "Rate Limit Request must be less than or equal to 1000")
    private int request = 3; // Default 3 requests

    @Min(value = 1, message = "Rate Limit Time must be at least 1 second")
    @Max(value = 3600, message = "Rate Limit Time must be less than or equal to 3600 seconds")
    private int time = 60; // Default 3 requests in 60 seconds

    @Min(value = 1, message = "Block Time must be at least 1 second")
    @Max(value = 86400, message = "Rate Limit Time must be less than or equal to 86400 seconds")
    private int blockTime = 3600; // // Default 3 requests in 60 seconds else block for 3600 seconds

}