package com.lw.sssp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lw.sssp.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	// ֱ����������������ɣ���Ϊ��springdata��jpa����
	Employee getByLastName(String lastName) ;
}
