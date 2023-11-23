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

import com.example.demo.dto.DepartmentDto;
import com.example.demo.service.DepartmentService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("departments/api")
@AllArgsConstructor
public class DepartmentController {
	
	@Autowired 
	private DepartmentService departmentService;
	
	@PostMapping("/post")
	public ResponseEntity<DepartmentDto> saveDepartment(@RequestBody DepartmentDto departmentDto){
		DepartmentDto savedDepartmentDto=departmentService.saveDepartment(departmentDto);
		return new ResponseEntity<>(savedDepartmentDto,HttpStatus.CREATED);
	}
	
	
	@GetMapping("/get/{departmentCode}")
	public ResponseEntity<DepartmentDto> getDepartmentByCode(@PathVariable("departmentCode") String departmentCode){
DepartmentDto departmentDto=departmentService.getDepartmentByCode(departmentCode);
		return new ResponseEntity<>(departmentDto,HttpStatus.OK);
	}
	
	
	

}
