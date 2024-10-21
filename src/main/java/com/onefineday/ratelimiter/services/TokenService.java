package com.onefineday.ratelimiter.services;

import com.onefineday.ratelimiter.models.Token;
import com.onefineday.ratelimiter.models.TokenStatus;
import com.onefineday.ratelimiter.models.User;
import com.onefineday.ratelimiter.repositories.TokenRepository;
import com.onefineday.ratelimiter.requests.CreateTokenRequest;
import com.onefineday.ratelimiter.requests.PaginationRequest;
import com.onefineday.ratelimiter.requests.UpdateTokenRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    public Token updateToken(Long id, UpdateTokenRequest updateTokenRequest) throws Exception {

        Token token = tokenRepository.findById(id).orElseThrow(() -> new Exception("Token not found with id " + id));

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
        return tokenRepository.save(token);
    }

    public Token getTokenDetails(Long id) throws Exception {
        return tokenRepository.findById(id).orElseThrow(() -> new Exception("Token not found with id " + id.toString()));
    }

    public Page<?> getAllTokens(PaginationRequest paginationRequest) {
        User user = userService.getCurrentUserDetails();
        return tokenRepository.findAllByUserId(user.getId(),paginationRequest.getPageRequest());
    }

    public Token validateToken(Long tokenId) throws Exception{
        User user = userService.getCurrentUserDetails();
        Token token = this.getTokenDetails(tokenId);
        if(user.getId() != token.getUser().getId()) {
            throw new Exception("Token not found with id " + token.getId().toString());
        }
        return token;
    }

}
