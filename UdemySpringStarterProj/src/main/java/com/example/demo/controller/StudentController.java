package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.Student;

@RestController
@RequestMapping("/s")
public class StudentController {
	
	@GetMapping("/student")
	public ResponseEntity<Student> getStudent() {
		Student student=new Student(1,"shri", "m");
		return ResponseEntity.ok(student);
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
		
		System.out.println(student.getFirstName());
		System.out.println(student.getLastName());
		System.out.println(student.getId());
		
		
		return student;
	}
	
	@PutMapping("/student/put/{id}")
	public Student updateStudent(@RequestBody Student student,@PathVariable("id") int studentId) {
		System.out.println(student.getFirstName());
		System.out.println(student.getLastName());
		return student;
	}
	
	@DeleteMapping("/student/delete/{id}")
	public String deleteStudent(@PathVariable("id") int id) {
		System.out.println(id);
		return "student deleted successfully"; 
	}

}
