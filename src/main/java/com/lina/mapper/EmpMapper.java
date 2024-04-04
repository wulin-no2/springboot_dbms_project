package com.lina.mapper;

import com.lina.pojo.Emp;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {
//    @Select("select count(*) from emp")
//    public Long count(); // get the count of records;
//    @Select("select * from emp limit #{start},#{pageSize}")
//    public List<Emp> page(Integer start, Integer pageSize); // check in different pages
//    @Select("select * from emp")
    public List<Emp> list(String name, Short gender, LocalDate begin, LocalDate end);
    // delete batch:
    void delete(List<Integer> ids);
    @Insert("insert into emp(username, name, gender, image, job, entrydate, dept_id, create_time, update_time)" +
        "VALUES (#{username},#{name},#{gender},#{image},#{job},#{entrydate},#{deptId},#{createTime}, #{updateTime})")
    void save(Emp emp);
    @Select("SELECT * from emp where id = #{id}")
    Emp listById(Integer id);

    void update(Emp emp);
    @Select("select * from emp where username = #{username} and password = #{password}")
    Emp getByUsernameAndPassword(Emp emp);
}
