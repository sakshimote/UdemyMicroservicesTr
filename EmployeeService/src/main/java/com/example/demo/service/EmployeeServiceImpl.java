package com.example.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.demo.dto.APIResponseDto;
import com.example.demo.dto.DepartmentDto;
import com.example.demo.dto.EmployeeDto;
import com.example.demo.dto.OrganizationDto;
import com.example.demo.entity.Employee;
import com.example.demo.mapper.EmployeeMapper;
import com.example.demo.repository.EmployeeRepository;


import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class 
 EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	private static Logger LOGGER=LoggerFactory.getLogger(EmployeeServiceImpl.class);
//	
//	@Autowired
//	private RestTemplate restTemplate;
	
	private WebClient webClient;
	
	@Autowired
	private APIClient apiClient;

	@Override
	public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
		// TODO Auto-generated method stub
		Employee employee=EmployeeMapper.mapToEmployee(employeeDto);
		
		Employee savedEmployee=employeeRepository.save(employee);
		
		EmployeeDto savedEmployeeDto=EmployeeMapper.mapToEmployeeDto(savedEmployee);


		return savedEmployeeDto;
	}

	@Override
	//@CircuitBreaker(name = "${spring.application.name}",fallbackMethod ="getDefaultDepartment" )
	@Retry(name ="${spring.application.name}" ,fallbackMethod ="getDefaultDepartment")
	public APIResponseDto getEmployeeById(Long id) {
		// TODO Auto-generated method stub
		LOGGER.info("get employee by id method");
		Employee  employee=employeeRepository.findById(id).get();
		
//	ResponseEntity<DepartmentDto> responseEntity=	restTemplate.getForEntity("http://localhost:8081/departments/api/get/"+employee.getDepartmentCode(),
//				DepartmentDto.class);
//		DepartmentDto departmentDto=responseEntity.getBody();
		
		DepartmentDto departmentDto=	webClient.get()
		.uri("http://localhost:8081/departments/api/get/"+employee.getDepartmentCode())
		.retrieve().bodyToMono(DepartmentDto.class).block();
		
		
		OrganizationDto OrganizationDto=	webClient.get()
				.uri("http://localhost:8083/api/organizations/get/"+employee.getOrganizationCode())
				.retrieve().bodyToMono(OrganizationDto.class).block();
				
		
//	DepartmentDto departmentDto=apiClient.getDepartmentByCode(employee.getDepartmentCode());
		EmployeeDto employeeDto=EmployeeMapper.mapToEmployeeDto(employee);

		APIResponseDto apiResponseDto=new APIResponseDto();
		apiResponseDto.setDepartmentDto(departmentDto);
		apiResponseDto.setEmployeeDto(employeeDto);
		apiResponseDto.setOrganizationDto(OrganizationDto);
		
		
		return apiResponseDto;
	}
	
	public APIResponseDto getDefaultDepartment(Long employeeId,Exception exception) {
		
	LOGGER.info("in fallback method");
		
		Employee  employee=employeeRepository.findById(employeeId).get();
		
DepartmentDto departmentDto=new DepartmentDto();
departmentDto.setDepartmentName("R&D Department");
departmentDto.setDepartmentCode("RD001");
departmentDto.setDepartmentDescription("Research & development department");


EmployeeDto employeeDto=EmployeeMapper.mapToEmployeeDto(employee);

			APIResponseDto apiResponseDto=new APIResponseDto();
			apiResponseDto.setDepartmentDto(departmentDto);
			apiResponseDto.setEmployeeDto(employeeDto);
			
			
			return apiResponseDto;
	}

}
