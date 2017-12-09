package com.zzz.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.zzz.model.po.EmployeePo;

public interface EmployeeRepository extends Repository<EmployeePo, Integer>{
	
	List<EmployeePo> findAll();
	
	EmployeePo findById(Integer id);
	
	void save(EmployeePo employee);

    @Modifying
    @Query(value = "UPDATE EmployeePo e SET e.name = ?2, e.sex = ?3, e.tel = ?4, e.idNum=?5 WHERE e.id = ?1")
	void update(Integer id, String name, Integer sex, String tel, String idNum);
	
}
