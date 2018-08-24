package com.pache.springboot.mysql;

import com.pache.springboot.mysql.entities.Department;
import com.pache.springboot.mysql.entities.Role;
import com.pache.springboot.mysql.entities.User;
import com.pache.springboot.mysql.repository.DepartmentRepository;
import com.pache.springboot.mysql.repository.RoleRepository;
import com.pache.springboot.mysql.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 编写spring boot 的单元测试，并模拟servlet的请求响应
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MyTest01 {

    //使用日志打印显示信息，该类来源于 slf4j.jar
    private static Logger logger = LoggerFactory.getLogger(MyTest01.class);

    @Autowired
    private MockMvc mvc;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    DepartmentRepository departmentRepository;

   /** 
   * @Description:
   * @Param:  
   * @return:  
   * @Author: Lin Liangjia
   * @Date:  
   */ 
    @Test
    public void getFromSayHello() throws Exception {
        /*mvc.perform(MockMvcRequestBuilders.get("/say-hello").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(content().string(equalTo("Greetings from Spring Boot!")));*/
       String responseString =  mvc.perform(
                MockMvcRequestBuilders.get("/say-hello").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()
       ).andDo(
               print()
       ).andReturn().getResponse().getContentAsString();

        System.out.println("--------返回的json = " + responseString);
    }

    @Test
    public void getFromMySelf() throws Exception{
        mvc.perform(
                MockMvcRequestBuilders.get("/myself").accept(MediaType.APPLICATION_JSON)).andExpect(
                status().isOk()
        ).andExpect(
                content().string(equalTo("你好吗？")  //equalTo里的内容要求和url/myself的返回值一致，不知道为什么
                )
        );
    }

    @Before
    public void initData(){
        userRepository.deleteAll();
        roleRepository.deleteAll();
        departmentRepository.deleteAll();

        Department department = new Department();
        department.setName("开发部");
        department.setCreateDate(new Date());
        departmentRepository.save(department);
        Assert.notNull(department.getId(),"部门id");

        Role role = new Role();
        role.setName("admin");
        roleRepository.save(role);
        Assert.notNull(role.getId(),"角色id");

        User user = new User();
        user.setName("adminUser");
        user.setCreateDate(new Date());
        user.setDepartment(department);
        List<Role> roles = roleRepository.findAll();
        Assert.notNull(roles,"角色");
        user.setRoles(roles);
        userRepository.save(user);
        Assert.notNull(user.getId(),"用户id");
    }

    @Test
    public void findPage(){
        //该方法已过时
        Pageable pageable = new PageRequest(0,10,new Sort(Sort.Direction.ASC,"id"));
        Page<User> page = userRepository.findAll(pageable);
        Assert.notNull(page,"用户分页查询");
        for(User user : page.getContent()){
            logger.info("======user====== user.name:{}, department.name{}, role.name:{}",user.getName(),user.getDepartment().getName(),user.getRoles().get(0).getName());
        }
    }
}
