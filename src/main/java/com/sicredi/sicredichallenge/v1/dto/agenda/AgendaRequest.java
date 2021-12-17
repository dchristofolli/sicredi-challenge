package com.sicredi.sicredichallenge.v1.dto.agenda;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AgendaRequest {

  @Schema(description = "Assunto da pauta", example = "Mais reuniões", required = true)
  @NotBlank(message = "O campo deve ser preenchido")
  @Size(min = 5, max = 32, message = "Deve ter entre 5 e 32 caracteres")
  String subject;

  @Schema(description = "Descrição da pauta", example = "Devemos fazer mais reuniões?", required = true)
  @NotBlank(message = "O campo deve ser preenchido")
  @Size(min = 16, message = "Deve ter pelo menos 16 caracteres")
  String description;
}
