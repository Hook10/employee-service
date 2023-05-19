package com.hook.emp.service.impl;

import com.hook.emp.dto.APIResponseDto;
import com.hook.emp.dto.DepartmentDto;
import com.hook.emp.dto.EmployeeDto;
import com.hook.emp.entity.Employee;
import com.hook.emp.exception.ResourceNotFoundException;
import com.hook.emp.mappers.EmployeeMapper;
import com.hook.emp.repository.EmployeeRepository;
import com.hook.emp.service.APIClient;
import com.hook.emp.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

  private final EmployeeRepository employeeRepository;
  private final EmployeeMapper employeeMapper;
//  private final RestTemplate restTemplate;
//  private final WebClient webClient;
  private final APIClient apiClient;

  @Override
  public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
    Employee employee = employeeMapper.toEntity(employeeDto);
    Employee savedEmployee = employeeRepository.save(employee);

    return employeeMapper.toDto(savedEmployee);
  }

  @Override
  public APIResponseDto getEmployeeById(Long employeeId) {
    Employee employee = employeeRepository.findById(employeeId).orElseThrow(
        () -> new ResourceNotFoundException("Employee", "id", employeeId)
    );
    EmployeeDto employeeDto = employeeMapper.toDto(employee);

//    ResponseEntity<DepartmentDto> departmentDtoResponseEntity = restTemplate.getForEntity(
//        "http://localhost:8080/api/departments/" + employee.getDepartmentCode(),
//        DepartmentDto.class);
//    DepartmentDto departmentDto = departmentDtoResponseEntity.getBody();

//    DepartmentDto departmentDto = webClient.get()
//        .uri("http://localhost:8080/api/departments/" + employee.getDepartmentCode())
//        .retrieve()
//        .bodyToMono(DepartmentDto.class)
//        .block();

    ResponseEntity<DepartmentDto> responseEntity = apiClient.getDepartment(
        employee.getDepartmentCode());
    DepartmentDto departmentDto = responseEntity.getBody();
    APIResponseDto apiResponseDto = new APIResponseDto();
    apiResponseDto.setDepartmentDto(departmentDto);
    apiResponseDto.setEmployeeDto(employeeDto);
    return apiResponseDto;
  }
}
