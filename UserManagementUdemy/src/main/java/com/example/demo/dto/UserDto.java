package com.example.demo.dto;

import com.example.demo.entity.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	
	
	private Long id;
	
	@NotEmpty(message = "user first name should not be empty")
	private String firstName;
	
	@NotEmpty
	private String lastName;
	
	@NotEmpty
	@Email
	private String email;
}
