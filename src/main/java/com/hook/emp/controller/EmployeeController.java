package com.hook.emp.controller;

import com.hook.emp.dto.EmployeeDto;
import com.hook.emp.service.EmployeeService;
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
@RequestMapping("api/employees")
@AllArgsConstructor
public class EmployeeController {

  private final EmployeeService employeeService;

  // Build save employee rest api
  @PostMapping
  public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto) {
    EmployeeDto savedEmployee = employeeService.saveEmployee(employeeDto);
    return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
  }

  @GetMapping("{id}")
  public ResponseEntity<EmployeeDto> getEmployee(@PathVariable("id") Long employeeId) {
    EmployeeDto employeeDto = employeeService.getEmployeeById(employeeId);
    return new ResponseEntity<>(employeeDto, HttpStatus.OK);
  }

}
