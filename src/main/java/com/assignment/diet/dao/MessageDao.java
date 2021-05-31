package com.assignment.diet.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.diet.entity.Message;

public interface MessageDao extends JpaRepository<Message, Long> {

}
