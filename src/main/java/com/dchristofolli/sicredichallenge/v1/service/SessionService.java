package com.dchristofolli.sicredichallenge.v1.service;

import com.dchristofolli.sicredichallenge.domain.model.SessionEntity;
import com.dchristofolli.sicredichallenge.domain.repository.SessionRepository;
import com.dchristofolli.sicredichallenge.exception.SessionNotFoundException;
import com.dchristofolli.sicredichallenge.v1.dto.session.SessionResult;
import com.dchristofolli.sicredichallenge.v1.dto.vote.VoteModel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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

    public Boolean sessionIsActive(String sessionId) {
        return findSessionById(sessionId).getSessionCloseTime().isAfter(Instant.now());
    }

    public SessionEntity findSessionById(String sessionId) {
        return sessionRepository.findById(sessionId)
            .orElseThrow(() -> new SessionNotFoundException("Session not found", HttpStatus.NOT_FOUND));
    }

    public Boolean alreadyVotedOnThisSession(VoteModel voteModel) {
        return findSessionById(voteModel.getSessionId()).getCpfAlreadyVoted()
            .contains(voteModel.getCpf());
    }

    public VoteModel vote(VoteModel voteModel) {
        SessionEntity sessionEntity = findSessionById(voteModel.getSessionId());
        List<String> votes = sessionEntity.getVotes();
        votes.add(voteModel.getOption().toUpperCase());
        List<String> cpf = sessionEntity.getCpfAlreadyVoted();
        cpf.add(voteModel.getCpf());
        sessionEntity.setCpfAlreadyVoted(cpf);
        sessionEntity.setVotes(votes);
        sessionRepository.save(sessionEntity);
        return voteModel;
    }

    public SessionResult checkSessionResult(String sessionId) {
        String agendaId = findSessionById(sessionId).getAgendaId();
        List<String> votes = findSessionById(sessionId).getVotes();
        return SessionResult.builder()
            .sessionId(sessionId)
            .agendaId(agendaId)
            .favor(votes.parallelStream().filter(v -> v.equals("S")).count())
            .against(votes.parallelStream().filter(v -> v.equals("N")).count())
            .total(votes.size())
            .build();
    }
}
