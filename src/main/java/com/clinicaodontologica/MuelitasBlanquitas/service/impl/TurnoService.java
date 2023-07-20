package com.clinicaodontologica.MuelitasBlanquitas.service.impl;

import com.clinicaodontologica.MuelitasBlanquitas.dto.TurnoDto;
import com.clinicaodontologica.MuelitasBlanquitas.entity.Turno;
import com.clinicaodontologica.MuelitasBlanquitas.exception.BadRequestException;
import com.clinicaodontologica.MuelitasBlanquitas.exception.ResourceNotFoundException;
import com.clinicaodontologica.MuelitasBlanquitas.repository.TurnoRepository;
import com.clinicaodontologica.MuelitasBlanquitas.service.ITurnoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TurnoService implements ITurnoService {
    private final TurnoRepository turnoRepository;

    @Autowired
    public TurnoService(TurnoRepository turnoRepository) {
        this.turnoRepository = turnoRepository;
    }

    @Override
    public TurnoDto registrarTurno(Turno turno) throws BadRequestException {
        TurnoDto turnoGuardado;
        if (turno.getPaciente() == null || turno.getOdontologo() == null) {
            if (turno.getPaciente() == null && turno.getOdontologo() == null) {
                LOGGER.error("🚨 El paciente y el odontólogo son nulos.");
                throw new BadRequestException("El paciente y el odontólogo son nulos.");
            } else if (turno.getPaciente() == null) {
                LOGGER.error("🚨 El paciente es nulo.");
                throw new BadRequestException("El paciente es nulo.");
            } else {
                LOGGER.error("🚨 El odontólogo es nulo.");
                throw new BadRequestException("El odontólogo es nulo.");
            }
        } else {
            turnoGuardado = new TurnoDto(turnoRepository.save(turno));
            LOGGER.info("🙌 Se guardó exitosamente tu turno: {}", turnoGuardado);
        }
        return turnoGuardado;
    }

    @Override
    public TurnoDto buscarTurnoPorId(Long id) throws ResourceNotFoundException {
        Turno turno = turnoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No encontrado"));
        TurnoDto turnoConvertido = new TurnoDto(turno);
        LOGGER.info("🔍 Turno encontrado: {}", turnoConvertido);
        return turnoConvertido;
    }

    @Override
    public List<TurnoDto> listarTodosLosTurnos() {
        List<TurnoDto> turnos = turnoRepository.findAll()
                .stream()
                .map(TurnoDto::new)
                .toList();
        LOGGER.info("🎫 Listando todos los turnos: {}", turnos);
        return turnos;
    }

    @Override
    public TurnoDto actualizarTurno(Turno turno) throws ResourceNotFoundException, BadRequestException {
        TurnoDto turnoActualizado;
        if (!turnoRepository.existsById(turno.getId())) {
            LOGGER.warn("🛑 Se intentó actualizar al turno con ID {}, pero no existe en la base de datos", turno.getId());
            throw new ResourceNotFoundException("🛑 El turno que intentaste actualizar no existe en la base de datos");
        } else {
            turnoActualizado = registrarTurno(turno);
            LOGGER.warn("🛑 Se ha actualizado el paciente con ID {}: {}", turno.getId(), turnoActualizado);
        }
        return turnoActualizado;
    }

    @Override
    public void eliminarTurno(Long id) throws ResourceNotFoundException {
        if (turnoRepository.existsById(id)) {
            turnoRepository.deleteById(id);
            LOGGER.warn("🚨 Se ha eliminado el turno con ID {}", id);
        } else {
            LOGGER.warn("🛑 Se intentó eliminar al turno con ID {}, pero no existe en la base de datos", id);
            throw new ResourceNotFoundException("🛑 El turno que intentaste eliminar no existe en la base de datos");
        }
    }
}
