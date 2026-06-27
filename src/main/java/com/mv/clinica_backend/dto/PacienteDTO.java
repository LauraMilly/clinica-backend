package com.mv.clinica_backend.dto;

import com.mv.clinica_backend.entity.Paciente;

public class PacienteDTO {

    private Long id;
    private String nome;
    private String email;
    private String telefone;

    public PacienteDTO(Paciente paciente) {
        this.id = paciente.getId();
        this.nome = paciente.getNome();
        this.email = paciente.getEmail();
        this.telefone = paciente.getTelefone();
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getTelefone() { return telefone; }
}
