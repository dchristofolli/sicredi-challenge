package com.dchristofolli.sicredichallenge;

import com.dchristofolli.sicredichallenge.domain.model.AgendaEntity;
import com.dchristofolli.sicredichallenge.v1.dto.agenda.AgendaRequest;

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
}
