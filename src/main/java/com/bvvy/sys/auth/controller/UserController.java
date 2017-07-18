package com.bvvy.sys.auth.controller;

import com.bvvy.basic.model.AjaxObj;
import com.bvvy.sys.auth.annotation.Res;
import com.bvvy.sys.auth.model.User;
import com.bvvy.sys.auth.service.IAclService;
import com.bvvy.sys.auth.service.IUserService;
import org.aspectj.weaver.loadtime.Aj;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by bvvy on 2017/5/18.
 */
@RestController
@RequestMapping("/admin/user")
public class UserController {

    @Resource
    private IUserService userServiceImpl;

    @Resource
    private IAclService aclServiceImpl;

    @RequestMapping("/list")
    public Page<User> list(Integer page,Integer size) {
        return userServiceImpl.page(page , size);
    }

    @RequestMapping(value = "/delete")
    public AjaxObj delete(@RequestParam("ids[]") Integer [] ids) {
        userServiceImpl.delete(ids);
        return AjaxObj.success();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public AjaxObj add(User user) {
        userServiceImpl.save(user);
        return AjaxObj.success();
    }

    @RequestMapping("/update")
    public AjaxObj update(User user) {
        userServiceImpl.save(user);
        return AjaxObj.success();
    }
    @RequestMapping("/{userId}/menus")
    public List<Integer> listMenu(@PathVariable int userId) {
        return aclServiceImpl.listMenuIdByUser(userId);
    }
}
