package com.hook.emp.controller;

import com.hook.emp.dto.APIResponseDto;
import com.hook.emp.dto.EmployeeDto;
import com.hook.emp.service.EmployeeService;
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

@RestController
@RequestMapping("/api/employees")
@AllArgsConstructor
public class EmployeeController {

  private final EmployeeService employeeService;

  // Build save employee rest api
  @PostMapping
  public ResponseEntity<EmployeeDto> saveEmployee(@Valid @RequestBody EmployeeDto employeeDto) {
    EmployeeDto savedEmployee = employeeService.saveEmployee(employeeDto);
    return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
  }

  @GetMapping("{id}")
  public ResponseEntity<APIResponseDto> getEmployee(@PathVariable("id") Long employeeId) {
    APIResponseDto apiResponse = employeeService.getEmployeeById(employeeId);
    return new ResponseEntity<>(apiResponse, HttpStatus.OK);
  }

}
