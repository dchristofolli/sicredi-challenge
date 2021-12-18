package com.dchristofolli.sicredichallenge.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class InactiveSessionException extends RuntimeException {

  private final HttpStatus status;

  public InactiveSessionException(String message, HttpStatus status) {
    super(message);
    this.status = status;
  }
}
