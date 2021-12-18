package com.dchristofolli.sicredichallenge.v1.service;

import com.dchristofolli.sicredichallenge.client.CpfClient;
import com.dchristofolli.sicredichallenge.domain.model.CpfCheckingModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
class CpfServiceTest {

    @InjectMocks
    private CpfService cpfService;
    @Mock
    private CpfClient cpfClient;

    @Test
    void testCpfIsAbleToVote() {
        BDDMockito.when(cpfClient.cpfChecking("44291675089"))
            .thenReturn(new CpfCheckingModel("ABLE_TO_VOTE"));
        boolean unableToVote = cpfService.cpfIsUnableToVote("44291675089");
        assertFalse(unableToVote);
    }

    @Test
    void testCpfIsUnableToVote() {
        BDDMockito.when(cpfClient.cpfChecking("11111111111"))
            .thenReturn(new CpfCheckingModel("UNABLE_TO_VOTE"));
        boolean unableToVote = cpfService.cpfIsUnableToVote("11111111111");
        assertTrue(unableToVote);
    }
}