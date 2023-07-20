package com.clinicaodontologica.MuelitasBlanquitas.dto;

import com.clinicaodontologica.MuelitasBlanquitas.entity.Turno;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class TurnoDto {
    private Long id;
    private PacienteDto pacienteDto;
    private OdontologoDto odontologoDto;
    private LocalDateTime fechaHora;

    public TurnoDto(Turno turno) {
        this.setId(turno.getId());
        this.setPacienteDto(new PacienteDto(turno.getPaciente()));
        this.setOdontologoDto(new OdontologoDto(turno.getOdontologo()));
        this.setFechaHora(turno.getFechaHora());
    }
}
