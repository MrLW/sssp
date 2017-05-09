package com.lw.sssp.entity;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Cacheable(value=true) // �Ὣ�������ע�������ӵ�����������
@Table(name="sssp_dept")
@Entity
public class Dept {

	private Integer id ;
	private String deptName;
	@Id
	@GeneratedValue // Ĭ���������ɲ���
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
