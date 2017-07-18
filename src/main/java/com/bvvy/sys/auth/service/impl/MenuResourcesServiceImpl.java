package com.bvvy.sys.auth.service.impl;

import com.bvvy.basic.builder.PageBuilder;
import com.bvvy.sys.auth.dto.LeftMenuDto;
import com.bvvy.sys.auth.model.MenuResources;
import com.bvvy.sys.auth.model.TreeDto;
import com.bvvy.sys.auth.repo.MenuResourcesRepository;
import com.bvvy.sys.auth.service.IMenuResourcesService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by bvvy on 2017/5/24.
 */
@Service
public class MenuResourcesServiceImpl implements IMenuResourcesService{

    @Resource
    private MenuResourcesRepository menuResourcesRepository;

    public TreeDto tree(){
        return menuResourcesRepository.tree();
    }

    @Override
    public void save(MenuResources menuResources) {
        menuResourcesRepository.save(menuResources);
    }

    @Override
    public void batchDel(Integer ids[]) {
        menuResourcesRepository.delete(ids);
    }

    @Override
    public Page<MenuResources> page(Integer page, Integer size) {
        return menuResourcesRepository.findAll(PageBuilder.generate(page, size));
    }

    @Override
    public List<MenuResources> listTopMenu() {
        return menuResourcesRepository.listTopMenu();
    }

    @Override
    public List<MenuResources> listModelMenu(String psn, int pos) {
        return menuResourcesRepository.listModelMenu(psn, pos);
    }

    @Override
    public MenuResources get(Integer id) {
        return menuResourcesRepository.findOne(id);
    }

    @Override
    public List<LeftMenuDto> listLeftMenu() {
        return menuResourcesRepository.listLeftMenu();
    }

    @Override
    public void addChild(Integer pid, MenuResources menu) {
        MenuResources parent = menuResourcesRepository.findOne(pid);
        menu.setParent(parent);
        menuResourcesRepository.save(menu);
    }
}
