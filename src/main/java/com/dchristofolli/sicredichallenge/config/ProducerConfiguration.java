package com.dchristofolli.sicredichallenge.config;

import java.util.HashMap;
import java.util.Map;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.record.CompressionType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

@Configuration
@EnableKafka
@NoArgsConstructor
@Generated
public class ProducerConfiguration {

  @Value("${spring.kafka.bootstrap-servers}")
  private String bootstrapServer;

  @Bean
  public Map<String, Object> producerConfigs() {
    final Integer RETRIES = 3;
    final Integer MAX_REQUESTS = 1;
    Map<String, Object> properties = new HashMap<>();
    properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
    properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
    properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
    properties.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, false);
    properties.put(ProducerConfig.RETRIES_CONFIG, RETRIES);
    properties.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, MAX_REQUESTS);
    properties.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, CompressionType.GZIP.name);
    return properties;
  }

  @Bean
  public ProducerFactory<String, Object> producerFactory() {
    return new DefaultKafkaProducerFactory<>(producerConfigs());
  }

  @Bean
  public KafkaTemplate<String, Object> kafkaTemplate() {
    return new KafkaTemplate<>(producerFactory());
  }

  @Bean
  public String string() {
    return "";
  }
}
