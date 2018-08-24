package com.pache.springboot.mysql.controller.admin;

import com.pache.springboot.mysql.repository.DepartmentRepository;
import com.pache.springboot.mysql.repository.RoleRepository;
import com.pache.springboot.mysql.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 用户管理控制类
 * @author: Lin Liangjia
 * @create: 2018-08-23 11:04
 **/
@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @RequestMapping(value = "/add-user",method = RequestMethod.POST)
    public String addUser(){





        return "保存成功";
    }


}
