package com.linhtran.statelessauthentication.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TokenDTO {
    private String accessToken;
}
