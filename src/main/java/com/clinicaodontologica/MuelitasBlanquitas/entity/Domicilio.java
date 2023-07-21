package com.clinicaodontologica.MuelitasBlanquitas.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "DOMICILIOS")
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@Getter
@Setter
public class Domicilio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(value = AccessLevel.NONE)
    private Long id;

    @Size(min = 2, max = 50, message = "🛑 El nombre de la calle debe contener entre 2 y 50 caracteres")
    @NotBlank(message = "🛑 El nombre de la calle no puede estar vacío ni ser nulo")
    @NonNull
    private String calle;

    @NonNull
    private Integer numero;

    @Size(min = 2, max = 50, message = "🛑 El nombre de la localidad debe contener entre 2 y 50 caracteres")
    @NotBlank(message = "🛑 El nombre de la localidad no puede estar vacío ni ser nulo")
    @NonNull
    private String localidad;

    @Size(min = 2, max = 50, message = "🛑 El nombre del departamento debe contener entre 2 y 50 caracteres")
    @NotBlank(message = "🛑 El nombre del departamento no puede estar vacío ni ser nulo")
    @NonNull
    private String departamento;
}
