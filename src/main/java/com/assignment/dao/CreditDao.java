package com.assignment.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.entity.Credit;

@Repository
public interface CreditDao extends JpaRepository<Credit, Integer>{

	Credit findByPan(String pan);

}
