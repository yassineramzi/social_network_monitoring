package com.socialnetworkmonitoring.service;

import com.socialnetworkmonitoring.service.dto.JwtDTO;

public interface AuthenticationService {
    JwtDTO login(String login, String password);
}
