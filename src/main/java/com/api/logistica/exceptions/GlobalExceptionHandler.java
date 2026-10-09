package com.api.logistica.exceptions;

import com.api.logistica.dto.erro.ErroResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

/**
 * Traduz exceções em respostas HTTP padronizadas (já pronto — não precisa mexer).
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<ErroResponse> naoEncontrado(RecursoNaoEncontradoException ex, HttpServletRequest request) {
        return montar(HttpStatus.NOT_FOUND, "Recurso não encontrado", ex.getMessage(), request);
    }

    @ExceptionHandler(RegraDeNegocioException.class)
    public ResponseEntity<ErroResponse> regraDeNegocio(RegraDeNegocioException ex, HttpServletRequest request) {
        return montar(HttpStatus.BAD_REQUEST, "Regra de negócio violada", ex.getMessage(), request);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResponse> validacao(MethodArgumentNotValidException ex, HttpServletRequest request) {
        String mensagem = ex.getBindingResult().getFieldErrors().stream()
                .map(erro -> erro.getField() + ": " + erro.getDefaultMessage())
                .collect(Collectors.joining("; "));
        return montar(HttpStatus.BAD_REQUEST, "Erro de validação", mensagem, request);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErroResponse> corpoInvalido(HttpMessageNotReadableException ex, HttpServletRequest request) {
        return montar(HttpStatus.BAD_REQUEST, "Corpo da requisição inválido",
                "JSON malformado, ausente ou com valor inválido (ex.: status inexistente)", request);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErroResponse> tipoInvalido(MethodArgumentTypeMismatchException ex, HttpServletRequest request) {
        return montar(HttpStatus.BAD_REQUEST, "Parâmetro inválido",
                "O parâmetro '" + ex.getName() + "' possui um valor inválido", request);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErroResponse> parametroAusente(MissingServletRequestParameterException ex, HttpServletRequest request) {
        return montar(HttpStatus.BAD_REQUEST, "Parâmetro obrigatório ausente",
                "O parâmetro '" + ex.getParameterName() + "' é obrigatório", request);
    }

    private ResponseEntity<ErroResponse> montar(HttpStatus status, String erro, String mensagem, HttpServletRequest request) {
        ErroResponse corpo = new ErroResponse(status.value(), erro, mensagem, request.getRequestURI(), LocalDateTime.now());
        return ResponseEntity.status(status).body(corpo);
    }
}
