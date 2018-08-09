package com.qfedu.SSHDemo.controller;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qfedu.SSHDemo.dto.StaffDto;
import com.qfedu.SSHDemo.service.StaffService;
import com.qfedu.SSHDemo.vo.DataTable;

@Controller
@RequestMapping("/staff")
public class StaffController {

	private final static Logger LOG = LogManager.getLogger(StaffController.class);

	/**
	 * 引入staffService
	 */
	@Resource
	private StaffService staffService;
	
	/**
	 * 打开员工列表
	 * @return
	 */
	@RequestMapping
	public String toHome(){
		return "staff/page";
	}
	
	/**
	 * 获取用户列表
	 * 这个功能返回的是提供给Ajax的Jason格式数据
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public DataTable<StaffDto> list(Integer draw,Integer start, Integer length, 
			@RequestParam("search[value]") String search, @RequestParam("order[0][dir]") String nameDir){
		DataTable<StaffDto> data = staffService.findBySearch(start,length,search,nameDir);
		data.setDraw(++draw);
		
		return data;
	}
	
}
