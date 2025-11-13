package br.com.fiap.skill4green.exception;

import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.*;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

  private final MessageSource messageSource;

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<Map<String, String>> handleNotFound(NotFoundException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("erro", localize(ex.getMessage())));
  }

  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<Map<String, String>> handleBusiness(BusinessException ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("erro", localize(ex.getMessage())));
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, String>> handleValidation(MethodArgumentNotValidException ex) {
    Map<String, String> erros = new HashMap<>();
    ex.getBindingResult().getFieldErrors().forEach(e ->
      erros.put(e.getField(), localize(e.getDefaultMessage()))
    );
    return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(erros);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<Map<String, String>> handleConstraint(ConstraintViolationException ex) {
    Map<String, String> erros = new HashMap<>();
    ex.getConstraintViolations().forEach(v ->
      erros.put("campo", localize(v.getMessage()))
    );
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Map<String, String>> handleGeneric(Exception ex, WebRequest request) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
      .body(Map.of("erro", "Erro interno: " + ex.getMessage()));
  }

  private String localize(String key) {
    return messageSource.getMessage(key, null, key, LocaleContextHolder.getLocale());
  }
}