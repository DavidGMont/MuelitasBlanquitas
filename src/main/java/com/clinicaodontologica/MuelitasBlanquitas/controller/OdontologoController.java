package com.clinicaodontologica.MuelitasBlanquitas.controller;

import com.clinicaodontologica.MuelitasBlanquitas.dto.OdontologoDto;
import com.clinicaodontologica.MuelitasBlanquitas.entity.Odontologo;
import com.clinicaodontologica.MuelitasBlanquitas.exception.ResourceNotFoundException;
import com.clinicaodontologica.MuelitasBlanquitas.service.impl.OdontologoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/odontologos")
public class OdontologoController {
    private final OdontologoService odontologoService;

    @Autowired
    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @PostMapping
    public ResponseEntity<OdontologoDto> registrarOdontologo(@RequestBody @Valid Odontologo odontologo) {
        OdontologoDto odontologoGuardado = odontologoService.registrarOdontologo(odontologo);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(odontologoGuardado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OdontologoDto> buscarOdontologoPorId(@PathVariable Long id) throws ResourceNotFoundException {
        OdontologoDto odontologoEncontrado = odontologoService.buscarOdontologoPorId(id);
        return ResponseEntity.ok(odontologoEncontrado);
    }

    @GetMapping
    public List<OdontologoDto> listarTodosLosOdontologos() {
        return odontologoService.listarTodosLosOdontologos();
    }

    @PutMapping
    public ResponseEntity<OdontologoDto> actualizarOdontologo(@RequestBody @Valid Odontologo odontologo) throws ResourceNotFoundException {
        OdontologoDto odontologoActualizado = odontologoService.actualizarOdontologo(odontologo);
        return ResponseEntity.ok(odontologoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarOdontologo(@PathVariable Long id) throws ResourceNotFoundException {
        odontologoService.eliminarOdontologo(id);
        return ResponseEntity.ok("💀 Odontólogo eliminado con éxito");
    }
}
