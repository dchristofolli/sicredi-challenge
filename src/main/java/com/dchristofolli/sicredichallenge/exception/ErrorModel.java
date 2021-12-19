package com.dchristofolli.sicredichallenge.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
@AllArgsConstructor
@lombok.Data
@JsonInclude(JsonInclude.Include.NON_NULL)
class ErrorModel {

  @Schema(description = "Mensagem que será exibida no corpo da resposta, em caso de erro",
      example = "Invalid form")
  String message;

  @Schema(description = "Classe da exceção que foi chamada",
      example = "org.springframework.web.bind.MethodArgumentNotValidException")
  String error;

  @Schema(description = "Status http da resposta",
      example = "BAD_REQUEST")
  HttpStatus status;

  @Schema(description = "Status http da resposta",
      example = "\"formErrors\": {\n" +
          "    \"description\": \"O campo deve ser preenchido\"\n" +
          "  }")
  Map<String, String> formErrors;
}
