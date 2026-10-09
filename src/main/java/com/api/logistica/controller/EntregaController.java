package com.api.logistica.controller;

import com.api.logistica.dto.entrega.EntregaRequestDTO;
import com.api.logistica.dto.entrega.EntregaResponseDTO;
import com.api.logistica.dto.entrega.EntregaStatusDTO;
import com.api.logistica.dto.historico.HistoricoRequestDTO;
import com.api.logistica.dto.historico.HistoricoResponseDTO;
import com.api.logistica.service.EntregaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

/**
 * Rota base: /api/entregas
 *
 * TODO: anotar a classe, injetar EntregaService (construtor) e implementar os métodos.
 */
@Tag(
        name = "Entregas",
        description = "Endpoints para cadastro, atualizar status e adicionar ao historico"
)
@RestController
@RequestMapping("/v1/api/entrega")
public class EntregaController {

    private final EntregaService entregaService;

    public EntregaController(EntregaService entregaService) {
        this.entregaService = entregaService;
    }

    // POST /api/entregas -> 201 Created (com header Location), 400 Bad Request ou 404 Not Found

    @Operation(
            summary = "Cadastrar entrega",
            description = "Gera uma nova entrega"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Entrega criada com sucesso!"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Dados invalidos"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Entrega não registrada."
            )
    })
    @PostMapping
    public ResponseEntity<EntregaResponseDTO> cadastrar(@Valid @RequestBody EntregaRequestDTO dto) {

        EntregaResponseDTO responseDTO = entregaService.cadastrar(dto);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(responseDTO.id())
                .toUri();

        return ResponseEntity.created(uri).body(responseDTO);
    }

    // PATCH /api/entregas/{id}/status -> 200 OK, 400 Bad Request ou 404 Not Found
    @Operation(
            summary = "Atualiza status",
            description = "Atualiza status pelo identificado unico"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Atualizado com sucesso."
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados invalidos"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Entrega não encontrada"
            )
    })
    @PatchMapping("/{id}/status")
    public ResponseEntity<EntregaResponseDTO> atualizarStatus(Long id, EntregaStatusDTO dto) {
        return ResponseEntity.ok(entregaService.atualizarStatus(id, dto));
    }

    // POST /api/entregas/{id}/historico -> 201 Created ou 404 Not Found
    @Operation(
            summary = "Gera um historico",
            description = "Gera um historico de uma entrega"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Historico gerado com sucesso!"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Entrega não encontrada com id fornecido"
            )
    })
    @PostMapping("/{id}/historico")
    public ResponseEntity<HistoricoResponseDTO> adicionarHistorico(@PathVariable Long id, @Valid @RequestBody HistoricoRequestDTO dto) {
        return ResponseEntity.ok(entregaService.adicionarHistorico(id, dto));
    }
}
