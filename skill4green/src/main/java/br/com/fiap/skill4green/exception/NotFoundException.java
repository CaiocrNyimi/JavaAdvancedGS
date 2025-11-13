package br.com.fiap.skill4green.exception;

public class NotFoundException extends RuntimeException {
  public NotFoundException(String messageKey) {
    super(messageKey);
  }
}