package com.mv.clinica_backend.service;

import com.mv.clinica_backend.entity.Profissional;
import com.mv.clinica_backend.repository.ProfissionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProfissionalService {

    @Autowired
    private ProfissionalRepository profissionalRepository;

    public Profissional cadastrar(Profissional profissional) {

        Profissional profissionalExistente = profissionalRepository.findByCrm(profissional.getCrm());
        if (profissionalExistente != null) {
            throw new RuntimeException("CRM já cadastrado!");
        }

        return profissionalRepository.save(profissional);
    }

    public List<Profissional> listarTodos() {
        return profissionalRepository.findAll();
    }

    public Profissional buscarPorId(Long id) {
        return profissionalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profissional não encontrado!"));
    }
}
