package com.onefineday.ratelimiter.controllers;

import com.onefineday.ratelimiter.requests.AddIpRequest;
import com.onefineday.ratelimiter.requests.PaginationRequest;
import com.onefineday.ratelimiter.services.IpService;
import com.onefineday.ratelimiter.utilities.ApiResponse;
import com.onefineday.ratelimiter.utilities.PaginatedResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/api/token/{tokenId}/ip")
public class IpController {

    @Autowired
    IpService ipService;

    @PostMapping()
    public ResponseEntity<?> addIpMapping(@Valid @RequestBody AddIpRequest ipRequest, @PathVariable Long tokenId) throws Exception {
        return ResponseEntity.ok(new ApiResponse<>(ipService.validateAndIpTokenMapping(tokenId, ipRequest), true, Collections.emptyList()));
    }

    @GetMapping()
    public ResponseEntity<?> getAllIpMappings(@Valid PaginationRequest paginationRequest, @PathVariable Long tokenId) throws Exception {
        PaginatedResponse<?> ips =  new PaginatedResponse<>(ipService.getAllTokenIps(tokenId, paginationRequest));
        return ResponseEntity.ok(new ApiResponse<>(ips, true, Collections.emptyList()));
    }

}
