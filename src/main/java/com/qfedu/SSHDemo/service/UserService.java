package com.qfedu.SSHDemo.service;

import java.util.List;

import com.qfedu.SSHDemo.dto.UserDto;
import com.qfedu.SSHDemo.po.User;
import com.qfedu.SSHDemo.vo.DataTable;

public interface UserService {

	List<UserDto> getAll();

	User authenticate(String loginName, String password);

	void save(UserDto u, Integer[] roleIds);

	UserDto findById(Integer id);

	void update(UserDto u, Integer[] roleIds);

	DataTable findBySearch(Integer start, Integer length, String search, String loginDir);

	void delete(Integer[] id, UserDto dto);
}
