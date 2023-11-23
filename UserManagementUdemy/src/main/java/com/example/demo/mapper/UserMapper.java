package com.example.demo.mapper;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;

public class UserMapper {
	
	public static UserDto mapToUserDto(User user) {
		UserDto userDto=new UserDto(
				user.getId(),
				user.getFirstName(),
				user.getLastName(),
				user.getEmail());
		
		return userDto;
		
	}
	
	public static User mapToUser(UserDto userDto) {
		User user=new User(
				userDto.getId(),
				userDto.getFirstName(),
				userDto.getLastName(),
				userDto.getEmail());
		return user;
	}

}
