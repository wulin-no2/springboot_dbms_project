package com.lina.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lina.mapper.EmpMapper;
import com.lina.pojo.Emp;
import com.lina.pojo.PageBean;
import com.lina.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;
//    @Override
//    public PageBean page(Integer page, Integer pageSize) {
//        Long count = empMapper.count();
//        Integer start = (page - 1) * pageSize;
//        List<Emp> empList = empMapper.page(start, pageSize);
//        return new PageBean(count, empList);
//    }
    @Override
    public PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end) {
        // set parameters of startPage();
        PageHelper.startPage(page,pageSize);
        // get list and cast it to Page;
        List<Emp> list = empMapper.list(name,gender,begin,end);
        Page<Emp> p = (Page<Emp>) list;
        // encapsulate it to PageBean;
        return new PageBean(p.getTotal(),p.getResult());
    }

    @Override
    public void delete(List<Integer> ids) {
        empMapper.delete(ids);
    }

    @Override
    public void save(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.save(emp);
    }

    @Override
    public Emp listById(Integer id) {
        Emp emp = empMapper.listById(id);
        return emp;
    }

    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);
    }

    @Override
    public Emp login(Emp emp) {
        Emp e = empMapper.getByUsernameAndPassword(emp);
        return e;
    }
}
