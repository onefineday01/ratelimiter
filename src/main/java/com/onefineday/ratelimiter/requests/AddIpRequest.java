package com.onefineday.ratelimiter.requests;

import com.onefineday.ratelimiter.models.Token;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@Data
public class AddIpRequest {

    @NotEmpty(message = "IP is mandatory")
    @Pattern(regexp = "^((25[0-5]|2[0-4]\\d|[01]?\\d{1,2})\\.){3}(25[0-5]|2[0-4]\\d|[01]?\\d{1,2})$|^(([0-9a-fA-F]{1,4}:){7}([0-9a-fA-F]{1,4}|:)|([0-9a-fA-F]{1,4}:){1,7}:|([0-9a-fA-F]{1,4}:){1,6}:[0-9a-fA-F]{1,4}|([0-9a-fA-F]{1,4}:){1,5}(:[0-9a-fA-F]{1,4}){1,2}|([0-9a-fA-F]{1,4}:){1,4}(:[0-9a-fA-F]{1,4}){1,3}|([0-9a-fA-F]{1,4}:){1,3}(:[0-9a-fA-F]{1,4}){1,4}|([0-9a-fA-F]{1,4}:){1,2}(:[0-9a-fA-F]{1,4}){1,5}|[0-9a-fA-F]{1,4}:((:[0-9a-fA-F]{1,4}){1,6}))$", message = "Not a valid IP")
    private String ip;

}
