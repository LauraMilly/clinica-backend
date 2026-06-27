package com.mv.clinica_backend.controller;

import com.mv.clinica_backend.dto.PacienteDTO;
import com.mv.clinica_backend.entity.Paciente;
import com.mv.clinica_backend.service.PacienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pacientes")
@Tag(name = "Pacientes", description = "Gerenciamento de pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @PostMapping
    @Operation(summary = "Cadastrar paciente", description = "Cadastra um novo paciente no sistema")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Paciente cadastrado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "CPF já cadastrado!")
    })
    public ResponseEntity<PacienteDTO> cadastrar(@RequestBody Paciente paciente) {
        Paciente salvo = pacienteService.cadastrar(paciente);
        return ResponseEntity.status(201).body(new PacienteDTO(salvo));
    }

    @GetMapping
    @Operation(summary = "Listar pacientes", description = "Retorna todos os pacientes cadastrados")
    @ApiResponse(responseCode = "200", description = "Lista de pacientes retornada com sucesso!")
    public ResponseEntity<List<PacienteDTO>> listarTodos() {
        List<PacienteDTO> pacientes = pacienteService.listarTodos()
                .stream()
                .map(PacienteDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(pacientes);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar paciente por ID", description = "Retorna um paciente pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Paciente encontrado!"),
            @ApiResponse(responseCode = "400", description = "Paciente não encontrado!")
    })
    public ResponseEntity<PacienteDTO> buscarPorId(@PathVariable Long id) {
        Paciente paciente = pacienteService.buscarPorId(id);
        return ResponseEntity.ok(new PacienteDTO(paciente));
    }
}
