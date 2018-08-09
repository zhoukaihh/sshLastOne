package com.qfedu.SSHDemo.dao;

import java.util.List;

import com.qfedu.SSHDemo.po.Staff;

public interface StaffDao {

	List<Staff> findBySearch(Integer start, Integer length, String search, String nameDir);

	Long count(String search);

}
