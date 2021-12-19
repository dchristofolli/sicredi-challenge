package com.dchristofolli.sicredichallenge;

import lombok.Generated;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@Generated
@EnableFeignClients
public class SicrediChallengeApplication {

  public static void main(String[] args) {
    SpringApplication.run(SicrediChallengeApplication.class, args);
  }
}
