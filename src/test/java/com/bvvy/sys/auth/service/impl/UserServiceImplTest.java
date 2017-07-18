package com.bvvy.sys.auth.service.impl;

import com.bvvy.sys.auth.model.Role;
import com.bvvy.sys.auth.model.User;
import com.bvvy.sys.auth.service.IRoleService;
import com.bvvy.sys.auth.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by bvvy on 2017/5/18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Resource
    private IUserService userServiceImpl;
    @Resource
    private IRoleService roleServiceImpl;
    @Test
    public void addUserRole() throws Exception {

        User user = userServiceImpl.page(1, 1).getContent().get(0);
        Role role = roleServiceImpl.page(1, 1).getContent().get(0);
//        roleServiceImpl.addRoleUsers(user, role);
    }

    @Test
    public void findAll() throws Exception {
        System.out.println("=======================");
        Page<User> users = userServiceImpl.page(0,3);
        for (User u : users) {
            System.out.println(u.getId());
        }
    }

    @Test
    public void save() throws Exception {
        User user = new User();
        user.setGender(1);
        user.setEmail("aaa@qq.com");
        user.setUsername("admin1");
        user.setPassword("aaaa");
        userServiceImpl.save(user);
        User user1 = new User();
        user.setGender(1);
        user.setEmail("aaa@qq.com");
        user.setUsername("admin2");
        user.setPassword("aaaa");
        userServiceImpl.save(user1); User user2 = new User();
        user.setGender(1);
        user.setEmail("aaa@qq.com");
        user.setUsername("admin3");
        user.setPassword("aaaa");
        userServiceImpl.save(user2);

    }

    @Test
    public void delete() throws Exception {
    }

}