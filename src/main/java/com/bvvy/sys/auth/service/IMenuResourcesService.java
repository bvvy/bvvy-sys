package com.bvvy.sys.auth.service;

import com.bvvy.sys.auth.dto.LeftMenuDto;
import com.bvvy.sys.auth.model.MenuResources;
import com.bvvy.sys.auth.model.TreeDto;
import org.springframework.data.domain.Page;

import java.awt.*;
import java.util.List;

/**
 * Created by bvvy on 2017/5/24.
 */
public interface IMenuResourcesService {

    public TreeDto tree();

    public void save(MenuResources menuResources);

    public void batchDel(Integer ids[]);

    public Page<MenuResources> page(Integer page, Integer size);

    public List<MenuResources> listTopMenu();

    public List<MenuResources> listModelMenu(String psn, int pos);

    public List<LeftMenuDto> listLeftMenu();

    MenuResources get(Integer id);

    void addChild(Integer pid, MenuResources menu);
}
