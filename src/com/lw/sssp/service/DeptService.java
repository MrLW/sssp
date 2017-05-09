package com.lw.sssp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lw.sssp.entity.Dept;
import com.lw.sssp.repository.DeptRepository;

@Service
public class DeptService {

	@Autowired
	private DeptRepository deptRepository ;
	
	// Ìí¼ÓÊÂÎñ
	@Transactional(readOnly=true)
	public List<Dept> getAll() {
		return deptRepository.getAll() ;
	}
}
