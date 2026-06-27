package com.mv.clinica_backend.service;

import com.mv.clinica_backend.entity.Paciente;
import com.mv.clinica_backend.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    public Paciente cadastrar(Paciente paciente) {

        Paciente pacienteExistente = pacienteRepository.findByCpf(paciente.getCpf());
        if (pacienteExistente != null) {
            throw new RuntimeException("CPF já cadastrado!");
        }

        return pacienteRepository.save(paciente);
    }

    public List<Paciente> listarTodos() {
        return pacienteRepository.findAll();
    }

    public Paciente buscarPorId(Long id) {
        return pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado!"));
    }
}