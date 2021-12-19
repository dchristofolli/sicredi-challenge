package com.dchristofolli.sicredichallenge;

import com.dchristofolli.sicredichallenge.domain.model.AgendaEntity;
import com.dchristofolli.sicredichallenge.domain.model.SessionEntity;
import com.dchristofolli.sicredichallenge.v1.dto.agenda.AgendaListResponse;
import com.dchristofolli.sicredichallenge.v1.dto.agenda.AgendaRequest;
import com.dchristofolli.sicredichallenge.v1.dto.agenda.AgendaResponse;
import com.dchristofolli.sicredichallenge.v1.dto.session.SessionListResponse;
import com.dchristofolli.sicredichallenge.v1.dto.session.SessionRequest;
import com.dchristofolli.sicredichallenge.v1.dto.session.SessionResponse;
import com.dchristofolli.sicredichallenge.v1.dto.session.SessionResult;
import com.dchristofolli.sicredichallenge.v1.dto.vote.VoteModel;
import java.time.Instant;
import java.util.Collections;

public class Stub {

  public static AgendaRequest agendaRequestStub() {
    return AgendaRequest.builder()
        .subject("Assunto")
        .description("Descrição")
        .build();
  }

  public static AgendaEntity agendaEntityStub() {
    return AgendaEntity.builder()
        .id("123456")
        .subject("Assunto")
        .description("Descrição")
        .build();
  }

  public static SessionEntity sessionEntityStub() {
    return SessionEntity.builder()
        .sessionId("123456")
        .agendaId("123")
        .sessionCloseTime(Instant.now().plusSeconds(60L))
        .cpfAlreadyVoted(Collections.singletonList("01063682061"))
        .votes(Collections.singletonList("S"))
        .build();
  }

  public static SessionRequest sessionRequestStub() {
    return SessionRequest.builder()
        .agendaId("1")
        .minutesRemaining(10L)
        .build();
  }

  public static SessionResponse sessionResponseStub() {
    return SessionResponse.builder()
        .sessionId("123456")
        .secondsRemaining(59L)
        .build();
  }

  public static SessionListResponse sessionListResponseStub() {
    return SessionListResponse.builder()
        .list(Collections.singletonList(sessionResponseStub()))
        .quantity(1)
        .build();
  }

  public static AgendaResponse agendaResponseStub() {
    return AgendaResponse.builder()
        .id("123456")
        .subject("Assunto")
        .build();
  }

  public static AgendaListResponse agendaListResponseStub() {
    return AgendaListResponse.builder()
        .list(Collections.singletonList(agendaResponseStub()))
        .quantity(1)
        .build();
  }

  public static VoteModel voteModelStub() {
    return VoteModel.builder()
        .sessionId("123456")
        .cpf("01234567891")
        .option("N")
        .build();
  }

  public static SessionResult sessionResultStub() {
    return SessionResult.builder()
        .sessionId("123456")
        .agendaId("1234")
        .against(3L)
        .favor(4L)
        .total(7)
        .build();
  }
}
