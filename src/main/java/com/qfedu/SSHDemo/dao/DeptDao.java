package com.qfedu.SSHDemo.dao;

import java.util.List;

import com.qfedu.SSHDemo.po.Dept;

public interface DeptDao {

	List<Dept> findAllBy(Integer start, Integer length, String search, String noDir);

	Long count(String search);


}
