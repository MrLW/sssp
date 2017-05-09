package com.lw.sssp.repository;

import java.util.List;

import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import com.lw.sssp.entity.Dept;
// 会添加到ioc容器中
public interface DeptRepository extends JpaRepository<Dept, Integer>{
	
	// 使用jpql的方式实现二级缓存
	@QueryHints({@QueryHint(name=org.hibernate.ejb.QueryHints.HINT_CACHEABLE,value="true")})
	@Query("From Dept d")
	List<Dept> getAll(); 
}
