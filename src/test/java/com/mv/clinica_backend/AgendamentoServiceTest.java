package com.mv.clinica_backend;

import com.mv.clinica_backend.entity.Paciente;
import com.mv.clinica_backend.entity.Profissional;
import com.mv.clinica_backend.repository.AgendamentoRepository;
import com.mv.clinica_backend.repository.PacienteRepository;
import com.mv.clinica_backend.repository.ProfissionalRepository;
import com.mv.clinica_backend.service.AgendamentoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDateTime;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AgendamentoServiceTest {


    @Mock
    private AgendamentoRepository agendamentoRepository;

    @Mock
    private PacienteRepository pacienteRepository;

    @Mock
    private ProfissionalRepository profissionalRepository;

    @InjectMocks
    private AgendamentoService agendamentoService;

    @Test
    void deveLancarExcecaoQuandoAgendarNoPassado() {

        LocalDateTime dataPassada = LocalDateTime.now().minusHours(1);

        RuntimeException excecao = assertThrows(RuntimeException.class, () -> {
            agendamentoService.criar(1L, 1L, dataPassada, "Consulta");
        });

        assertEquals("Não é permitido agendar em data/hora passada!", excecao.getMessage());
    }

    @Test
    void deveLancarExcecaoQuandoProfissionalJaTemAgendamentoNoHorario() {

        LocalDateTime dataFutura = LocalDateTime.now().plusHours(2);

        Paciente paciente = new Paciente("João", "12345678900", "joao@email.com", "(11) 98765-4321");
        Profissional profissional = new Profissional("Dr. Carlos", "123456", "Cardiologia", "(11) 91234-5678");

        when(pacienteRepository.findById(1L)).thenReturn(Optional.of(paciente));
        when(profissionalRepository.findById(1L)).thenReturn(Optional.of(profissional));

        when(agendamentoRepository.existsByProfissionalAndDataHora(profissional, dataFutura))
                .thenReturn(true);

        RuntimeException excecao = assertThrows(RuntimeException.class, () -> {
            agendamentoService.criar(1L, 1L, dataFutura, "Consulta");
        });

        assertEquals("Profissional já possui agendamento neste horário!", excecao.getMessage());
    }

    @Test
    void deveLancarExcecaoQuandoCancelarSemMotivo() {

        RuntimeException excecao = assertThrows(RuntimeException.class, () -> {
            agendamentoService.cancelar(1L, "");
        });

        assertEquals("Motivo de cancelamento é obrigatório!", excecao.getMessage());
    }
}
