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
        Odontologo odontologoParaGuardar = odontologoRepository.save(odontologo);
        OdontologoDto odontologoConvertido = new OdontologoDto(odontologoParaGuardar);
        LOGGER.info("🙌 Se guardó exitosamente tu odontólogo: {}", odontologoConvertido);
        return odontologoConvertido;
    }

    @Override
    public OdontologoDto buscarOdontologoPorId(Long id) throws ResourceNotFoundException {
        Odontologo odontologoEncontrado = odontologoRepository
                .findById(id).orElseThrow(() -> new ResourceNotFoundException("No encontrado"));
        OdontologoDto odontologoConvertido = new OdontologoDto(odontologoEncontrado);
        LOGGER.info("🔍 Odontólogo encontrado: {}", odontologoConvertido);
        return odontologoConvertido;
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
            LOGGER.warn("🛑 Se intentó actualizar al odontólogo con ID {}, pero no existe en la base de datos", odontologo.getId());
            throw new ResourceNotFoundException("🛑 El odontólogo que intentaste actualizar no existe en la base de datos");
        } else {
            odontologoActualizado = registrarOdontologo(odontologo);
            LOGGER.warn("🛑 Se ha actualizado el odontólogo con ID {}: {}", odontologoActualizado.getId(), odontologoActualizado);
        }
        return odontologoActualizado;
    }

    @Override
    public void eliminarOdontologo(Long id) throws ResourceNotFoundException {
        if (odontologoRepository.existsById(id)) {
            odontologoRepository.deleteById(id);
            LOGGER.warn("🚨 Se ha eliminado el odontólogo con ID {}", id);
        } else {
            LOGGER.warn("🛑 Se intentó eliminar al odontólogo con ID {}, pero no existe en la base de datos", id);
            throw new ResourceNotFoundException("🛑 El odontólogo que intentaste eliminar no existe en la base de datos");
        }
    }
}
