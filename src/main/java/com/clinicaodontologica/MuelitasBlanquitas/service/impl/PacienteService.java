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
        PacienteDto pacienteDto = new PacienteDto(pacienteRepository.save(paciente));
        LOGGER.info("👩 Se registró correctamente al paciente: {}", pacienteDto);
        return pacienteDto;
    }

    @Override
    public PacienteDto buscarPacientePorId(Long id) throws ResourceNotFoundException {
        Paciente pacienteEncontrado = pacienteRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("🛑 El paciente que buscas no existe en la base de datos."));
        PacienteDto pacienteDto = new PacienteDto(pacienteEncontrado);
        LOGGER.info("🔍 Se ha encontrado al paciente con ID {}: {}", id, pacienteDto);
        return pacienteDto;
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
            LOGGER.error("🛑 Se intentó actualizar al paciente con ID {}, pero este no existe en la base de datos.",
                    paciente.getId());
            throw new ResourceNotFoundException("🛑 El paciente que intentaste actualizar no existe en la " +
                    "base de datos.");
        } else {
            pacienteActualizado = registrarPaciente(paciente);
            LOGGER.warn("🛑 Se ha actualizado el paciente con ID {}: {}", pacienteActualizado.getId(),
                    pacienteActualizado);
        }
        return pacienteActualizado;
    }

    @Override
    public void eliminarPaciente(Long id) throws ResourceNotFoundException {
        if (pacienteRepository.existsById(id)) {
            pacienteRepository.deleteById(id);
            LOGGER.warn("🚨 Se ha eliminado el paciente con ID {}", id);
        } else {
            LOGGER.error("🛑 Se intentó eliminar el paciente con ID {}, pero este no existe en la base de datos.", id);
            throw new ResourceNotFoundException("🛑 El paciente que intentaste eliminar no existe en la base de datos.");
        }
    }
}
