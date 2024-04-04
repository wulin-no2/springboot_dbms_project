package com.lina.controller;

import com.lina.pojo.Dept;
import com.lina.pojo.Result;
import com.lina.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/depts")
public class DeptController {
    @Autowired
    private DeptService deptService;
    // log used to check print content;
//    private static Logger log = LoggerFactory.getLogger(DeptController.class);
    @GetMapping
    public Result list(){
        log.info("get all the depts info.");
        // use service method
        List<Dept> deptList = deptService.list();
        return Result.success(deptList);
    }
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("delete dept by id {}", id);
        deptService.delete(id);
        return Result.success();
    }
    @PostMapping
    public Result add(@RequestBody Dept dept){
        log.info("add a new dept {}",dept);
        deptService.add(dept);
        return Result.success();
    }
    @GetMapping("/{id}")
    public Result listById(@PathVariable Integer id){
        log.info("list by id.");
        Dept dept = deptService.listById(id);
        return Result.success(dept);
    }
    @PutMapping
    public Result update(@RequestBody Dept dept){
        log.info("update dept info.");
        deptService.update(dept);
        return Result.success();
    }
}
