package com.mv.clinica_backend.repository;

import com.mv.clinica_backend.entity.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProfissionalRepository extends JpaRepository <Profissional, Long>{

    Profissional findByCrm(String crm);

    List<Profissional> findByEspecialidade(String especialidade);
}
