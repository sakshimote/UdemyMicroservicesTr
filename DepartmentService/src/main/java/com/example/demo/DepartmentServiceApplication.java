package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;


@OpenAPIDefinition(
		info = @Info(
				title = "Department Service",
				description = "microservice",
				version="v1",
				contact =@Contact(name = "sakshi",email = "sakshimote19@gmail.com"
				),license =@License(
						name = "Apache 2.0",
						url="http.sts.com")
				),
		externalDocs = @ExternalDocumentation(description = "department service doc"
		,url="dummyurl.com"))

@SpringBootApplication
@EnableDiscoveryClient
public class DepartmentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DepartmentServiceApplication.class, args);
	}

}
