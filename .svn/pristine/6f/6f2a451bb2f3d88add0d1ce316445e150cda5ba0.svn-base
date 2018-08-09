package com.qfedu.SSHDemo.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.qfedu.SSHDemo.dto.MenuDto;
import com.qfedu.SSHDemo.dto.UserDto;
import com.qfedu.SSHDemo.po.User;
import com.qfedu.SSHDemo.service.MenuService;
import com.qfedu.SSHDemo.service.UserService;



@Controller
@SessionAttributes("user")
public class MainController {

	private final static Logger LOG = LogManager.getLogger(MainController.class);

	@Resource
	private UserService userService;
	
	/**
	 * 表单的加载
	 * @return
	 */
	@RequestMapping(value = "/login",method = RequestMethod.GET)
	public String login(String error, Map<String, Object>map){
		map.put("error", error);
		return "login";
	}
	
	/**
	 * 登陆表单提交
	 * @return
	 */
	@RequestMapping(value = "/login",method = RequestMethod.POST)
	public String login(User u , Map<String, Object> map){
		User user = userService.authenticate(u.getLoginName(),u.getPassword());
		map.put("user", user);
		return "redirect:/";
	}
	
	
	/**
	 * 退出登录
	 * @return
	 */
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String logout(Map<String, Object> map,SessionStatus status){
		map.remove("user");
		status.setComplete();

		return "redirect:/login";
	}
	
	
	/**
	 * 引入menuService
	 * @return
	 */
	@Resource
	private MenuService menuService; 
	
	/**
	 * 跳转到主页面
	 * @return
	 */
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String main(Map<String, Object>map){
		if (map.get("user")==null) {
			return "login";
		}
		
		List<MenuDto> menus = menuService.getTopMenus();
		map.put("menus", menus);
		return "main";
	}
}
