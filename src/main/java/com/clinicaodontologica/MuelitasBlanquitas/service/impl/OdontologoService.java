package com.clinicaodontologica.MuelitasBlanquitas.service.impl;

import com.clinicaodontologica.MuelitasBlanquitas.dto.OdontologoDto;
import com.clinicaodontologica.MuelitasBlanquitas.entity.Odontologo;
import com.clinicaodontologica.MuelitasBlanquitas.exception.ResourceNotFoundException;
import com.clinicaodontologica.MuelitasBlanquitas.repository.OdontologoRepository;
import com.clinicaodontologica.MuelitasBlanquitas.service.IOdontologoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class OdontologoService implements IOdontologoService {
    private final OdontologoRepository odontologoRepository;

    @Autowired
    public OdontologoService(OdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

    @Override
    public OdontologoDto registrarOdontologo(Odontologo odontologo) {
        OdontologoDto odontologoDto = new OdontologoDto(odontologoRepository.save(odontologo));
        LOGGER.info("👨‍⚕️ Se registró correctamente al odontólogo: {}", odontologoDto);
        return odontologoDto;
    }

    @Override
    public OdontologoDto buscarOdontologoPorId(Long id) throws ResourceNotFoundException {
        Odontologo odontologoEncontrado = odontologoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("🛑 El odontólogo que buscas no existe en la base de datos."));
        OdontologoDto odontologoDto = new OdontologoDto(odontologoEncontrado);
        LOGGER.info("🔍 Se ha encontrado al odontólogo con ID {}: {}", id, odontologoDto);
        return odontologoDto;
    }

    @Override
    public List<OdontologoDto> listarTodosLosOdontologos() {
        List<OdontologoDto> odontologos = odontologoRepository.findAll()
                .stream()
                .map(OdontologoDto::new)
                .toList();
        LOGGER.info("🦷 Listando todos nuestros odontólogos: {}", odontologos);
        return odontologos;
    }

    @Override
    public OdontologoDto actualizarOdontologo(Odontologo odontologo) throws ResourceNotFoundException {
        OdontologoDto odontologoActualizado;
        if (!odontologoRepository.existsById(odontologo.getId())) {
            LOGGER.error("🛑 Se intentó actualizar al odontólogo con ID {}, pero este no existe en la base de datos.",
                    odontologo.getId());
            throw new ResourceNotFoundException("🛑 El odontólogo que intentaste actualizar no existe en la " +
                    "base de datos.");
        } else {
            odontologoActualizado = registrarOdontologo(odontologo);
            LOGGER.warn("🛑 Se ha actualizado el odontólogo con ID {}: {}", odontologoActualizado.getId(),
                    odontologoActualizado);
        }
        return odontologoActualizado;
    }

    @Override
    public void eliminarOdontologo(Long id) throws ResourceNotFoundException {
        if (odontologoRepository.existsById(id)) {
            odontologoRepository.deleteById(id);
            LOGGER.warn("🚨 Se ha eliminado el odontólogo con ID {}.", id);
        } else {
            LOGGER.error("🛑 Se intentó eliminar al odontólogo con ID {}, pero este no existe en la base de datos.", id);
            throw new ResourceNotFoundException("🛑 El odontólogo que intentaste eliminar no existe en la " +
                    "base de datos.");
        }
    }
}
