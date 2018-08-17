package com.qfedu.SSHDemo.service;

import com.qfedu.SSHDemo.dto.PositionDto;
import com.qfedu.SSHDemo.vo.DataTable;

public interface PositionService {

	DataTable findBySearch(Integer start, Integer length, String search, String levelDir);


	void create(PositionDto dto);

	PositionDto findById(Integer id);

	void update(PositionDto dto);
	
	void delete(Integer[] id);

}
