package com.frontend.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.frontend.exam.model.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer>{

}
