package com.example.Springquiz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.Springquiz.dto.Student;
import com.example.Springquiz.service.StudentService;

@RestController
public class StudentController {
	@Autowired
	StudentService studentservice;
	
	@GetMapping("/student")
	public List<Student> student(){
		List<Student> list = studentservice.selectList();
		return list;
	}
}
