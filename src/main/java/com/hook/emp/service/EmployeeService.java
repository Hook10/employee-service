package com.hook.emp.service;

import com.hook.emp.dto.EmployeeDto;

public interface EmployeeService {

  EmployeeDto saveEmployee(EmployeeDto employeeDto);

  EmployeeDto getEmployeeById(Long employeeId);

}
