package com.dchristofolli.sicredichallenge.v1.service;

import com.dchristofolli.sicredichallenge.domain.model.SessionEntity;
import com.dchristofolli.sicredichallenge.domain.repository.SessionRepository;
import com.dchristofolli.sicredichallenge.v1.dto.session.SessionResult;
import com.dchristofolli.sicredichallenge.v1.dto.vote.VoteModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.dchristofolli.sicredichallenge.Stub.sessionEntityStub;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
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

    @Test
    void sessionIsActiveWhenOk() {
        SessionEntity sessionEntity = sessionEntityStub();
        sessionEntity.setSessionCloseTime(Instant.now().plusSeconds(10));
        given(sessionRepository.save(sessionEntity)).willReturn(sessionEntity);
        given(sessionRepository.findById("123456")).willReturn(Optional.of(sessionEntity));
        sessionService.createVotingSession(sessionEntity);
        assertTrue(sessionService.sessionIsActive("123456"));
    }

    @Test
    void shouldFindAllOpenSessions() {
        SessionEntity sessionEntity = SessionEntity.builder()
            .sessionCloseTime(Instant.now().plusSeconds(60)).build();
        given(sessionRepository.findAll()).willReturn(Collections.singletonList(sessionEntity));
        sessionService.findAllOpenSessions();
        assertEquals(Collections.singletonList(sessionEntity), sessionService.findAllOpenSessions());
    }
    @Test
    void shouldVoteWhenOk() {
        List<String> votesList = new ArrayList<>();
        votesList.add("S");
        List<String> cpfList = new ArrayList<>();
        cpfList.add("85337392069");
        SessionEntity session = SessionEntity.builder()
            .sessionId("123456")
            .agendaId("1")
            .sessionCloseTime(Instant.now().plusSeconds(60))
            .votes(votesList)
            .cpfAlreadyVoted(cpfList)
            .build();
        VoteModel vote = VoteModel.builder()
            .sessionId("123456")
            .cpf("93011201005")
            .option("N")
            .build();
        cpfList.add(vote.getCpf());
        votesList.add(vote.getOption());
        session.setCpfAlreadyVoted(cpfList);
        session.setVotes(votesList);
        when(sessionRepository.findById("123456")).thenReturn(Optional.of(session));
        sessionService.vote(vote);
        assertEquals(vote, sessionService.vote(vote));
    }

    @Test
    void alreadyVotedOnThisSession() {
        SessionEntity session = sessionEntityStub();
        VoteModel vote = VoteModel.builder()
            .sessionId("123456")
            .cpf("01063682061")
            .option("N")
            .build();
        when(sessionRepository.findById("123456")).thenReturn(Optional.of(session));
        sessionRepository.findById("123456");
        assertEquals(true, sessionService.alreadyVotedOnThisSession(vote));
    }

    @Test
    void checkSessionResult() {
        SessionEntity session = sessionEntityStub();
        SessionResult result = SessionResult.builder()
            .sessionId("123456")
            .agendaId("123")
            .favor(1L)
            .against(0L)
            .total(1)
            .build();
        when(sessionRepository.findById("123456")).thenReturn(Optional.of(session));
        sessionService.checkSessionResult("123456");
        assertEquals(result, sessionService.checkSessionResult("123456"));
    }
}