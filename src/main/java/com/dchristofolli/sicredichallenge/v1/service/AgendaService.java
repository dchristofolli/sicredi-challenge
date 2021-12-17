package com.dchristofolli.sicredichallenge.v1.service;

import com.dchristofolli.sicredichallenge.domain.model.AgendaEntity;
import com.dchristofolli.sicredichallenge.domain.repository.AgendaRepository;
import com.dchristofolli.sicredichallenge.v1.dto.agenda.AgendaRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static com.dchristofolli.sicredichallenge.v1.mapper.AgendaMapper.mapAgendaToEntity;

@Service
@AllArgsConstructor
public class AgendaService {
    private final AgendaRepository agendaRepository;
    public AgendaEntity save(AgendaRequest agendaRequest) {
        return agendaRepository.save(mapAgendaToEntity(agendaRequest));
    }
}
