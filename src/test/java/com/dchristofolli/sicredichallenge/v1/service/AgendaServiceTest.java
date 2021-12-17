package com.dchristofolli.sicredichallenge.v1.service;

import com.dchristofolli.sicredichallenge.Stub;
import com.dchristofolli.sicredichallenge.domain.model.AgendaEntity;
import com.dchristofolli.sicredichallenge.domain.repository.AgendaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class AgendaServiceTest {
    @InjectMocks
    AgendaService agendaService;
    @Mock
    private AgendaRepository agendaRepository;

    @BeforeEach
    void setUp() {
        BDDMockito.when(agendaRepository.save(BDDMockito.any(AgendaEntity.class)))
            .thenReturn(Stub.agendaEntityStub());
    }

    @Test
    void shouldSave() {
        when(agendaRepository.save(BDDMockito.any(AgendaEntity.class)))
            .thenReturn(Stub.agendaEntityStub());
        AgendaEntity entity = agendaService.save(Stub.agendaRequestStub());
        assertEquals(Stub.agendaEntityStub(), entity);
    }
    @Test
    void shouldExistsById() {
        when(agendaRepository.existsById("123456")).thenReturn(true);
        boolean existsById = agendaService.existsById("123456");
        assertTrue(existsById);
    }
}