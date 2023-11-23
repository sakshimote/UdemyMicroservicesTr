package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.DepartmentDto;
import com.example.demo.entity.Department;
import com.example.demo.repository.DepartmentRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl  implements DepartmentService{
	
	@Autowired
	private DepartmentRepository departmentRepository;

	@Override
	public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
		Department department=new Department(
				departmentDto.getId(),departmentDto.getDepartmentName(),
				departmentDto.getDepartmentDescription(),
				departmentDto.getDepartmentCode());
		
		Department savedDepartment=departmentRepository.save(department);
		
		DepartmentDto savedDepartmentDto=new DepartmentDto(
				savedDepartment.getId(),
				savedDepartment.getDepartmentName(),
				savedDepartment.getDepartmentDescription(),
				savedDepartment.getDepartmentCode());
		return savedDepartmentDto;
	}

	@Override
	public DepartmentDto getDepartmentByCode(String DepartmentCode) {
		// TODO Auto-generated method stub
		
		Department department=departmentRepository.findByDepartmentCode(DepartmentCode);
		DepartmentDto departmentDto=new DepartmentDto(
				department.getId(),
				department.getDepartmentName(),
				department.getDepartmentDescription(),
				department.getDepartmentCode()
			);
		return departmentDto;
	}

}
