package com.api.forohub.domain.curso.validaciones;

import com.api.forohub.domain.curso.CrearCursoDTO;
import com.api.forohub.infra.errores.ValidacionDeIntegridad;
import org.springframework.stereotype.Component;

@Component
public class CategoriaValida implements ValidadorCurso{

    @Override
    public void validar(CrearCursoDTO datos) {
        if (datos.categoria() == null) {
            throw new ValidacionDeIntegridad("Debe asignarle una categoria al curso");
        }
        //Validar Enum

    }
}
