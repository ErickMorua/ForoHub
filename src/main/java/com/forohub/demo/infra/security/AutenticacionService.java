package com.forohub.demo.infra.security;

import com.forohub.demo.domain.usuario.UsuarioRpository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class AutenticacionService implements UserDetailsService {

    @Autowired
    private UsuarioRpository usuarioRpository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRpository.findByEmail(username);
    }
}
