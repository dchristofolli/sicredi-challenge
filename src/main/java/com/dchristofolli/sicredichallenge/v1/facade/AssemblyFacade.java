package com.dchristofolli.sicredichallenge.v1.facade;

import com.dchristofolli.sicredichallenge.exception.AgendaNotFoundException;
import com.dchristofolli.sicredichallenge.v1.dto.agenda.AgendaRequest;
import com.dchristofolli.sicredichallenge.v1.dto.agenda.AgendaResponse;
import com.dchristofolli.sicredichallenge.v1.dto.session.SessionRequest;
import com.dchristofolli.sicredichallenge.v1.dto.session.SessionResponse;
import com.dchristofolli.sicredichallenge.v1.service.AgendaService;
import com.dchristofolli.sicredichallenge.v1.service.SessionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import static com.dchristofolli.sicredichallenge.v1.mapper.AgendaMapper.mapEntityToResponse;
import static com.dchristofolli.sicredichallenge.v1.mapper.SessionMapper.mapEntityToModel;
import static com.dchristofolli.sicredichallenge.v1.mapper.SessionMapper.mapModelToEntity;

@Component
@AllArgsConstructor
public class AssemblyFacade {
    private final AgendaService agendaService;
    private final SessionService sessionService;

    public AgendaResponse createAgenda(AgendaRequest agendaRequest) {
        return mapEntityToResponse(agendaService.save(agendaRequest));
    }

    public SessionResponse createVotingSession(SessionRequest sessionRequest) {
        if (!agendaService.existsById(sessionRequest.getAgendaId())) {
            throw new AgendaNotFoundException("Agenda not found", HttpStatus.NOT_FOUND);
        }
        if (sessionRequest.getMinutesRemaining().equals(0L)
            || sessionRequest.getMinutesRemaining() == null) {
            sessionRequest.setMinutesRemaining(1L);
        }
        return mapEntityToModel(sessionService.createVotingSession(mapModelToEntity(sessionRequest)));
    }
}
