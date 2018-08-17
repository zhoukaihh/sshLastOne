package com.qfedu.SSHDemo.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.qfedu.SSHDemo.dao.RoleDao;
import com.qfedu.SSHDemo.dto.RoleDto;
import com.qfedu.SSHDemo.po.Menu;
import com.qfedu.SSHDemo.po.Role;
import com.qfedu.SSHDemo.po.User;
import com.qfedu.SSHDemo.service.RoleService;
import com.qfedu.SSHDemo.vo.DataTable;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

	private final static Logger LOG = LogManager.getLogger(RoleServiceImpl.class);

	
	/**
	 *引入roleDao 
	 */
	@Resource
	private RoleDao roleDao;
	
	/**
	 *查询角色列表信息
	 */
	@Override
	public DataTable findBySearch(Integer start, Integer length, String search, String nameDir) {
		List<Role> roles = roleDao.findAllBy( start,  length,  search,  nameDir);
		Long count = roleDao.countAllBy(search);
		DataTable data = new DataTable();
		data.setData(RoleDto.getDtos(roles));
		data.setRecordsFiltered(count);
		data.setRecordsTotal(count);
		
		return data;
	}

	/**
	 *创建角色
	 */
	@Override
	public void create(RoleDto r, Integer[] menuIds) {

		Role role = new Role();
		role.setDescription(r.getDescription());
		role.setName(r.getName());
		ArrayList<Menu> menus = new ArrayList<Menu>();
		for (Integer id : menuIds) {
			Menu menu = new Menu();
			menu.setId(id);
			menus.add(menu);
		}
		role.setMenus(menus);
		
		roleDao.create(role);
	}

	/**
	 *通过id查询角色
	 */
	@Override
	public RoleDto findById(Integer id) {
		Role r = roleDao.faindById(id);
		return new RoleDto(r);
	}

	/**
	 *修改角色信息
	 */
	@Override
	public void update(RoleDto r, Integer[] menuIds) {
		Role po = roleDao.faindById(r.getId());
		
		po.setName(r.getName());
		po.setDescription(r.getDescription());
		
		ArrayList<Menu> menus = new ArrayList<Menu>();
		for (Integer id : menuIds) {
			Menu menu = new Menu();
			menu.setId(id);
			menus.add(menu);
		}
		po.setMenus(menus);
		
		roleDao.update(po);
	}

	/**
	 *删除批量或单个角色
	 */
	@Override
	public void delete(Integer[] ids) {
		for (Integer id : ids) {
			Role po = roleDao.faindById(id);
			roleDao.delete(po);
		}
	}

}
