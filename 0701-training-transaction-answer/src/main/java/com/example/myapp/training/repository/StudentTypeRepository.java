package com.example.myapp.training.repository;

import com.example.myapp.training.entity.StudentType;

public interface StudentTypeRepository {
	StudentType selectByCode(String studentTypeCode);
}
