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
	
	// 记住删除不可以添加readonly=true
	@Transactional
	public void delete(Integer id){
		try {
			System.out.println("删除service：" + id );
			employeeRepository.delete(id);
		} catch (Exception e) {
			System.err.println("出错了：" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	@Transactional(readOnly=true)
	public Employee get(Integer id){
		Employee employee = employeeRepository.findOne(id);
		return employee ;
	}
	
	@Transactional(readOnly=true) // 添加事务支持
	public Employee getByLastName(String lastName){
		Employee employee = employeeRepository.getByLastName(lastName);
		return employee ;
	}
	
	@Transactional(readOnly=true) // 添加事务操作
	public Page<Employee> getPage(int pageNo,int pageSize){
		PageRequest request = new PageRequest(pageNo - 1 , pageSize) ;
		Page<Employee> page = employeeRepository.findAll(request);
		return page; 
	}
	
	@Transactional(readOnly=true)
	public void save(Employee employee){
		// 设置创建时间
		if(employee.getId() == null ){ // 添加操作
			employee.setCreateTime(new Date());
		}
		employeeRepository.saveAndFlush(employee);
	}
}
