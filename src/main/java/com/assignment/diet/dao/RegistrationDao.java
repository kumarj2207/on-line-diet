package com.assignment.diet.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.assignment.diet.entity.Registration;

public interface RegistrationDao extends JpaRepository<Registration, String> {

	@Query("FROM Registration r WHERE status IS NULL")
	List<Registration> getNewRegistrations();

	Registration findByEmail(String email);
}
