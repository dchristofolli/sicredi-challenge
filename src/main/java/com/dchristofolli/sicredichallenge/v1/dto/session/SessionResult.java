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
public class SessionResult {

  @Schema(description = "Identificador da sessão", example = "5edd42536877d815f01a0a4a")
  String sessionId;

  @Schema(description = "Identificador da pauta", example = "5edd42536877d815f01a0a4a")
  String agendaId;

  @Schema(description = "Quantidade de votos a favor", example = "5")
  Long favor;

  @Schema(description = "Quantidade de votos contra", example = "0")
  Long against;

  @Schema(description = "Total de votos", example = "5")
  Integer total;
}
