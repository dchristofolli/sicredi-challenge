package com.dchristofolli.sicredichallenge.domain.repository;

import com.dchristofolli.sicredichallenge.domain.model.AgendaEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendaRepository extends MongoRepository<AgendaEntity, String> {

}
