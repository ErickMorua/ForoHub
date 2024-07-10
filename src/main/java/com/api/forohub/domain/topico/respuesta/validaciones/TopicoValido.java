package com.api.forohub.domain.topico.respuesta.validaciones;

import com.api.forohub.domain.topico.TopicoRepository;
import com.api.forohub.domain.topico.respuesta.CrearRespuestaDTO;
import com.api.forohub.infra.errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicoValido implements ValidadorRespuesta {

    @Autowired
    TopicoRepository topicoRepository;

    @Override
    public void validar(CrearRespuestaDTO datos) {
        if (datos.idTopico() == null || !topicoRepository.existsById(datos.idTopico())) {
            throw new ValidacionDeIntegridad("Topico no encontrado");
        }
    }
}
