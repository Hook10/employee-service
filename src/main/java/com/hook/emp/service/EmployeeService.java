package com.hook.emp.service;

import com.hook.emp.dto.APIResponseDto;
import com.hook.emp.dto.EmployeeDto;

public interface EmployeeService {

  EmployeeDto saveEmployee(EmployeeDto employeeDto);

  APIResponseDto getEmployeeById(Long employeeId);

}
