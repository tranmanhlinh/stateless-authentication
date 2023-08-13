package com.linhtran.statelessauthentication.service;

import java.util.Random;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.stereotype.Service;

import com.linhtran.statelessauthentication.dto.LoginDTO;
import com.linhtran.statelessauthentication.dto.TokenDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final CryptoService cryptoService;
    private final JWTService jwtService;

    private final String STATIC_TOKEN = "Token_123456";
    private final String FIXED_USERNAME = "username";
    private final String FIXED_PASSWORD = "password";

    private final String SECRET_HASH_VALUE = "secret";

    public TokenDTO authenticateRawStringToken(LoginDTO loginDTO) throws AccountNotFoundException{
        validateUsernameAndPassword(loginDTO);
        return TokenDTO.builder()
                .accessToken(STATIC_TOKEN)
                .build();
    }

    public TokenDTO authenticateHashToken(LoginDTO loginDTO) throws Exception {
        validateUsernameAndPassword(loginDTO);
        return TokenDTO.builder()
                .accessToken(cryptoService.generate(loginDTO.getUsername()))
                .build();
    }

    public boolean validateRawToken(String rawToken) {
        return rawToken != null && rawToken.equals(STATIC_TOKEN);
    }

    public boolean validateHashToken(String token, String username) throws Exception {
        return cryptoService.validateHash(token, username);
    }

    private void validateUsernameAndPassword(LoginDTO loginDTO) throws AccountNotFoundException {
        if (loginDTO == null || !FIXED_USERNAME.equals(loginDTO.getUsername()) || !FIXED_PASSWORD.equals(loginDTO.getPassword())) {
            throw new AccountNotFoundException();
        }
    }


    public TokenDTO authenticateJwtToken(LoginDTO loginDTO) throws AccountNotFoundException {
        validateUsernameAndPassword(loginDTO);

        // Random assign role for user for testing purpose
        String role = new Random().nextInt(2) == 0 ? "ADMIN" : "USER";
        try {
            return TokenDTO.builder().accessToken(jwtService.generateJwt(loginDTO.getUsername(), role)).build();
        } catch (Exception ex) {
            throw ex;
        }
    }

    public String validateJWTToken(String token) {
        return jwtService.validate(token);
    }
}
