package com.mv.clinica_backend.repository;

import com.mv.clinica_backend.entity.Agendamento;
import com.mv.clinica_backend.entity.Paciente;
import com.mv.clinica_backend.entity.Profissional;
import com.mv.clinica_backend.entity.StatusAgendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    List<Agendamento> findByPaciente(Paciente paciente);

    List<Agendamento> findByProfissional(Profissional profissional);

    List<Agendamento> findByStatus(StatusAgendamento status);

    boolean existsByProfissionalAndDataHora(Profissional profissional, LocalDateTime dataHora);
}
