package com.ithbu.tilas.service;

import com.ithbu.tilas.pojo.Emp;
import com.ithbu.tilas.pojo.pageBean;

import java.time.LocalDate;


public interface EmpService {
    pageBean selectLimit(String name, Short gender, LocalDate begin, LocalDate end, Integer page, Integer pageSize);

    void delete(Integer[] ids);

    void insert(Emp emp);

    void upate(Emp emp);

    Emp select(Integer id);

    Emp login(Emp emp);
}
