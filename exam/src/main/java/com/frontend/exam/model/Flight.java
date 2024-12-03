package com.frontend.exam.model;

import com.frontend.exam.enums.Airline;
import com.frontend.exam.enums.Destination;
import com.frontend.exam.enums.Source;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Flight {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	int id;
    
    String FlightNumber;
    
    @Enumerated(EnumType.STRING)    
    Source source;
    
    @Enumerated(EnumType.STRING)
    Destination destination;
    
    @Enumerated(EnumType.STRING)
	Airline airline;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFlightNumber() {
		return FlightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		FlightNumber = flightNumber;
	}

	public Source getSource() {
		return source;
	}

	public void setSource(Source source) {
		this.source = source;
	}

	public Destination getDestination() {
		return destination;
	}

	public void setDestination(Destination destination) {
		this.destination = destination;
	}

	public Airline getAirline() {
		return airline;
	}

	public void setAirline(Airline airline) {
		this.airline = airline;
	}

}
