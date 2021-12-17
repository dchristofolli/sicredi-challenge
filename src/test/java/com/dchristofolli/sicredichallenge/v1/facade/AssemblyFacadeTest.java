package com.dchristofolli.sicredichallenge.v1.facade;

import com.dchristofolli.sicredichallenge.Stub;
import com.dchristofolli.sicredichallenge.domain.model.SessionEntity;
import com.dchristofolli.sicredichallenge.v1.dto.session.SessionResponse;
import com.dchristofolli.sicredichallenge.v1.service.AgendaService;
import com.dchristofolli.sicredichallenge.v1.service.SessionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.dchristofolli.sicredichallenge.Stub.agendaEntityStub;
import static com.dchristofolli.sicredichallenge.Stub.agendaRequestStub;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class AssemblyFacadeTest {
    @Mock
    AgendaService agendaService;
    @Mock
    SessionService sessionService;
    @InjectMocks
    AssemblyFacade assemblyFacade;

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
}