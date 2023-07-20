package com.clinicaodontologica.MuelitasBlanquitas.dto;

import com.clinicaodontologica.MuelitasBlanquitas.entity.Odontologo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OdontologoDto {
    private Long id;
    private String nombre;
    private String apellido;
    private String matricula;

    public OdontologoDto(Odontologo odontologo) {
        this.setId(odontologo.getId());
        this.setNombre(odontologo.getNombre());
        this.setApellido(odontologo.getApellido());
        this.setMatricula(odontologo.getMatricula());
    }
}
