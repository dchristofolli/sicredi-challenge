package com.dchristofolli.sicredichallenge.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.Instant;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "Session")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SessionEntity {

  @JsonIgnore
  @Builder.Default
  String messageAlreadySent = "N";
  @Id
  private String sessionId;
  private String agendaId;
  private List<String> votes;
  private List<String> cpfAlreadyVoted;
  private Instant sessionCloseTime;
}
