package com.adkdevelopment_test.application.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.CustomAutowireConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import static org.junit.jupiter.api.Assertions.*;


public class JwtServiceTest {

    private JwtService jwtService;
    private UserDetails userDetails;

    @BeforeEach
    public void setUp(){
        jwtService = new JwtService();
        userDetails = new User("gabriel@gmail.com", "senha123",java.util.List.of());
    }

    @Test
    public  void deveGerarTokenValido(){
        String token = jwtService.gerarToken(userDetails);

        assertNotNull(token);
        assertFalse(token.isBlank());
    }

    @Test
    public void deveGerarEmailCorretoDoToken(){
        String token = jwtService.gerarToken(userDetails);

        String emailExtraido = jwtService.extrairEmail(token);

        assertEquals("gabriel@gmail.com",emailExtraido);
    }

    @Test
    public void deveConsiderarTokenValidoParaMesmoUsuario(){
        String token = jwtService.gerarToken(userDetails);

        boolean valido = jwtService.isTokenValido(token, userDetails);

        assertTrue(valido);
    }

    @Test
    public void deveConsiderarTokenInvalidoParaUsuarioDiferente(){
        String token = jwtService.gerarToken(userDetails);
        UserDetails outroUsuario = new User("outro@gmail.com","senha456",java.util.List.of());

        boolean valido = jwtService.isTokenValido(token, outroUsuario);

        assertFalse(valido);
    }

    @Test
    public void deveConsiderarTokenAdulteradoComoInvalido(){
        String complemento = "XXXXX";
        String token = jwtService.gerarToken(userDetails);
        String tokenAdulterado = token.substring(0, token.length() - 5) + complemento;

        boolean valido = jwtService.isTokenValido(tokenAdulterado, userDetails);

        assertFalse(valido);
    }
}
