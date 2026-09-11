package com.mv.security.demo.dtos.logindto;

import lombok.Data;

@Data
public class LoginRequestDTO {

    private String username;

    private String password;
}
