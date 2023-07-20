package com.clinicaodontologica.MuelitasBlanquitas.service;

import com.clinicaodontologica.MuelitasBlanquitas.dto.TurnoDto;
import com.clinicaodontologica.MuelitasBlanquitas.entity.Turno;
import com.clinicaodontologica.MuelitasBlanquitas.exception.BadRequestException;
import com.clinicaodontologica.MuelitasBlanquitas.exception.ResourceNotFoundException;

import java.util.List;

public interface ITurnoService {

    TurnoDto registrarTurno(Turno turno) throws BadRequestException;

    TurnoDto buscarTurnoPorId(Long id) throws ResourceNotFoundException;

    List<TurnoDto> listarTodosLosTurnos();

    TurnoDto actualizarTurno(Turno turno) throws ResourceNotFoundException, BadRequestException;

    void eliminarTurno(Long id) throws ResourceNotFoundException;
}
