package com.qfedu.SSHDemo.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.qfedu.SSHDemo.po.Position;
import com.qfedu.SSHDemo.po.Staff;
import com.qfedu.SSHDemo.po.User;

public class StaffDto {

	private Integer id;
	
	private String  name;
	
	private String gender;
	
	private Date birthday;
	
	private String mobile;
	
	private String headImage;
	
	private List<Position> positions = new ArrayList<Position>();
	
	private User user;
	
	private String userName;
	
	private String positionNames;
	
	
	public StaffDto() {
	}

	public StaffDto(Staff s){
		this.id = s.getId();
		this.name = s.getName();
		this.gender = s.getGender();
		this.birthday = s.getBirthday();
		this.mobile = s.getMobile();
		this.headImage = s.getHeadImage();
		this.user = s.getUser();
		
		if (s.getPositions().size()!=0) {
			for (Position p : s.getPositions()) {
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	public List<Position> getPositions() {
		return positions;
	}

	public void setPositions(List<Position> positions) {
		this.positions = positions;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public static List<StaffDto> getDtos(List<Staff> pos){
		ArrayList<StaffDto> dtos = new ArrayList<StaffDto>();
		for (Staff po : pos) {
			dtos.add(new StaffDto(po));
		}
		return dtos;
	}
	
	public String getUserName(){
		if (this.user==null) {
			return "";
		}
		return user.getName();
	}
	
}
