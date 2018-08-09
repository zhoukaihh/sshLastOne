package com.qfedu.SSHDemo.service;

import com.qfedu.SSHDemo.vo.DataTable;

public interface DeptService {

	DataTable findBySearch(Integer start, Integer length, String search, String noDir);

}
