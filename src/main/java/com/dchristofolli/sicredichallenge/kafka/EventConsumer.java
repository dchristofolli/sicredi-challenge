package com.dchristofolli.sicredichallenge.kafka;

import java.util.concurrent.CountDownLatch;
import lombok.Data;
import lombok.Generated;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;

@Slf4j
@Data
@Generated
public class EventConsumer {

  private final CountDownLatch countDownLatch = new CountDownLatch(1);

  @KafkaListener(topics = "${session-result.kafka.topic}")
  public String consumer(String payload) {
    log.info("Received voting session result: {}", payload);
    countDownLatch.countDown();
    return payload;
  }
}
