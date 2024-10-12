package com.ithbu.tilas.service;

import com.ithbu.tilas.pojo.Dept;
import com.ithbu.tilas.pojo.Emp;
import org.springframework.stereotype.Service;

import java.util.List;


public interface DeptService {
    public List<Dept> select();

    public void delete(Integer id);

    void add(Dept dept);

    Dept selectById(Integer id);

    void update(Dept dept);
}
