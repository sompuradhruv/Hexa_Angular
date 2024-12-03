package com.frontend.exam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frontend.exam.model.Flight;
import com.frontend.exam.model.User;
import com.frontend.exam.model.UserFlight;
import com.frontend.exam.repository.FlightRepository;
import com.frontend.exam.repository.UserFlightRepository;
import com.frontend.exam.repository.UserRepository;

@Service
public class FlightService {
	
	@Autowired
	FlightRepository flightRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserFlightRepository userFlightRepository;

	public Flight addFlight(Flight flight) {
		return flightRepository.save(flight);
	}

	public List<Flight> getFlights() {
		// TODO Auto-generated method stub
		return flightRepository.findAll();
	}

	public UserFlight addUserFlight(UserFlight userFlight) {
		User user=userFlight.getUser();
		userRepository.save(user);
		return userFlightRepository.save(userFlight);
	}

	public List<UserFlight> getUserFlight() {
		// TODO Auto-generated method stub
		return userFlightRepository.findAll();
	}

}
