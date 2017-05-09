package com.lw.sssp.entity;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Cacheable(value=true) // 会将带有这个注解的类添加到二级缓存中
@Table(name="sssp_dept")
@Entity
public class Dept {

	private Integer id ;
	private String deptName;
	@Id
	@GeneratedValue // 默认主键生成策略
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="DEPT_NAME")
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	} 
	
}
