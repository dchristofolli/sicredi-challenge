package com.sicredi.sicredichallenge.v1.controller;

import com.sicredi.sicredichallenge.v1.dto.agenda.AgendaRequest;
import com.sicredi.sicredichallenge.v1.dto.agenda.AgendaResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/v1/admin")
public class AdminController {
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(description = "Creates a new agenda")
    @ApiResponse(responseCode = "201", description = "Agenda successfully registered")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "500", description = "An error occurred on the server")

    @PostMapping("/agenda")
    public AgendaResponse createAgenda(@Valid @RequestBody AgendaRequest agendaRequest) {
        return null;
    }
}
