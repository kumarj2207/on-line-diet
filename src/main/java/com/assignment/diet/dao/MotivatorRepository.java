package com.assignment.diet.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.diet.entity.Motivator;


@Repository
public interface MotivatorRepository extends JpaRepository<Motivator, Long> {

	

}
