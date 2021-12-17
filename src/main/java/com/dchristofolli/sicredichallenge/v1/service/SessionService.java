package com.dchristofolli.sicredichallenge.v1.service;

import com.dchristofolli.sicredichallenge.domain.model.SessionEntity;
import com.dchristofolli.sicredichallenge.domain.repository.SessionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class SessionService {

    private final SessionRepository sessionRepository;

    public SessionEntity createVotingSession(SessionEntity sessionEntity) {
        return sessionRepository.save(sessionEntity);
    }

    public List<SessionEntity> findAllOpenSessions() {
        return sessionRepository.findAll().parallelStream()
            .filter(a -> a.getSessionCloseTime().isAfter(Instant.now()))
            .collect(Collectors.toList());
    }
}
