package com.kosign.todolist.service.Authencition;

import com.kosign.todolist.domain.User;
import com.kosign.todolist.payload.authentication.AuthenticationRequest;
import com.kosign.todolist.payload.authentication.AuthenticationResponse;
import com.kosign.todolist.payload.authentication.RegisterRequest;

public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request);
    AuthenticationResponse authenticate(AuthenticationRequest request);
    User findUserByEmail(String email);
}
