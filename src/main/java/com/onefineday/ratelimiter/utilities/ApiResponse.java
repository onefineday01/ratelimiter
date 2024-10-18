package com.onefineday.ratelimiter.utilities;

import lombok.Data;
import java.util.List;

@Data
public class ApiResponse<T> {

    private T data;
    private boolean success;
    private List<String> errors;

    public ApiResponse() {}

    public ApiResponse(T data, boolean success, List<String> errors) {
        this.data = data;
        this.success = success;
        this.errors = errors;
    }

}