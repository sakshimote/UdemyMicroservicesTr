package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.OrganizationDto;
import com.example.demo.service.OrganizationService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/organizations")
@AllArgsConstructor
public class OrganizationController {
	
	
	private OrganizationService organizationService;
	
	
	@PostMapping("/post")
	public ResponseEntity<OrganizationDto> saveOrganization(@RequestBody OrganizationDto organizationDto){
		OrganizationDto savedOrganizationDto=organizationService.saveOrganization(organizationDto);
		return new ResponseEntity<>(savedOrganizationDto,HttpStatus.CREATED);
	}
	
	@GetMapping("/get/{organizationCode}")
	public ResponseEntity<OrganizationDto> getOrganizationByCode(@PathVariable String organizationCode){
		OrganizationDto organizationDto=organizationService.getOrganizationByCode(organizationCode);
		return ResponseEntity.ok(organizationDto);
	}
	
	
	
	
	

}
