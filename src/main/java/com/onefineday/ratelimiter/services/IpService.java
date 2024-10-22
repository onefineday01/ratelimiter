package com.onefineday.ratelimiter.services;

import com.onefineday.ratelimiter.models.*;
import com.onefineday.ratelimiter.repositories.IpRepository;
import com.onefineday.ratelimiter.requests.AddIpRequest;
import com.onefineday.ratelimiter.requests.PaginationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class IpService {

    @Autowired
    UserService userService;

    @Autowired
    TokenService tokenService;

    @Autowired
    IpRepository ipRepository;

    public Ip validateAndIpTokenMapping(Long tokenId, AddIpRequest ipRequest) throws Exception {

        Token token = tokenService.validateToken(tokenId);
        return addIp(ipRequest.getIp(), token);
    }

    public Ip addIp(String ipValue, Token token) {

        Ip ip = new Ip();
        ip.setToken(token);
        ip.setIp(ipValue);
        ip.setStatus(IpStatus.NORMAL);

        return ipRepository.save(ip);
    }

    public Page<?> getAllTokenIps(Long tokenId, PaginationRequest paginationRequest) throws Exception {
        Token token = tokenService.validateToken(tokenId);
        return ipRepository.findAllByTokenId(tokenId, paginationRequest.getPageRequest());
    }


    public Ip getTokenIpDetails(Token token, String ip) {
        return ipRepository.findByTokenIdAndIp(token.getId(), ip);
    }
}
