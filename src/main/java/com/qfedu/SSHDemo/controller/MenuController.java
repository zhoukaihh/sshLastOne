package com.qfedu.SSHDemo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qfedu.SSHDemo.dto.MenuDto;
import com.qfedu.SSHDemo.service.MenuService;
import com.qfedu.SSHDemo.vo.DataTable;
import com.qfedu.SSHDemo.vo.Result;

@Controller
@RequestMapping("/menu")
public class MenuController {

	private final static Logger LOG = LogManager.getLogger(MainController.class);

	@Resource
	private MenuService menuService;
	
	/**
	 * 打开菜单列表
	 * @return
	 */
	@RequestMapping
	public String toHome(){
		return "menu/page";
	}
	
	/**
	 * 获取菜单列表
	 * 这个功能返回的是提供给Ajax的Jason格式数据
	 * @return
	 */
	@RequestMapping("/menus")
	@ResponseBody
	public DataTable menus(Integer draw,Integer start, Integer length, 
			@RequestParam("search[value]") String search, @RequestParam("order[0][dir]") String noDir){
		DataTable data = menuService.findBySearch(start,length,search,noDir);
		data.setDraw(++draw);
		
		return data;
	}
	
	/**
	 * 创建父菜单
	 * @return
	 */
	@RequestMapping(value ="/create",method=RequestMethod.GET)
	public String create (){
		return "menu/create";
	}
	
	@RequestMapping(value="/create",method=RequestMethod.POST)
	@ResponseBody
	public Result create(MenuDto dto){
		menuService.create(dto);
		return new Result().setSuccess(true).setMessage("创建父菜单成功");
	}
	
	/**
	 * 创建子菜单
	 * @return
	 */
	@RequestMapping(value="/createChild",method=RequestMethod.GET)
	public String createChild(Integer id,Map<String, Object>map){
		map.put("parentId", id);
		Integer id1= (Integer) map.get("parentId");
		return "menu/createChild";
	}
	
	
	/**
	 * 保存要创建的菜单
	 * @return
	 */
	@RequestMapping(value = "/createChild",method= RequestMethod.POST)
	@ResponseBody
	public Result createChild(MenuDto m){
		
		menuService.save(m);
		
		return new Result().setSuccess(true).setMessage("创建子菜单成功");		
	}
	
	/**
	 * 删除菜单
	 * @return
	 */
	@RequestMapping(value="/delete")
	@ResponseBody
	public Object deleteUsers(Integer[] id){
			menuService.deleteById(id);
	
		return new HashMap(){
			{
				put("success", true);
			}
		};
	} 
	
	/**
	 * 修改菜单
	 */
	@RequestMapping(value="update",method=RequestMethod.GET)
	public String update(Integer id,Map<String, Object>map){
		 
		MenuDto m = menuService.findById(id);
		map.put("menu", m); 
		return "menu/update";
	}
	
	@RequestMapping(value="update",method=RequestMethod.POST)
	@ResponseBody
	public Result update(MenuDto m){
		menuService.update(m);
		return new Result().setSuccess(true).setMessage("修改菜单成功");
	}
}
