package com.example.Springquiz.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.example.Springquiz.dto.Student;

@Mapper
public interface StudentDao {
	public List<Student> selectList();
}
