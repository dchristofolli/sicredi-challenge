package com.dchristofolli.sicredichallenge.v1.facade;

import com.dchristofolli.sicredichallenge.v1.dto.agenda.AgendaRequest;
import com.dchristofolli.sicredichallenge.v1.dto.agenda.AgendaResponse;
import com.dchristofolli.sicredichallenge.v1.service.AgendaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static com.dchristofolli.sicredichallenge.v1.mapper.AgendaMapper.mapEntityToResponse;

@Component
@AllArgsConstructor
public class AssemblyFacade {
    private final AgendaService agendaService;
    public AgendaResponse createAgenda(AgendaRequest agendaRequest) {
        return mapEntityToResponse(agendaService.save(agendaRequest));
    }
}
