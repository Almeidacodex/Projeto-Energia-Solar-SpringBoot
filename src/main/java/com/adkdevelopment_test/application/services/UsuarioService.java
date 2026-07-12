package com.adkdevelopment_test.application.services;


import com.adkdevelopment_test.application.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email ) throws UsernameNotFoundException{
        return usuarioRepository.findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException(
                        "Usuário não encontrado com email: " + email));
    }
}
