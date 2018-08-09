package com.qfedu.SSHDemo.dao;

import java.util.List;

import com.qfedu.SSHDemo.dto.MenuDto;
import com.qfedu.SSHDemo.po.Menu;

public interface MenuDao {



	List<Menu> getTopMenuParentIdIsNull();

	List<Menu> findAll(Integer start, Integer length, String search, String noDir);

	Long countAll(String search);

	void save(Menu po);

	void deleteById(Integer i);

	Menu findById(Integer id);

	void update(Menu po);

}
