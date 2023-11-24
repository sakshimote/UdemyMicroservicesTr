package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.demo.dto.APIResponseDto;
import com.example.demo.dto.DepartmentDto;
import com.example.demo.dto.EmployeeDto;
import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class 
 EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
//	
//	@Autowired
//	private RestTemplate restTemplate;
	
	//private WebClient webClient;
	
	@Autowired
	private APIClient apiClient;

	@Override
	public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
		// TODO Auto-generated method stub
		Employee employee=new Employee(
				employeeDto.getId(),
				employeeDto.getFirstName(),
				employeeDto.getLastName(),
				employeeDto.getEmail(),
				employeeDto.getDepartmentCode());
		
		Employee savedEmployee=employeeRepository.save(employee);
		
		EmployeeDto savedEmployeeDto=new EmployeeDto(
				savedEmployee.getId(),
				savedEmployee.getFirstName(),
				savedEmployee.getLastName(),
				savedEmployee.getEmail(),
				savedEmployee.getDepartmentCode());


		return savedEmployeeDto;
	}

	@Override
	public APIResponseDto getEmployeeById(Long id) {
		// TODO Auto-generated method stub
		
		Employee  employee=employeeRepository.findById(id).get();
		
//	ResponseEntity<DepartmentDto> responseEntity=	restTemplate.getForEntity("http://localhost:8081/departments/api/get/"+employee.getDepartmentCode(),
//				DepartmentDto.class);
//		DepartmentDto departmentDto=responseEntity.getBody();
		
//		DepartmentDto departmentDto=	webClient.get()
//		.uri("http://localhost:8081/departments/api/get/"+employee.getDepartmentCode())
//		.retrieve().bodyToMono(DepartmentDto.class).block();
//		
		
	DepartmentDto departmentDto=apiClient.getDepartmentByCode(employee.getDepartmentCode());
		EmployeeDto employeeDto=new EmployeeDto(
				employee.getId(),
				employee.getFirstName(),
				employee.getLastName(),
				employee.getEmail(),
				employee.getDepartmentCode());
		

		APIResponseDto apiResponseDto=new APIResponseDto();
		apiResponseDto.setDepartmentDto(departmentDto);
		apiResponseDto.setEmployeeDto(employeeDto);
		
		
		return apiResponseDto;
	}

}
