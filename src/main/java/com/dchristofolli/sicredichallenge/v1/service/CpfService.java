package com.dchristofolli.sicredichallenge.v1.service;

import com.dchristofolli.sicredichallenge.client.CpfClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CpfService {
    private final CpfClient cpfClient;

    public boolean cpfIsUnableToVote(String cpf) {
        return cpfClient.cpfChecking(cpf).getStatus().equals("UNABLE_TO_VOTE");
    }
}
