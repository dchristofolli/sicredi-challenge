package com.dchristofolli.sicredichallenge.v1.service;

import com.dchristofolli.sicredichallenge.domain.model.SessionEntity;
import com.dchristofolli.sicredichallenge.domain.repository.SessionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.dchristofolli.sicredichallenge.Stub.sessionEntityStub;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class SessionServiceTest {
    @Mock
    SessionRepository sessionRepository;
    @InjectMocks
    SessionService sessionService;

    @Test
    void createVotingSession() {
        when(sessionRepository.save(BDDMockito.any(SessionEntity.class)))
            .thenReturn(sessionEntityStub());
        SessionEntity votingSession = sessionService.createVotingSession(sessionEntityStub());
        assertEquals(sessionEntityStub().getSessionId(), votingSession.getSessionId());
        assertEquals(sessionEntityStub().getAgendaId(), votingSession.getAgendaId());
        assertEquals(sessionEntityStub().getMessageAlreadySent(), votingSession.getMessageAlreadySent());
        assertEquals(sessionEntityStub().getCpfAlreadyVoted(), votingSession.getCpfAlreadyVoted());
    }
}