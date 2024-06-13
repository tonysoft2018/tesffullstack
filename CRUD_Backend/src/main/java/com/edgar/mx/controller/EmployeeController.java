package com.edgar.mx.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edgar.mx.entity.Employee;
import com.edgar.mx.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	
	@GetMapping("/find")
	public ResponseEntity<?>  getAllEmployees(){
		
		List<Employee> employees = StreamSupport
				.stream(employeeService.getEmployees().spliterator(), false)
				.collect(Collectors.toList());
		
		return ResponseEntity.ok().body(employees);
	}
	
	@GetMapping("/find/{id}")
	public ResponseEntity<?> getEmployeeById(@PathVariable Long id){
		Optional<Employee> oEmployee = employeeService.getEmployeeById(id);
		
		if(!oEmployee.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		
		return ResponseEntity.ok(oEmployee);
	}
	
	
	@PostMapping("/create")
	public ResponseEntity<?> createEmployee(@RequestBody Employee employee){
		Employee employeeNew = employeeService.saveEmployees(employee);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(employeeNew);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateEmployee(@RequestBody Employee employee, @PathVariable Long id){
		Optional<Employee> oEmployee = employeeService.getEmployeeById(id);
		
		if(!oEmployee.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		oEmployee.get().setFirtsName(employee.getFirtsName());
		oEmployee.get().setLastName(employee.getLastName());
		oEmployee.get().setBirthDate(employee.getBirthDate());
		oEmployee.get().setPosition(employee.getPosition());
		oEmployee.get().setSalary(employee.getSalary());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.saveEmployees(oEmployee.get()));
		
		
	}
	
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteEmployee(@PathVariable Long id){
		Optional<Employee> oEmployee = employeeService.getEmployeeById(id);
		
		if(!oEmployee.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(oEmployee);
	}

}
