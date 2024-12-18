package com.frontend.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.frontend.exam.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}

