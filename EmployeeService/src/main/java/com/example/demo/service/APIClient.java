package com.example.demo.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.dto.DepartmentDto;



@FeignClient(url = "http://localhost:8081",value = "DEPARTMENT-SERVICE")


public interface APIClient {
	
	@GetMapping("departments/api/get/{departmentCode}")
	DepartmentDto getDepartmentByCode(@PathVariable("departmentCode") String departmentCode);
//	{
////DepartmentDto departmentDto=departmentService.getDepartmentByCode(departmentCode);
////		return new ResponseEntity<>(departmentDto,HttpStatus.OK);
//	}

}
