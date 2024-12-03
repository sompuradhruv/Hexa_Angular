package com.frontend.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.frontend.exam.model.UserFlight;

@Repository
public interface UserFlightRepository extends JpaRepository<UserFlight, Integer>{

}

