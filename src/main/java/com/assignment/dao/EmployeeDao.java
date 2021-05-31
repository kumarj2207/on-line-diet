package com.assignment.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.assignment.entity.Employee;

@Repository
public interface EmployeeDao extends JpaRepository<Employee, Integer>{

	@Query(value = "from Employee e where upper(e.name) like %:name%")
	List<Employee> findByName(@Param("name") String name);

}
