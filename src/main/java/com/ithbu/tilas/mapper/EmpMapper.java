package com.ithbu.tilas.mapper;

import com.ithbu.tilas.pojo.Emp;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {
//    @Select("select * from emp ")
    List<Emp> select(@Param("name") String name,@Param("gender") Short gender,
                     @Param("begin") LocalDate begin,@Param("end") LocalDate end);
    @Delete("delete from emp where dept_id=#{id}")
    void deleteByDeptId(@Param("id") Integer id);

    void delete(@Param("ids") Integer[] ids);
    @Options(keyProperty = "id",useGeneratedKeys = true)
    @Insert("insert into emp (image,username, name, gender, job, entrydate, dept_id, create_time, update_time)" +
            " VALUES (#{image},#{username},#{name},#{gender},#{job},#{entrydate},#{deptId},now(),now())")
    void insert(Emp emp);

    void update(Emp emp);
   @Select("select * from emp where id=#{id}")
    Emp selectOne(Integer id);
    @Select("select * from emp where username=#{username} and password=#{password}")
    Emp selectLogin(Emp emp);
}
