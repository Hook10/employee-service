package com.hook.emp.mappers;

import com.hook.emp.dto.EmployeeDto;
import com.hook.emp.entity.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

  EmployeeDto toDto(Employee employee);

  Employee toEntity(EmployeeDto employeeDto);

}
