package com.mv.security.demo.service.authservice;

import com.mv.security.demo.dtos.RegisterRequestDto;
import com.mv.security.demo.dtos.RegisterResponseDto;
import com.mv.security.demo.dtos.logindto.LoginRequestDTO;
import com.mv.security.demo.dtos.logindto.LoginResponseDTO;
import com.mv.security.demo.entity.userentity.User;
import com.mv.security.demo.repository.UserRepository;
import com.mv.security.demo.userdetail.CustomUserDetail;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository repository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthService
            (UserRepository userRepository,
             PasswordEncoder passwordEncoder,
             AuthenticationManager authenticationManager) {
        this.repository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }
    public RegisterResponseDto registerUser(RegisterRequestDto requestDto) {

        Optional<User> existingUser =
                repository.findByUsername(requestDto.getUsername());

        if (existingUser.isPresent()) {
            throw new RuntimeException("User already exists");
        }


        User user1 = new User();

        user1.setUsername(requestDto.getUsername());
        user1.setPassword(passwordEncoder.encode(requestDto.getPassword()));

        User user2 = repository.save(user1);

        return new RegisterResponseDto
                (user2.getId(),user2.getUsername());
    }

    public LoginResponseDTO login(LoginRequestDTO loginRequest) {
        Authentication authentication = authenticationManager.authenticate
                (new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));

        CustomUserDetail user =(CustomUserDetail) authentication.getPrincipal();

        return new LoginResponseDTO(user.getUsername());
    }
}
