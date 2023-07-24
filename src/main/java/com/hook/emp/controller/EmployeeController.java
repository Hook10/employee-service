package com.hook.emp.controller;

import com.hook.emp.dto.APIResponseDto;
import com.hook.emp.dto.EmployeeDto;
import com.hook.emp.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(
        name = "Employee Service - EmployeeController",
        description = "EmployeeController Exposes REST APIs for Employee Service"
)
@RestController
@RequestMapping("/api/employees")
@AllArgsConstructor
public class EmployeeController {

  private final EmployeeService employeeService;

  @Operation(
          summary = "Save Employee REST API",
          description = "Save Employee REST API is used to save employee object into the database"
  )
  @ApiResponse(
          responseCode = "201",
          description = "HTTP Status 201 Created"
  )
  // Build save employee rest api
  @PostMapping
  public ResponseEntity<EmployeeDto> saveEmployee(@Valid @RequestBody EmployeeDto employeeDto) {
    EmployeeDto savedEmployee = employeeService.saveEmployee(employeeDto);
    return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
  }

  @Operation(
          summary = "Get Employee REST API",
          description = "Get Employee REST API is used to get an employee object from the database"
  )
  @ApiResponse(
          responseCode = "200",
          description = "HTTP Status 200 OK"
  )
  @GetMapping("{id}")
  public ResponseEntity<APIResponseDto> getEmployee(@PathVariable("id") Long employeeId) {
    APIResponseDto apiResponse = employeeService.getEmployeeById(employeeId);
    return new ResponseEntity<>(apiResponse, HttpStatus.OK);
  }

}
