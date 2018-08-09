package com.qfedu.SSHDemo.dao;

import java.util.List;

import com.qfedu.SSHDemo.po.Role;

public interface RoleDao {

	List<Role> findAllBy(Integer start, Integer length, String search, String nameDir);

	Long countAllBy(String search);

	void create(Role role);

	Role faindById(Integer id);

}
