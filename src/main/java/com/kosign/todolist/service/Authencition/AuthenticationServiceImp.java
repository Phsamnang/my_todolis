package com.kosign.todolist.service.Authencition;

import com.kosign.todolist.configuration.JwtService;
import com.kosign.todolist.domain.User;
import com.kosign.todolist.payload.authentication.AuthenticationRequest;
import com.kosign.todolist.payload.authentication.AuthenticationResponse;
import com.kosign.todolist.payload.authentication.RegisterRequest;
import com.kosign.todolist.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImp implements AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtServices;
    private final AuthenticationManager authenticationManager;
    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        var user= User.builder().name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword())).build();
        repository.save(user);
        var jwtToken= jwtServices.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                ));
                var user=repository.findByEmail(request.getEmail()).orElseThrow(
        );
        var jwtToken= jwtServices.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();

    }
}
