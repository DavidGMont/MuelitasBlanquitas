package com.clinicaodontologica.MuelitasBlanquitas.dto;

import com.clinicaodontologica.MuelitasBlanquitas.entity.Paciente;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PacienteDto {
    private Long id;
    private String nombre;
    private String apellido;
    private String cedula;
    private LocalDate fechaIngreso;
    private DomicilioDto domicilioDto;

    public PacienteDto(Paciente paciente) {
        this.setId(paciente.getId());
        this.setNombre(paciente.getNombre());
        this.setApellido(paciente.getApellido());
        this.setCedula(paciente.getCedula());
        this.setFechaIngreso(paciente.getFechaIngreso());
        this.setDomicilioDto(new DomicilioDto(paciente.getDomicilio()));
    }
}
