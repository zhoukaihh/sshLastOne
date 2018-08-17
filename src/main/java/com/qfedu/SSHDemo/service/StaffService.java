package com.qfedu.SSHDemo.service;

import com.qfedu.SSHDemo.dto.StaffDto;
import com.qfedu.SSHDemo.vo.DataTable;

public interface StaffService {

	DataTable findBySearch(Integer start, Integer length, String search, String nameDir);

	void create(StaffDto dto, Integer[] positionIds);

	void update(StaffDto dto, String oldMobile, Integer[] positionIds);

	StaffDto findById(Integer id);

	void deleteById(Integer[] ids);

}
