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

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	
	@ResponseBody
	@RequestMapping("/findALL")
	List<EmployeeVo> findALL(Specification<EmployeePo> employeePo){
		return employeeService.findALL(employeePo);
	}
	
	@ResponseBody
	@RequestMapping("/findById")
	EmployeeVo findById(long id){
		return employeeService.findById(id);
	}
	@ResponseBody
	@RequestMapping("/save")
	void save(EmployeePo employee){
		employeeService.save(employee);
	}
	@ResponseBody
	@RequestMapping("/update")
	void update(Integer id, String name, Integer sex, String tel, String idNum){
		employeeService.update(id, name, sex, tel, idNum);
		
	}
}
