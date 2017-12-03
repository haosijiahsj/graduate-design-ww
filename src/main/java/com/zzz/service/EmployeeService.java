package com.zzz.service;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.zzz.model.po.EmployeePo;
import com.zzz.model.vo.EmployeeVo;

/**
 * Created by 胡胜钧 on 12/2 0002.
 */
public interface EmployeeService {
	
	List<EmployeeVo> findALL(Specification<EmployeePo> employeePo);
	
	EmployeeVo findById(long id);
	
	void save(EmployeePo employee);

	void update(Integer id, String name, Integer sex, String tel, String idNum);
}
