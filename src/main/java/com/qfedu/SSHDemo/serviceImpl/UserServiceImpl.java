package com.qfedu.SSHDemo.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.qfedu.SSHDemo.dao.UserDao;
import com.qfedu.SSHDemo.dto.UserDto;
import com.qfedu.SSHDemo.po.User;
import com.qfedu.SSHDemo.service.UserService;
import com.qfedu.SSHDemo.vo.DataTable;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	/**
	 * 查询所有的数据
	 */
	@Resource
	private UserDao userDao;
	
	public List<UserDto> getAll() {
//		查询出所有的user
		List<User> pos = userDao.getAll();
//		创建一个dto数组
		ArrayList<UserDto> dots = new ArrayList<>();
//		遍历pos并将每一个数据的值赋给dto
		for (User u : pos) {
			UserDto dto = new UserDto(u);
			dots.add(dto);
		}
		
		return dots;
	}

	/**
	 * 登陆验证
	 */
	@Override
	public User authenticate(String loginName, String password) {
		User user = userDao.getByName(loginName);
		if (user == null) {
			throw new RuntimeException("您输入的用户或密码有误");
		}
		if (!user.getPassword().equals(password)) {
			throw new RuntimeException("您输入的用户或密码有误");
		}
		return user;
	}

	/**
	 * 保存用户，这里要将Dto转成po
	 */
	@Override
	public void save(UserDto u) {
		User user = new User();
		
		user.setLoginName(u.getLoginName());
		user.setName(u.getName());
		user.setPassword(u.getPassword());
		user.setGender(u.getGender());
		user.setCreateTime(u.getCreateTime());
		userDao.save(user);	
	}

	@Override
	public void deleteById(Integer[] ids) {
		for (Integer id : ids) {
			userDao.deleteById(id);	
		}
			
	}

	@Override
	public UserDto findById(Integer id) {
		
		User user = userDao.findById(id);
		
		return new UserDto(user);
	}

	@Override
	public void update(UserDto u) {
			User po = userDao.findById(u.getId());
			 
			po.setLoginName(u.getLoginName());
			po.setName(u.getName());
			userDao.update(po);
	}

	@Override
	public DataTable findBySearch(Integer start, Integer length, String search, String loginDir) {
		List<User> pos = userDao.findAll(start,length,search,loginDir);
		Long count = userDao.countAll(search);
		DataTable data = new DataTable();
		data.setData(UserDto.getDtos(pos));
		data.setRecordsFiltered(count);
		data.setRecordsTotal(count);
		
		return data;
	}

}
