package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.DepartmentDto;
import com.example.demo.entity.Department;
import com.example.demo.mapper.DepartmentMapper;
import com.example.demo.repository.DepartmentRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl  implements DepartmentService{
	
	@Autowired
	private DepartmentRepository departmentRepository;

	@Override
	public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
		Department department=DepartmentMapper.mapToDepartment(departmentDto);
		
		Department savedDepartment=departmentRepository.save(department);
		
		DepartmentDto savedDepartmentDto=DepartmentMapper.mapToDepartmentDto(savedDepartment);
		return savedDepartmentDto;
	}

	@Override
	public DepartmentDto getDepartmentByCode(String DepartmentCode) {
		// TODO Auto-generated method stub
		
		Department department=departmentRepository.findByDepartmentCode(DepartmentCode);
		DepartmentDto departmentDto=DepartmentMapper.mapToDepartmentDto(department);
		return departmentDto;
	}

}
