package com.edgar.mx.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edgar.mx.entity.Employee;
import com.edgar.mx.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	
	public Iterable<Employee> getEmployees() {
		return employeeRepository.findAll();
	}
	
	public Optional<Employee> getEmployeeById(Long id){
		return employeeRepository.findById(id);
	}
	
	public Employee saveEmployees(Employee employee) {
		return employeeRepository.save(employee);
	}
	
	public void deleteEmployees(Long id) {
		employeeRepository.deleteById(id);
	}

}
