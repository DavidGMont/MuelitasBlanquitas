package com.clinicaodontologica.MuelitasBlanquitas.dto;

import com.clinicaodontologica.MuelitasBlanquitas.entity.Turno;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class TurnoDto {
    @Schema(title = "ID", description = "Valor autogenerado por la base de datos.", example = "154")
    private Long id;

    @Schema(title = "Paciente DTO", description = "La información de tu paciente en DTO.")
    private PacienteDto pacienteDto;

    @Schema(title = "Odontólogo DTO", description = "La información de tu odontólogo en DTO.")
    private OdontologoDto odontologoDto;

    @Schema(title = "Fecha y Hora", description = "La fecha y hora en que programaste tu turno.",
            example = "2023-08-28T13:40")
    private LocalDateTime fechaHora;

    public TurnoDto(Turno turno) {
        this.setId(turno.getId());
        this.setPacienteDto(new PacienteDto(turno.getPaciente()));
        this.setOdontologoDto(new OdontologoDto(turno.getOdontologo()));
        this.setFechaHora(turno.getFechaHora());
    }
}
