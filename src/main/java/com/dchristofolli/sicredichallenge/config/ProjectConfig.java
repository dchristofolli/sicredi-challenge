package com.dchristofolli.sicredichallenge.config;

import com.dchristofolli.sicredichallenge.client.CpfClient;
import com.dchristofolli.sicredichallenge.client.CpfClientImpl;
import lombok.Generated;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Generated
public class ProjectConfig {
    @Bean
    public CpfClient cpfClient(){
        return new CpfClientImpl();
    }
}
