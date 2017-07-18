package com.bvvy.sys.auth.controller;

import com.bvvy.basic.model.AjaxObj;
import com.bvvy.sys.auth.annotation.NavMenu;
import com.bvvy.sys.auth.dto.LeftMenuDto;
import com.bvvy.sys.auth.model.MenuResources;
import com.bvvy.sys.auth.model.TreeDto;
import com.bvvy.sys.auth.service.IAclService;
import com.bvvy.sys.auth.service.IMenuResourcesService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by bvvy on 2017/5/18.
 */
@NavMenu(label = "菜单资源管理",orderNum = 2,psn="sys_root")
@RestController
@RequestMapping("/admin/menu")
public class MenuResourcesController {

    @Resource
    private IMenuResourcesService menuServiceImpl;

    @Resource
    private IAclService aclServiceImpl;

    @RequestMapping("/list")
    public Page<MenuResources> list(Integer page, Integer size) {
        return menuServiceImpl.page(page , size);
    }

    @RequestMapping(value = "/delete")
    public AjaxObj delete(@RequestParam("ids[]") Integer [] ids) {
        menuServiceImpl.batchDel(ids);
        return AjaxObj.success();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public AjaxObj add(MenuResources menu) {
        menuServiceImpl.save(menu);
        return AjaxObj.success();
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public AjaxObj update(MenuResources menu) {
        menuServiceImpl.save(menu);
        return AjaxObj.success();
    }

    @RequestMapping("/{id}")
    public MenuResources get(@PathVariable Integer id) {
        return menuServiceImpl.get(id);
    }

    @RequestMapping("/{pid}/addChild")
    public AjaxObj addChild(@PathVariable Integer pid,MenuResources menu) {
        System.out.println(menu.getLabel());
        menuServiceImpl.addChild(pid, menu);
        return AjaxObj.success();
    }

    @RequestMapping("/tree")
    public TreeDto tree() {
        return menuServiceImpl.tree();
    }

    @RequestMapping("/leftMenus")
    public List<LeftMenuDto> listLeftMenu() {
        return menuServiceImpl.listLeftMenu();
    }

    @RequestMapping("/modelMenus")
    public List<MenuResources> listModelMenu(String psn,int pos) {
        return menuServiceImpl.listModelMenu(psn, pos);
    }

    @RequestMapping("/topMenus")
    public List<MenuResources> listTopMenu() {
        return menuServiceImpl.listTopMenu();
    }


}
