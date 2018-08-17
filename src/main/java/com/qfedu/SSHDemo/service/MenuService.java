package com.qfedu.SSHDemo.service;

import java.util.List;

import com.qfedu.SSHDemo.dto.MenuDto;
import com.qfedu.SSHDemo.vo.DataTable;

public interface MenuService {

	List<MenuDto> getTopMenus();

	DataTable findBySearch(Integer start, Integer length, String search, String noDir);

	void save(MenuDto m);

	void deleteById(Integer[] id);
	
	MenuDto findById(Integer id);

	void update(MenuDto m);

	void create(MenuDto dto);
}
