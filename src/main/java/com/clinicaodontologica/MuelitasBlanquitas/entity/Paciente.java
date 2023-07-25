package com.clinicaodontologica.MuelitasBlanquitas.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
    @Schema(title = "ID", description = "Valor autogenerado por la base de datos.", example = "16")
    private Long id;

    @NonNull
    @NotBlank(message = "🔠 Por favor ingresa un nombre, este no puede ser nulo, ni estar vacío.")
    @Size(min = 2, max = 50, message = "🔠 El nombre ingresado es inválido. Por favor ingresa un nombre válido, " +
            "puedes ingresar hasta 50 caracteres.")
    @Pattern(regexp = "^[\\p{IsLatin}+?\\-' ]+$", flags = Pattern.Flag.UNICODE_CASE,
            message = "🔠 Se encontraron caracteres inválidos en el nombre ingresado. Por favor ingresa solamente " +
                    "caracteres latinos, apóstrofo (') o guion (-).")
    @Schema(title = "Nombre", description = "El nombre de tu paciente.", example = "Willy")
    private String nombre;

    @NonNull
    @NotBlank(message = "🔠 Por favor ingresa un apellido, este no puede ser nulo, ni estar vacío.")
    @Size(min = 2, max = 50, message = "🔠 El apellido ingresado es inválido. Por favor ingresa un apellido válido, " +
            "puedes ingresar hasta 50 caracteres.")
    @Pattern(regexp = "^[\\p{IsLatin}+?\\-' ]+$", flags = Pattern.Flag.UNICODE_CASE,
            message = "🔠 Se encontraron caracteres inválidos en el apellido ingresado. Por favor ingresa solamente " +
                    "caracteres latinos, apóstrofo (') o guion (-).")
    @Schema(title = "Apellido", description = "El apellido de tu paciente.", example = "Wonka")
    private String apellido;

    @NonNull
    @NotBlank(message = "🆔 Por favor ingresa una cédula, esta no puede ser nula, ni estar vacía.")
    @Size(min = 7, max = 12, message = "🆔 La cédula ingresado es inválido. Por favor ingresa una cédula válida, este debe " +
            "tener entre 7 y 12 números.")
    @Pattern(regexp = "^\\d+$", message = "🆔 Se encontraron caracteres inválidos en la cédula ingresada. Por favor " +
            "ingresa solamente números.")
    @Schema(title = "Cédula", description = "La cédula de tu paciente.", example = "1144668")
    private String cedula;

    @NonNull
    @NotNull(message = "📅 Por favor ingresa una fecha, esta no puede ser nula.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @FutureOrPresent(message = "📅 La fecha ingresada es inválida. Por favor ingresa una fecha que sea igual o " +
            "posterior a la fecha actual.")
    @Schema(title = "Fecha de Ingreso", description = "La fecha en que registraste a tu paciente.",
            example = "2023-06-29")
    private LocalDate fechaIngreso;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "domicilio_id")
    @NonNull
    @NotNull(message = "🏡 Por favor ingresa un domicilio, este no puede ser nulo.")
    @Schema(title = "Domicilio", description = "La información del domicilio de tu paciente.")
    private Domicilio domicilio;

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Turno> turnos;
}
