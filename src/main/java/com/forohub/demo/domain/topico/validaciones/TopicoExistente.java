package com.forohub.demo.domain.topico.validaciones;

import com.forohub.demo.domain.topico.CrearTopicoDTO;
import com.forohub.demo.domain.topico.TopicoRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;

public class TopicoExistente implements ValidarTopico {
    @Autowired
    TopicoRepository repository;

    @Override
    public void validar(CrearTopicoDTO datos) {
        var tituloExistente = repository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje());

        if (tituloExistente != null && tituloExistente) {
            throw new ValidationException("Existe un topico con ese titulo");
        }
    }
}
