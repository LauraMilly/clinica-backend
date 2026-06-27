package com.mv.clinica_backend.repository;

import com.mv.clinica_backend.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long>{

    Paciente findByCpf(String cpf);

    Paciente findByEmail(String email);
}
