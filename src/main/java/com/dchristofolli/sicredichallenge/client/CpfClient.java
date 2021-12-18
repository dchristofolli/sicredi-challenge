package com.dchristofolli.sicredichallenge.client;

import lombok.Generated;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@Generated
@FeignClient(value = "cpf", url = "https://user-info.herokuapp.com/users/")
public interface CpfClient {

  @GetMapping(value = "/{cpf}")
  String cpfChecking(@PathVariable String cpf);
}
