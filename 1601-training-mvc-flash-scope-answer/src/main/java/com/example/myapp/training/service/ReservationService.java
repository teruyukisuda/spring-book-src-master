package com.example.myapp.training.service;

import java.util.List;

import com.example.myapp.training.entity.Reservation;
import com.example.myapp.training.entity.StudentType;
import com.example.myapp.training.input.ReservationInput;

public interface ReservationService {
	List<StudentType> findAllStudentType();
	StudentType findStudentTypeByCode(String studentTypeCode);
	Reservation reserve(ReservationInput reservationInput);
}
