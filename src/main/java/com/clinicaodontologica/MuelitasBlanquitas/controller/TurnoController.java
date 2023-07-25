package com.clinicaodontologica.MuelitasBlanquitas.controller;

import com.clinicaodontologica.MuelitasBlanquitas.dto.TurnoDto;
import com.clinicaodontologica.MuelitasBlanquitas.entity.Turno;
import com.clinicaodontologica.MuelitasBlanquitas.exception.BadRequestException;
import com.clinicaodontologica.MuelitasBlanquitas.exception.ResourceNotFoundException;
import com.clinicaodontologica.MuelitasBlanquitas.service.impl.TurnoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/turnos")
@Tag(name = "Turnos", description = "🎫 Métodos CRUD para gestionar turnos.")
public class TurnoController {
    private final TurnoService turnoService;

    @Autowired
    public TurnoController(TurnoService turnoService) {
        this.turnoService = turnoService;
    }

    @Operation(summary = "️🎫 Registrar un turno",
            description = "Crea un turno nuevo en nuestra API, recuerda que primero necesitas un odontólogo y " +
                    "un paciente registrados, luego de eso ¡Te agendamos!",
            responses = {
                    @ApiResponse(responseCode = "201",
                            description = "🙌 Turno creado exitosamente ¡Va llegando el trabajo!",
                            content = {
                                    @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = TurnoDto.class))
                            }),
                    @ApiResponse(responseCode = "400",
                            description = "😟 Tu solicitud no cumple con los requisitos y no se creó el turno, " +
                                    "por favor intenta nuevamente.",
                            content = @Content),
                    @ApiResponse(responseCode = "404",
                            description = "🔎 Eso que buscas puede que no exista, al menos en nuestra base de datos.",
                            content = @Content),
                    @ApiResponse(responseCode = "500",
                            description = "💻 No eres tú, es el servidor; por favor, intenta nuevamente en breve.",
                            content = @Content)
            })
    @PostMapping
    public ResponseEntity<TurnoDto> registrarTurno(@RequestBody @Valid Turno turno) throws BadRequestException {
        return ResponseEntity.status(HttpStatus.CREATED).body(turnoService.registrarTurno(turno));
    }

    @Operation(summary = "🔍 Buscar un turno por ID",
            description = "¿Se te olvidó cuándo y con quién agendaste tu turno? ¡Pásame el ID y te doy " +
                    "esa información!",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "🎫 Encontraste a tu turno ¿dudas despejadas?",
                            content = {
                                    @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = TurnoDto.class))
                            }
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "🚨 ¡Pásame solamente números!",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "🔎 Ese turno que pensabas que existía, ya no existe (al menos en " +
                                    "nuestra base de datos).",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "💻 No eres tú, es el servidor; por favor, intenta nuevamente en breve.",
                            content = @Content
                    )
            })
    @GetMapping("/{id}")
    public ResponseEntity<TurnoDto> buscarTurnoPorId(@PathVariable Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(turnoService.buscarTurnoPorId(id));
    }

    @Operation(summary = "🎫 Listar todos los turnos",
            description = "¿Quieres ver toda la cantidad de trabajo que tenemos? Bien, aquí va.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "🎫 He aquí todos los turnos que tenemos ¡eso se traduce en dinero!",
                            content = {
                                    @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = TurnoDto.class))
                            }
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "🚨 La probabilidad de encontrar este error es mínima, y, aun así, lo " +
                                    "lograste encontrar ¿quieres un premio?",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "🔎 Eso que buscas, no existe, al menos en nuestra base de datos.",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "💻 No eres tú, es el servidor; por favor, intenta nuevamente en breve.",
                            content = @Content
                    )
            })
    @GetMapping
    public List<TurnoDto> listarTodosLosTurnos() {
        return turnoService.listarTodosLosTurnos();
    }

    @Operation(summary = "🔁 Actualizar un turno",
            description = "¿Que tu paciente ya no viene? ¿Y si reagendas esa cita con otro odontólogo? Hazlo aquí " +
                    "pasándome toda la información.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "🎫‍️ Actualizaste tu turno correctamente ¡El trabajo continúa!",
                            content = {
                                    @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = TurnoDto.class))
                            }
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "😟 Tu solicitud no cumple con los requisitos y no se actualizó al turno, " +
                                    "por favor intenta nuevamente.",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "🔎 Eso que buscas, no existe, al menos en nuestra base de datos.",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "💻 No eres tú, es el servidor; por favor, intenta nuevamente en breve.",
                            content = @Content
                    )
            })
    @PutMapping
    public ResponseEntity<TurnoDto> actualizarTurno(@RequestBody @Valid Turno turno)
            throws BadRequestException, ResourceNotFoundException {
        return ResponseEntity.ok(turnoService.actualizarTurno(turno));
    }

    @Operation(summary = "🚮 Eliminar un turno por ID",
            description = "¿Acaso te equivocaste agendando ese turno? ¿Y si corriges tu error eliminándolo? 😈",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "️❌ ¡Felicitaciones! Pasado pisado, ahora ese turno está eliminado.",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "🚨 ¡Pásame solamente números!",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "🔎 Ese turno que pensabas que existía, ya no existe (al menos en " +
                                    "nuestra base de datos).",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "💻 No eres tú, es el servidor; por favor, intenta nuevamente en breve.",
                            content = @Content
                    )
            })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarTurnoPorId(@PathVariable Long id) throws ResourceNotFoundException {
        turnoService.eliminarTurno(id);
        return ResponseEntity.ok("💀 Turno eliminado con éxito");
    }
}
