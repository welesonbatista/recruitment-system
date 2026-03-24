package br.com.weleson.recruitment_system.exceptions;

public class UserFoundException extends RuntimeException {
  public UserFoundException() {
    super("The user already exists.");
  }
}
