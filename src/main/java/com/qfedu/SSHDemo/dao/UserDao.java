package com.qfedu.SSHDemo.dao;

import java.util.List;

import com.qfedu.SSHDemo.po.User;

public interface UserDao {

	List<User> getAll();

	User getByName(String loginName);

	void save(User u);

	User findById(Integer id);

	void update(User user);

	List<User> findAll(Integer start, Integer length, String search, String loginDir);

	Long countAll(String search);

	User findByMobile(String mobile);

	void delete(User po);
}
