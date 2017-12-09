package com.zzz.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zzz.model.po.EmployeePo;
import com.zzz.model.vo.EmployeeVo;
import com.zzz.service.EmployeeService;
import com.google.common.base.Preconditions;
import com.zzz.dao.EmployeeRepository;

@Service
@Transactional(rollbackFor = Exception.class)
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<EmployeeVo> findALL() {
        List<EmployeePo> employeePos = employeeRepository.findAll();
        if (CollectionUtils.isEmpty(employeePos)) {
            return Collections.emptyList();
        }

        return employeePos.stream()
                .map(employeePo -> {
                    EmployeeVo employeeVo = new EmployeeVo();
                    BeanUtils.copyProperties(employeePo, employeeVo);
                    return employeeVo;
                })
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeVo findById(Integer id) {
        Preconditions.checkNotNull(id, "入参id不能为空！");

        EmployeePo employeePo = employeeRepository.findById(id);
        if (employeePo == null) {
            return null;
        }

        EmployeeVo employeeVo = new EmployeeVo();
        BeanUtils.copyProperties(employeePo, employeeVo);
        return employeeVo;
    }

    @Override
    public void save(EmployeeVo employeeVo) {
        Preconditions.checkNotNull(employeeVo, "入参employeeVo不能为空！");

        EmployeePo employeePo = new EmployeePo();
        BeanUtils.copyProperties(employeeVo, employeePo);

        employeeRepository.save(employeePo);
    }

    @Override
    public void update(Integer id, String name, Integer sex, String tel, String idNum) {
        Preconditions.checkNotNull(id, "入参id不能为空！");

        employeeRepository.update(id, name, sex, tel, idNum);
    }

}
