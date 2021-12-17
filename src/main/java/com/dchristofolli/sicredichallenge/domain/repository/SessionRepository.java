package com.dchristofolli.sicredichallenge.domain.repository;

import com.dchristofolli.sicredichallenge.domain.model.SessionEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends MongoRepository<SessionEntity, String> {

}
