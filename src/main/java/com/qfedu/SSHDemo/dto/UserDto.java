package com.qfedu.SSHDemo.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.qfedu.SSHDemo.po.Role;
import com.qfedu.SSHDemo.po.User;

public class UserDto {

	private Integer id;
	
	private String loginName;
	
	private String password;
	
	private String name;
	
	private String gender;
	
	private Date createTime;
	
	private String roleNames;
	
	public UserDto() {
	}
	
	public UserDto(User u) {
		this.id = u.getId();
		this.loginName = u.getLoginName();
		this.name = u.getName();
		this.password = u.getPassword();
		this.gender = u.getGender();
		this.createTime = u.getCreateTime();
		
		if (u.getRoles().size()!=0) {
			for (Role r : u.getRoles()) {
				if (roleNames == null) {
					roleNames = r.getName();
				}else if(roleNames != ""){
					roleNames += ",";
					roleNames += r.getName();
				}
			}
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss",locale = "zh", timezone="GMT+8")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getRoleNames() {
		return roleNames;
	}

	public void setRoleNames(String roleNames) {
		this.roleNames = roleNames;
	}

	/**
	 * 将pos转换成dtos
	 * @param pos
	 * @return
	 */
	public static List<UserDto> getDtos(List<User> pos){
		ArrayList<UserDto> dtos = new ArrayList<UserDto>();
		for (User po : pos) {
			dtos.add(new UserDto(po));
		}
		return dtos;
	}
}
