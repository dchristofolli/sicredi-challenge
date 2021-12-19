package com.dchristofolli.sicredichallenge.v1.dto.session;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@lombok.Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Builder
public class SessionRequest {

  @Schema(description = "Identificador da pauta que será votada na sessão", example = "5edd42536877d815f01a0a4a")
  @NotBlank(message = "ID deve ser preenchido")
  private String agendaId;

  @Schema(description = "Duração da sessão que será iniciada", example = "2")
  private Long minutesRemaining;
}
