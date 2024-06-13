package com.edgar.mx.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edgar.mx.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
