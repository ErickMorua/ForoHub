package com.api.forohub.domain.topico;

import com.api.forohub.domain.topico.respuesta.DatosRespuesta;
import org.springframework.data.domain.Page;

public record DatosTopicoRespuestas(TopicoDTO topico, Page<DatosRespuesta> respuestas) {
}
