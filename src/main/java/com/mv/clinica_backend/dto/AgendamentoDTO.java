package com.mv.clinica_backend.dto;

import com.mv.clinica_backend.entity.Agendamento;
import java.time.LocalDateTime;

public class AgendamentoDTO {

    private Long id;
    private String pacienteNome;
    private String profissionalNome;
    private String profissionalEspecialidade;
    private LocalDateTime dataHora;
    private String tipoAtendimento;
    private String status;
    private String motivoCancelamento;
    private LocalDateTime dataCancelamento;

    public AgendamentoDTO(Agendamento agendamento) {
        this.id = agendamento.getId();
        this.pacienteNome = agendamento.getPaciente().getNome();
        this.profissionalNome = agendamento.getProfissional().getNome();
        this.profissionalEspecialidade = agendamento.getProfissional().getEspecialidade();
        this.dataHora = agendamento.getDataHora();
        this.tipoAtendimento = agendamento.getTipoAtendimento();
        this.status = agendamento.getStatus().name();
        this.motivoCancelamento = agendamento.getMotivoCancelamento();
        this.dataCancelamento = agendamento.getDataCancelamento();
    }

    public Long getId() { return id; }
    public String getPacienteNome() { return pacienteNome; }
    public String getProfissionalNome() { return profissionalNome; }
    public String getProfissionalEspecialidade() { return profissionalEspecialidade; }
    public LocalDateTime getDataHora() { return dataHora; }
    public String getTipoAtendimento() { return tipoAtendimento; }
    public String getStatus() { return status; }
    public String getMotivoCancelamento() { return motivoCancelamento; }
    public LocalDateTime getDataCancelamento() { return dataCancelamento; }
}
