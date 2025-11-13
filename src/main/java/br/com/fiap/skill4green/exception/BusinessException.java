package br.com.fiap.skill4green.exception;

public class BusinessException extends RuntimeException {
  public BusinessException(String messageKey) {
    super(messageKey);
  }
}