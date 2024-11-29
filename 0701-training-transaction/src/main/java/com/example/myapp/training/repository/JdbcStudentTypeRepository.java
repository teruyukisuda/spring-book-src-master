package com.example.myapp.training.repository;

import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.myapp.training.entity.StudentType;

@Repository
public class JdbcStudentTypeRepository implements StudentTypeRepository {

	private final JdbcTemplate jdbcTemplate;

	public JdbcStudentTypeRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public StudentType selectByCode(String studentTypeCode) {
		return jdbcTemplate.queryForObject("SELECT * FROM student_type WHERE code=?", new DataClassRowMapper<>(StudentType.class), studentTypeCode);
	}

}
