package com.forohub.demo.domain.topico.respuesta;

import com.forohub.demo.domain.topico.EstadoTopicoRespuesta;
import com.forohub.demo.domain.topico.TopicoDTO;
import com.forohub.demo.domain.topico.TopicoRepository;
import com.forohub.demo.domain.usuario.UsuarioRpository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RespuestaService {
    @Autowired
    private RespuestaRepository repository;
    @Autowired
    private UsuarioRpository usuarioRepository;
    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private List<ValidadorRespuesta> validarRespuesta;

    public RespuestaDTO crearRespuesta(CrearRespuestaDTO datos) {
        validarRespuesta.forEach(v -> v.validar(datos));

        var mensaje = datos.mensaje();
        var autor = usuarioRepository.getReferenceById(datos.idAutor());
        var topico = topicoRepository.getReferenceById(datos.idTopico());
        var respuesta = new Respuesta(mensaje, autor, topico);

        repository.save(respuesta);
        return new RespuestaDTO(respuesta);
    }

    public RespuestaDTO editarRespuesta(EditarRespuestaDTO datos) {
        if (datos.mensaje() == null) {
            throw new ValidationException("Respuesta no encontrada")
        }
        if (!repository.existsById(datos.idAutor())) {
            throw new ValidationException("ID de respuesta no válido");
        }
        var respuesta = repository.getReferenceById(datos.idAutor());
        respuesta.actualizarMensaje(datos.mensaje());
        return new RespuestaDTO(respuesta);
    }

    public EstadoTopicoRespuesta marcarDesmarcarComoSolucion(Long id) {
        idValido(id);
        var respuesta = repository.getReferenceById(id);
        var topico = topicoRepository.getReferenceById(respuesta.getTopico().getId());

        respuesta.setSolucion();

        var solucionado = repository.existsByTopicoAndSolucion(topico, true);
        System.out.println(solucionado);

        if ((solucionado && topico.getStatus() == false) || (!solucionado && topico.getStatus() == true)) {
            topico.setStatus();
        }
        var resultado = new EstadoTopicoRespuesta(new TopicoDTO(topico), new RespuestaDTO(respuesta));
        return resultado;

    }

    public void eliminarRespuesta(Long id) {
        idValido(id);
        var idTopicoRespuesta = repository.getReferenceById(id).getTopico().getId();
        var topico = topicoRepository.getReferenceById(idTopicoRespuesta);

        repository.deleteById(id);

        var topicoSolucionado = repository.existsByTopicoAndSolucion(topico, true);
        var estadoTopico = topico.getStatus();

        if (estadoTopico && !topicoSolucionado) {
            topico.setStatus();
        }
    }

    public void idValido(Long id) {
        if (id == null) {
            throw new ValidationException("Debe ingresar el ID de la respuesta");
        }
        if (!repository.existsById(id)) {
            throw new ValidationException("No existe respuesta con el id: " + id);
        }
    }
}
