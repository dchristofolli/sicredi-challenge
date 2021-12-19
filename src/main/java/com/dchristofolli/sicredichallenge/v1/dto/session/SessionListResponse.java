package com.dchristofolli.sicredichallenge.v1.dto.session;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SessionListResponse {

  @Schema(description = "Sessões encontradas")
  private List<SessionResponse> list;
  @Schema(description = "Total de reuniões encontradas", example = "1")
  private Integer quantity;
}
