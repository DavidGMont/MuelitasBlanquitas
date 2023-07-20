package com.clinicaodontologica.MuelitasBlanquitas.dto;

import com.clinicaodontologica.MuelitasBlanquitas.entity.Domicilio;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class DomicilioDto {
    private Long id;
    private String calle;
    private Integer numero;
    private String localidad;
    private String departamento;

    public DomicilioDto(Domicilio domicilio) {
        this.setId(domicilio.getId());
        this.setCalle(domicilio.getCalle());
        this.setNumero(domicilio.getNumero());
        this.setLocalidad(domicilio.getLocalidad());
        this.setDepartamento(domicilio.getDepartamento());
    }
}
