package com.kosign.todolist.payload.authentication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Builder
@Data
@AllArgsConstructor
public class RegisterRequest {
    private String name;
    private String email;
    private String password;
}
