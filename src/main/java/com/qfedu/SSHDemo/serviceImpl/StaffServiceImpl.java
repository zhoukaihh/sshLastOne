package com.qfedu.SSHDemo.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.qfedu.SSHDemo.dao.StaffDao;
import com.qfedu.SSHDemo.dao.UserDao;
import com.qfedu.SSHDemo.dto.StaffDto;
import com.qfedu.SSHDemo.po.Position;
import com.qfedu.SSHDemo.po.Staff;
import com.qfedu.SSHDemo.po.User;
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

	/**
	 * 通过id查询人员
	 */
	@Override
	public StaffDto findById(Integer id) {
		Staff po = staffDao.findById(id);
		return new StaffDto(po);
	}
	
	
	/**
	 * 引入userDao
	 */
	@Resource
	private UserDao userDao;
	
	/**
	 * 创建人员
	 */
	@Override
	public void create(StaffDto dto,Integer[] positionIds) {
		Staff po = new Staff();
		po.setName(dto.getName());
		po.setGender(dto.getGender());
		po.setBirthday(dto.getBirthday());
		po.setHeadImage(dto.getHeadImage());
		po.setMobile(dto.getMobile());
		User user = new User();
		user.setLoginName(dto.getMobile());
		user.setName("admin");
		user.setPassword("123123");
		userDao.save(user);
		po.setUser(user);
		ArrayList<Position> positions = new ArrayList<Position>();
		for (Integer id : positionIds) {
			Position position = new Position();
			position.setId(id);
			positions.add(position);
		}
		po.setPositions(positions);
		staffDao.save(po);
	}

	/**
	 * 修改人员信息
	 */
	@Override
	public void update(StaffDto dto,String oldMobile, Integer[] positionIds) {
		Staff po = new Staff();
		po.setId(dto.getId());
		po.setName(dto.getName());
		po.setGender(dto.getGender());
		po.setBirthday(dto.getBirthday());
		po.setHeadImage(dto.getHeadImage());
		po.setMobile(dto.getMobile());
//		设置人员关联的用户
		User user = userDao.findByMobile(oldMobile);
		user.setLoginName(dto.getMobile());
		po.setUser(user);
//		设置人员关联的职位
		ArrayList<Position> positions = new ArrayList<Position>();
		for (Integer id : positionIds) {
			Position position = new Position();
			position.setId(id);
			positions.add(position);
		}
		po.setPositions(positions);
//		保存修改的信息
		staffDao.update(po);
	}

	/**
	 * 删除职位
	 */
	@Override
	public void deleteById(Integer[] ids) {
		for (Integer id : ids) {
			Staff po = staffDao.findById(id);
			User user = userDao.findByMobile(po.getMobile());
			if (po != null) {
				staffDao.delete(po);
				userDao.delete(user);
			}
		}
	}



}
