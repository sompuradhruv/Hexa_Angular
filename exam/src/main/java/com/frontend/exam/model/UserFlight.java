package com.frontend.exam.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class UserFlight {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	int id;

    @ManyToOne
    User user;
    
    @ManyToOne
    Flight flight;
    
    LocalDate date_of_journey;
    
    int number_of_passengers;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public LocalDate getDate_of_journey() {
		return date_of_journey;
	}

	public void setDate_of_journey(LocalDate date_of_journey) {
		this.date_of_journey = date_of_journey;
	}

	public int getNumber_of_passengers() {
		return number_of_passengers;
	}

	public void setNumber_of_passengers(int number_of_passengers) {
		this.number_of_passengers = number_of_passengers;
	}

}
