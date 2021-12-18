package com.dchristofolli.sicredichallenge.v1.dto.agenda;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AgendaListResponse {
    @Schema(description = "Pautas para as reuniões")
    private List<AgendaResponse> list;
    @Schema(description = "Total de pautas cadastradas", example = "1")
    private Integer quantity;
}
