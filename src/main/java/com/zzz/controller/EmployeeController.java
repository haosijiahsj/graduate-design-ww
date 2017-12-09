package com.zzz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.zzz.service.EmployeeService;
import com.zzz.model.po.EmployeePo;
import com.zzz.model.vo.EmployeeVo;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping("/findALL")
	List<EmployeeVo> findALL(){
		return employeeService.findALL();
	}
	
	@RequestMapping("/findById")
	EmployeeVo findById(Integer id){
		return employeeService.findById(id);
	}

	@RequestMapping("/save")
	void save(EmployeeVo employeeVo){
		employeeService.save(employeeVo);
	}

	@RequestMapping("/update")
	void update(Integer id, String name, Integer sex, String tel, String idNum){
		employeeService.update(id, name, sex, tel, idNum);
	}
}
