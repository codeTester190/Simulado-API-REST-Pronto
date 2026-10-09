package com.api.logistica.exceptions;

/** Lançada quando uma regra de negócio é violada. Vira HTTP 400. */
public class RegraDeNegocioException extends RuntimeException {
    public RegraDeNegocioException(String message) {
        super(message);
    }
}
