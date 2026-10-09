# 🚚 Logística API — Versão PROVA

Projeto de treino para a prova de **API REST com Spring Boot**. Tudo já está pronto (entity, dto, mapper, repository, service, exceptions),
**exceto os controllers**, que só têm a assinatura dos métodos, e a configuração do Swagger.

## ✅ O que você deve fazer

1. **Anotar** cada controller (`@RestController`, `@RequestMapping`, `@Tag`...).
2. **Injetar o service** pelo construtor.
3. **Implementar** cada método: `@GetMapping`, `@PostMapping`, `@PatchMapping`, `@DeleteMapping`.
4. Receber **`@PathVariable`**, **`@RequestParam`** e **`@RequestBody`** com **`@Valid`**.
5. Retornar o **status correto** (`ok`, `created` com **`Location`**, `noContent`).
6. Criar a classe **`OpenApiConfig`** (pacote `config`) e documentar com **`@Operation`**, **`@ApiResponses`**, **`@Parameter`**.

> Sem anotações, os controllers nem viram *beans* — por isso o Swagger abre vazio até você implementar.

## ▶️ Como executar

Requisito: **JDK 21**.

```bash
./mvnw spring-boot:run        # Linux/macOS
mvnw.cmd spring-boot:run      # Windows
```

- API: `http://localhost:9010`
- Swagger UI: `http://localhost:9010/swagger-ui/index.html`

## 🧱 Estrutura

```
src/main/java/com/api/logistica
├── controller/   # camada HTTP
├── dto/          # records de entrada/saída (com Bean Validation) e ErroResponse
├── entity/       # entidades + enums (StatusPedido, StatusEntrega)
├── exceptions/   # RecursoNaoEncontradoException (404), RegraDeNegocioException (400), GlobalExceptionHandler
├── mapper/       # Entity <-> DTO
├── repository/   # persistência em memória (List + AtomicLong)
└── service/      # regras de negócio
```

## 📡 Endpoints a implementar

| Método | Endpoint | Entrada | Status esperado | Descrição |
|---|---|---|---|---|
| GET | `/api/clientes` | — | 200 | Lista todos os clientes |
| GET | `/api/clientes/{id}` | Path `{id}` | 200 / 404 | Busca cliente por ID |
| GET | `/api/clientes/busca?documento=...` | Query `documento` | 200 / 404 | Localiza cliente por CPF ou CNPJ |
| POST | `/api/clientes` | Body `ClienteRequestDTO` | 201 (+`Location`) / 400 | Cadastra cliente |
| DELETE | `/api/clientes/{id}` | Path `{id}` | 204 / 400 / 404 | Remove cliente sem pedidos vinculados |
| POST | `/api/motoristas` | Body `MotoristaRequestDTO` | 201 (+`Location`) / 400 | Cadastra motorista |
| GET | `/api/motoristas` | — | 200 | Lista motoristas |
| POST | `/api/pedidos` | Body `PedidoRequestDTO` | 201 (+`Location`) / 400 | Registra pedido `PENDENTE` |
| PATCH | `/api/pedidos/{id}/cancelar` | Path `{id}` | 200 / 404 | Cancela o pedido |
| POST | `/api/entregas` | Body `EntregaRequestDTO` | 201 (+`Location`) / 400 / 404 | Gera entrega `EM_ROTA` |
| PATCH | `/api/entregas/{id}/status` | Path `{id}` + Body `EntregaStatusDTO` | 200 / 400 / 404 | Atualiza status (`ENTREGUE` / `ATRASADA`) |
| POST | `/api/entregas/{id}/historico` | Path `{id}` + Body `HistoricoRequestDTO` | 201 / 404 | Adiciona evento ao histórico |
| GET | `/api/relatorios/entregas-por-motorista` | — | 200 | Total de entregas por motorista |


## 📐 Regras já implementadas nos services

- **Cliente:** CPF/CNPJ duplicado → 400; `DELETE` de cliente inexistente → 404, com pedidos → 400.
- **Motorista:** CNH duplicada → 400.
- **Pedido:** cliente inexistente → 404; nasce `PENDENTE`; cancelar pedido já cancelado ou com entrega → 400.
- **Entrega:** pedido/motorista inexistentes → 404; pedido cancelado ou que já tem entrega → 400; nasce `EM_ROTA`.
- **Status da entrega:** só `ENTREGUE`/`ATRASADA`; permitido `EM_ROTA → ENTREGUE/ATRASADA` e `ATRASADA → ENTREGUE`; o resto → 400.
- **Erros** seguem o formato `{ status, erro, mensagem, caminho, timestamp }` (via `GlobalExceptionHandler`).
- Dados em memória: **reiniciar a aplicação apaga tudo**.


## 🧪 JSONs para testar

**Cliente** `POST /api/clientes`
```json
{ "nome": "Maria Silva", "cpfCnpj": "12345678900", "endereco": "Rua das Flores, 123", "cidade": "Joinville", "estado": "SC" }
```
**Motorista** `POST /api/motoristas`
```json
{ "nome": "João Pereira", "cnh": "12345678901", "veiculo": "Caminhão Baú", "cidadeBase": "Joinville" }
```
**Pedido** `POST /api/pedidos`
```json
{ "clienteId": 1, "volumeM3": 2.5, "pesoKg": 150.0 }
```
**Entrega** `POST /api/entregas`
```json
{ "pedidoId": 1, "motoristaId": 1 }
```
**Status** `PATCH /api/entregas/1/status`
```json
{ "status": "ENTREGUE" }
```
**Histórico** `POST /api/entregas/1/historico`
```json
{ "descricao": "Saiu do centro de distribuição" }
```

Ordem sugerida para testar: cliente → motorista → pedido → entrega → status → histórico → relatório.

## 📊 Checklist de correção

| Item | Pontos |
|---|---|
| Rotas exatamente como a tabela (sem verbos) | 1,5 |
| Injeção do service por construtor | 0,5 |
| `@PathVariable`, `@RequestParam`, `@RequestBody` corretos | 1,5 |
| `@Valid` nos corpos | 1,0 |
| Status HTTP corretos (200, 201, 204) | 1,5 |
| `Location` correto nos 201 | 1,0 |
| `OpenApiConfig` | 1,0 |
| `@Tag`, `@Operation`, `@ApiResponses`, `@Parameter` | 2,0 |
| **Total** | **10,0** |
