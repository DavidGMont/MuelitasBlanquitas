package com.clinicaodontologica.MuelitasBlanquitas.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "TURNOS")
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@Getter
@Setter
public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(value = AccessLevel.NONE)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "paciente_id", nullable = false)
    @NotNull(message = "🛑 El paciente no puede ser nulo")
    private Paciente paciente;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "odontologo_id", nullable = false)
    @NotNull(message = "🛑 El odontólogo no puede ser nulo")
    private Odontologo odontologo;

    @FutureOrPresent(message = "🛑 La fecha y hora deben ser iguales o posteriores a la fecha y hora actual")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    @NotNull(message = "🛑 La fecha y hora no puede ser nulas")
    @NonNull
    private LocalDateTime fechaHora;
}
