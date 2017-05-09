package com.lw.sssp.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lw.sssp.entity.Employee;
import com.lw.sssp.repository.EmployeeRepository;
@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository ;
	
	// ��סɾ�����������readonly=true
	@Transactional
	public void delete(Integer id){
		try {
			System.out.println("ɾ��service��" + id );
			employeeRepository.delete(id);
		} catch (Exception e) {
			System.err.println("�����ˣ�" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	@Transactional(readOnly=true)
	public Employee get(Integer id){
		Employee employee = employeeRepository.findOne(id);
		return employee ;
	}
	
	@Transactional(readOnly=true) // �������֧��
	public Employee getByLastName(String lastName){
		Employee employee = employeeRepository.getByLastName(lastName);
		return employee ;
	}
	
	@Transactional(readOnly=true) // ����������
	public Page<Employee> getPage(int pageNo,int pageSize){
		PageRequest request = new PageRequest(pageNo - 1 , pageSize) ;
		Page<Employee> page = employeeRepository.findAll(request);
		return page; 
	}
	
	@Transactional(readOnly=true)
	public void save(Employee employee){
		// ���ô���ʱ��
		if(employee.getId() == null ){ // ��Ӳ���
			employee.setCreateTime(new Date());
		}
		employeeRepository.saveAndFlush(employee);
	}
}
