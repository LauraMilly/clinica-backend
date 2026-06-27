package com.mv.clinica_backend.controller;

import com.mv.clinica_backend.entity.Agendamento;
import com.mv.clinica_backend.entity.StatusAgendamento;
import com.mv.clinica_backend.service.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService;

    @PostMapping
    public ResponseEntity<Agendamento> criar(@RequestBody Map<String, Object> body) {
        Long pacienteId = Long.valueOf(body.get("pacienteId").toString());
        Long profissionalId = Long.valueOf(body.get("profissionalId").toString());
        LocalDateTime dataHora = LocalDateTime.parse(body.get("dataHora").toString());
        String tipoAtendimento = body.get("tipoAtendimento").toString();

        Agendamento agendamento = agendamentoService.criar(pacienteId, profissionalId, dataHora, tipoAtendimento);
        return ResponseEntity.status(201).body(agendamento);
    }

    @GetMapping
    public ResponseEntity<List<Agendamento>> listar(
            @RequestParam(required = false) Long pacienteId,
            @RequestParam(required = false) Long profissionalId,
            @RequestParam(required = false) StatusAgendamento status) {

        List<Agendamento> agendamentos = agendamentoService.listar(pacienteId, profissionalId, status);
        return ResponseEntity.ok(agendamentos);
    }

    @DeleteMapping("/{id}/cancelar")
    public ResponseEntity<Agendamento> cancelar(
            @PathVariable Long id,
            @RequestBody Map<String, String> body) {

        String motivo = body.get("motivo");
        Agendamento agendamento = agendamentoService.cancelar(id, motivo);
        return ResponseEntity.ok(agendamento);
    }
}
