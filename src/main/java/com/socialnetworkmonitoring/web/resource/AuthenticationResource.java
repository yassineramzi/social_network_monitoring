package com.socialnetworkmonitoring.web.resource;

import com.socialnetworkmonitoring.service.AuthenticationService;
import com.socialnetworkmonitoring.service.dto.JwtDTO;
import com.socialnetworkmonitoring.service.dto.LoginRequestDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationResource {

    private AuthenticationService authenticationService;

    @Autowired
    public AuthenticationResource(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDTO> login(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
        JwtDTO jwtDTO = this.authenticationService.login(loginRequestDTO.getUsername(), loginRequestDTO.getPassword());
        return ResponseEntity.ok(jwtDTO);
    }
}
