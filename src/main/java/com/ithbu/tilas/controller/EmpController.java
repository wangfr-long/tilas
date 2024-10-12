package com.ithbu.tilas.controller;

import com.ithbu.tilas.anno.Log;
import com.ithbu.tilas.pojo.Emp;
import com.ithbu.tilas.pojo.Result;
import com.ithbu.tilas.pojo.pageBean;
import com.ithbu.tilas.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {
    @Autowired
    public EmpService empService;
    @GetMapping
    public Result selectLimit(String name, Short gender, @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin, @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end, @RequestParam(defaultValue = "1") Integer page,
                              @RequestParam(defaultValue = "10") Integer pageSize){
        log.info("分页查询");
        pageBean pageBean = empService.selectLimit(name,gender,begin,end,page, pageSize);
        return Result.success(pageBean);

    }
    @Log
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable Integer[]ids){
        log.info("删除员工");
        empService.delete(ids);
        return Result.success();
    }
    @Log
    @PostMapping
    public Result insert(@RequestBody Emp emp){
        log.info("添加员工");
        empService.insert(emp);
        return Result.success();
    }
    @GetMapping("/{id}")
    public Result select(@PathVariable Integer id){
        log.info("查询员工信息，{}",id);
        Emp select = empService.select(id);
        return Result.success(select);
    }
    @Log
    @PutMapping
    public Result update (@RequestBody Emp emp){
        log.info("修改员工数据",emp.getName());
        empService.upate(emp);
        return Result.success();
    }
}
