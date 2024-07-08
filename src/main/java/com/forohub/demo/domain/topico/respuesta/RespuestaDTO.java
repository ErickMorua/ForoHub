package com.forohub.demo.domain.topico.respuesta;

public record RespuestaDTO(Respuesta respuesta) {
     this(respuesta.getId(),
             respuesta.getMensaje(),
             respuesta.getTopico().getId(),
                respuesta.getAutorRespuesta().getId(),
                respuesta.getSolucion());
}
