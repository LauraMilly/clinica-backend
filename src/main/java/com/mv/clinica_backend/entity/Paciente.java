package com.mv.clinica_backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name="pacientes")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 150)
    private String nome;

    @Column(unique = true, nullable = false, length = 11)
    private String cpf;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(unique = true)
    private String telefone;
}
