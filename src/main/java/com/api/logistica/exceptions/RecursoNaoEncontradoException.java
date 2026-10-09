package com.api.logistica.exceptions;

/** Lançada quando um recurso (cliente, pedido, motorista, entrega) não existe. Vira HTTP 404. */
public class RecursoNaoEncontradoException extends RuntimeException {
    public RecursoNaoEncontradoException(String message) {
        super(message);
    }
}
