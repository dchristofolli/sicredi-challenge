package com.dchristofolli.sicredichallenge.v1.service;

import com.dchristofolli.sicredichallenge.kafka.EventProducer;
import com.dchristofolli.sicredichallenge.v1.dto.session.SessionResult;
import lombok.AllArgsConstructor;
import lombok.Generated;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Generated
public class KafkaService {

  private final EventProducer eventProducer;
  @Value("${session-result.kafka.topic}")
  private final String topic;

  public void send(ProducerRecord<String, Object> producerRecord) {
    eventProducer.send(producerRecord);
  }

  public ProducerRecord<String, Object> makeRecord(SessionResult sessionResult) {
    return new ProducerRecord<>(topic, sessionResult.getSessionId(),
        sessionResult);
  }
}
