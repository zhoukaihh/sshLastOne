package com.qfedu.SSHDemo.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.qfedu.SSHDemo.dto.MenuDto;
import com.qfedu.SSHDemo.dto.RoleDto;
import com.qfedu.SSHDemo.service.MenuService;
import com.qfedu.SSHDemo.service.RoleService;
import com.qfedu.SSHDemo.vo.DataTable;
import com.qfedu.SSHDemo.vo.Result;

@Controller
@RequestMapping("/role")
@SessionAttributes("role")
public class RoleController {

	private final static Logger LOG = LogManager.getLogger(RoleController.class);

	/**
	 * 引入roleService
	 */
	@Resource
	private RoleService roleService;
	
	/**
	 * 打开角色列表
	 * @return
	 */
	@RequestMapping
	public String toHome(){
		return "role/page";
	}
	
	/**
	 * 获取用户列表
	 * 这个功能返回的是提供给Ajax的Jason格式数据
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public DataTable list(Integer draw,Integer start, Integer length, 
			@RequestParam("search[value]") String search, @RequestParam("order[0][dir]") String nameDir){
		DataTable data = roleService.findBySearch(start,length,search,nameDir);
		data.setDraw(++draw);
		
		return data;
	}
	
	/**
	 * 创建角色
	 */
	@RequestMapping(value="/create",method=RequestMethod.GET)
	public String create (){
		return "role/create";
	}
	
	/**
	 * 加载角色选择菜单的列表
	 */
	@RequestMapping(value="menus",method=RequestMethod.GET)
	public String selectMenus(){
		return "role/menuSelectorPage";
	}
	
	/**
	 * 提交创建角色列表数据
	 */
	@RequestMapping(value = "/create" , method=RequestMethod.POST)
	@ResponseBody
	public Result create(RoleDto r , Integer[] menuIds){
		roleService.create(r,menuIds);
		return new Result().setSuccess(true).setMessage("创建角色成功");
	}
	
	
	/**
	 * 引入menuService
	 */
	@Resource
	private MenuService menuService;
	
	/**
	 * 为选择页面添加菜单的列表供其选择
	 */
	@RequestMapping(value="/menu/list",method=RequestMethod.GET)
	@ResponseBody
	public DataTable menuList(Integer draw, Integer start, Integer length,
			@RequestParam("search[value]") String search, @RequestParam("order[0][dir]") String noDir){
		DataTable<MenuDto> dt = menuService.findBySearch(start, length, search, noDir);
		dt.setDraw(++draw);
		return dt;
	} 
	
	/**
	 * 修改角色
	 */
	@RequestMapping(value="update",method=RequestMethod.GET)
	public String updateRole(Integer id,Map<String, Object>map){
		RoleDto dto = roleService.findById(id);
		map.put("role", dto);
		return "role/update";
	}
	
	@RequestMapping(value="update" , method=RequestMethod.POST)
	@ResponseBody
	public Result updateRole(RoleDto r , Integer[] menuIds){
		roleService.update(r,menuIds);
		Result result = new Result().setSuccess(true).setMessage("修改成功");
		return result;
	}
	
	/**
	 * 执行删除角色
	 * @param id
	 * @return
	 */
	@RequestMapping(value="delete",method=RequestMethod.POST)
	@ResponseBody
	public Result deleteRole(Integer id){
		roleService.delete(id);
		return new Result().setSuccess(true).setMessage("删除角色成功");
	}
}
