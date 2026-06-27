package com.mv.clinica_backend.controller;

import com.mv.clinica_backend.entity.Profissional;
import com.mv.clinica_backend.service.ProfissionalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/profissionais")
@Tag(name = "Profissionais", description = "Gerenciamento de profissionais de saúde")
public class ProfissionalController {

    @Autowired
    private ProfissionalService profissionalService;

    @PostMapping
    @Operation(summary = "Cadastrar profissional", description = "Cadastra um novo profissional no sistema")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Profissional cadastrado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "CRM já cadastrado!")
    })
    public ResponseEntity<Profissional> cadastrar(@RequestBody Profissional profissional) {
        Profissional salvo = profissionalService.cadastrar(profissional);
        return ResponseEntity.status(201).body(salvo);
    }

    @GetMapping
    @Operation(summary = "Listar profissionais", description = "Retorna todos os profissionais cadastrados")
    @ApiResponse(responseCode = "200", description = "Lista de profissionais retornada com sucesso!")
    public ResponseEntity<List<Profissional>> listarTodos() {
        List<Profissional> profissionais = profissionalService.listarTodos();
        return ResponseEntity.ok(profissionais);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar profissional por ID", description = "Retorna um profissional pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Profissional encontrado!"),
            @ApiResponse(responseCode = "400", description = "Profissional não encontrado!")
    })
    public ResponseEntity<Profissional> buscarPorId(@PathVariable Long id) {
        Profissional profissional = profissionalService.buscarPorId(id);
        return ResponseEntity.ok(profissional);
    }
}
