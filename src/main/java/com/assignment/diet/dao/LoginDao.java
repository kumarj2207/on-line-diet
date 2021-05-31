package com.assignment.diet.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.diet.entity.DietUser;

@Repository
public interface LoginDao extends JpaRepository<DietUser, Long> {

	public DietUser findByUserName(String userName);

	public DietUser findByReferralIdAndPassword(Long referralId, String oldPassword);

}
