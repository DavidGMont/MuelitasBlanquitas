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
        TurnoDto turnoDto;
        if (turno.getPaciente() == null || turno.getOdontologo() == null) {
            if (turno.getPaciente() == null && turno.getOdontologo() == null) {
                LOGGER.error("🛑 El paciente solicitado y el odontólogo solicitado no están registrados en la "
                        .concat("base de datos."));
                throw new BadRequestException("💀 ¡Error grave! Ni el paciente y ni el odontólogo con los que quieres "
                        .concat("agendar tu turno están registralos en nuestra base de datos, regístralos y ")
                        .concat("vuelve de nuevo a este módulo."));
            } else if (turno.getPaciente() == null) {
                LOGGER.error("🛑 El paciente solicitado no está registrado en la base de datos.");
                throw new BadRequestException("🥺 El paciente al que le estás intentando agendar un turno no se "
                        .concat("encuentra registrado en nuestra base de datos, no puedes dejar al odontólogo ")
                        .concat("solito ¡Le daría frío!"));
            } else {
                LOGGER.error("🛑 El odontólogo solicitado no está registrado en la base de datos.");
                throw new BadRequestException("🥺 El odontólogo al que le estás intentando agendar un turno no se "
                        .concat("encuentra registrado en nuestra base de datos, no puedes dejar al paciente ")
                        .concat("solito ¡Eso no es ético y profesional!"));
            }
        } else {
            turnoDto = new TurnoDto(turnoRepository.save(turno));
            LOGGER.info("🎫 Se registró correctamente el turno: {}", turnoDto);
        }
        return turnoDto;
    }

    @Override
    public TurnoDto buscarTurnoPorId(Long id) throws ResourceNotFoundException {
        Turno turnoEncontrado = turnoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("🛑 El turno que buscas no existe en la base de datos."));
        TurnoDto turnoDto = new TurnoDto(turnoEncontrado);
        LOGGER.info("🔍 Se ha encontrado el turno con ID {}: {}", id, turnoDto);
        return turnoDto;
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
            LOGGER.error("🛑 Se intentó actualizar el turno con ID {}, pero este no existe en la base de datos.",
                    turno.getId());
            throw new ResourceNotFoundException("🛑 El turno que intentaste actualizar no existe en la base de datos.");
        } else {
            turnoActualizado = registrarTurno(turno);
            LOGGER.warn("🛑 Se ha actualizado el turno con ID {}: {}", turnoActualizado.getId(), turnoActualizado);
        }
        return turnoActualizado;
    }

    @Override
    public void eliminarTurno(Long id) throws ResourceNotFoundException {
        if (turnoRepository.existsById(id)) {
            turnoRepository.deleteById(id);
            LOGGER.warn("🚨 Se ha eliminado el turno con ID {}.", id);
        } else {
            LOGGER.error("🛑 Se intentó eliminar el turno con ID {}, pero este no existe en la base de datos.", id);
            throw new ResourceNotFoundException("🛑 El turno que intentaste eliminar no existe en la base de datos.");
        }
    }
}
