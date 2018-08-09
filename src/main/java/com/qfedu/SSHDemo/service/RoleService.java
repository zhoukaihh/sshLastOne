package com.qfedu.SSHDemo.service;

import com.qfedu.SSHDemo.dto.RoleDto;
import com.qfedu.SSHDemo.vo.DataTable;

public interface RoleService {

	DataTable findBySearch(Integer start, Integer length, String search, String nameDir);

	void create(RoleDto r, Integer[] menuIds);

	RoleDto findById(Integer id);

}
