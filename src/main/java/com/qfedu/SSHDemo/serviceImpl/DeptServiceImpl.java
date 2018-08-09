package com.qfedu.SSHDemo.serviceImpl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.qfedu.SSHDemo.controller.DeptController;
import com.qfedu.SSHDemo.dao.DeptDao;
import com.qfedu.SSHDemo.dto.DeptDto;
import com.qfedu.SSHDemo.po.Dept;
import com.qfedu.SSHDemo.service.DeptService;
import com.qfedu.SSHDemo.vo.DataTable;

@Service
@Transactional
public class DeptServiceImpl implements DeptService {

	private final static Logger LOG = LogManager.getLogger(DeptServiceImpl.class);

	
	/**
	 * 引入deptDao
	 */
	@Resource
	private DeptDao deptDao;
	
	@Override
	public DataTable findBySearch(Integer start, Integer length, String search, String noDir) {
		List<Dept> pos = deptDao.findAllBy(start,length,search,noDir);
		Long count = deptDao.count(search);
		DataTable<DeptDto> data = new DataTable<DeptDto>();
//		把po对象转换成dto对象，存入到data里面
		data.setData(DeptDto.getDtos(pos, false));
		List<DeptDto> datas = data.getData();
		for (DeptDto deptDto : datas) {
			LOG.info(deptDto);
		}
		data.setRecordsFiltered(count);
		data.setRecordsTotal(count);
		return data;
	}

}
