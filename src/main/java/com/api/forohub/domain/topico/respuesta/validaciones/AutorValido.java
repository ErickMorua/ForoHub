package com.api.forohub.domain.topico.respuesta.validaciones;

import com.api.forohub.domain.topico.respuesta.CrearRespuestaDTO;
import com.api.forohub.domain.usuario.UsuarioRepository;
import com.api.forohub.infra.errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AutorValido implements ValidadorRespuesta {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public void validar(CrearRespuestaDTO datos) {
        if (datos.idAutor() == null || !usuarioRepository.existsById(datos.idAutor())) {
            throw new ValidacionDeIntegridad("Autor no encontrado");
        }
    }
}
