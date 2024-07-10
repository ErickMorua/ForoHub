package com.forohub.demo.domain.topico.respuesta.validaciones;

import com.forohub.demo.domain.topico.respuesta.CrearRespuestaDTO;
import com.forohub.demo.domain.usuario.UsuarioRpository;
import com.forohub.demo.infra.errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;

public class AutorValido implements ValidarRespuesta {
    @Autowired
    UsuarioRpository usuarioRepository;

    @Override
    public void validar(CrearRespuestaDTO datos) {
        if (datos.idAutor() == null || !usuarioRepository.existsById(datos.idAutor())) {
            throw new ValidacionDeIntegridad("Autor no encontrado");
        }
    }
}
