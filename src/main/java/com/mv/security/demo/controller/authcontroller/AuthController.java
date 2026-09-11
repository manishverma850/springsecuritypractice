package com.mv.security.demo.controller.authcontroller;

import com.mv.security.demo.dtos.RegisterRequestDto;
import com.mv.security.demo.dtos.RegisterResponseDto;
import com.mv.security.demo.dtos.logindto.LoginRequestDTO;
import com.mv.security.demo.dtos.logindto.LoginResponseDTO;
import com.mv.security.demo.service.authservice.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.server.csrf.CsrfToken;
import org.springframework.security.web.server.csrf.DefaultCsrfToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDto>
    registerUser(@RequestBody RegisterRequestDto requestDto) {

        RegisterResponseDto responseDto = authService.registerUser(requestDto);

        return ResponseEntity.ok(responseDto);

    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login
            (@RequestBody LoginRequestDTO loginRequestDTO){
        return ResponseEntity.ok((authService.login(loginRequestDTO)));

    }

}
