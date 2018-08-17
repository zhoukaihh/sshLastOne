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

import com.qfedu.SSHDemo.dto.PositionDto;
import com.qfedu.SSHDemo.dto.UserDto;
import com.qfedu.SSHDemo.po.User;
import com.qfedu.SSHDemo.service.RoleService;
import com.qfedu.SSHDemo.service.UserService;
import com.qfedu.SSHDemo.vo.DataTable;
import com.qfedu.SSHDemo.vo.Result;

@Controller
@RequestMapping("/user")
@SessionAttributes ("user")
public class UserController {
	
	private final static Logger LOG = LogManager.getLogger(MainController.class);

	@Resource
	private UserService userService;
	
	/**
	 * 打开用户列表
	 * @return
	 */
	@RequestMapping
	public String toHome(){
		return "user/page";
	}
	
	/**
	 * 获取用户列表
	 * 这个功能返回的是提供给Ajax的Jason格式数据
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public DataTable list(Integer draw,Integer start, Integer length, 
			@RequestParam("search[value]") String search, @RequestParam("order[0][dir]") String loginDir){
		DataTable data = userService.findBySearch(start,length,search,loginDir);
		data.setDraw(++draw);
		
		return data;
	}
	
	
	
	/**
	 * 创建用户
	 * @return
	 */
	@RequestMapping(value="/create",method=RequestMethod.GET)
	public String create(){
		
		return "user/create";
	}
	
	
	/**
	 * 保存要创建的用户
	 * @return
	 */
	@RequestMapping(value = "/create",method= RequestMethod.POST)
	@ResponseBody
	public Result create(UserDto u,Integer[] roleIds){
		
		userService.save(u,roleIds);
		return new Result().setSuccess(true).setMessage("添加用户成功");		
	}
	
	/**
	 * 用户选择角色的主页面加载
	 */
	@RequestMapping("/roles")
	public String selectRoles(){
		return "user/roleSelectorPage";
	}
	
	@Resource
	private RoleService roleService;
	
	@RequestMapping(value="/role/list",method=RequestMethod.GET)
	@ResponseBody
	public DataTable<PositionDto> listPositions(Integer draw, Integer start, Integer length,
			@RequestParam("search[value]") String search, @RequestParam("order[0][dir]") String noDir) {
		DataTable<PositionDto> dt = roleService.findBySearch(start, length, search, noDir);
		dt.setDraw(++draw);
		return dt;
	}
	
	
	/**
	 * 修改用户
	 */
	@RequestMapping(value="update",method=RequestMethod.GET)
	public String update(Integer id,Map<String, Object>map){
		 
		UserDto u = userService.findById(id);
		map.put("user", u); 
		return "user/update";
	}
	
	@RequestMapping(value="update",method=RequestMethod.POST)
	public String update(UserDto u,Integer[] roleIds){
		userService.update(u,roleIds);
		return "redirect:/";
	}
	
	/**
	 * 删除用户
	 * @return
	 */
	@RequestMapping(value="/delete")
	@ResponseBody
	public Object deleteUsers(Integer[] id,Map<String, Object>map){
		UserDto dto = (UserDto) map.get("user");
		
			userService.delete(id,dto);
		return new Result().setSuccess(true).setMessage("删除用户成功");	
	} 
	
}
