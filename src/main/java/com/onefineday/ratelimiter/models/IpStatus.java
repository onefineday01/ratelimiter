package com.onefineday.ratelimiter.models;

public enum IpStatus {
    NORMAL,
    WHITELISTED,
    TEMPORARY_BLOCK,
    PERMANENT_BLOCK
}
