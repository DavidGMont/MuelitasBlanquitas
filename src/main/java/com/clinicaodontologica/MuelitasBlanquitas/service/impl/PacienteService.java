package com.clinicaodontologica.MuelitasBlanquitas.service.impl;

import com.clinicaodontologica.MuelitasBlanquitas.dto.PacienteDto;
import com.clinicaodontologica.MuelitasBlanquitas.entity.Paciente;
import com.clinicaodontologica.MuelitasBlanquitas.exception.ResourceNotFoundException;
import com.clinicaodontologica.MuelitasBlanquitas.repository.PacienteRepository;
import com.clinicaodontologica.MuelitasBlanquitas.service.IPacienteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PacienteService implements IPacienteService {
    private final PacienteRepository pacienteRepository;

    @Autowired
    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public PacienteDto registrarPaciente(Paciente paciente) {
        Paciente pacienteParaGuardar = pacienteRepository.save(paciente);
        PacienteDto pacienteConvertido = new PacienteDto(pacienteParaGuardar);
        LOGGER.info("🙌 Se guardó exitosamente tu paciente: {}", pacienteConvertido);
        return pacienteConvertido;
    }

    @Override
    public PacienteDto buscarPacientePorId(Long id) throws ResourceNotFoundException {
        Paciente pacienteEncontrado = pacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("🛑 No encontrado"));
        PacienteDto pacienteConvertido = new PacienteDto(pacienteEncontrado);
        LOGGER.info("🔍 Paciente encontrado: {}", pacienteConvertido);
        return pacienteConvertido;
    }

    @Override
    public List<PacienteDto> listarTodosLosPacientes() {
        List<PacienteDto> pacientes = pacienteRepository.findAll()
                .stream()
                .map(PacienteDto::new)
                .toList();
        LOGGER.info("👨‍👩‍👧‍👦 Listando todos nuestros pacientes: {}", pacientes);
        return pacientes;
    }

    @Override
    public PacienteDto actualizarPaciente(Paciente paciente) throws ResourceNotFoundException {
        PacienteDto pacienteActualizado;
        if (!pacienteRepository.existsById(paciente.getId())) {
            LOGGER.warn("🛑 Se intentó actualizar al paciente con ID {}, pero no existe en la base de datos", paciente.getId());
            throw new ResourceNotFoundException("🛑 El paciente que intentaste actualizar no existe en la base de datos");
        } else {
            pacienteActualizado = registrarPaciente(paciente);
            LOGGER.warn("🛑 Se ha actualizado el paciente con ID {}: {}", pacienteActualizado.getId(), pacienteActualizado);
        }
        return pacienteActualizado;
    }

    @Override
    public void eliminarPaciente(Long id) throws ResourceNotFoundException {
        if (pacienteRepository.existsById(id)) {
            pacienteRepository.deleteById(id);
            LOGGER.warn("🚨 Se ha eliminado el paciente con ID {}", id);
        } else {
            LOGGER.warn("🛑 Se intentó eliminar al paciente con ID {}, pero no existe en la base de datos", id);
            throw new ResourceNotFoundException("🛑 El paciente que intentaste eliminar no existe en la base de datos");
        }
    }
}
