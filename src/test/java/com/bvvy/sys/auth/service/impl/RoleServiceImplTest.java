package com.bvvy.sys.auth.service.impl;

import com.bvvy.sys.auth.model.Role;
import com.bvvy.sys.auth.model.User;
import com.bvvy.sys.auth.service.IRoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by bvvy on 2017/5/23.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleServiceImplTest {
    @Resource
    private IRoleService roleServiceImpl;
    @Test
    public void findAll() throws Exception {
    }

    @Test
    public void save() throws Exception {
        Role role = new Role();
        role.setName("管理员");
        role.setSn("ADMIN");
        roleServiceImpl.save(role);
    }

    @Test
    public void delete() throws Exception {
    }

}