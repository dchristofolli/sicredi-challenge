package com.dchristofolli.sicredichallenge.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SessionIsStillActiveException extends RuntimeException {

  private final HttpStatus status;

  public SessionIsStillActiveException(String message, HttpStatus status) {
    super(message);
    this.status = status;
  }
}
