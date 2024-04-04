package com.lina.service;

import com.lina.pojo.Dept;

import java.util.List;

public interface DeptService {
    List<Dept> list();

    void delete(Integer id);

    void add(Dept dept);

    Dept listById(Integer id);

    void update(Dept dept);
}
