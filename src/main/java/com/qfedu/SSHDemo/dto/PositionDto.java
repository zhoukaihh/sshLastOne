package com.qfedu.SSHDemo.dto;

import java.util.ArrayList;
import java.util.List;

import com.qfedu.SSHDemo.po.Dept;
import com.qfedu.SSHDemo.po.Position;
import com.qfedu.SSHDemo.po.Staff;

public class PositionDto {

	private Integer id;
	
	private String name;
	
	private String description;
	
	private Integer level;
	
	private Dept dept;
	
	public PositionDto() {
	}

	public PositionDto(Position po) {
		this.id = po.getId();
		this.name = po.getName();
		this.description = po.getDescription();
		this.level = po.getLevel();
		this.dept = po.getDept();
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

	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}
	
	public String getDeptName(){
		if (this.dept==null) {
			return "";
		}
		return dept.getName();
	}

	public static List<PositionDto> getDtos(List<Position> pos){
		ArrayList<PositionDto> dtos = new ArrayList<PositionDto>();
		for (Position po : pos) {
			dtos.add(new PositionDto(po));
		}
		return dtos;
	}
}
