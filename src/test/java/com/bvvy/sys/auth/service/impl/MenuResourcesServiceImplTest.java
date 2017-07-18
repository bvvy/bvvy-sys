package com.bvvy.sys.auth.service.impl;

import com.bvvy.sys.auth.dto.LeftMenuDto;
import com.bvvy.sys.auth.model.MenuResources;
import com.bvvy.sys.auth.service.IMenuResourcesService;
import com.bvvy.sys.constant.MenuPosition;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by bvvy on 2017/5/26.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MenuResourcesServiceImplTest {

    @Resource
    private IMenuResourcesService menuResourcesServiceImpl;
    @Test
    public void tree() throws Exception {
    }

    @Test
    public void save() throws Exception {
    }

    @Test
    public void batchDel() throws Exception {
    }

    @Test
    public void page() throws Exception {
    }

    @Test
    public void listTopMenu() throws Exception {
        List<MenuResources> ms = menuResourcesServiceImpl.listTopMenu();
        for (MenuResources m : ms) {
            System.out.println(m.getLabel());
        }

    }

    @Test
    public void listModelMenu() throws Exception {
        List<MenuResources> menuResources = menuResourcesServiceImpl.listModelMenu("user", MenuPosition.MENU_MODEL_OPER.ordinal());
        for (MenuResources m : menuResources) {
            System.out.println(m.getLabel());

        }
    }

    @Test
    public void listLeftMenu() throws Exception {
        List<LeftMenuDto> list = menuResourcesServiceImpl.listLeftMenu();
        for (LeftMenuDto lm : list) {
            System.out.println(lm.getParent().getLabel());
            for (MenuResources l : lm.getChilds()) {
                System.out.println("--------"+l.getLabel());
            }
        }
    }

}