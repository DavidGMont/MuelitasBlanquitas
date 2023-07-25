package com.clinicaodontologica.MuelitasBlanquitas.dto;

import com.clinicaodontologica.MuelitasBlanquitas.entity.Domicilio;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class DomicilioDto {
    @Schema(title = "ID", description = "Valor autogenerado por la base de datos.", example = "16")
    private Long id;

    @Schema(title = "Calle", description = "El nombre de la calle del domicilio.", example = "Pudding Lane")
    private String calle;

    @Schema(title = "Número", description = "El número del domicilio.", example = "10")
    private Integer numero;

    @Schema(title = "Localidad", description = "El nombre de la localidad del domicilio.", example = "Birmingham")
    private String localidad;

    @Schema(title = "Provincia", description = "El nombre de la provincia del domicilio.",
            example = "Tierras Medias Occidentales")
    private String departamento;

    public DomicilioDto(Domicilio domicilio) {
        this.setId(domicilio.getId());
        this.setCalle(domicilio.getCalle());
        this.setNumero(domicilio.getNumero());
        this.setLocalidad(domicilio.getLocalidad());
        this.setDepartamento(domicilio.getDepartamento());
    }
}
