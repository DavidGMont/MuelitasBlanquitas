package com.clinicaodontologica.MuelitasBlanquitas.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "PACIENTES")
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@Getter
@Setter
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(value = AccessLevel.NONE)
    private Long id;

    @Size(min = 2, max = 50, message = "🛑 Tu nombre debe contener entre 2 y 50 caracteres")
    @NotBlank(message = "🛑 Tu nombre no puede estar vacío ni ser nulo")
    @NonNull
    private String nombre;

    @Size(min = 2, max = 50, message = "🛑 Tu apellido debe contener entre 2 y 50 caracteres")
    @NotBlank(message = "🛑 Tu apellido no puede estar vacío ni ser nulo")
    @NonNull
    private String apellido;

    @Size(min = 8, max = 9, message = "🛑 Tu cédula debe contener entre 8 y 9 caracteres")
    @NotBlank(message = "🛑 Tu cédula no puede estar vacío ni ser nulo")
    @NonNull
    private String cedula;

    @FutureOrPresent(message = "🛑 La fecha debe ser igual o posterior a la fecha actual")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotNull(message = "🛑 La fecha no puede ser nula")
    @NonNull
    private LocalDate fechaIngreso;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "domicilio_id")
    @NotNull(message = "🛑 El domicilio no puede ser nulo")
    @NonNull
    private Domicilio domicilio;

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Turno> turnos;
}
