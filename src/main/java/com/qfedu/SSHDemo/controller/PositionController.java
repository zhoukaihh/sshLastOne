package com.qfedu.SSHDemo.controller;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qfedu.SSHDemo.dto.PositionDto;
import com.qfedu.SSHDemo.service.PositionService;
import com.qfedu.SSHDemo.vo.DataTable;

@Controller
@RequestMapping("/position")
public class PositionController {

	private final static Logger LOG = LogManager.getLogger(PositionController.class);

	/**
	 * 引入positioService
	 * @return
	 */
	@Resource
	private PositionService positionService;
	
	/**
	 * 打开职位列表
	 * @return
	 */
	@RequestMapping
	public String toHome(){
		return "position/page";
	}
	
	/**
	 * 获取职位列表
	 * 这个功能返回的是提供给Ajax的Jason格式数据
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public DataTable<PositionDto> list(Integer draw,Integer start, Integer length, 
			@RequestParam("search[value]") String search, @RequestParam("order[0][dir]") String levelDir){
		DataTable<PositionDto> data = positionService.findBySearch(start,length,search,levelDir);
		data.setDraw(++draw);
		
		return data;
	}
	
	
	
}
