package com.mv.security.demo.service.authservice;

import com.mv.security.demo.dtos.RegisterRequestDto;
import com.mv.security.demo.dtos.RegisterResponseDto;
import com.mv.security.demo.entity.userentity.User;
import com.mv.security.demo.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository repository;

    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository,PasswordEncoder passwordEncoder) {
        this.repository = userRepository;
        this.passwordEncoder = passwordEncoder;
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
}
