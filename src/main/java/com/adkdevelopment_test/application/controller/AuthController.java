package com.adkdevelopment_test.application.controller;

import com.adkdevelopment_test.application.dto.AuthResponse;
import com.adkdevelopment_test.application.dto.LoginRequest;
import com.adkdevelopment_test.application.dto.RegistroRequest;
import com.adkdevelopment_test.application.model.Usuario;
import com.adkdevelopment_test.application.services.JwtService;
import com.adkdevelopment_test.application.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UsuarioService usuarioService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthController(UsuarioService usuarioService,
                          JwtService jwtService,
                          AuthenticationManager authenticationManager) {
        this.usuarioService = usuarioService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/registro")
    public ResponseEntity<AuthResponse> registrar(@Valid @RequestBody RegistroRequest request) {
        Usuario usuario = usuarioService.registrar(
                request.nome(), request.email(), request.senha());

        String token = jwtService.gerarToken(usuario);
        return ResponseEntity.ok(new AuthResponse(token, usuario.getEmail()));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.senha()));

        UserDetails userDetails = usuarioService.loadUserByUsername(request.email());
        String token = jwtService.gerarToken(userDetails);

        return ResponseEntity.ok(new AuthResponse(token, request.email()));
    }
}