package com.qfedu.SSHDemo.serviceImpl;

import java.util.ArrayList;
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
import com.qfedu.SSHDemo.po.Menu;
import com.qfedu.SSHDemo.po.Position;
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
	
	/**
	 * 查询部门列表信息
	 */
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

	/**
	 * 创建子部门
	 */
	@Override
	public void save(DeptDto d,Integer[] positionIds) {
		Dept po = new Dept();
		po.setName(d.getName());
		po.setDescription(d.getDescription());
		po.setNo(d.getNo());
		
		Dept parent = new Dept();
		parent.setId(d.getParent().getId());
		po.setParent(parent);
		
		deptDao.save(po);
	}

	/**
	 * 通过id查询部门
	 */
	@Override
	public DeptDto findById(Integer id) {
		Dept po = deptDao.findById(id);
		return new DeptDto(po, false);
	}

	/**
	 * 修改部门
	 */
	@Override
	public void update(DeptDto d) {
		Dept po = deptDao.findById(d.getId());
		po.setName(d.getName());
		po.setDescription(d.getDescription());
		po.setNo(d.getNo());
		
		deptDao.update(po);
	}

	/**
	 * 创建部门
	 */
	@Override
	public void create(DeptDto dto) {
		Dept po = new Dept();
		po.setName(dto.getName());
		po.setDescription(dto.getDescription());
		po.setNo(dto.getNo());
		
		deptDao.save(po);
	}

	/**
	 * 删除选中部门
	 */
	@Override
	public void delete(Integer id) {
		Dept po = deptDao.findById(id);
		deptDao.delete(po);
	}

}
