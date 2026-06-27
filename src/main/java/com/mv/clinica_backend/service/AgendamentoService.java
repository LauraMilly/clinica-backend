package com.mv.clinica_backend.service;

import com.mv.clinica_backend.entity.Agendamento;
import com.mv.clinica_backend.entity.Paciente;
import com.mv.clinica_backend.entity.Profissional;
import com.mv.clinica_backend.entity.StatusAgendamento;
import com.mv.clinica_backend.repository.AgendamentoRepository;
import com.mv.clinica_backend.repository.PacienteRepository;
import com.mv.clinica_backend.repository.ProfissionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private ProfissionalRepository profissionalRepository;

    public Agendamento criar(Long pacienteId, Long profissionalId, LocalDateTime dataHora, String tipoAtendimento){

        if (dataHora.isBefore(LocalDateTime.now())){
            throw new RuntimeException("Não é permitido agendar em data/hora passada");
        }

        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        Profissional profissional = profissionalRepository.findById(profissionalId)
                .orElseThrow(() -> new RuntimeException("Profissional não encontrado"));

        boolean jaTemAgendamento = agendamentoRepository
                .existsByProfissionalAndDataHora(profissional, dataHora);

        if (jaTemAgendamento){
            throw new RuntimeException("Profissional já possui agendamento neste horário");
        }

        Agendamento agendamento = new Agendamento(paciente, profissional, dataHora, tipoAtendimento);

        return agendamentoRepository.save(agendamento);
    }

    public List<Agendamento> listar(Long pacienteId, Long profissionalId, StatusAgendamento status){
        if (pacienteId != null){
            Paciente paciente = pacienteRepository.findById(pacienteId)
                    .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));
            return agendamentoRepository.findByPaciente(paciente);
        }

        if (profissionalId != null){
            Profissional profissional = profissionalRepository.findById(profissionalId)
                    .orElseThrow(() -> new RuntimeException("Profissional não encontrado"));
            return agendamentoRepository.findByProfissional(profissional);
        }

        if (status != null){
            return agendamentoRepository.findByStatus(status);
        }
        return agendamentoRepository.findAll();
    }

    public Agendamento cancelar(Long id, String motivo) {
        if (motivo == null || motivo.trim().isEmpty()){
            throw new RuntimeException("Motivo de cancelamento é obrigatório!");
        }
        Agendamento agendamento = agendamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agendamento não encontrado!"));

        if (agendamento.getStatus() == StatusAgendamento.CANCELADO){
            throw new RuntimeException("Agendamento já está cancelado!");
        }

        agendamento.setStatus(StatusAgendamento.CANCELADO);
        agendamento.setMotivoCancelamento(motivo);
        agendamento.setDataCancelamento(LocalDateTime.now());

        return agendamentoRepository.save(agendamento);
    }
}
