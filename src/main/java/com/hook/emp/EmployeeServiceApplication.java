package com.hook.emp;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@OpenAPIDefinition(
        info = @Info(
                title = "Employee Service REST APIs",
                description = "Employee Service REST APIs Documentation",
                version = "v1.0",
                contact = @Contact(
                        name = "Hook Ten",
                        email = "some@gmail.com",
                        url = "someurl.com"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "someurl.net/license"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "Employee Service Doc",
                url = "someurl.com/employee_service.html"
        )
)
@SpringBootApplication
@EnableFeignClients
public class EmployeeServiceApplication {

//  @Bean
//  public RestTemplate restTemplate() {
//    return new RestTemplate();
//  }
//
//  @Bean
//  public WebClient webClient() {
//    return WebClient.builder().build();
//  }

  public static void main(String[] args) {
    SpringApplication.run(EmployeeServiceApplication.class, args);
  }

}
