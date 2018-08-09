package com.qfedu.SSHDemo.po;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="t_position")
public class Position {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length=20 , unique = true , nullable=false)
	private String name;
	
	@Column(length=50)
	private String description;
	
	@Column(length=10)
	private Integer level;
	
//	多对一
	@ManyToOne
	@JoinColumn(name="dept_id")
	private Dept dept;
	
//	多对多
	@ManyToMany(mappedBy = "positions")
	private List<Staff> staffs = new ArrayList<Staff>();
	
	public Position() {
	}

	
	public Position(Integer id, String name, String description, Integer level, Dept dept) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.level = level;
		this.dept = dept;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}


	public List<Staff> getStaffs() {
		return staffs;
	}


	public void setStaffs(List<Staff> staffs) {
		this.staffs = staffs;
	}
	
	
	
}
