package com.forohub.demo.domain.curso.validaciones;

import com.forohub.demo.domain.curso.CrearCursoDTO;
import com.forohub.demo.domain.curso.CursoRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;

public class CursoExistenteValidarCurso implements ValidarCurso {

    @Autowired
    CursoRepository repository;

    @Override
    public void validar(CrearCursoDTO datos) {
        if (repository.existsByNombre(datos.nombre())) {
            throw new ValidationException("Existe un curso con ese nombre");
        }
        if (datos.nombre() == null) {
            throw new ValidationException("el nombre del curso no debe estar vacio");
        }
    }
}
