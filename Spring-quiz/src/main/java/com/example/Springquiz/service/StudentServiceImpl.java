package com.example.Springquiz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Springquiz.dao.StudentDao;
import com.example.Springquiz.dto.Student;

@Service
public class StudentServiceImpl implements StudentService {
	@Autowired
	StudentDao studentDao;

	@Override
	public List<Student> selectList() {
		return studentDao.selectList();
	}
	
	
}
