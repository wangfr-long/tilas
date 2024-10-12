package com.ithbu.tilas.service.impl;

import com.ithbu.tilas.mapper.DeptLogMapper;
import com.ithbu.tilas.mapper.DeptMapper;
import com.ithbu.tilas.mapper.EmpMapper;
import com.ithbu.tilas.pojo.Dept;
import com.ithbu.tilas.pojo.DeptLog;
import com.ithbu.tilas.pojo.Emp;
import com.ithbu.tilas.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    public DeptMapper deptMapper;
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private DeptLogServiceImpl deptLogService;

    public List<Dept> select() {
        List<Dept> select = deptMapper.select();
        return select;
    }

     @Transactional(rollbackFor = Exception.class)
    public void delete(Integer id) {
         try {
             empMapper.deleteByDeptId(id);
             int i=1/0;
             deptMapper.delete(id);
         }  finally {
             DeptLog deptLog = new DeptLog();
             deptLog.setCreateTime(LocalDateTime.now());
             deptLog.setDescription("执行了解散部门的操作解散的部门id是"+id);
             deptLogService.insert(deptLog);
         }
     }


    public void add(Dept dept) {
        deptMapper.add(dept);
    }

    public Dept selectById(Integer id) {
        Dept dept = deptMapper.selectById(id);
        return dept;
    }

    public void update(Dept dept) {
        deptMapper.update(dept);
    }
}
