package com.dchristofolli.sicredichallenge.v1.facade;

import com.dchristofolli.sicredichallenge.exception.AgendaNotFoundException;
import com.dchristofolli.sicredichallenge.exception.InactiveSessionException;
import com.dchristofolli.sicredichallenge.exception.UnableToVoteException;
import com.dchristofolli.sicredichallenge.exception.UserAlreadyVotedException;
import com.dchristofolli.sicredichallenge.v1.dto.agenda.AgendaListResponse;
import com.dchristofolli.sicredichallenge.v1.dto.agenda.AgendaRequest;
import com.dchristofolli.sicredichallenge.v1.dto.agenda.AgendaResponse;
import com.dchristofolli.sicredichallenge.v1.dto.session.SessionListResponse;
import com.dchristofolli.sicredichallenge.v1.dto.session.SessionRequest;
import com.dchristofolli.sicredichallenge.v1.dto.session.SessionResponse;
import com.dchristofolli.sicredichallenge.v1.dto.vote.VoteModel;
import com.dchristofolli.sicredichallenge.v1.mapper.SessionMapper;
import com.dchristofolli.sicredichallenge.v1.service.AgendaService;
import com.dchristofolli.sicredichallenge.v1.service.CpfService;
import com.dchristofolli.sicredichallenge.v1.service.SessionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import static com.dchristofolli.sicredichallenge.v1.mapper.AgendaMapper.mapEntityToResponse;
import static com.dchristofolli.sicredichallenge.v1.mapper.AgendaMapper.mapToAgendaList;
import static com.dchristofolli.sicredichallenge.v1.mapper.SessionMapper.mapEntityToModel;
import static com.dchristofolli.sicredichallenge.v1.mapper.SessionMapper.mapModelToEntity;

@Component
@AllArgsConstructor
public class AssemblyFacade {
    private final AgendaService agendaService;
    private final SessionService sessionService;
    private final CpfService cpfService;

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

    public SessionListResponse findAllOpenSessions() {
        return SessionMapper.mapToSessionList(sessionService.findAllOpenSessions());
    }

    public AgendaListResponse findAllAgendas() {
        return mapToAgendaList(agendaService.findAll());
    }

    public VoteModel vote(VoteModel voteModel) {
        if (Boolean.FALSE.equals(sessionService.sessionIsActive(voteModel.getSessionId()))) {
            throw new InactiveSessionException("Session is not active", HttpStatus.BAD_REQUEST);
        }
        if (cpfService.cpfIsUnableToVote(voteModel.getCpf())) {
            throw new UnableToVoteException("Unable to vote", HttpStatus.UNAUTHORIZED);
        }
        if (Boolean.TRUE.equals(sessionService.alreadyVotedOnThisSession(voteModel))) {
            throw new UserAlreadyVotedException("User already voted on this session", HttpStatus.FORBIDDEN);
        }
        return sessionService.vote(voteModel);
    }
}
