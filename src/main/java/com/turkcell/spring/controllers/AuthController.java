package com.turkcell.spring.controllers;

import com.turkcell.spring.business.abstracts.AuthService;
import com.turkcell.spring.entities.dtos.auth.AuthenticationResponse;
import com.turkcell.spring.entities.dtos.auth.LoginRequest;
import com.turkcell.spring.entities.dtos.auth.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("login")
    public AuthenticationResponse login(@RequestBody LoginRequest request){
        return authService.login(request);
    }

    //Kaydedilen kullanicilar direkt user olarak kaydolacak.
    @PostMapping("register")
    public AuthenticationResponse register(@RequestBody RegisterRequest request){
        return  authService.register(request);
    }
}
