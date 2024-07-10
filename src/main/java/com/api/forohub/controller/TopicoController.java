package com.api.forohub.controller;

import com.api.forohub.domain.topico.ActualizarTopicoDTO;
import com.api.forohub.domain.topico.CrearTopicoDTO;
import com.api.forohub.domain.topico.TopicoDTO;
import com.api.forohub.domain.topico.TopicoService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    private TopicoService service;

    @PostMapping
    @Transactional
    public ResponseEntity crearTopico(@RequestBody @Valid CrearTopicoDTO datos) {
        var response = service.crearTopico(datos);
        return ResponseEntity.ok(response);
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarTopico(@RequestBody @Valid ActualizarTopicoDTO datos) {
        var response = service.actualizarTopico(datos);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id) {
        var response = service.eliminarTopico(id);
        return ResponseEntity.ok(response);
    }


    //TODO: implementar PadeModel
    @GetMapping
    public ResponseEntity<Page<TopicoDTO>> listarTopicos(@PageableDefault(size = 10)Pageable paginacion) {
        var response = service.listarTopicos(paginacion);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/resueltos")
    public ResponseEntity<Page<TopicoDTO>> listarTopicosResueltos(@PageableDefault(size = 10) Pageable paginacion) {
        var response = service.listarTopicosResueltos(paginacion);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/irresolutos")
    public ResponseEntity<Page<TopicoDTO>> listarTopicosIrresolutos(@PageableDefault(size = 10) Pageable paginacion) {
        var response = service.listarTopicosIrresolutos(paginacion);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity mostrarTopicoRespuestas(@PageableDefault(size = 10)Pageable paginacion,
                                        @PathVariable Long id) {
        var response = service.mostrarTopico(id, paginacion);
        return ResponseEntity.ok(response);
    }
}
