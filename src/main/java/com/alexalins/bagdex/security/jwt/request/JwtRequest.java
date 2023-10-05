package com.alexalins.bagdex.security.jwt.request;

import lombok.Data;

@Data
public class JwtRequest {
    private String email;
    private String senha;
}
