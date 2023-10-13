package com.turkcell.spring.business.abstracts;


import com.turkcell.spring.entities.dtos.auth.AuthenticationResponse;
import com.turkcell.spring.entities.dtos.auth.LoginRequest;
import com.turkcell.spring.entities.dtos.auth.RegisterRequest;

public interface AuthService {
    AuthenticationResponse register(RegisterRequest request);
    AuthenticationResponse login(LoginRequest request);
}
