package com.onefineday.ratelimiter.controllers;

import com.onefineday.ratelimiter.models.Token;
import com.onefineday.ratelimiter.models.TokenStatus;
import com.onefineday.ratelimiter.requests.CreateTokenRequest;
import com.onefineday.ratelimiter.requests.UpdateTokenRequest;
import com.onefineday.ratelimiter.services.TokenService;
import com.onefineday.ratelimiter.utilities.ApiResponse;
import com.onefineday.ratelimiter.utilities.ClientIpUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;

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
        System.out.println(ClientIpUtil.getClientIp());
        Token token = tokenService.updateToken(id, updateTokenRequest);
        return ResponseEntity.ok(new ApiResponse<>(token, true, Collections.emptyList()));
    }

    // Exception Handler
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        HashMap<Object, Object> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        ApiResponse<Object> response = new ApiResponse<>(Collections.emptyList(), false, errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
