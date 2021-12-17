package com.dchristofolli.sicredichallenge.v1.controller;

import com.dchristofolli.sicredichallenge.v1.dto.session.SessionListResponse;
import com.dchristofolli.sicredichallenge.v1.facade.AssemblyFacade;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@OpenAPIDefinition(info = @Info(description = "Cooperative Assembly"))
@RequestMapping(path = "/v1/member")
public class MemberController {
    private final AssemblyFacade assemblyFacade;
    @Operation(description = "Find all open sessions")
    @ApiResponse(responseCode = "200", description = "Sessions found")
    @ApiResponse(responseCode = "500", description = "Bad server")
    @GetMapping("/session/open-sessions")
    public SessionListResponse findAllOpenSessions() {
        return assemblyFacade.findAllOpenSessions();
    }
}
