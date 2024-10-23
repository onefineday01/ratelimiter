package com.onefineday.ratelimiter.services;

import com.onefineday.ratelimiter.models.*;
import com.onefineday.ratelimiter.repositories.IpRepository;
import com.onefineday.ratelimiter.requests.AddIpRequest;
import com.onefineday.ratelimiter.requests.PaginationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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

    public Ip saveOrUpdateIp(Ip ip) {
        return ipRepository.save(ip);
    }

    public Ip getTokenIpDetails(Token token, String ip) {
        return ipRepository.findByTokenIdAndIp(token.getId(), ip);
    }

    public Ip updateTokenIpStatus(Token token, String ip, IpStatus ipStatus, LocalDateTime blockTime) {

        Ip ipObj = this.getTokenIpDetails(token, ip);
        if(ipObj == null) {
            ipObj = new Ip();
        }
        ipObj.setStatus(ipStatus);

        if(ipStatus.equals(IpStatus.TEMPORARY_BLOCK) || ipStatus.equals(IpStatus.PERMANENT_BLOCK)) {
            if(blockTime == null) {
                blockTime = LocalDateTime.now();
            }
            ipObj.setBlockedAt(blockTime);
        } else {
            ipObj.setBlockedAt(null);
        }

        return this.saveOrUpdateIp(ipObj);
    }
}
