package org.therapazes.luisaoproject.services;

import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.therapazes.luisaoproject.config.auth.JwtUtil;
import org.therapazes.luisaoproject.dto.AuthenticationRequestDto;
import org.therapazes.luisaoproject.dto.AuthenticationResponseDto;
import org.therapazes.luisaoproject.dto.RegisterRequestDto;
import org.therapazes.luisaoproject.entities.User;
import org.therapazes.luisaoproject.repositories.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponseDto register(RegisterRequestDto request) throws EntityExistsException {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new EntityExistsException("Email already registered");
        }

        var user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);

        var jwtToken = jwtUtil.generateToken(user);
        return AuthenticationResponseDto.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponseDto authenticate(AuthenticationRequestDto request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow(RuntimeException::new);
        var jwtToken = jwtUtil.generateToken(user);
        return AuthenticationResponseDto.builder()
                .token(jwtToken)
                .build();
    }
}