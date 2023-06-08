package com.hook.emp.service.impl;

import com.hook.emp.dto.APIResponseDto;
import com.hook.emp.dto.DepartmentDto;
import com.hook.emp.dto.EmployeeDto;
import com.hook.emp.dto.OrganizationDto;
import com.hook.emp.entity.Employee;
import com.hook.emp.exception.ResourceNotFoundException;
import com.hook.emp.mappers.EmployeeMapper;
import com.hook.emp.repository.EmployeeRepository;
import com.hook.emp.service.DepartmentClient;
import com.hook.emp.service.EmployeeService;
import com.hook.emp.service.OrganizationClient;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Log4j2
public class EmployeeServiceImpl implements EmployeeService {

  private final EmployeeRepository employeeRepository;
  private final EmployeeMapper employeeMapper;
  //  private final RestTemplate restTemplate;
//  private final WebClient webClient;
  private final DepartmentClient departmentClient;
  private final OrganizationClient organizationClient;

  @Override
  public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
    Employee employee = employeeMapper.toEntity(employeeDto);
    Employee savedEmployee = employeeRepository.save(employee);

    return employeeMapper.toDto(savedEmployee);
  }

  //  @CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
  @Retry(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
  @Override
  public APIResponseDto getEmployeeById(Long employeeId) {
    Employee employee = employeeRepository.findById(employeeId).orElseThrow(
        () -> new ResourceNotFoundException("Employee", "id", employeeId)
    );
    log.info("Inside getEmployee by id method");
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

    ResponseEntity<DepartmentDto> responseEntity = departmentClient.getDepartment(
        employee.getDepartmentCode());
    DepartmentDto departmentDto = responseEntity.getBody();

    ResponseEntity<OrganizationDto> organizationResponse = organizationClient.getOrganizationByCode(
        employee.getOrganizationCode());
    OrganizationDto organizationDto = organizationResponse.getBody();

    APIResponseDto apiResponseDto = new APIResponseDto();
    apiResponseDto.setDepartmentDto(departmentDto);
    apiResponseDto.setEmployeeDto(employeeDto);
    apiResponseDto.setOrganizationDto(organizationDto);
    return apiResponseDto;
  }

  public APIResponseDto getDefaultDepartment(Long employeeId, Exception exception) {
    log.info("Inside getDefaultDepartment method");
    Employee employee = employeeRepository.findById(employeeId).orElseThrow(
        () -> new ResourceNotFoundException("Employee", "id", employeeId)
    );
    EmployeeDto employeeDto = employeeMapper.toDto(employee);
    DepartmentDto departmentDto = new DepartmentDto();
    departmentDto.setDepartmentName("R&D Department");
    departmentDto.setDepartmentCode("RD001");
    departmentDto.setDepartmentDescription("Research and Development Department");

    APIResponseDto apiResponseDto = new APIResponseDto();
    apiResponseDto.setDepartmentDto(departmentDto);
    apiResponseDto.setEmployeeDto(employeeDto);
    return apiResponseDto;
  }
}
