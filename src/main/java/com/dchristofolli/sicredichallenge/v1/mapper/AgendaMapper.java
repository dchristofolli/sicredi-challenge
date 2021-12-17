package com.dchristofolli.sicredichallenge.v1.mapper;

import com.dchristofolli.sicredichallenge.domain.model.AgendaEntity;
import com.dchristofolli.sicredichallenge.v1.dto.agenda.AgendaRequest;
import com.dchristofolli.sicredichallenge.v1.dto.agenda.AgendaResponse;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Generated;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Generated
public class AgendaMapper {
    public static AgendaEntity mapAgendaToEntity(AgendaRequest agendaRequest) {
        return AgendaEntity.builder()
            .subject(agendaRequest.getSubject())
            .description(agendaRequest.getDescription())
            .build();
    }
    public static AgendaResponse mapEntityToResponse(AgendaEntity agendaEntity) {
        return AgendaResponse.builder()
            .id(agendaEntity.getId())
            .subject(agendaEntity.getSubject())
            .build();
    }
}
