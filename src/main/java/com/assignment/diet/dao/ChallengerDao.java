package com.assignment.diet.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.diet.entity.Batch;
import com.assignment.diet.entity.Challenger;


@Repository
public interface ChallengerDao extends JpaRepository<Challenger, Long> {

	List<Challenger> findByBatch(Batch batch);

	


}
