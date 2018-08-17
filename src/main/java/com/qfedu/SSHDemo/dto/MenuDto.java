package com.qfedu.SSHDemo.dto;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.qfedu.SSHDemo.po.Menu;



public class MenuDto {

	private final static Logger LOG = LogManager.getLogger(MenuDto.class);


	private Integer id;
	
	private String name;
	
	private String url;
	
	private String icon;
	
	private Boolean active=false;
	
	private String no;
			
	private MenuDto parent;
	
	private String parentName;
	
	private List<MenuDto> children = new ArrayList<MenuDto>();

	
	public MenuDto() {
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public MenuDto getParent() {
		return parent;
	}

	public void setParent(MenuDto parent) {
		this.parent = parent;
	}

	public List<MenuDto> getChildren() {
		return children;
	}

	public void setChildren(List<MenuDto> children) {
		this.children = children;
	}

	public String getActiveCls(){
		if (active) {
			return "active";
		}
		return "";
	}
	
	
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getParentName() {
		if (this.parent == null) {
			return "";
		}
		return parent.getName();
	}


	/**
	 * 这是菜单嵌套的实现-1
	 * @param m
	 * @param loadChildren
	 */
	public MenuDto (Menu m,Boolean loadChildren){
		
			this.id=m.getId();
			this.icon=m.getIcon();
			this.name=m.getName();
			this.no=m.getNo();
			this.url=m.getUrl();
		
			if (m.getParent() != null) {
				this.parent = new MenuDto(m.getParent(),false); 
			}
			if (loadChildren) {
				this.children = MenuDto.getDtos(m.getChildren(),false);
			}
	}


	/**
	 * 这是菜单嵌套的实现-2
	 * @param m
	 * @param loadChildren
	 */
	public static List<MenuDto> getDtos(List<Menu> pos, boolean loadChildren) {
		List<MenuDto> dtos = new ArrayList<MenuDto>();
		
		for (Menu po : pos) {
			dtos.add(new MenuDto(po,loadChildren));
		}
		return dtos;
	}
	
}
