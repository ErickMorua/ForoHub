package com.forohub.demo.domain.topico.respuesta.validaciones;

import com.forohub.demo.domain.topico.TopicoRepository;
import com.forohub.demo.domain.topico.respuesta.CrearRespuestaDTO;
import com.forohub.demo.domain.topico.respuesta.RespuestaRepository;
import com.forohub.demo.domain.usuario.UsuarioRpository;
import com.forohub.demo.infra.errores.ValidacionDeIntegridad;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;

public class RespuestaValida implements ValidarRespuesta {

    @Autowired
    RespuestaRepository respuestaRepository;

    @Autowired
    UsuarioRpository usuarioRepository;

    @Autowired
    TopicoRepository topicoRepository;

    @Override
    public void validar(CrearRespuestaDTO datos) {
        if (datos.mensaje() == null) {
            throw new ValidacionDeIntegridad("Mensaje no encontrado");
        }

        if (respuestaRepository.existsByTopicoAndMensajeAndAutorRespuesta(
                topicoRepository.getReferenceById(datos.idTopico()),
                datos.mensaje(),
                usuarioRepository.getReferenceById(datos.idAutor()))) {
            throw new ValidationException("Ya existe una respuesta en el topico");
        }
    }
}
