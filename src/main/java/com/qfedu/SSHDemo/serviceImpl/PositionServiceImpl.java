package com.qfedu.SSHDemo.serviceImpl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.qfedu.SSHDemo.dao.PositionDao;
import com.qfedu.SSHDemo.dto.PositionDto;
import com.qfedu.SSHDemo.po.Dept;
import com.qfedu.SSHDemo.po.Position;
import com.qfedu.SSHDemo.service.PositionService;
import com.qfedu.SSHDemo.vo.DataTable;

@Service
@Transactional
public class PositionServiceImpl implements PositionService {

	/**
	 * 引入positionDao
	 */
	@Resource
	private PositionDao positionDao;
	
	@Override
	public DataTable<PositionDto> findBySearch(Integer start, Integer length, String search, String loginDir) {
		List<Position> pos = positionDao.findAllBy(start,length,search,loginDir);
		Long count = positionDao.countAllBy(search);
		DataTable<PositionDto> data = new DataTable();
		data.setData(PositionDto.getDtos(pos));
		data.setRecordsFiltered(count);
		data.setRecordsTotal(count);
		
		return data;
	}

	/**
	 * 创建职位
	 */
	@Override
	public void create(PositionDto dto) {
		Position po = new Position();
		po.setName(dto.getName());
		po.setDescription(dto.getDescription());
		po.setLevel(dto.getLevel());
		
		positionDao.save(po);
	}
	
	/**
	 * 通过id查找职位
	 */
	@Override
	public PositionDto findById(Integer id) {
		Position po = positionDao.findById(id);
		return new PositionDto(po);
	}
	
	/**
	 * 修改职位信息
	 */
	@Override
	public void update(PositionDto dto) {
		Position po = positionDao.findById(dto.getId());
		po.setName(dto.getName());
		po.setDescription(dto.getDescription());
		po.setLevel(dto.getLevel());
		
		positionDao.update(po);
	}
	
	
	/**
	 * 删除职位
	 */
	@Override
	public void delete(Integer[] ids) {
		for (Integer id : ids) {
			Position po = positionDao.findById(id);
			Dept dept = new Dept();
			po.setDept(dept);
			positionDao.delete(po);
		}
		
	}


}
