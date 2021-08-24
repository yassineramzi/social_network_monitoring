package com.socialnetworkmonitoring.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtDTO implements Serializable {
    private String token;
    private String type;
    private Long id;
    private String username;
    private List<String> roles;
}
