package com.clinicaodontologica.MuelitasBlanquitas.controller;

import com.clinicaodontologica.MuelitasBlanquitas.dto.PacienteDto;
import com.clinicaodontologica.MuelitasBlanquitas.entity.Paciente;
import com.clinicaodontologica.MuelitasBlanquitas.exception.ResourceNotFoundException;
import com.clinicaodontologica.MuelitasBlanquitas.service.impl.PacienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/pacientes")
public class PacienteController {
    private final PacienteService pacienteService;

    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping
    public ResponseEntity<PacienteDto> registrarPaciente(@RequestBody @Valid Paciente paciente) {
        PacienteDto pacienteGuardado = pacienteService.registrarPaciente(paciente);
        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteGuardado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDto> buscarPacientePorId(@PathVariable Long id) throws ResourceNotFoundException {
        PacienteDto pacienteEncontrado = pacienteService.buscarPacientePorId(id);
        return ResponseEntity.ok(pacienteEncontrado);
    }

    @GetMapping
    public List<PacienteDto> listarTodosLosPacientes() {
        return pacienteService.listarTodosLosPacientes();
    }

    @PutMapping
    public ResponseEntity<PacienteDto> actualizarPaciente(@RequestBody @Valid Paciente paciente) throws ResourceNotFoundException {
        PacienteDto pacienteEncontrado = pacienteService.actualizarPaciente(paciente);
        return ResponseEntity.ok(pacienteEncontrado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPaciente(@PathVariable Long id) throws ResourceNotFoundException {
        pacienteService.eliminarPaciente(id);
        return ResponseEntity.ok("💀 Paciente eliminado con éxito");
    }
}
