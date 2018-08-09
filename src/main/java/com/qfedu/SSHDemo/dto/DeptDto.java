package com.qfedu.SSHDemo.dto;

import java.util.ArrayList;
import java.util.List;

import com.qfedu.SSHDemo.po.Dept;
import com.qfedu.SSHDemo.po.Position;

public class DeptDto {

	private Integer id;
	
	private String name;
	
	private String description;
	
	private String no;
	
	private DeptDto parent;
	
	private List<DeptDto> children = new ArrayList<DeptDto>();
	
	private List<Position> positions = new ArrayList<Position>();
	
	private String parentName;
	
	private String positionNames;

	
	public DeptDto() {
	}

	public DeptDto(Dept d,Boolean loadChildren){
		this.id=d.getId();
		this.name=d.getName();
		this.description=d.getDescription();
		this.no=d.getNo();
		
		if (d.getParent() != null) {
			this.parent = new DeptDto(d.getParent(),false);
		}
		if (loadChildren) {
			this.children = DeptDto.getDtos(d.getChildren(), false);
		}
		
		if (d.getPositions().size()!=0) {
			for (Position p : d.getPositions()) {
				if (positionNames == null) {
					positionNames = p.getName();
				}else if(positionNames != ""){
					positionNames += ",";
					positionNames += p.getName();
				}
			}
		}
	}
	
	

	
	
	public String getPositionNames() {
		return positionNames;
	}

	public void setPositionNames(String positionNames) {
		this.positionNames = positionNames;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public DeptDto getParent() {
		return parent;
	}

	public void setParent(DeptDto parent) {
		this.parent = parent;
	}

	public List<DeptDto> getChildren() {
		return children;
	}

	public void setChildren(List<DeptDto> children) {
		this.children = children;
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

	public List<Position> getPositions() {
		return positions;
	}

	public void setPositions(List<Position> positions) {
		this.positions = positions;
	}
	
	public String getParentName(){
		if (this.parent==null) {
			return "";
		}
		return parent.getName();
	}
	
	public static List<DeptDto> getDtos(List<Dept> pos,Boolean loadChildren) {
		ArrayList<DeptDto> dtos = new ArrayList<DeptDto>();
		for (Dept po : pos) {
			dtos.add(new DeptDto(po, loadChildren));
		}
		return dtos;
	}
}
