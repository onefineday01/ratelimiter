package com.onefineday.ratelimiter.requests;


import jakarta.validation.constraints.*;

public class RegisterUserRequest {

    @NotEmpty(message = "Name is mandatory")
    @Size(min = 3, max = 20, message = "Name must be between 3 and 50 characters")
    private String name;

    @NotEmpty(message = "Username is mandatory")
    @Email(message = "Username must be a valid email address")
    private String username;

    @NotEmpty(message = "Password is mandatory")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[a-zA-Z]).{8,}$", message = "Password must be at least 8 characters long, contain at least one number, one special character, and one letter")
    private String password;

    @NotEmpty(message = "Mobile number is mandatory")
    @Pattern(regexp = "^\\d{10}$", message = "Mobile number must be exactly 10 digits")
    private String mobile;


}
