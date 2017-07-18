package com.bvvy.sys.auth.controller;

import com.bvvy.basic.model.AjaxObj;
import com.bvvy.sys.auth.annotation.NavMenu;
import com.bvvy.sys.auth.model.Role;
import com.bvvy.sys.auth.model.User;
import com.bvvy.sys.auth.service.IAclService;
import com.bvvy.sys.auth.service.IRoleService;
import com.bvvy.sys.auth.service.IUserService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by bvvy on 2017/5/18.
 */
@NavMenu(label = "角色管理",orderNum = 2,psn="sys_root")
@RestController
@RequestMapping("/admin/role")
public class RoleController {

    @Resource
    private IRoleService roleServiceImpl;

    @Resource
    private IAclService aclServiceImpl;

    @RequestMapping("/list")
    public Page<Role> list(Integer page,Integer size) {
        return roleServiceImpl.page(page , size);
    }

    @RequestMapping(value = "/delete")
    public AjaxObj delete(@RequestParam("ids[]") Integer [] ids) {
        roleServiceImpl.delete(ids);
        return AjaxObj.success();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public AjaxObj add(Role role) {
        roleServiceImpl.save(role);
        return AjaxObj.success();
    }
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public AjaxObj update(Role role) {
        roleServiceImpl.save(role);
        return AjaxObj.success();
    }

    @RequestMapping("/{id}/addUsers")
    public AjaxObj addUsers(@PathVariable Integer id,@RequestParam(value = "userIds[]",required = false) Integer[] userIds) {
        roleServiceImpl.addRoleUsers(userIds, id);
        return AjaxObj.success();
    }

    @RequestMapping("/{id}/addAuth")
    public AjaxObj addAuth(@PathVariable int id, @RequestParam(value = "mIds[]",required = false) Integer[] mIds) {
        aclServiceImpl.add(id, mIds);
        return AjaxObj.success();
    }
    @RequestMapping("/{id}/users")
    public List<User> listUsers(@PathVariable int id) {
        return roleServiceImpl.listRoleUsers(id);
    }

    @RequestMapping("{roleId}/auths")
    public List<Integer> listAuths(@PathVariable Integer roleId) {
        return aclServiceImpl.listMenuIdByRole(roleId);
    }

}
