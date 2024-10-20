package com.onefineday.ratelimiter.services;

import com.onefineday.ratelimiter.models.Token;
import com.onefineday.ratelimiter.models.TokenStatus;
import com.onefineday.ratelimiter.repositories.TokenRepository;
import com.onefineday.ratelimiter.requests.CreateTokenRequest;
import com.onefineday.ratelimiter.requests.UpdateTokenRequest;
import com.onefineday.ratelimiter.utilities.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.UUID;


@Service
public class TokenService {

    @Autowired
    UserService userService;

    @Autowired
    TokenRepository tokenRepository;

    public Token createToken(CreateTokenRequest createTokenRequest) {

        Token token = new Token();
        token.setName(createTokenRequest.getName());
        token.setToken(this.generateToken());
        token.setRequest(createTokenRequest.getRequest());
        token.setTime(createTokenRequest.getTime());
        token.setBlockTime(createTokenRequest.getBlockTime());
        token.setStatus(TokenStatus.ACTIVE);
        token.setUser(userService.getCurrentUserDetails());

        return tokenRepository.save(token);

    }

    public String generateToken() {
        UUID randomUUID = UUID.randomUUID();
        return randomUUID.toString().replaceAll("-", "");
    }

    public Token updateToken(Long id, UpdateTokenRequest updateTokenRequest) throws Exception {

        Token token = tokenRepository.findById(id).orElseThrow(() -> new Exception("Token not found with id: " + id));

        if (updateTokenRequest.getRequest() != null) {
            token.setRequest((updateTokenRequest.getRequest()));
        }
        if (updateTokenRequest.getTime() != null) {
            token.setTime((updateTokenRequest.getTime()));
        }
        if (updateTokenRequest.getBlockTime() != null) {
            token.setBlockTime((updateTokenRequest.getBlockTime()));
        }
        if (updateTokenRequest.getStatus() != null) {
            token.setStatus(TokenStatus.valueOf((updateTokenRequest.getStatus())));
        }
        // Save the updated task
        return tokenRepository.save(token);

    }
}
