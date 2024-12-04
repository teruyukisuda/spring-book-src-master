package com.example.myapp.training.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.example.myapp.training.input.ReservationInput;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.myapp.training.entity.Reservation;
import com.example.myapp.training.entity.StudentType;
import com.example.myapp.training.entity.Training;
import com.example.myapp.training.exception.CapacityOverException;
import com.example.myapp.training.repository.ReservationRepository;
import com.example.myapp.training.repository.StudentTypeRepository;
import com.example.myapp.training.repository.TrainingRepository;

@Service
@Transactional
public class ReservationServiceImpl implements  ReservationService {

	private final StudentTypeRepository studentTypeRepository;
	private final TrainingRepository trainingRepository;
	private final ReservationRepository reservationRepository;

	public ReservationServiceImpl(StudentTypeRepository studentTypeRepository, TrainingRepository trainingRepository, ReservationRepository reservationRepository) {
		this.studentTypeRepository = studentTypeRepository;
		this.trainingRepository = trainingRepository;
		this.reservationRepository = reservationRepository;
	}

	@Override
	public List<StudentType> findAllStudentType() {
		return studentTypeRepository.selectAll();
	}

	@Override
	public StudentType findStudentTypeByCode(String studentTypeCode) {
		return studentTypeRepository.selectByCode(studentTypeCode);
	}

	@Override
	public Reservation reserve(ReservationInput reservationInput) {
		Training training = trainingRepository.selectById(reservationInput.getTrainingId());
		training.setReserved(training.getReserved() + 1);
		if (training.getReserved() > training.getCapacity() ) {
			throw new CapacityOverException("定員オーバー");
		}
		trainingRepository.update(training);

		StudentType studentType = studentTypeRepository.selectByCode(reservationInput.getStudentTypeCode());
		Reservation reservation = new Reservation();
		reservation.setId(UUID.randomUUID().toString());
		reservation.setTrainingId(training.getId());
		reservation.setStudentTypeId(studentType.getId());
		reservation.setName(reservationInput.getName());
		reservation.setPhone(reservationInput.getPhone());
		reservation.setReservedDateTime(LocalDateTime.now());
		reservation.setEmailAddress(reservationInput.getEmailAddress());
		reservationRepository.insert(reservation);

		return reservation;
	}
}