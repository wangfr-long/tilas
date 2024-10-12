package com.ithbu.tilas.controller;

import com.ithbu.tilas.anno.Log;
import com.ithbu.tilas.pojo.Dept;
import com.ithbu.tilas.pojo.Emp;
import com.ithbu.tilas.pojo.Result;
import com.ithbu.tilas.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@Slf4j
@RequestMapping("/depts")
public class DeptController {
    @Autowired
    public DeptService deptService;

    /**
     * 查询全部部门数据
     * @return
     */
    @GetMapping
    public Result select(){
        log.info("查询全部员工数据");
        List<Dept> select = deptService.select();
        return  Result.success(select);
    }

    /**
     * 根据id删除部门
     * @param id
     * @return
     */
    @Log
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("删除部门信息");
        deptService.delete(id);
        return Result.success();
    }

    /**
     * 添加部门信息
     * @param dept
     * @return
     */
    @Log
    @PostMapping
    public Result add(@RequestBody Dept dept){
        log.info("添加部门信息");
        deptService.add(dept);
        return Result.success();
    }

    /**
     * 根据id查询部门
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result selectById(@PathVariable Integer id){
        log.info("根据id查询数据");
        Dept dept = deptService.selectById(id);
        return Result.success(dept);
    }
    @Log
    @PutMapping
    public Result update(@RequestBody Dept dept){
        log.info("修改部门信息");
        deptService.update(dept);
        return Result.success();
    }
}
