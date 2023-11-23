package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.example.demo.dto.UserDto;
import com.example.demo.exception.ErrorDetails;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.service.UserService;

import ch.qos.logback.core.spi.ErrorCodes;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	@PostMapping("/post")
	public ResponseEntity<UserDto> postUser(@RequestBody UserDto userDto){
		UserDto savedUserDto=userService.addUser(userDto);
		return new ResponseEntity<>(savedUserDto,HttpStatus.CREATED);
		
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable Long id){
		UserDto userDto=userService.getUserById(id);
		return new ResponseEntity<>(userDto,HttpStatus.OK);
		
	}
	
	@GetMapping("/get/users")
	public ResponseEntity<List<UserDto>> getAllUsers(){
		List<UserDto> users=userService.getAllUsers();
		return new ResponseEntity<>(users,HttpStatus.OK);
	}
	
	@PutMapping("/put/{id}")
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto,@PathVariable Long id ){
		userDto.setId(id);
		UserDto dto=userService.updateUser(userDto);
		return new ResponseEntity<>(dto,HttpStatus.OK);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	
	public ResponseEntity<ErrorDetails> handleResourceNoFoundException(ResourceNotFoundException exception,
			WebRequest webRequest){
		
		ErrorDetails errorDetails=new ErrorDetails(
				LocalDateTime.now(),
				exception.getMessage(),
				webRequest.getDescription(false),
				"USER_NOT_FOUND");
		
		return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
	}
	
	

}
