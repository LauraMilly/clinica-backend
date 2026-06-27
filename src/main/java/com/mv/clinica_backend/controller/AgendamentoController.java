package com.mv.clinica_backend.controller;

import com.mv.clinica_backend.dto.AgendamentoDTO;
import java.util.stream.Collectors;
import com.mv.clinica_backend.entity.Agendamento;
import com.mv.clinica_backend.entity.StatusAgendamento;
import com.mv.clinica_backend.service.AgendamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/agendamentos")
@Tag(name = "Agendamentos", description = "Gerenciamento de agendamentos de consultas")
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService;

    @PostMapping
    @Operation(summary = "Criar agendamento", description = "Cria um novo agendamento de consulta")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Agendamento criado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Profissional já possui agendamento neste horário ou data no passado!")
    })
    public ResponseEntity<AgendamentoDTO> criar(@RequestBody Map<String, Object> body) {
        Long pacienteId = Long.valueOf(body.get("pacienteId").toString());
        Long profissionalId = Long.valueOf(body.get("profissionalId").toString());
        LocalDateTime dataHora = LocalDateTime.parse(body.get("dataHora").toString());
        String tipoAtendimento = body.get("tipoAtendimento").toString();

        Agendamento agendamento = agendamentoService.criar(pacienteId, profissionalId, dataHora, tipoAtendimento);
        return ResponseEntity.status(201).body(new AgendamentoDTO(agendamento));
    }

    @GetMapping
    @Operation(summary = "Listar agendamentos", description = "Retorna agendamentos com filtros opcionais")
    @ApiResponse(responseCode = "200", description = "Lista de agendamentos retornada com sucesso!")
    public ResponseEntity<List<AgendamentoDTO>> listar(
            @RequestParam(required = false) Long pacienteId,
            @RequestParam(required = false) Long profissionalId,
            @RequestParam(required = false) StatusAgendamento status) {

        List<AgendamentoDTO> agendamentos = agendamentoService.listar(pacienteId, profissionalId, status)
            .stream()
                .map(AgendamentoDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(agendamentos);
    }

    @DeleteMapping("/{id}/cancelar")
    @Operation(summary = "Cancelar agendamento", description = "Cancela um agendamento registrando o motivo")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Agendamento cancelado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Motivo obrigatório ou agendamento já cancelado!")
    })
    public ResponseEntity<AgendamentoDTO> cancelar(
            @PathVariable Long id,
            @RequestBody Map<String, String> body) {

        String motivo = body.get("motivo");
        Agendamento agendamento = agendamentoService.cancelar(id, motivo);
        return ResponseEntity.ok(new AgendamentoDTO(agendamento));
    }
}
