package com.dchristofolli.sicredichallenge.client;

import com.dchristofolli.sicredichallenge.domain.model.CpfCheckingModel;
import lombok.Generated;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@Generated
@FeignClient(value = "cpf", url = "https://user-info.herokuapp.com/users/")
public interface CpfClient {
    @GetMapping(value = "/{cpf}")
    CpfCheckingModel cpfChecking(@PathVariable String cpf);
}
