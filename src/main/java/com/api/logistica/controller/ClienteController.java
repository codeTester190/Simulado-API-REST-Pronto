package com.api.logistica.controller;

import com.api.logistica.dto.cliente.ClienteRequestDTO;
import com.api.logistica.dto.cliente.ClienteResponseDTO;
import com.api.logistica.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * Rota base: /api/clientes
 *
 * TODO: anotar a classe, injetar ClienteService (construtor) e implementar os métodos.
 */
@Tag(
        name = "Cliente",
        description = "Endpoints relacionados a clientes, tais como: listar, buscar por id, buscar por documento, cadastrar e deletar"
)
@RestController
@RequestMapping("/v1/api/cliente")
public class ClienteController {

    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    // GET /api/clientes -> 200 OK
    @Operation(
            summary = "Listar todos os clientes",
            description = "Exibe todos os clientes cadastrados"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Clientes listados com sucesso!"
    )
    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    // GET /api/clientes/{id} -> 200 OK ou 404 Not Found
    @Operation(
            summary = "Buscar cliente por id",
            description = "Realizar a busca para verificar se encontra o cliente pelo id ou não"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Cliente encontrado com sucesso!"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Cliente não encontrado"
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    // GET /api/clientes/busca?documento=... -> 200 OK ou 404 Not Found

    @Operation(
            summary = "Busca cliente pelo documento (CPF ou CNPJ)",
            description = "Realiza a busca para verificar se encontra o cliente pelo documento ou não"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Cliente encontrado sucesso!"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Cliente não encontrado"
            )
    })
    @GetMapping("/busca")
    public ResponseEntity<ClienteResponseDTO> buscarPorDocumento(@RequestParam String documento) {
        return ResponseEntity.ok(service.buscarPorDocumento(documento));
    }

    // POST /api/clientes -> 201 Created (com header Location) ou 400 Bad Request
    @Operation(
            summary = "cadastro de cliente",
            description = "Realiza o cadastro de cliente"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Cliente cadastrado com sucesso"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados invalidos"
            )
    })
    @PostMapping
    public ResponseEntity<ClienteResponseDTO> cadastrar(@Valid @RequestBody ClienteRequestDTO dto) {

        ClienteResponseDTO cliente = service.cadastrar(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.id()).toUri();

        return ResponseEntity.created(uri).body(cliente);
    }

    // DELETE /api/clientes/{id} -> 204 No Content, 400 Bad Request ou 404 Not Found
    @Operation(
            summary = "Deletar cliente",
            description = "Deletar cliente com base no id fornecido pelo usuario"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Cliente deletado com sucesso!"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Id fornecido invalido"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Cliente não encontrado"
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
