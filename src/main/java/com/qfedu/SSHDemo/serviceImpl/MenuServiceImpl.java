package com.qfedu.SSHDemo.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.qfedu.SSHDemo.dao.MenuDao;
import com.qfedu.SSHDemo.dto.MenuDto;
import com.qfedu.SSHDemo.po.Menu;
import com.qfedu.SSHDemo.service.MenuService;
import com.qfedu.SSHDemo.vo.DataTable;

@Service
@Transactional
public class MenuServiceImpl implements MenuService {

	/**
	 * 引入menuDao
	 */
	@Resource
	private MenuDao menuDao;
	
	
	/**
	 * 查询顶级菜单
	 */
	@Override
	public List<MenuDto> getTopMenus() {
		List<Menu> pos = menuDao.getTopMenuParentIdIsNull();
	
		List<MenuDto> dtos = MenuDto.getDtos(pos, true);
		
		if (dtos.size()>0) {
			dtos.get(0).setActive(true);
			if (dtos.get(0).getChildren().size()>0) {
				dtos.get(0).getChildren().get(0).setActive(true);
			}
		}
		return dtos;
	}


	/**
	 * 查询菜单列表
	 */
	@Override
	public DataTable findBySearch(Integer start, Integer length, String search, String noDir) {
		List<Menu> pos = menuDao.findAll(start,length,search,noDir);
		Long count = menuDao.countAll(search);
		DataTable data = new DataTable();
		data.setData(MenuDto.getDtos(pos, false));
		data.setRecordsFiltered(count);
		data.setRecordsTotal(count);
		
		return data;
	}

	/**
	 * 创建父菜单
	 */
	@Override
	public void create(MenuDto dto) {
		Menu po = new Menu();
		po.setIcon(dto.getIcon());
		po.setName(dto.getName());
		po.setNo(dto.getNo());
		po.setUrl(dto.getUrl());
		
		menuDao.save(po);
	}

	/**
	 * 保存要添加的子菜单
	 */
	@Override
	public void save(MenuDto m) {
		Menu po = new Menu();
		po.setIcon(m.getIcon());
		po.setId(m.getId());
		po.setName(m.getName());
		po.setNo(m.getNo());
		po.setUrl(m.getUrl());
		
		Menu parent = new Menu();
		parent.setId(m.getParent().getId());
		po.setParent(parent);
		menuDao.save(po);
	}


	/**
	 * 批量删除菜单
	 */
	@Override
	public void deleteById(Integer[] id) {
		
		for (Integer i : id) {
			menuDao.deleteById(i);
		}
	}


	@Override
	public MenuDto findById(Integer id) {
		
		Menu m = menuDao.findById(id);
		return new MenuDto(m,false);
	}


	@Override
	public void update(MenuDto m) {

		Menu po = menuDao.findById(m.getId());
		po.setName(m.getName());
		po.setNo(m.getNo());
		Menu menu = new Menu();
		menu.setId(m.getParent().getId());
		po.setParent(menu);
		
		menuDao.update(po);
	}


	

}
