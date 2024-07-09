package com.forohub.demo.domain.topico.validaciones;

import com.forohub.demo.domain.topico.CrearTopicoDTO;
import com.forohub.demo.domain.usuario.UsuarioRpository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;

public class UsuarioExistente implements ValidarTopico {


    @Autowired
    private UsuarioRpository usuarioRpository;

    @Override
    public void validar(CrearTopicoDTO datos) {
        var usuario = usuarioRpository.existsById(datos.idAutor());

        if (!usuario) {
            new ValidationException("Autor no registrado");
        }
    }
}
