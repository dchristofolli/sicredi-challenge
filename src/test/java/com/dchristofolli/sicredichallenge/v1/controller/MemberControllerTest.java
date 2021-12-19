package com.dchristofolli.sicredichallenge.v1.controller;

import com.dchristofolli.sicredichallenge.Stub;
import com.dchristofolli.sicredichallenge.domain.repository.AgendaRepository;
import com.dchristofolli.sicredichallenge.domain.repository.SessionRepository;
import com.dchristofolli.sicredichallenge.v1.facade.AssemblyFacade;
import com.dchristofolli.sicredichallenge.v1.service.AgendaService;
import com.dchristofolli.sicredichallenge.v1.service.SessionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
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

  private final MediaType APPLICATION_JSON_UTF8 = new MediaType(
      MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(),
      StandardCharsets.UTF_8);
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
    BDDMockito.when(assemblyFacade.findAllAgendas())
        .thenReturn(Stub.agendaListResponseStub());
    BDDMockito.when(assemblyFacade.vote(Stub.voteModelStub()))
        .thenReturn(Stub.voteModelStub());
    BDDMockito.when(assemblyFacade.sessionResult("123456"))
        .thenReturn(Stub.sessionResultStub());
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

  @Test
  void shouldFindAllAgendasWhenOk() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/v1/member/agendas"))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.jsonPath("$.list[0].id")
            .value("123456"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.list[0].subject")
            .value("Assunto"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.quantity")
            .value(1))
        .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  void shouldVoteWhenOk() throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
    ObjectWriter writer = mapper.writer().withDefaultPrettyPrinter();
    String request = writer.writeValueAsString(Stub.voteModelStub());
    mockMvc.perform(MockMvcRequestBuilders.post("/v1/member/votes")
            .contentType(APPLICATION_JSON_UTF8)
            .content(request))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.jsonPath("$.sessionId")
            .value("123456"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.cpf")
            .value("01234567891"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.option")
            .value("N"))
        .andExpect(MockMvcResultMatchers.status().isCreated());
  }

  @Test
  void shouldGetSessionResultWhenOk() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/v1/member/session/{sessionId}/results",
                "123456")
            .contentType(APPLICATION_JSON_UTF8))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.jsonPath("$.sessionId")
            .value("123456"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.agendaId")
            .value("1234"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.against")
            .value(3L))
        .andExpect(MockMvcResultMatchers.jsonPath("$.favor")
            .value(4L))
        .andExpect(MockMvcResultMatchers.jsonPath("$.total")
            .value(7))
        .andExpect(MockMvcResultMatchers.status().isOk());
  }
}