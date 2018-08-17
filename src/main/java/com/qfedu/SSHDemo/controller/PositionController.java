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

import com.qfedu.SSHDemo.dto.PositionDto;
import com.qfedu.SSHDemo.service.PositionService;
import com.qfedu.SSHDemo.vo.DataTable;
import com.qfedu.SSHDemo.vo.Result;

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
	
	/**
	 * 创建职位
	 */
	@RequestMapping(value="/create",method=RequestMethod.GET)
	public String create(){
		return "position/create";
	}
	
	@RequestMapping(value="/create",method=RequestMethod.POST)
	@ResponseBody
	public Result create(PositionDto dto){
		positionService.create(dto);
		return new Result().setSuccess(true).setMessage("创建职位成功");
	}
	
	/**
	 * 修改职位
	 */
	@RequestMapping(value="/update",method=RequestMethod.GET)
	public String update(Integer id,Map<String, Object>map){
		PositionDto dto = positionService.findById(id);
		map.put("position", dto);
		return "position/update";
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	@ResponseBody
	public Result update(PositionDto dto){
		positionService.update(dto);
		return new Result().setSuccess(true).setMessage("修改职位成功");
	}
	
	/**
	 * 删除职位
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Result delete(Integer[] id){
		positionService.delete(id);
		return new Result().setSuccess(true).setMessage("删除职位成功");
	}
	
}
