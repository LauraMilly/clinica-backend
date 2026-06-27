package com.mv.clinica_backend.controller;

import com.mv.clinica_backend.entity.Profissional;
import com.mv.clinica_backend.service.ProfissionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/profissionais")
public class ProfissionalController {

    @Autowired
    private ProfissionalService profissionalService;

    @PostMapping
    public ResponseEntity<Profissional> cadastrar(@RequestBody Profissional profissional) {
        Profissional salvo = profissionalService.cadastrar(profissional);
        return ResponseEntity.status(201).body(salvo);
    }

    @GetMapping
    public ResponseEntity<List<Profissional>> listarTodos() {
        List<Profissional> profissionais = profissionalService.listarTodos();
        return ResponseEntity.ok(profissionais);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profissional> buscarPorId(@PathVariable Long id) {
        Profissional profissional = profissionalService.buscarPorId(id);
        return ResponseEntity.ok(profissional);
    }
}
