package com.zzz.controller;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.zzz.service.EmployeeService;
import com.zzz.support.ResponseEntity;

import com.zzz.model.vo.EmployeeVo;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping("/findALL")
	public ResponseEntity findALL(){
		List<EmployeeVo> list=employeeService.findALL();
		ResponseEntity responseEntity=new ResponseEntity();
		if(CollectionUtils.isEmpty(list)){
			responseEntity.setMsgCode(400);
			responseEntity.setMsgContent("查询失败");
			return responseEntity;
		}
		responseEntity.setMsgCode(200);
		responseEntity.setMsgContent("查询成功");
		responseEntity.setResult(list);
		return responseEntity;
	}
	
	@RequestMapping("/findById")
	public ResponseEntity findById(Integer id){
		EmployeeVo employeeVo=new EmployeeVo();
		ResponseEntity responseEntity=new ResponseEntity();
		if(employeeVo == null){
			responseEntity.setMsgCode(400);
			responseEntity.setMsgContent("查询失败");
			return responseEntity;
		}
		responseEntity.setMsgCode(200);
		responseEntity.setMsgContent("查询成功");
		responseEntity.setResult(employeeVo);
		return responseEntity;
	}

	@RequestMapping("/save")
	public ResponseEntity save(EmployeeVo employeeVo){
		ResponseEntity responseEntity=new ResponseEntity();
		try {
			employeeService.save(employeeVo);
		} catch (Exception e) {
			responseEntity.setMsgCode(400);
			responseEntity.setMsgContent("更新失败");
			return responseEntity;
		}
		responseEntity.setMsgCode(200);
		responseEntity.setMsgContent("更新成功");
		return responseEntity;
	}

	@RequestMapping("/update")
	public ResponseEntity update(Integer id, String name, Integer sex, String tel, String idNum){
		ResponseEntity responseEntity=new ResponseEntity();
		try {
			employeeService.update(id, name, sex, tel, idNum);
		} catch (Exception e) {
			responseEntity.setMsgCode(400);
			responseEntity.setMsgContent("更新失败");
			return responseEntity;
		}
		responseEntity.setMsgCode(200);
		responseEntity.setMsgContent("更新成功");
		return responseEntity;
	}
}
