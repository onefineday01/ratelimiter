package com.onefineday.ratelimiter.controllers;

import com.onefineday.ratelimiter.models.User;
import com.onefineday.ratelimiter.requests.RegisterUserRequest;
import com.onefineday.ratelimiter.services.UserService;
import com.onefineday.ratelimiter.utilities.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<User>> register(@Valid @RequestBody RegisterUserRequest userReq) {

        System.out.println(userReq);
        return null;
//        User user = new User();
//        user.setName(userReq.);
//        user.setUsername(userReq.);
//        user.setPassword(userReq.);
//        user.setMobile(userReq.);
    }
}
