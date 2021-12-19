package com.dchristofolli.sicredichallenge.v1.mapper;

import static java.time.Instant.now;

import com.dchristofolli.sicredichallenge.domain.model.SessionEntity;
import com.dchristofolli.sicredichallenge.v1.dto.session.SessionListResponse;
import com.dchristofolli.sicredichallenge.v1.dto.session.SessionRequest;
import com.dchristofolli.sicredichallenge.v1.dto.session.SessionResponse;
import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.Generated;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Generated
public class SessionMapper {

  public static SessionEntity mapModelToEntity(SessionRequest sessionRequest) {
    return SessionEntity.builder()
        .agendaId(sessionRequest.getAgendaId())
        .sessionCloseTime(now().plusSeconds(sessionRequest.getMinutesRemaining() * 60))
        .cpfAlreadyVoted(Collections.emptyList())
        .votes(Collections.emptyList())
        .build();
  }

  public static SessionResponse mapEntityToModel(SessionEntity sessionEntity) {
    return SessionResponse.builder()
        .sessionId(sessionEntity.getSessionId())
        .secondsRemaining(Duration.between(now(), sessionEntity.getSessionCloseTime()).toSeconds())
        .build();
  }

  public static SessionListResponse mapToSessionList(List<SessionEntity> sessionEntityList) {
    List<SessionResponse> sessionResponses = sessionEntityList
        .parallelStream()
        .map(s -> SessionResponse.builder()
            .sessionId(s.getSessionId())
            .secondsRemaining(Duration.between(now(), s.getSessionCloseTime()).toSeconds())
            .build()).collect(Collectors.toList());
    return SessionListResponse.builder()
        .list(sessionResponses)
        .quantity(sessionResponses.size())
        .build();
  }
}
