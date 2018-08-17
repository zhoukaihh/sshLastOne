package com.qfedu.SSHDemo.service;

import com.qfedu.SSHDemo.dto.DeptDto;
import com.qfedu.SSHDemo.vo.DataTable;

public interface DeptService {

	DataTable findBySearch(Integer start, Integer length, String search, String noDir);

	void save(DeptDto dto, Integer[] positionIds);

	DeptDto findById(Integer id);

	void update(DeptDto r);

	void create(DeptDto dto);

	void delete(Integer id);

}
