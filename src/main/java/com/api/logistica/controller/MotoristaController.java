package com.api.logistica.controller;

import com.api.logistica.dto.motorista.MotoristaRequestDTO;
import com.api.logistica.dto.motorista.MotoristaResponseDTO;
import com.api.logistica.service.MotoristaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * Rota base: /api/motoristas
 *
 * TODO: anotar a classe, injetar MotoristaService (construtor) e implementar os métodos.
 */
@Tag(
        name = "Motorista",
        description = "Endpoints relacionados as seguintes operações: cadastrar motorista, e listar todos os motoristas"
)
@RestController
@RequestMapping("/v1/api/motorista")
public class MotoristaController {

    private final MotoristaService service;

    public MotoristaController(MotoristaService service) {
        this.service = service;
    }

    // POST /api/motoristas -> 201 Created (com header Location) ou 400 Bad Request
    @PostMapping
    @Operation(
            summary = "Cadastrar motorista",
            description = "Realiza o cadastro de motoristas"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Motorista cadastrado com sucesso!"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados invalidos"
            )
    })
    public ResponseEntity<MotoristaResponseDTO> cadastrar(MotoristaRequestDTO dto) {

        MotoristaResponseDTO motorista = service.cadastrar(dto);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(motorista.id())
                .toUri();

        return ResponseEntity.created(uri).body(motorista);
    }

    // GET /api/motoristas -> 200 OK
    @Operation(
            summary = "Listar motoristas",
            description = "Listar todos os motoristas"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Todos os motoristas retornados com sucesso"
    )
    @GetMapping
    public ResponseEntity<List<MotoristaResponseDTO>> listar() {
        return null;
    }
}