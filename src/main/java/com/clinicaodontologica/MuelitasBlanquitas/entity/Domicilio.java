package com.clinicaodontologica.MuelitasBlanquitas.entity;

import jakarta.persistence.*;
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

    @NonNull
    private String calle;
    @NonNull
    private Integer numero;
    @NonNull
    private String localidad;
    @NonNull
    private String departamento;
}
