package com.clinicaodontologica.MuelitasBlanquitas.service;

import com.clinicaodontologica.MuelitasBlanquitas.dto.OdontologoDto;
import com.clinicaodontologica.MuelitasBlanquitas.entity.Odontologo;
import com.clinicaodontologica.MuelitasBlanquitas.exception.ResourceNotFoundException;

import java.util.List;

public interface IOdontologoService {

    OdontologoDto registrarOdontologo(Odontologo odontologo);

    OdontologoDto buscarOdontologoPorId(Long id) throws ResourceNotFoundException;

    List<OdontologoDto> listarTodosLosOdontologos();

    OdontologoDto actualizarOdontologo(Odontologo odontologo) throws ResourceNotFoundException;

    void eliminarOdontologo(Long id) throws ResourceNotFoundException;
}
