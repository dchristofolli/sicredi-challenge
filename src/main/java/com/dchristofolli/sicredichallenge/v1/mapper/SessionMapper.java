package com.dchristofolli.sicredichallenge.v1.mapper;

import com.dchristofolli.sicredichallenge.domain.model.SessionEntity;
import com.dchristofolli.sicredichallenge.v1.dto.session.SessionRequest;
import com.dchristofolli.sicredichallenge.v1.dto.session.SessionResponse;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.util.Collections;

import static java.time.Instant.now;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
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
}
