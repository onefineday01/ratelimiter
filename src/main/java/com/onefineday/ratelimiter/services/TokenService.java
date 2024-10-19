package com.onefineday.ratelimiter.services;

import com.onefineday.ratelimiter.models.Token;
import com.onefineday.ratelimiter.models.TokenStatus;
import com.onefineday.ratelimiter.repositories.TokenRepository;
import com.onefineday.ratelimiter.requests.CreateTokenRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
