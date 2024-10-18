package com.onefineday.ratelimiter.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class LoginUserRequest {

    @NotEmpty(message = "Username is mandatory")
    @Email(message = "Username must be a valid email address")
    private String username;

    @NotEmpty(message = "Password is mandatory")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[a-zA-Z]).{8,}$", message = "Password must be at least 8 characters long, contain at least one number, one special character, and one letter")
    private String password;

}
