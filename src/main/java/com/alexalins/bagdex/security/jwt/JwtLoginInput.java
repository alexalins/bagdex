package com.alexalins.bagdex.security.jwt;

import lombok.Data;

@Data
class JwtLoginInput {
    private String email;
    private String senha;
}
