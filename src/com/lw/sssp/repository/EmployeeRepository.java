package com.lw.sssp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lw.sssp.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	// 直接申明这个方法即可，因为是springdata和jpa整个
	Employee getByLastName(String lastName) ;
}
