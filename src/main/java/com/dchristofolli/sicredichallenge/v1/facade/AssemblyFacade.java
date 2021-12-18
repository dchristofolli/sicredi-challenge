package com.dchristofolli.sicredichallenge.v1.facade;

import com.dchristofolli.sicredichallenge.exception.*;
import com.dchristofolli.sicredichallenge.v1.dto.agenda.AgendaListResponse;
import com.dchristofolli.sicredichallenge.v1.dto.agenda.AgendaRequest;
import com.dchristofolli.sicredichallenge.v1.dto.agenda.AgendaResponse;
import com.dchristofolli.sicredichallenge.v1.dto.session.SessionListResponse;
import com.dchristofolli.sicredichallenge.v1.dto.session.SessionRequest;
import com.dchristofolli.sicredichallenge.v1.dto.session.SessionResponse;
import com.dchristofolli.sicredichallenge.v1.dto.session.SessionResult;
import com.dchristofolli.sicredichallenge.v1.dto.vote.VoteModel;
import com.dchristofolli.sicredichallenge.v1.mapper.SessionMapper;
import com.dchristofolli.sicredichallenge.v1.service.AgendaService;
import com.dchristofolli.sicredichallenge.v1.service.CpfService;
import com.dchristofolli.sicredichallenge.v1.service.KafkaService;
import com.dchristofolli.sicredichallenge.v1.service.SessionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static com.dchristofolli.sicredichallenge.v1.mapper.AgendaMapper.mapEntityToResponse;
import static com.dchristofolli.sicredichallenge.v1.mapper.AgendaMapper.mapToAgendaList;
import static com.dchristofolli.sicredichallenge.v1.mapper.SessionMapper.mapEntityToModel;
import static com.dchristofolli.sicredichallenge.v1.mapper.SessionMapper.mapModelToEntity;

@Component
@AllArgsConstructor
@EnableScheduling
public class AssemblyFacade {
    private final AgendaService agendaService;
    private final SessionService sessionService;
    private final CpfService cpfService;
    private final KafkaService kafkaService;

    public AgendaResponse createAgenda(AgendaRequest agendaRequest) {
        return mapEntityToResponse(agendaService.save(agendaRequest));
    }

    public SessionResponse createVotingSession(SessionRequest sessionRequest) {
        if (!agendaService.existsById(sessionRequest.getAgendaId())) {
            throw new AgendaNotFoundException("Agenda not found", HttpStatus.NOT_FOUND);
        }
        if (sessionRequest.getMinutesRemaining() == null || sessionRequest.getMinutesRemaining().equals(0L)) {
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

    public SessionResult sessionResult(String sessionId) {
        if (Boolean.TRUE.equals(sessionService.sessionIsActive(sessionId))) {
            throw new SessionIsStillActiveException("The session is still active", HttpStatus.FORBIDDEN);
        }
        return sessionService.checkSessionResult(sessionId);
    }

    @Scheduled(fixedDelay = 1000)
    public void sendResults() {
        sessionService.getSessionResultsForTopic()
            .parallelStream()
            .filter(result ->
                Objects.equals(sessionService.findSessionById(result.getSessionId())
                    .getMessageAlreadySent(), "N"))
            .map(kafkaService::makeRecord)
            .forEach(kafkaService::send);
        sessionService.findAllClosedSessions()
            .forEach(sessionService::setMessageAlreadySent);
    }
}
