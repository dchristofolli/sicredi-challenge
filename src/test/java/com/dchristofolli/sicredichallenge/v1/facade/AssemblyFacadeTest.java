package com.dchristofolli.sicredichallenge.v1.facade;

import com.dchristofolli.sicredichallenge.Stub;
import com.dchristofolli.sicredichallenge.domain.model.SessionEntity;
import com.dchristofolli.sicredichallenge.exception.InactiveSessionException;
import com.dchristofolli.sicredichallenge.exception.UnableToVoteException;
import com.dchristofolli.sicredichallenge.exception.UserAlreadyVotedException;
import com.dchristofolli.sicredichallenge.v1.dto.session.SessionListResponse;
import com.dchristofolli.sicredichallenge.v1.dto.session.SessionResponse;
import com.dchristofolli.sicredichallenge.v1.dto.session.SessionResult;
import com.dchristofolli.sicredichallenge.v1.dto.vote.VoteModel;
import com.dchristofolli.sicredichallenge.v1.service.AgendaService;
import com.dchristofolli.sicredichallenge.v1.service.CpfService;
import com.dchristofolli.sicredichallenge.v1.service.SessionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Instant;
import java.util.Collections;

import static com.dchristofolli.sicredichallenge.Stub.agendaEntityStub;
import static com.dchristofolli.sicredichallenge.Stub.agendaRequestStub;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class AssemblyFacadeTest {
    @Mock
    private AgendaService agendaService;
    @Mock
    private SessionService sessionService;
    @Mock
    private CpfService cpfService;
    @InjectMocks
    private AssemblyFacade assemblyFacade;

    @Test
    void createAgenda() {
        when(agendaService.save(agendaRequestStub())).thenReturn(agendaEntityStub());
        assemblyFacade.createAgenda(agendaRequestStub());
        assertEquals(agendaEntityStub(), agendaService.save(agendaRequestStub()));
    }

    @Test
    void shouldCreateVotingSession() {
        BDDMockito.when(agendaService.existsById(BDDMockito.anyString())).thenReturn(true);
        BDDMockito.when(sessionService.createVotingSession(BDDMockito.any(SessionEntity.class)))
            .thenReturn(Stub.sessionEntityStub());
        SessionResponse votingSession = assemblyFacade.createVotingSession(Stub.sessionRequestStub());
        assertEquals(Stub.sessionResponseStub(), votingSession);
    }

    @Test
    void findAllOpenSessions() {
        when(sessionService.findAllOpenSessions()).thenReturn(Collections.singletonList(
            SessionEntity.builder().sessionId("1").sessionCloseTime(Instant.now().plusSeconds(60L))
                .build()));
        assertEquals(SessionListResponse.builder().list(Collections.singletonList(
                SessionResponse.builder().sessionId("1").secondsRemaining(59L).build())).quantity(1)
            .build(), assemblyFacade.findAllOpenSessions());
    }

    @Test
    void shouldVote() {
        VoteModel voteModel = VoteModel.builder().sessionId("1").cpf("01063682061").option("S").build();
        when(sessionService.sessionIsActive("1")).thenReturn(true);
        when(sessionService.alreadyVotedOnThisSession(voteModel)).thenReturn(false);
        when(cpfService.cpfIsUnableToVote("01063682061")).thenReturn(false);
        assemblyFacade.vote(voteModel);
        assertNull(assemblyFacade.vote(voteModel));
    }
    @Test
    void shouldNotVoteWhenSessionIsNotActive() {
        VoteModel voteModel = VoteModel.builder().sessionId("1").cpf("01063682061").option("S").build();
        when(sessionService.sessionIsActive("1")).thenReturn(false);
        when(sessionService.alreadyVotedOnThisSession(voteModel)).thenReturn(true);
        assertThrows(InactiveSessionException.class, () -> assemblyFacade.vote(voteModel));
    }

    @Test
    void shouldNotVoteWhenUserAlreadyVoted() {
        VoteModel voteModel = VoteModel.builder().sessionId("1").cpf("01063682061").option("S").build();
        when(sessionService.sessionIsActive("1")).thenReturn(true);
        when(sessionService.alreadyVotedOnThisSession(voteModel)).thenReturn(true);
        when(cpfService.cpfIsUnableToVote("01063682061")).thenReturn(false);
        assertThrows(UserAlreadyVotedException.class, () -> assemblyFacade.vote(voteModel));
    }

    @Test
    void shouldNotVoteWhenCpfUnableToVote() {
        VoteModel voteModel = VoteModel.builder().sessionId("1").cpf("01063682061").option("S").build();
        when(sessionService.sessionIsActive("1")).thenReturn(true);
        when(sessionService.alreadyVotedOnThisSession(voteModel)).thenReturn(false);
        when(cpfService.cpfIsUnableToVote("01063682061")).thenReturn(true);
        assertThrows(UnableToVoteException.class, () -> assemblyFacade.vote(voteModel));
    }
    @Test
    void shouldGetSessionResultWhenOk() {
        SessionResult result = SessionResult.builder().total(1).favor(1L).build();
        when(sessionService.sessionIsActive("1")).thenReturn(false);
        when(sessionService.checkSessionResult("1")).thenReturn(result);
        assemblyFacade.sessionResult("1");
        assertEquals(result, assemblyFacade.sessionResult("1"));
    }
}