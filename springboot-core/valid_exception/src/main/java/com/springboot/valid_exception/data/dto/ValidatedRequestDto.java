package com.springboot.valid_exception.data.dto;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.springboot.valid_exception.data.group.ValidationGroup1;
import com.springboot.valid_exception.data.group.ValidationGroup2;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ValidatedRequestDto {
  @NotBlank
  private String name;

  @Email
  private String email;

  @Pattern(regexp = "01(?:0|1|[6-9])[.-]?(\\d{4})[.-]?(\\d{4})$")
  private String phoneNumber;

  @Min(value = 20, groups = ValidationGroup1.class)
  @Max(value = 40, groups = ValidationGroup2.class)
  int age;

  @Size(min = 0, max = 40)
  String description;

  @Positive(groups = ValidationGroup2.class)
  int count;

  @AssertTrue
  boolean booleanCheck;
}
