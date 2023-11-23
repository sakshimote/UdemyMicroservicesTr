package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;

public interface UserService {

	UserDto addUser(UserDto userDto);
	UserDto getUserById(Long id );
	List<UserDto> getAllUsers();
	UserDto updateUser(UserDto userDto);
	String deleteUser(Long id);

}
