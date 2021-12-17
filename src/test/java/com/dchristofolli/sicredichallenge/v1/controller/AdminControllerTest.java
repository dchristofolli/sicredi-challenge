package com.dchristofolli.sicredichallenge.v1.controller;

import com.dchristofolli.sicredichallenge.Stub;
import com.dchristofolli.sicredichallenge.domain.model.AgendaEntity;
import com.dchristofolli.sicredichallenge.domain.repository.AgendaRepository;
import com.dchristofolli.sicredichallenge.v1.facade.AssemblyFacade;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
    AdminController.class,
    AssemblyFacade.class
})
@WebMvcTest
@AutoConfigureMockMvc
class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AssemblyFacade assemblyFacade;
    @MockBean
    private AgendaRepository agendaRepository;


    private final MediaType APPLICATION_JSON_UTF8 = new MediaType(
        MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(),
        StandardCharsets.UTF_8);

    @BeforeEach
    void setUp() {
        BDDMockito.when(agendaRepository.save(BDDMockito.any(AgendaEntity.class)))
            .thenReturn(Stub.agendaEntityStub());
    }

    @Test
    void shouldCreateAgenda() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter writer = mapper.writer().withDefaultPrettyPrinter();
        String request = writer.writeValueAsString(Stub.agendaEntityStub());
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/admin/agenda")
                .contentType(APPLICATION_JSON_UTF8)
                .content(request))
            .andDo(print())
            .andExpect(MockMvcResultMatchers.status().isCreated());
    }
    @Test
    void shouldCreateSession() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter writer = mapper.writer().withDefaultPrettyPrinter();
        String request = writer.writeValueAsString(Stub.sessionRequestStub());
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/admin/session")
                .contentType(APPLICATION_JSON_UTF8)
                .content(request))
            .andDo(print())
            .andExpect(MockMvcResultMatchers.status().isCreated());
    }
}