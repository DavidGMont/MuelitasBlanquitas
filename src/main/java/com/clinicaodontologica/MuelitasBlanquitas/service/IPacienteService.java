package com.clinicaodontologica.MuelitasBlanquitas.service;


import com.clinicaodontologica.MuelitasBlanquitas.dto.PacienteDto;
import com.clinicaodontologica.MuelitasBlanquitas.entity.Paciente;
import com.clinicaodontologica.MuelitasBlanquitas.exception.ResourceNotFoundException;

import java.util.List;

public interface IPacienteService {

    PacienteDto registrarPaciente(Paciente paciente);

    PacienteDto buscarPacientePorId(Long id) throws ResourceNotFoundException;

    List<PacienteDto> listarTodosLosPacientes();

    PacienteDto actualizarPaciente(Paciente paciente) throws ResourceNotFoundException;

    void eliminarPaciente(Long id) throws ResourceNotFoundException;
}
