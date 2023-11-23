package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDto addUser(UserDto userDto) {
		// TODO Auto-generated method stub
	
	User user=UserMapper.mapToUser(userDto);
	User savedUser=userRepository.save(user);
	
	UserDto userDto2=UserMapper.mapToUserDto(savedUser);
		return userDto2;
	}

	@Override
	public UserDto getUserById(Long id) {
		// TODO Auto-generated method stub
		
		User user=userRepository.findById(id).get();
		UserDto userDto=UserMapper.mapToUserDto(user);
		return userDto;
	}

	@Override
	public List<UserDto> getAllUsers() {
		// TODO Auto-generated method stub
		
		List<User> users=userRepository.findAll();
		
		return users.stream().map(UserMapper::mapToUserDto).collect(Collectors.toList());
	}

	@Override
	public UserDto updateUser(UserDto user) {
		// TODO Auto-generated method stub
		
		
		User existingUser=userRepository.findById(user.getId()).get();
		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		existingUser.setEmail(user.getEmail());
		User updatedUser=userRepository.save(existingUser);
		
		return UserMapper.mapToUserDto(updatedUser);
	}

	@Override
	public String deleteUser(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
