package com.turkcell.spring.business.abstracts;


import com.turkcell.spring.entities.dtos.auth.AuthenticationResponse;
import com.turkcell.spring.entities.dtos.auth.LoginRequest;
import com.turkcell.spring.entities.dtos.auth.RegisterRequest;
import com.turkcell.spring.entities.dtos.auth.UpdateUserRolesRequest;

public interface AuthService {
    AuthenticationResponse register(RegisterRequest request);
    AuthenticationResponse login(LoginRequest request);
    void updateRolesForUser(Integer userId, UpdateUserRolesRequest updateUserRolesRequest);
}
