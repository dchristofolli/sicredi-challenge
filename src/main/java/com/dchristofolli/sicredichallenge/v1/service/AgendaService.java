package com.dchristofolli.sicredichallenge.v1.service;

import static com.dchristofolli.sicredichallenge.v1.mapper.AgendaMapper.mapAgendaToEntity;

import com.dchristofolli.sicredichallenge.domain.model.AgendaEntity;
import com.dchristofolli.sicredichallenge.domain.repository.AgendaRepository;
import com.dchristofolli.sicredichallenge.v1.dto.agenda.AgendaRequest;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AgendaService {

  private final AgendaRepository agendaRepository;

  public AgendaEntity save(AgendaRequest agendaRequest) {
    return agendaRepository.save(mapAgendaToEntity(agendaRequest));
  }

  public boolean existsById(String agendaId) {
    return agendaRepository.existsById(agendaId);
  }

  public List<AgendaEntity> findAll() {
    return agendaRepository.findAll();
  }
}
