package com.qfedu.SSHDemo.serviceImpl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qfedu.SSHDemo.dao.PositionDao;
import com.qfedu.SSHDemo.dto.PositionDto;
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

}
