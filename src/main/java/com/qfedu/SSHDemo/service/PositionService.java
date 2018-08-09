package com.qfedu.SSHDemo.service;

import com.qfedu.SSHDemo.vo.DataTable;

public interface PositionService {

	DataTable findBySearch(Integer start, Integer length, String search, String levelDir);

}
