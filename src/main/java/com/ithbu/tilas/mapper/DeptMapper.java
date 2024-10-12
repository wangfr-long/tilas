package com.ithbu.tilas.mapper;

import com.ithbu.tilas.pojo.Dept;
import com.ithbu.tilas.pojo.Emp;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {
    @Select("select id, name, create_time, update_time from dept")
    public List<Dept> select();
    @Delete("delete from dept where id=#{id}")
     public void delete(Integer id);
    @Insert("insert into dept values (null,#{name},now(),now())")
    void add(Dept dept);
    @Select("select * from dept where id=#{id}")
    Dept selectById(Integer id);
    @Update("update dept set name=#{name},update_time=now() where id=#{id}")
    void update(Dept dept);
}
