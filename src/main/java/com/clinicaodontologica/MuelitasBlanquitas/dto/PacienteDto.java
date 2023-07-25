package com.clinicaodontologica.MuelitasBlanquitas.dto;

import com.clinicaodontologica.MuelitasBlanquitas.entity.Paciente;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PacienteDto {
    @Schema(title = "ID", description = "Valor autogenerado por la base de datos.", example = "16")
    private Long id;

    @Schema(title = "Nombre", description = "El nombre de tu paciente.", example = "Willy")
    private String nombre;

    @Schema(title = "Apellido", description = "El apellido de tu paciente.", example = "Wonka")
    private String apellido;

    @Schema(title = "Cédula", description = "La cédula de tu paciente.", example = "1144668")
    private String cedula;

    @Schema(title = "Fecha de Ingreso", description = "La fecha en que registraste a tu paciente.",
            example = "2023-06-29")
    private LocalDate fechaIngreso;

    @Schema(title = "Domicilio", description = "La información del domicilio de tu paciente.")
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
