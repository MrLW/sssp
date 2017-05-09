package com.lw.sssp.repository;

import java.util.List;

import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import com.lw.sssp.entity.Dept;
// ����ӵ�ioc������
public interface DeptRepository extends JpaRepository<Dept, Integer>{
	
	// ʹ��jpql�ķ�ʽʵ�ֶ�������
	@QueryHints({@QueryHint(name=org.hibernate.ejb.QueryHints.HINT_CACHEABLE,value="true")})
	@Query("From Dept d")
	List<Dept> getAll(); 
}
