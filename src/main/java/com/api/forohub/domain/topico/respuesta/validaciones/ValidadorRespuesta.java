package com.api.forohub.domain.topico.respuesta.validaciones;

import com.api.forohub.domain.topico.respuesta.CrearRespuestaDTO;

public interface ValidadorRespuesta {
    public void validar(CrearRespuestaDTO datos);
}
