package com.qfedu.SSHDemo.dto;

import java.util.ArrayList;
import java.util.List;

import com.qfedu.SSHDemo.po.Menu;
import com.qfedu.SSHDemo.po.Role;

public class RoleDto {

	private Integer id;
	
	private String name;
	
	private String description;

	private String menuIds="";
	
	private String menuNames="";
	
	public RoleDto() {
	}

	public RoleDto(Role role){
		this.setId(role.getId());
		this.setName(role.getName());
		this.setDescription(role.getDescription());
		
		for(Menu m : role.getMenus()){
			if (!menuIds.equals("")) {
				menuIds += ",";
				menuNames += ",";
			}
			menuIds += m.getId();
			menuNames += m.getName();
		}
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
	
		
	public String getMenuNames() {
		return menuNames;
	}

	public void setMenuNames(String menuNames) {
		this.menuNames = menuNames;
	}
	
	public String getMenuIds() {
		return menuIds;
	}

	public void setMenuIds(String menuIds) {
		this.menuIds = menuIds;
	}

	/**
	 * 通过pos得到dtos
	 */
	public static List<RoleDto> getDtos(List<Role> pos){
		List<RoleDto> dtos = new ArrayList<RoleDto>();
		for (Role po : pos) {
			dtos.add(new RoleDto(po));
		}
		
		return dtos;
	}
	
	
}
