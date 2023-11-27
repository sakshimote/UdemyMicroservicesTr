package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.dto.OrganizationDto;
import com.example.demo.entity.Organization;
import com.example.demo.mapper.OrganizationMapper;
import com.example.demo.repository.OrganizationRepository;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class OrganizationServiceImpl  implements OrganizationService{
	
	
	private OrganizationRepository organizationRepository;

	@Override
	public OrganizationDto saveOrganization(OrganizationDto organizationDto) {
		// TODO Auto-generated method stub
		Organization organization=OrganizationMapper.mapToOrganization(organizationDto);
		Organization savedOrganization=organizationRepository.save(organization);
		
		
		return OrganizationMapper.mapToOrganizationDto(savedOrganization);
	}

	@Override
	public OrganizationDto getOrganizationByCode(String organizationCode) {
		// TODO Auto-generated method stub
		
		Organization organization=organizationRepository.findByOrganizationCode(organizationCode);
		
		return OrganizationMapper.mapToOrganizationDto(organization);
		
	}

	
}
