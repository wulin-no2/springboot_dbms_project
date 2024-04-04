package com.lina.mapper;

import com.lina.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {
    @Select("SELECT * FROM dept")
    List<Dept> list();
    @Delete("delete from dept where id = #{id}")
    void deleteById(Integer id);
    @Insert("insert into dept(name, create_time, update_time) values (#{name},#{createTime},#{updateTime})")

    void add(Dept dept);
    @Select("select * from dept where id = #{id}")
    Dept listById(Integer id);
    @Update("update dept set name = #{name},update_time = #{updateTime} where id = #{id} ")
    void update(Dept dept);
}
