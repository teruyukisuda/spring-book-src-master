package com.example.myapp.training.service;

import com.example.myapp.training.entity.Reservation;
import com.example.myapp.training.input.ReservationInput;

public interface ReservationService {
	Reservation reserve(ReservationInput reservationInput);
}
