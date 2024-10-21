package com.onefineday.ratelimiter.controllers;

import com.onefineday.ratelimiter.models.User;
import com.onefineday.ratelimiter.requests.LoginUserRequest;
import com.onefineday.ratelimiter.requests.RegisterUserRequest;
import com.onefineday.ratelimiter.security.JwtUtil;
import com.onefineday.ratelimiter.services.UserDetailsServiceImpl;
import com.onefineday.ratelimiter.services.UserService;
import com.onefineday.ratelimiter.utilities.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.Collections;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Object>> register(@Valid @RequestBody RegisterUserRequest registerUserRequest) {

        User user = new User();
        user.setName(registerUserRequest.getName());
        user.setUsername(registerUserRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerUserRequest.getPassword()));
        user.setMobile(registerUserRequest.getMobile());

        return ResponseEntity.ok(new ApiResponse<>(userService.registerUser(user), true, Collections.emptyList()));
    }

    @PostMapping("/login")
    public ResponseEntity<?>  login(@Valid @RequestBody LoginUserRequest loginUserRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginUserRequest.getUsername(), loginUserRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginUserRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails.getUsername());
//        final String jwt = jwtUtil.createToken(map, userDetails.getUsername());

        return ResponseEntity.ok(new ApiResponse<>(jwt, true, Collections.emptyList()));
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getUserProfile() {
        ApiResponse<User> userDetail = new ApiResponse<>(userService.getCurrentUserDetails(), true, Collections.emptyList());
        return ResponseEntity.ok(userDetail);
    }

}
