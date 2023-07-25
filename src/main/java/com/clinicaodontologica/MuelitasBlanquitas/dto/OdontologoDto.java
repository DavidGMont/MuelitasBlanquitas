package com.clinicaodontologica.MuelitasBlanquitas.dto;

import com.clinicaodontologica.MuelitasBlanquitas.entity.Odontologo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OdontologoDto {
    @Schema(title = "ID", description = "Valor autogenerado por la base de datos.", example = "23")
    private Long id;

    @Schema(title = "Nombre", description = "El nombre de tu odontólogo.", example = "Wilbur")
    private String nombre;

    @Schema(title = "Apellido", description = "El apellido de tu odontólogo.", example = "Wonka")
    private String apellido;

    @Schema(title = "Matrícula", description = "La matrícula profesional de tu odontólogo.",
            example = "61922-201578 GBR")
    private String matricula;

    public OdontologoDto(Odontologo odontologo) {
        this.setId(odontologo.getId());
        this.setNombre(odontologo.getNombre());
        this.setApellido(odontologo.getApellido());
        this.setMatricula(odontologo.getMatricula());
    }
}
