package com.api.forohub.domain.curso;

import com.api.forohub.domain.topico.TopicoDTO;
import org.springframework.data.domain.Page;

public record CursoTopicosDTO(CursoDTO curso, Page<TopicoDTO> topicos) {

}
