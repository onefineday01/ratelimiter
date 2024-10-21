package com.onefineday.ratelimiter.controllers;

import com.onefineday.ratelimiter.models.Token;
import com.onefineday.ratelimiter.models.TokenStatus;
import com.onefineday.ratelimiter.requests.CreateTokenRequest;
import com.onefineday.ratelimiter.requests.PaginationRequest;
import com.onefineday.ratelimiter.requests.UpdateTokenRequest;
import com.onefineday.ratelimiter.services.TokenService;
import com.onefineday.ratelimiter.utilities.ApiResponse;
import com.onefineday.ratelimiter.utilities.ClientIpUtil;
import com.onefineday.ratelimiter.utilities.PaginatedResponse;
import jakarta.validation.Valid;
import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/token")
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @PostMapping("/create")
    public ResponseEntity<?> createToken(@Valid @RequestBody CreateTokenRequest createTokenRequest){
         Token token = tokenService.createToken(createTokenRequest);
         return ResponseEntity.ok(new ApiResponse<>(token, true, Collections.emptyList()));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateToken(@PathVariable Long id,  @Valid @RequestBody UpdateTokenRequest updateTokenRequest) throws Exception {
        Token token = tokenService.updateToken(id, updateTokenRequest);
        return ResponseEntity.ok(new ApiResponse<>(token, true, Collections.emptyList()));
    }

    @GetMapping("")
    public  ResponseEntity<?> getAllTokens(@Valid PaginationRequest paginationRequest) {
        PaginatedResponse<?> tokens =  new PaginatedResponse<>(tokenService.getAllTokens(paginationRequest));
        return ResponseEntity.ok(new ApiResponse<>(tokens, true, Collections.emptyList()));
    }

}
