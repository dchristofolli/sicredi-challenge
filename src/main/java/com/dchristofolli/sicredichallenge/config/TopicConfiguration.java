package com.dchristofolli.sicredichallenge.config;

import lombok.Generated;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.apache.kafka.common.record.CompressionType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@Generated
public class TopicConfiguration {
  @Value("${session-result.kafka.topic}")
  private String topicName;
  @Bean
  public NewTopic topic(){
    return TopicBuilder
        .name(topicName)
        .partitions(3)
        .replicas(3)
        .config(TopicConfig.COMPRESSION_TYPE_CONFIG, CompressionType.GZIP.name)
        .build();
  }
}
