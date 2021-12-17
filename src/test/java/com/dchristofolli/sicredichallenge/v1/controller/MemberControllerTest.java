package com.dchristofolli.sicredichallenge.v1.controller;

import com.dchristofolli.sicredichallenge.Stub;
import com.dchristofolli.sicredichallenge.domain.repository.AgendaRepository;
import com.dchristofolli.sicredichallenge.domain.repository.SessionRepository;
import com.dchristofolli.sicredichallenge.v1.facade.AssemblyFacade;
import com.dchristofolli.sicredichallenge.v1.service.AgendaService;
import com.dchristofolli.sicredichallenge.v1.service.SessionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
    MemberController.class,
    AssemblyFacade.class,
    AgendaService.class,
    SessionService.class
})
@WebMvcTest
@AutoConfigureMockMvc
class MemberControllerTest {
    @InjectMocks
    private MemberController memberController;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AssemblyFacade assemblyFacade;
    @MockBean
    private AgendaRepository agendaRepository;
    @MockBean
    private SessionRepository sessionRepository;

    @BeforeEach
    void setUp() {
        BDDMockito.when(assemblyFacade.findAllOpenSessions())
            .thenReturn(Stub.sessionListResponseStub());
    }

    @Test
    void shouldFindAllOpenSessionsWhenOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/member/session/open-sessions"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.jsonPath("$.list[0].sessionId")
                .value("123456"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.quantity")
                .value(1))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }
}