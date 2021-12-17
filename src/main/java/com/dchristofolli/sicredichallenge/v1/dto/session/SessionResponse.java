package com.dchristofolli.sicredichallenge.v1.dto.session;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@lombok.Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Builder
public class SessionResponse {

  @Schema(description = "Identificador da sessão que foi iniciada", example = "5edd42536877d815f01a0a4a")
  private String sessionId;
  private Long secondsRemaining;
}
