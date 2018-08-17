package com.qfedu.SSHDemo.controller;

import java.io.File;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.qfedu.SSHDemo.dto.PositionDto;
import com.qfedu.SSHDemo.dto.StaffDto;
import com.qfedu.SSHDemo.service.PositionService;
import com.qfedu.SSHDemo.service.StaffService;
import com.qfedu.SSHDemo.vo.DataTable;
import com.qfedu.SSHDemo.vo.Result;

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
	
	/**
	 * 创建人员
	 * @return
	 */
	@RequestMapping(value="/create",method=RequestMethod.GET)
	public String create(){
		return "staff/create";
	}
	
	@RequestMapping(value="/create",method=RequestMethod.POST)
	@ResponseBody
	public Result create(StaffDto dto,Integer[] positionIds, MultipartFile headImage1, HttpServletRequest req){
		try {
			staffService.create(dto,positionIds);
			String fileName = headImage1.getOriginalFilename();
			String dirPath ="D:/doc/CD-1802/codes/SelfSSHDemo/src/main/webapp/files";
			String dirPath1 =req.getRealPath("/files");
			File dir = new File (dirPath);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			headImage1.transferTo(new File (dirPath + "/" + fileName));
			headImage1.transferTo(new File (dirPath1 + "/" + fileName));
			return new Result().setSuccess(true).setMessage("文件上传成功，创建人员成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result().setMessage("文件上传失败！");
		}
		
	}
	
	/**
	 * 人员选择职位的主页面加载
	 */
	@RequestMapping("/positions")
	public String selectPositions(){
		return "staff/positionSelectorPage";
	}
	
	@Resource
	private PositionService positionService;
	
	@RequestMapping(value="/position/list",method=RequestMethod.GET)
	@ResponseBody
	public DataTable<PositionDto> listPositions(Integer draw, Integer start, Integer length,
			@RequestParam("search[value]") String search, @RequestParam("order[0][dir]") String noDir) {
		DataTable<PositionDto> dt = positionService.findBySearch(start, length, search, noDir);
		dt.setDraw(++draw);
		return dt;
	}
	
	/**
	 * 修改人员信息
	 */
	@RequestMapping(value="update",method=RequestMethod.GET)
	public String update(Integer id,Map<String, Object>map){
		StaffDto dto = staffService.findById(id);
		map.put("staff", dto);
		return "staff/update";
	}
	
	@RequestMapping(value="update",method=RequestMethod.POST)
	@ResponseBody
	public Result update(StaffDto dto ,String oldMobile, Integer[] positionIds){
		staffService.update(dto,oldMobile,positionIds);
		return new Result().setSuccess(true).setMessage("修改人员成功");
	}
	
	/**
	 * 删除职位
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Result delete(Integer[] id){
		staffService.deleteById(id);
		return new Result().setSuccess(true).setMessage("删除职位成功");
	}
}
