package com.api.logistica.controller;

import com.api.logistica.dto.pedido.PedidoRequestDTO;
import com.api.logistica.dto.pedido.PedidoResponseDTO;
import com.api.logistica.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

/**
 * Rota base: /api/pedidos
 *
 * TODO: anotar a classe, injetar PedidoService (construtor) e implementar os métodos.
 */
@Tag(
        name = "Pedido",
        description = "Endpoints relacionados a pedido, tais como: cadastrar, cancelar pedido"
)
@RestController
@RequestMapping("/v1/api/pedido")
public class PedidoController {

    private final PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    // POST /api/pedidos -> 201 Created (com header Location) ou 400 Bad Request
    @Operation(
            summary = "Cadastrar pedido",
            description = "Método relacionado ao cadastro de pedidos"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Pedido cadastrado com sucesso!"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados invalidos"
            )
    })
    @PostMapping
    public ResponseEntity<PedidoResponseDTO> cadastrar(@Valid @RequestBody PedidoRequestDTO dto) {
        PedidoResponseDTO pedido = service.cadastrar(dto);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(pedido.id())
                .toUri();

        return ResponseEntity.created(uri).body(pedido);
    }

    // PATCH /api/pedidos/{id}/cancelar -> 200 OK ou 404 Not Found
    @Operation(
            summary = "Cancelar pedido",
            description = "Alterar status de um pedido já criado"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Pedido atualizado com sucesso!"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Pedido não encontrado com o ID informado."
            )
    })
    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<PedidoResponseDTO> cancelar(@PathVariable Long id) {
        return ResponseEntity.ok(service.cancelar(id));
    }
}
