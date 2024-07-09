package com.forohub.demo.domain.curso.validaciones;

import com.forohub.demo.domain.curso.CrearCursoDTO;
import com.forohub.demo.infra.errores.ValidacionDeIntegridad;
import org.springframework.stereotype.Component;

@Component
public class CategoriaValida implements ValidarCurso {

    @Override
    public void validar(CrearCursoDTO datos) {
        if (datos.categoria() == null) {
            throw new ValidacionDeIntegridad("Debe asiganarle una categoria al curso");
        }
    }
}
