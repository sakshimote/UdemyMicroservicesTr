package com.example.demo.dto;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationDto {


    private Long id;
	private String organizationName;
	private String organizationDescription;
	private String organizationCode;
	private LocalDate createdDate;
}
