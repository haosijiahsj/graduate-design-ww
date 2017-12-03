package com.zzz.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zzz.model.po.EmployeePo;
import com.zzz.model.vo.EmployeeVo;
import com.zzz.service.EmployeeService;
import com.google.common.base.Preconditions;
import com.zzz.dao.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;
	@Override
	public List<EmployeeVo> findALL(Specification<EmployeePo> employeePo) {
		List<EmployeePo> list=employeeRepository.findALL(employeePo);
		List<EmployeeVo> resList=new ArrayList<EmployeeVo>();
		if(list==null){
			return null;
		}else{
			for (EmployeePo employeePo2 : list) {
				EmployeeVo employeeVo=new EmployeeVo();
				BeanUtils.copyProperties(employeePo2, employeeVo);
				resList.add(employeeVo);
			}
			
		}
		
		return resList;
	}

	@Override
	public EmployeeVo findById(long id) {
		EmployeePo employeePo=employeeRepository.findById(id);
		EmployeeVo employeeVo=new EmployeeVo();
		if(employeePo==null){
			return null;
		}else{
			
			BeanUtils.copyProperties(employeePo, employeeVo);
		}
		
		return employeeVo;
		
	}

	@Override
	public void save(EmployeePo employee) {
		employeeRepository.save(employee);

	}

	@Override
	public void update(Integer id, String name, Integer sex, String tel, String idNum) {
		Preconditions.checkNotNull(id, "入参id不能为空！");
		employeeRepository.update(id, name, sex, tel, idNum);

	}

}
