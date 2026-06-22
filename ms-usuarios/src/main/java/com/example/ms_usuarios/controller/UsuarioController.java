package com.example.ms_usuarios.controller;

import com.example.ms_usuarios.dto.UsuarioDTO;
import com.example.ms_usuarios.dto.UsuarioRequestDTO;
import com.example.ms_usuarios.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usuarios")
@Tag(
        name = "Usuarios",
        description = "Operaciones CRUD y búsqueda de usuarios del sistema"
)
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/buscar")
    @Operation(
            summary = "Buscar usuarios activos por correo electrónico",
            description = "Retorna usuarios activos que coincidan con el email indicado."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Consulta realizada correctamente",
                    content = @Content(
                            schema = @Schema(implementation = UsuarioDTO.class),
                            examples = @ExampleObject(
                                    value = """
                                    [
                                      {
                                        "id": 1,
                                        "nombre": "Ana Pérez",
                                        "email": "ana.perez@email.com",
                                        "telefono": "+56912345678",
                                        "direccionEntrega": "Av. Principal 123",
                                        "puntosFidelidad": 150,
                                        "activo": true,
                                        "fechaRegistro": "2026-06-01"
                                      }
                                    ]
                                    """
                            )
                    )
            )
    })
    public ResponseEntity<List<UsuarioDTO>> buscarPorEmailActivo(
            @Parameter(
                    description = "Correo electrónico del usuario",
                    example = "ana.perez@email.com"
            )
            @RequestParam String email
    ) {
        return ResponseEntity.ok(usuarioService.buscarPorEmailActivo(email));
    }

    @GetMapping
    @Operation(
            summary = "Listar todos los usuarios",
            description = "Retorna el listado completo de usuarios registrados."
    )
    @ApiResponse(responseCode = "200", description = "Usuarios obtenidos correctamente")
    public ResponseEntity<List<UsuarioDTO>> listar() {
        return ResponseEntity.ok(usuarioService.listar());
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Obtener usuario por ID",
            description = "Busca y retorna un usuario según su identificador."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Usuario encontrado",
                    content = @Content(
                            schema = @Schema(implementation = UsuarioDTO.class),
                            examples = @ExampleObject(
                                    value = """
                                    {
                                      "id": 1,
                                      "nombre": "Ana Pérez",
                                      "email": "ana.perez@email.com",
                                      "telefono": "+56912345678",
                                      "direccionEntrega": "Av. Principal 123",
                                      "puntosFidelidad": 150,
                                      "activo": true,
                                      "fechaRegistro": "2026-06-01"
                                    }
                                    """
                            )
                    )
            ),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    public ResponseEntity<UsuarioDTO> obtenerPorId(
            @Parameter(description = "ID del usuario", example = "1")
            @PathVariable Integer id
    ) {
        UsuarioDTO dto = usuarioService.obtenerPorId(id);

        if (dto == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(dto);
    }

    @PostMapping
    @Operation(
            summary = "Crear usuario",
            description = "Registra un nuevo usuario en el sistema."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Usuario creado correctamente",
                    content = @Content(schema = @Schema(implementation = UsuarioDTO.class))
            ),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    public ResponseEntity<UsuarioDTO> crear(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos necesarios para crear un usuario",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = UsuarioRequestDTO.class),
                            examples = @ExampleObject(
                                    value = """
                                    {
                                      "nombre": "Ana Pérez",
                                      "email": "ana.perez@email.com",
                                      "telefono": "+56912345678",
                                      "direccionEntrega": "Av. Principal 123",
                                      "puntosFidelidad": 0,
                                      "activo": true,
                                      "fechaRegistro": "2026-06-21"
                                    }
                                    """
                            )
                    )
            )
            @Valid @RequestBody UsuarioRequestDTO requestDTO
    ) {
        UsuarioDTO creado = usuarioService.crear(requestDTO);

        if (creado == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Actualizar usuario",
            description = "Actualiza todos los datos del usuario indicado."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuario actualizado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    public ResponseEntity<UsuarioDTO> actualizar(
            @Parameter(description = "ID del usuario", example = "1")
            @PathVariable Integer id,
            @Valid @RequestBody UsuarioRequestDTO requestDTO
    ) {
        UsuarioDTO actualizado = usuarioService.actualizar(id, requestDTO);

        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Eliminar usuario",
            description = "Elimina un usuario según su identificador."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Usuario eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    public ResponseEntity<Void> eliminar(
            @Parameter(description = "ID del usuario", example = "1")
            @PathVariable Integer id
    ) {
        boolean eliminado = usuarioService.eliminar(id);

        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}