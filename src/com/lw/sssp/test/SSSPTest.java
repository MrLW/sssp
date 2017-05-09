package com.lw.sssp.test;


import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.sql.DataSource;

import org.hibernate.annotations.QueryHints;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lw.sssp.entity.Dept;
import com.lw.sssp.service.DeptService;

public class SSSPTest {
	
	private ApplicationContext ac = null ;

	private EntityManagerFactory entityManagerFactory = null ;
	
	private DeptService deptService = null ;
	
	{
		 ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml") ;
		 entityManagerFactory = ac.getBean(EntityManagerFactory.class);
		 deptService = ac.getBean(DeptService.class);
	}
	
	// 测试二级缓存
	@Test
	public void testRepositorySecondCache(){
		List<Dept> list = deptService.getAll() ;
		list = deptService.getAll() ;
	}
	
	@Test // 测试jpa的二级缓存，核心调用该方法：query.setHint(QueryHints.CACHEABLE, true)
	public void testJpaSecondCache(){
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Query query = entityManager.createQuery("FROM Dept d");
		List list = query.setHint(QueryHints.CACHEABLE, true).getResultList();
		entityManager.close();
		
		
		entityManager = entityManagerFactory.createEntityManager();
		query = entityManager.createQuery("FROM Dept d");
		list = query.setHint(QueryHints.CACHEABLE, true).getResultList();
		entityManager.close();
		
		//只会查询一次数据库
	}
	
	// 测试DataSource和jpa配置
	@Test
	public void testDataSource() throws SQLException{ 
		DataSource dataSource = ac.getBean(DataSource.class);
		System.out.println("conn => " + dataSource.getConnection());
	}
}
