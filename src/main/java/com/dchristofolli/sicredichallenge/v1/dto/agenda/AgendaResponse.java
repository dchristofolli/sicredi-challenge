package com.dchristofolli.sicredichallenge.v1.dto.agenda;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@lombok.Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Builder
public class AgendaResponse {

  @Schema(description = "Identificador da pauta", example = "5edd42536877d815f01a0a4a")
  String id;

  @Schema(description = "Assunto da pauta", example = "Mais reuniões")
  String subject;
}
