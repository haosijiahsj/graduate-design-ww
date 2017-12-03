package com.zzz.dao;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.zzz.model.po.ConsumerPo;
import com.zzz.model.po.EmployeePo;

public interface EmployeeRepository extends Repository<ConsumerPo, Integer>{
	
	List<EmployeePo> findALL(Specification<EmployeePo> employeePo);
	
	EmployeePo findById(long id);
	
	void save(EmployeePo employee);

    @Modifying
    @Query("UPDATE employee e SET e.name_ = ?2, e.sex = ?3, e.tel = ?4, e.id_num=?5 WHERE u.id = ?1")
	void update(Integer id, String name, Integer sex, String tel, String idNum);
	
}
