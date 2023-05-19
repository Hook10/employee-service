package com.hook.emp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {

  private Long id;

  @NotEmpty(message = "First name should not be empty.")
  @Size(min = 2, message = "First name should have at least 2 characters.")
  private String firstName;

  @NotEmpty(message = "Last name should not be empty")
  @Size(min = 2, message = "Last name should have at least 2 characters.")
  private String lastName;

  @NotEmpty(message = "Email should not be empty. ")
  @Email(message = "Email should have proper structure. Example: username@gmail.com ")
  private String email;

}
