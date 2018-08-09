package com.qfedu.SSHDemo.serviceImpl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.qfedu.SSHDemo.dao.StaffDao;
import com.qfedu.SSHDemo.dto.StaffDto;
import com.qfedu.SSHDemo.po.Staff;
import com.qfedu.SSHDemo.service.StaffService;
import com.qfedu.SSHDemo.vo.DataTable;

@Service
@Transactional
public class StaffServiceImpl implements StaffService {

	private final static Logger LOG = LogManager.getLogger(StaffServiceImpl.class);

	/**
	 * 引入staffDao
	 */
	@Resource
	private StaffDao staffDao;
	
	@Override
	public DataTable<StaffDto> findBySearch(Integer start, Integer length, String search, String nameDir) {
		List<Staff> pos = staffDao.findBySearch( start,  length,  search,  nameDir);
		Long count = staffDao.count(search);
		DataTable<StaffDto> data = new DataTable<StaffDto>();
		
		data.setData(StaffDto.getDtos(pos));
		data.setRecordsFiltered(count);
		data.setRecordsTotal(count);
		
		return data;
	}

}
