package com.lina.service.impl;

import com.lina.mapper.DeptMapper;
import com.lina.pojo.Dept;
import com.lina.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Slf4j
@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;
    @Override
    public List<Dept> list() {
        log.info("This is class DeptServiceImpl.");
        return deptMapper.list();
    }

    @Override
    public void delete(Integer id) {
        log.info("delete by id in service.");
        deptMapper.deleteById(id);
    }

    @Override
    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        log.info("add a dept.");
        deptMapper.add(dept);
    }

    @Override
    public Dept listById(Integer id) {
        Dept dept = deptMapper.listById(id);
        return dept;
    }

    @Override
    public void update(Dept dept) {
//        Dept dept1 = listById(dept.getId());
        dept.setUpdateTime(LocalDateTime.now());
//        dept1.setName(dept.getName());
        deptMapper.update(dept);
    }
}
