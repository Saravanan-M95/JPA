package com.sara.jpa.controller;

import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sara.jpa.entity.Employee;
import com.sara.jpa.repository.EmployeeRepository;

@RestController
public class EmployeeController 
{
	
	private final EmployeeRepository employeeRepository;
	
	@Autowired
	private EmployeeController(EmployeeRepository employeeRepository)
	{
		this.employeeRepository=employeeRepository;
	}
	
	@GetMapping("/get")
	public List<Employee>getEmp()
	{
		List<Employee> emp = employeeRepository.findAll();
		return emp;
	}
	
	@GetMapping("/get/{id}")
	public Employee getById(@PathVariable int id)
	{
		Optional<Employee> emp = employeeRepository.findById(id);
		return emp.get();
	}
	
	@GetMapping("/get/name/{name}")
	public List<Employee> getByName(@PathVariable String name)
	{
		List<Employee> emp = employeeRepository.findByFirstName(name);
		return emp;
	}
	
	@PostMapping("/save")
	public List<Employee> saveEmp(@RequestBody Employee emp)
	{
		employeeRepository.save(emp);
		return employeeRepository.findAll();
	}
	
	@PutMapping("/update/{id}")
	public Employee updateEmp(@PathVariable int id,@RequestBody Employee employee)
	{
		Employee emp = employeeRepository.getById(id);
		if(emp!=null)
		{
			emp.setEmail(employee.getEmail());
			emp.setFirstName(employee.getFirstName());
			emp.setLastName(employee.getLastName());
			emp.setDoj(employee.getDoj());
			employeeRepository.save(emp);
		}
		return emp;
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteEmp(@PathVariable int id)
	{
		employeeRepository.deleteById(id);
		return "Employee "+id+" deleted";
	}
}
