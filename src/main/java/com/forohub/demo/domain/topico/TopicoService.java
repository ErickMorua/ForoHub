package com.forohub.demo.domain.topico;

import com.forohub.demo.domain.curso.CursoRepository;
import com.forohub.demo.domain.topico.respuesta.RespuestaDTO;
import com.forohub.demo.domain.topico.respuesta.RespuestaRepository;
import com.forohub.demo.domain.usuario.UsuarioRpository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private UsuarioRpository usuarioRpository;
    @Autowired
    private RespuestaRepository respuestaRepository;
    @Autowired
    private List<ValidadorTopico> validadorTopico;

    public TopicoDTO crearTopico(CrearTopicoDTO datos) {



        var titulo = datos.titulo();
        var mensaje = datos.mensaje();
        var autor = usuarioRpository.getReferenceById(datos.idAutor());
        var curso = cursoRepository.getReferenceById(datos.idCurso());
        var topico = new Topico(titulo, mensaje, autor, curso);

        topicoRepository.save(topico);

        return new TopicoDTO(topico);
    }

    public TopicoDTO actualizarTopico(ActualizarTopicoDTO datos) {
        var topico = topicoRepository.getReferenceById(datos.id());

        topico.actualizarTopico(datos);

        return new TopicoDTO(topico);
    }

    public String eliminarTopico(Long id) {
        var topico = topicoRepository.findById(id);
        if (!topico.isPresent() || id == null) {
            throw new ValidationException("No existe topico con id = " + id);
        }
        respuestaRepository.removeAllByTopico(topicoRepository.getReferenceById(id));
        topicoRepository.removeById(id);

        return "Topico y respuestas eliminados correctamente";
    }

    public Page<TopicoDTO> listarTopicos(Pageable paginacion) {
        return topicoRepository.findAll(paginacion).map(TopicoDTO::new);
    }

    public TopicoRespuestaDTO mostrarTopico(Long id, Pageable paginacion) {
        if (id == null || !topicoRepository.existsById(id)) {
            throw new ValidationException("No existe un topico con ese ID");
        }
        var topico = topicoRepository.getReferenceById(id);
        var respuestas = respuestaRepository
                .findAllByTopico(topico, paginacion)
                .map(RespuestaDTO::new);

        return new TopicoRespuestaDTO(new TopicoDTO(topico), respuestas);
    }

    public Page<TopicoDTO> listarTopicosResueltos(Pageable paginacion) {
        var topicosSolucionados = topicoRepository.findAllByStatusTrue(paginacion);

        return topicosSolucionados.map(TopicoDTO::new);
    }

    public Page<TopicoDTO> listarTopicosIrresueltos(Pageable paginacion) {
        var topicosSolucionados = topicoRepository.findAllByStatusFalse(paginacion);
        return topicosSolucionados.map(TopicoDTO::new);
    }
}
