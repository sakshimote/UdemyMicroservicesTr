package com.example.demo.dto;

import com.example.demo.entity.User;

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
	private String firstName;
	private String lastName;
	private String email;
}
