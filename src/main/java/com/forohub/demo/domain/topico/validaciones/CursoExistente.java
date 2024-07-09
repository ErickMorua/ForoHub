package com.forohub.demo.domain.topico.validaciones;

import com.forohub.demo.domain.curso.CursoRepository;
import com.forohub.demo.domain.topico.CrearTopicoDTO;
import com.forohub.demo.infra.errores.ValidacionDeIntegridad;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;

public class CursoExistente implements ValidarTopico {
    @Autowired
    private CursoRepository repository;

    @Override
    public void validar(CrearTopicoDTO datos) {
        if (datos.idCurso() == null) {
            throw new ValidacionDeIntegridad("El topico debe tener un curso asignado");
        }
        var curso = repository.findById(datos.idCurso());

        if (!curso.isPresent()) {
            throw new ValidationException("El curso al que desea agregar el topico no existe");
        }
    }
}
