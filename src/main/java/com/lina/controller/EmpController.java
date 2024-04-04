package com.lina.controller;

import com.lina.pojo.Emp;
import com.lina.pojo.PageBean;
import com.lina.pojo.Result;
import com.lina.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {
    @Autowired
    private EmpService empService;
    // select by conditions;
    @GetMapping
    public Result page(@RequestParam(defaultValue = "1")Integer page,
                       @RequestParam(defaultValue = "10")Integer pageSize,
                       String name, Short gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end){
        log.info("Get the emp list: {}, {}, {}, {}, {},{}.",
                page,pageSize,name,gender,begin,end);
        // get service method to get result;
        PageBean pageBean = empService.page(page,pageSize,name,gender,begin,end);
        return Result.success(pageBean);
    }
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids){
        log.info("delete batch {}",ids);
        empService.delete(ids);
        return Result.success();
    }
    @PostMapping
    public Result save(@RequestBody Emp emp){
        log.info("add new emp: {}",emp);
        empService.save(emp);
        return Result.success();
    }
    @GetMapping("/{id}")
    public Result listById(@PathVariable Integer id){
        log.info("get emp info by id: {}",id);
        Emp emp = empService.listById(id);
        return Result.success(emp);
    }
    @PutMapping
    public Result update(@RequestBody Emp emp){
        log.info("update emp:{}",emp);
        empService.update(emp);
        return Result.success();
    }


}
