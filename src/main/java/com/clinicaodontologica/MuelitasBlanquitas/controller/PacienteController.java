package com.clinicaodontologica.MuelitasBlanquitas.controller;

import com.clinicaodontologica.MuelitasBlanquitas.dto.PacienteDto;
import com.clinicaodontologica.MuelitasBlanquitas.entity.Paciente;
import com.clinicaodontologica.MuelitasBlanquitas.exception.ResourceNotFoundException;
import com.clinicaodontologica.MuelitasBlanquitas.service.impl.PacienteService;
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
@RequestMapping("/pacientes")
@Tag(name = "Pacientes", description = "👨‍👩‍👧‍👦 Métodos CRUD para gestionar pacientes.")
public class PacienteController {
    private final PacienteService pacienteService;

    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @Operation(summary = "️👩 Registrar un paciente",
            description = "Crea un paciente nuevo en nuestra API, solamente envíanos los datos como los requerimos, " +
                    "et voilà !",
            responses = {
                    @ApiResponse(responseCode = "201",
                            description = "🙌 Paciente creado exitosamente ¡Cada vez tenemos más clientela!",
                            content = {
                                    @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = PacienteDto.class))
                            }),
                    @ApiResponse(responseCode = "400",
                            description = "😟 Tu solicitud no cumple con los requisitos y no se creó al paciente, " +
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
    public ResponseEntity<PacienteDto> registrarPaciente(@RequestBody @Valid Paciente paciente) {
        PacienteDto pacienteGuardado = pacienteService.registrarPaciente(paciente);
        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteGuardado);
    }

    @Operation(summary = "🔍 Buscar un paciente por ID",
            description = "¿Necesitas investigar a un paciente? Solamente pásame su ID y te paso todo lo que sé.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "👧 Encontraste a tu paciente ¿y ahora qué?",
                            content = {
                                    @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = PacienteDto.class))
                            }
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "🚨 ¡Pásame solamente números!",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "🔎 Ese paciente que pensabas que existía, ya no existe (al menos en " +
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
    public ResponseEntity<PacienteDto> buscarPacientePorId(@PathVariable Long id) throws ResourceNotFoundException {
        PacienteDto pacienteEncontrado = pacienteService.buscarPacientePorId(id);
        return ResponseEntity.ok(pacienteEncontrado);
    }

    @Operation(summary = "👨‍👩‍👧‍👦 Listar todos los pacientes",
            description = "Nuestra clientela es exclusiva, eso, sin dudas ¿quieres conocerla?",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "👨‍👩‍👧‍👦 He aquí todos los pacientes que tenemos ¡Son nuestra razón de ser!",
                            content = {
                                    @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = PacienteDto.class))
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
    public List<PacienteDto> listarTodosLosPacientes() {
        return pacienteService.listarTodosLosPacientes();
    }

    @Operation(summary = "🔁 Actualizar un paciente",
            description = "¿Con que tu paciente no era ese que había dicho ser? ¿O se te fue un dato errado? " +
                    "¡Corrígelo aquí! Solamente necesitas consultar los datos ya guardados y remplazarlos.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "👨‍️ Actualizaste tu paciente correctamente ¿acaso no quedó más lindo?",
                            content = {
                                    @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = PacienteDto.class))
                            }
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "😟 Tu solicitud no cumple con los requisitos y no se actualizó al " +
                                    "paciente, por favor intenta nuevamente.",
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
    public ResponseEntity<PacienteDto> actualizarPaciente(@RequestBody @Valid Paciente paciente) throws ResourceNotFoundException {
        PacienteDto pacienteEncontrado = pacienteService.actualizarPaciente(paciente);
        return ResponseEntity.ok(pacienteEncontrado);
    }

    @Operation(summary = "🚮 Eliminar un paciente por ID",
            description = "¿Acaso ese paciente fue maleducado contigo? ¿Y si lo eliminas? 😈",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "️❌ ¡Felicitaciones! Ese paciente dejó de ser un problema, ahora " +
                                    "está eliminado.",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "🚨 ¡Pásame solamente números!",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "🔎 Ese paciente que pensabas que existía, ya no existe (al menos en " +
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
    public ResponseEntity<?> eliminarPaciente(@PathVariable Long id) throws ResourceNotFoundException {
        pacienteService.eliminarPaciente(id);
        return ResponseEntity.ok("💀 Paciente eliminado con éxito");
    }
}
