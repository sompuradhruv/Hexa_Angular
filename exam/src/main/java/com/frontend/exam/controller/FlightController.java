package com.frontend.exam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frontend.exam.model.Flight;
import com.frontend.exam.model.UserFlight;
import com.frontend.exam.service.FlightService;

@RestController
@RequestMapping("/flight")
@CrossOrigin(origins = {"http://localhost:4200"})
public class FlightController {
	
	@Autowired
	FlightService flightService;
	
	@PostMapping("/add")
	public Flight addFlight(@RequestBody Flight flight) {
		return flightService.addFlight(flight);
	}
	
	@GetMapping("/all")
	public List<Flight> getFlights(){
		return flightService.getFlights();
	}
	
	@PostMapping("/userflight")
	public UserFlight addUserFlight(@RequestBody UserFlight userFlight) {
		return flightService.addUserFlight(userFlight);
	} 
	
	@GetMapping("/userflight/all")
	public List<UserFlight> getUserFlight(){
		return flightService.getUserFlight();
	}

}
