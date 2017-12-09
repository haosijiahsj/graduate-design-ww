package com.zzz.service;

import java.util.List;

import com.zzz.model.vo.EmployeeVo;

/**
 * Created by 胡胜钧 on 12/2 0002.
 */
public interface EmployeeService {
	
	List<EmployeeVo> findALL();
	
	EmployeeVo findById(Integer id);
	
	void save(EmployeeVo employeeVo);

	void update(Integer id, String name, Integer sex, String tel, String idNum);
}
