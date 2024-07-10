package com.forohub.demo.domain.topico.respuesta.validaciones;

import com.forohub.demo.domain.topico.TopicoRepository;
import com.forohub.demo.domain.topico.respuesta.CrearRespuestaDTO;
import com.forohub.demo.infra.errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;

public class TopicoValido implements ValidarRespuesta {

    @Autowired
    TopicoRepository topicoRepository;

    @Override
    public void validar(CrearRespuestaDTO datos) {
        if (datos.idTopico() == null || !topicoRepository.existsById(datos.idTopico())) {
            throw new ValidacionDeIntegridad("Topico no encontrado");
        }
    }
}
