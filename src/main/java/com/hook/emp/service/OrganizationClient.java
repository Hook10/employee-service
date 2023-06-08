package com.hook.emp.service;

import com.hook.emp.dto.OrganizationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ORGANIZATION-SERVICE")
public interface OrganizationClient {

  @GetMapping("api/organizations/{code}")
  ResponseEntity<OrganizationDto> getOrganizationByCode(@PathVariable("code") String code);

}
