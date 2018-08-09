package com.qfedu.SSHDemo.controller;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qfedu.SSHDemo.service.DeptService;
import com.qfedu.SSHDemo.vo.DataTable;

@Controller
@RequestMapping("/dept")
public class DeptController {

	
	private final static Logger LOG = LogManager.getLogger(DeptController.class);

	/**
	 * 引入deptDao
	 */
	@Resource
	private DeptService deptService;
	
	/**
	 * 打开部门列表
	 * @return
	 */
	@RequestMapping
	public String toHome(){
		return "dept/page";
	}
	
	/**
	 * 获取部门列表数据
	 */
	@RequestMapping("/list" )
	@ResponseBody
	public DataTable list(Integer draw,Integer start, Integer length, 
			@RequestParam("search[value]") String search, @RequestParam("order[0][dir]") String noDir){
		DataTable data = deptService.findBySearch(start,length,search,noDir);
		data.setDraw(++draw);
		
		return data;
	}
		
	
}
