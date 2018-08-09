package com.qfedu.SSHDemo.dao;

import java.util.List;

import com.qfedu.SSHDemo.po.Position;

public interface PositionDao {

	List<Position> findAllBy(Integer start, Integer length, String search, String levelDir);

	Long countAllBy(String search);


}
