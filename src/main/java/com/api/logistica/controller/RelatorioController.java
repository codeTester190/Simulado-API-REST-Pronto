package com.api.logistica.controller;

import com.api.logistica.dto.relatorio.EntregasPorMotoristaDTO;
import com.api.logistica.service.RelatorioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Rota base: /api/relatorios
 *
 * TODO: anotar a classe, injetar RelatorioService (construtor) e implementar o método.
 */
@Tag(
        name = "Relatorio",
        description = "Endpoints relacionados a entregas por motorista"
)
@RestController
@RequestMapping("/v1/api/relatorio")
public class RelatorioController {

    private final RelatorioService service;

    public RelatorioController(RelatorioService service) {
        this.service = service;
    }

    // GET /api/relatorios/entregas-por-motorista -> 200 OK
    @Operation(
            summary = "Exibe relatorios",
            description = "Associa a cada motorista todas as entregas relacionadas a ele."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Relatorio realizado com sucesso!"
    )
    @GetMapping("/entregas-por-motorista")
    public ResponseEntity<List<EntregasPorMotoristaDTO>> entregasPorMotorista() {
        return ResponseEntity.ok(service.entregasPorMotorista());
    }
}
