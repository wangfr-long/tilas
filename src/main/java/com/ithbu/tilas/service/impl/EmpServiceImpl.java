package com.ithbu.tilas.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ithbu.tilas.mapper.EmpMapper;
import com.ithbu.tilas.pojo.Emp;
import com.ithbu.tilas.pojo.pageBean;
import com.ithbu.tilas.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;

    public pageBean selectLimit(String name, Short gender, LocalDate begin, LocalDate end, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Emp> emps = empMapper.select(name, gender, begin, end);
        Page<Emp> p = (Page<Emp>) emps;
        pageBean pageBean = new pageBean(p.getTotal(), p.getResult());
        return pageBean;
    }

    @Override
    public void delete(Integer[] ids) {
        empMapper.delete(ids);
    }

    @Override
    public void insert(Emp emp) {
        empMapper.insert(emp);
    }

    @Override
    public void upate(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);
    }

    @Override
    public Emp select(Integer id) {
        Emp emp = empMapper.selectOne(id);
        return emp;
    }


    public Emp login(Emp emp) {
         return empMapper.selectLogin(emp);
    }
}
