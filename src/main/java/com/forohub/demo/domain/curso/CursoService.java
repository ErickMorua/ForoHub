package com.forohub.demo.domain.curso;

import com.forohub.demo.domain.topico.TopicoDTO;
import com.forohub.demo.domain.topico.TopicoRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private List<ValidadorCurso> validadorCursos;


    public CursoDTO crearCurso(CrearCursoDTO datos) {
        validadorCursos.forEach(v -> v.validar(datos));
        var curso = new Curso(datos.nombre(), datos.categoria());
        cursoRepository.save(curso);
        return new CursoDTO(curso);
    }

    public CursoDTO actualizarCurso(ActualizarCursoDTO datos) {
        cursoExistente(datos.id());
        var curso = cursoRepository.getReferenceById(datos.id());
        curso.actualizar(datos);
        return new CursoDTO(curso);
    }

    public CursoDTO cambiarEstado( Long id) {
        cursoExistente(id);
        var curso = cursoRepository.getReferenceById(id);
        curso.setActivo();
        return new CursoDTO(curso);
    }

    private void cursoExistente(Long id) {
        if (id == null) {
            throw new ValidationException("Debe ingresar el ID del curso");
        }

        if (!cursoRepository.existsById(id)) {
            throw new ValidationException("Debe ingresar el ID de curso VALIDO");
        }
    }

    public Page<CursoDTO> listarCursosActivos(Pageable paginacion) {
        return cursoRepository.findByActivoTrue(paginacion).map(CursoDTO::new);
    }

    public Page<CursoDTO> listarCursosInactivos(Pageable paginacion) {
        return cursoRepository.findByActivoFalse(paginacion).map(CursoDTO::new);
    }

    public Page<CursoDTO> listarCursos(Pageable paginacion) {
        return cursoRepository.findAll(paginacion).map(CursoDTO::new);
    }

    public CursoTopicosDTO mostrarCurso(Long id, Pageable paginacion) {
        cursoExistente(id);
        var curso = cursoRepository.getReferenceById(id);
        var topicos = topicoRepository.findAllByCurso(curso, paginacion).map(TopicoDTO::new);
        return new CursoTopicosDTO(new CursoDTO(curso), topicos);
    }


}
