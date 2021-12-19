package com.dchristofolli.sicredichallenge.v1.controller;

import com.dchristofolli.sicredichallenge.v1.dto.agenda.AgendaListResponse;
import com.dchristofolli.sicredichallenge.v1.dto.session.SessionListResponse;
import com.dchristofolli.sicredichallenge.v1.dto.session.SessionResult;
import com.dchristofolli.sicredichallenge.v1.dto.vote.VoteModel;
import com.dchristofolli.sicredichallenge.v1.facade.AssemblyFacade;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@OpenAPIDefinition(info = @Info(description = "Cooperative Assembly"))
@RequestMapping(path = "/v1/member")
public class MemberController {

  private final AssemblyFacade assemblyFacade;

  @Operation(description = "Find all agendas")
  @ApiResponse(responseCode = "200", description = "Sessions found")
  @ApiResponse(responseCode = "500", description = "Bad server")
  @GetMapping(value = "/agendas", produces = MediaType.APPLICATION_JSON_VALUE)
  public AgendaListResponse findAllAgendas() {
    return assemblyFacade.findAllAgendas();
  }

  @Operation(description = "Find all open sessions")
  @ApiResponse(responseCode = "200", description = "Sessions found")
  @ApiResponse(responseCode = "500", description = "Bad server")
  @GetMapping("/session/open-sessions")
  public SessionListResponse findAllOpenSessions() {
    return assemblyFacade.findAllOpenSessions();
  }

  @ResponseStatus(HttpStatus.CREATED)
  @Operation(description = "Allows a member to vote on an agenda that has an active session")
  @ApiResponse(responseCode = "201", description = "Vote successfully registered")
  @ApiResponse(responseCode = "400", description = "Bad request")
  @ApiResponse(responseCode = "500", description = "An error occurred on the server")
  @PostMapping("/votes")
  public VoteModel vote(@Valid @RequestBody VoteModel voteModel) {
    return assemblyFacade.vote(voteModel);
  }

  @Operation(description = "Displays the result of closed polls")
  @ApiResponse(responseCode = "200", description = "Results found")
  @ApiResponse(responseCode = "400", description = "Session is still open")
  @ApiResponse(responseCode = "404", description = "Not found")
  @ApiResponse(responseCode = "500", description = "Bad server")
  @GetMapping("/session/{sessionId}/results")
  public SessionResult sessionResult(@PathVariable String sessionId) {
    return assemblyFacade.sessionResult(sessionId);
  }
}
