package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.Student;

@RestController
public class StudentController {
	
	@GetMapping("/student")
	public Student getStudent() {
		Student student=new Student(1,"shri", "m");
		return student;
	}
	
	@GetMapping("/students")
	public List<Student> getStudentList(){
		List<Student> students=new ArrayList<>();
		students.add(new Student(2, "sakshi", "mote"));
		students.add(new Student(3, "shriyash", "kulkarni"));
		students.add(new Student(3, "rohit", "sharma"));
		students.add(new Student(3, "virat", "kohli"));
		return students;
		
	}
	
	@GetMapping("/student/get/{id}/{first-name}/{last-name}")
	public Student studentPathvariable(@PathVariable("id") int id,
			@PathVariable("first-name") String fName,
			@PathVariable("last-name") String lName) {
		return new Student(id,fName,lName);
		
	}
	@GetMapping("/student/query")
	public Student studentRequestParam(@RequestParam int id,@RequestParam String firstName,@RequestParam String lastName) {
		
		return new Student(id, firstName, lastName);
	}
	
	@PostMapping("/student/post")
	public Student createStudent(@RequestBody Student student) {
		Student student2=new Student(student.getId(), student.getFirstName(), student.getLastName());
		return student2;
	}

}
