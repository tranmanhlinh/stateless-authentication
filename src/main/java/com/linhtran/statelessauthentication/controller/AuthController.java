package com.linhtran.statelessauthentication.controller;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.linhtran.statelessauthentication.dto.LoginDTO;
import com.linhtran.statelessauthentication.dto.TokenDTO;
import com.linhtran.statelessauthentication.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "login-raw-token")
    public ResponseEntity<TokenDTO> loginRawToken(@RequestBody LoginDTO loginDTO) {
        try {
            return ResponseEntity.ok(authService.authenticateRawStringToken(loginDTO));
        } catch (AccountNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

    }

    @PostMapping(value = "login-hash-token")
    public ResponseEntity<TokenDTO> loginHashToken(@RequestBody LoginDTO loginDTO) {
        try {
            return ResponseEntity.ok(authService.authenticateHashToken(loginDTO));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @PostMapping(value = "login-jwt")
    public ResponseEntity<TokenDTO> loginJWT(@RequestBody LoginDTO loginDTO) {
        try {
            return ResponseEntity.ok(authService.authenticateJwtToken(loginDTO));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
}
