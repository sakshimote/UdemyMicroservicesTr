package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.exception.EmailAlreadyExistsException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public UserDto addUser(UserDto userDto) {
		// TODO Auto-generated method stub
	
//	User user=UserMapper.mapToUser(userDto);
		User user=modelMapper.map(userDto, User.class);
		
		
		Optional<User> optionalUser=userRepository.findByEmail(userDto.getEmail());
		
		if(optionalUser.isPresent()) {
			throw new EmailAlreadyExistsException("Email already exists for user");
		}
	User savedUser=userRepository.save(user);
	
	UserDto userDto2=modelMapper.map(savedUser, UserDto.class);
		return userDto2;
	}

	@Override
	public UserDto getUserById(Long id) {
		// TODO Auto-generated method stub
		
		User user=userRepository.findById(id).orElseThrow(
				()->new ResourceNotFoundException("user", "id", id));
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
		
		
		User existingUser=userRepository.findById(user.getId()).orElseThrow(
				()->new ResourceNotFoundException("user", "id", user.getId()));
		
		
		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		existingUser.setEmail(user.getEmail());
		User updatedUser=userRepository.save(existingUser);
		
		return UserMapper.mapToUserDto(updatedUser);
	}

	@Override
	public String deleteUser(Long id) {
		  User existingUser = userRepository.findById(id).orElseThrow(
	                () -> new ResourceNotFoundException("User", "id", id)
	        );

	        userRepository.deleteById(id);
	        return "user deleted successfully!!";
	}
	
	

}
