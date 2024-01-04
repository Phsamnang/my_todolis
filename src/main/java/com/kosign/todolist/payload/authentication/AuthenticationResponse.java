package com.kosign.todolist.payload.authentication;


import lombok.*;

@Builder
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {
    private String token;
}
