package com.example.demo.service;

import com.example.demo.dto.OrganizationDto;

public interface OrganizationService {
	
	OrganizationDto saveOrganization(OrganizationDto organizationDto) ;
	
	OrganizationDto getOrganizationByCode(String organizationCode);

}
