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

import com.qfedu.SSHDemo.dto.DeptDto;
import com.qfedu.SSHDemo.dto.PositionDto;
import com.qfedu.SSHDemo.dto.RoleDto;
import com.qfedu.SSHDemo.service.DeptService;
import com.qfedu.SSHDemo.service.PositionService;
import com.qfedu.SSHDemo.vo.DataTable;
import com.qfedu.SSHDemo.vo.Result;

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
	
	/**
	 * 创建部门
	 */
	@RequestMapping(value="/create",method=RequestMethod.GET)
	public String create(){
		return "dept/create";
	}
	
	@RequestMapping(value="/create",method=RequestMethod.POST)
	@ResponseBody
	public Result create(DeptDto dto){
		deptService.create(dto);
		return new Result().setSuccess(true).setMessage("创建部门成功");
	}
	
	
	
	/**
	 * 创建子部门
	 */
	@RequestMapping(value="/createChild",method=RequestMethod.GET)
	public String createChild(Integer id,Map<String, Object>map){
		map.put("parentId", id);
		return "dept/createChild";
	}
	
	@RequestMapping(value="/createchild",method=RequestMethod.POST)
	@ResponseBody
	public Result createChild(DeptDto dto,Integer[] positionIds){
		deptService.save(dto,positionIds);
		return new Result().setSuccess(true).setMessage("创建子部门成功");
	}
	
	/**
	 * 创建子部门时选择职位
	 */
	@RequestMapping(value="/positions")
	public String selectPositions(){
		return "dept/positionSelectorPage";
	}
	
	@Resource
	private PositionService positionService;
	
	@RequestMapping("/position/list")
	@ResponseBody
	public DataTable<PositionDto> listPositions(Integer draw,Integer start, Integer length, 
			@RequestParam("search[value]") String search, @RequestParam("order[0][dir]") String levelDir){
		DataTable<PositionDto> data = positionService.findBySearch(start,length,search,levelDir);
		data.setDraw(++draw);
		
		return data;
	}
	
	/**
	 * 修改部门信息
	 */
	@RequestMapping(value="update",method=RequestMethod.GET)
	public String updateDept(Integer id,Map<String, Object>map){
		DeptDto dto = deptService.findById(id);
		map.put("dept", dto);
		return "dept/update";
	}
	
	@RequestMapping(value="update" , method=RequestMethod.POST)
	@ResponseBody
	public Result updateDept(DeptDto r){
		deptService.update(r);
		Result result = new Result().setSuccess(true).setMessage("修改成功");
		return result;
	}
	
	/**
	 * 删除选中部门
	 */
	@RequestMapping(value= "/delete",method=RequestMethod.POST)
	@ResponseBody
	public Result delete(Integer id){
		deptService.delete(id);
		return new Result().setSuccess(true).setMessage("删除部门成功");
	}
}
