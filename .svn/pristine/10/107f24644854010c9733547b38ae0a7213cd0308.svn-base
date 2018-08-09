package com.qfedu.SSHDemo.service;

import java.util.List;

import com.qfedu.SSHDemo.dto.UserDto;
import com.qfedu.SSHDemo.po.User;
import com.qfedu.SSHDemo.vo.DataTable;

public interface UserService {

	List<UserDto> getAll();

	User authenticate(String loginName, String password);

	void save(UserDto u);

	void deleteById(Integer[] id);

	UserDto findById(Integer id);

	void update(UserDto u);

	DataTable findBySearch(Integer start, Integer length, String search, String loginDir);
}
