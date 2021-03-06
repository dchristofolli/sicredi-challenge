package com.dchristofolli.sicredichallenge.v1.controller;

import com.dchristofolli.sicredichallenge.v1.dto.agenda.AgendaRequest;
import com.dchristofolli.sicredichallenge.v1.dto.agenda.AgendaResponse;
import com.dchristofolli.sicredichallenge.v1.dto.session.SessionRequest;
import com.dchristofolli.sicredichallenge.v1.dto.session.SessionResponse;
import com.dchristofolli.sicredichallenge.v1.facade.AssemblyFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/v1/admin")
public class AdminController {

  private final AssemblyFacade assemblyFacade;

  @ResponseStatus(HttpStatus.CREATED)
  @Operation(description = "Creates a new agenda")
  @ApiResponse(responseCode = "201", description = "Agenda successfully registered")
  @ApiResponse(responseCode = "400", description = "Bad request")
  @ApiResponse(responseCode = "500", description = "An error occurred on the server")

  @PostMapping("/agenda")
  public AgendaResponse createAgenda(@Valid @RequestBody AgendaRequest agendaRequest) {
    return assemblyFacade.createAgenda(agendaRequest);
  }

  @ResponseStatus(HttpStatus.CREATED)
  @Operation(description = "Creates a voting session, receiving the agenda id and the duration of the session.")
  @ApiResponse(responseCode = "201", description = "Open session")
  @ApiResponse(responseCode = "400", description = "Bad request")
  @ApiResponse(responseCode = "500", description = "Bad server")
  @PostMapping("/session")
  public SessionResponse createSession(@RequestBody SessionRequest sessionRequest) {
    return assemblyFacade.createVotingSession(sessionRequest);
  }
}
