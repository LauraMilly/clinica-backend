package com.mv.clinica_backend.controller;

import com.mv.clinica_backend.dto.PacienteDTO;
import java.util.stream.Collectors;
import com.mv.clinica_backend.entity.Paciente;
import com.mv.clinica_backend.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @PostMapping
    public ResponseEntity<PacienteDTO> cadastrar(@RequestBody Paciente paciente) {
        Paciente salvo = pacienteService.cadastrar(paciente);
        return ResponseEntity.status(201).body(new PacienteDTO(salvo));
    }

    @GetMapping
    public ResponseEntity<List<PacienteDTO>> listarTodos() {
        List<PacienteDTO> pacientes = pacienteService.listarTodos()
                .stream()
                .map(PacienteDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(pacientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDTO> buscarPorId(@PathVariable Long id) {
        Paciente paciente = pacienteService.buscarPorId(id);
        return ResponseEntity.ok(new PacienteDTO(paciente));
    }
}
