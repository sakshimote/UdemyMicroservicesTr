package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.APIResponseDto;
import com.example.demo.dto.EmployeeDto;
import com.example.demo.service.EmployeeService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/employees/api")
@AllArgsConstructor
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("/post")
	public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto){
		EmployeeDto savedEmployeeDto=employeeService.saveEmployee(employeeDto);
		return new ResponseEntity<>(savedEmployeeDto,HttpStatus.CREATED);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<APIResponseDto> getEmployeeById(@PathVariable("id") Long id){
		APIResponseDto employeeDto=employeeService.getEmployeeById(id);
		return new ResponseEntity<>(employeeDto,HttpStatus.OK);
	}
	

}
