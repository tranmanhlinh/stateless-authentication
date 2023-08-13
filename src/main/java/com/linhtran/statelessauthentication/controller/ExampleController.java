package com.linhtran.statelessauthentication.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.linhtran.statelessauthentication.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api")
public class ExampleController {

    private final AuthService authService;

    @GetMapping("rawtoken")
    public ResponseEntity<String> checkRawTokenAccess(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        boolean valid = authService.validateRawToken(token);

        if (!valid) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You don't have permission");
        }
        return ResponseEntity.ok("You have permission to use this api");
    }

    @GetMapping("hashtoken")
    public ResponseEntity<String> checkHashAccess(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                                                  @RequestHeader("Username") String username) throws Exception {

        boolean valid = authService.validateHashToken(token, username);
        if (!valid) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You don't have permission");
        }

        return ResponseEntity.ok("You have permission to use this api");
    }

    @GetMapping("jwt")
    public ResponseEntity<String> checkJWTAdminAccess(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) throws Exception {

       String role = authService.validateJWTToken(token);

       if (role != null) {
           return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You have permission with role " + role);
       }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You don't have permission");
    }

}
