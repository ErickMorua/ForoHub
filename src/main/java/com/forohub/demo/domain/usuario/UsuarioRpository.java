package com.forohub.demo.domain.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRpository extends JpaRepository<Usuario, Long> {
    UserDetails findByEmail(String username);
}
