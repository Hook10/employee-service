package com.hook.emp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(
        description = "EmployeeDto Model Information"
)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {

  private Long id;

  @Schema(description = "Employee First Name")
  @NotEmpty(message = "First name should not be empty.")
  @Size(min = 2, message = "First name should have at least 2 characters.")
  private String firstName;

  @Schema(description = "Employee Last Name")
  @NotEmpty(message = "Last name should not be empty")
  @Size(min = 2, message = "Last name should have at least 2 characters.")
  private String lastName;

  @Schema(description = "Employee Email Address")
  @NotEmpty(message = "Email should not be empty. ")
  @Email(message = "Email should have proper structure. Example: username@gmail.com ")
  private String email;

  @Schema(description = "Employee's Department Code")
  private String departmentCode;

  @Schema(description = "Employee's Organization Code")
  private String organizationCode;


}
