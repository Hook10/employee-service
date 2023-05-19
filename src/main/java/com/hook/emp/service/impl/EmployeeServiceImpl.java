package com.hook.emp.service.impl;

import com.hook.emp.dto.EmployeeDto;
import com.hook.emp.entity.Employee;
import com.hook.emp.mappers.EmployeeMapper;
import com.hook.emp.repository.EmployeeRepository;
import com.hook.emp.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

  private final EmployeeRepository employeeRepository;
  private final EmployeeMapper employeeMapper;

  @Override
  public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
    Employee employee = employeeMapper.toEntity(employeeDto);
    Employee savedEmployee = employeeRepository.save(employee);

    return employeeMapper.toDto(savedEmployee);
  }

  @Override
  public EmployeeDto getEmployeeById(Long employeeId) {
    Employee employee = employeeRepository.findById(employeeId).get();

    return employeeMapper.toDto(employee);
  }
}
