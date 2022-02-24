package com.sara.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sara.jpa.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> 
{
	List<Employee> findByFirstName(String name);
}
