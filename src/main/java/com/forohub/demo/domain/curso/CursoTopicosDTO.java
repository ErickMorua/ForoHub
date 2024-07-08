package com.forohub.demo.domain.curso;

import com.forohub.demo.domain.topico.TopicoDTO;
import org.springframework.data.domain.Page;

public record CursoTopicosDTO(CursoDTO curso, Page<TopicoDTO> topicos) {
}
