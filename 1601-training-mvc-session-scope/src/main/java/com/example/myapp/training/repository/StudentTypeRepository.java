package com.example.myapp.training.repository;

import java.util.List;

import com.example.myapp.training.entity.StudentType;

public interface StudentTypeRepository {
	List<StudentType> selectAll();

	StudentType selectByCode(String studentTypeCode);
}
