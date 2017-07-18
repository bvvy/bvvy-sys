package com.bvvy.sys.auth.controller;

import com.bvvy.basic.constant.BaseFinalValue;
import com.bvvy.basic.constant.ResultCode;
import com.bvvy.basic.model.AjaxObj;
import com.bvvy.sys.auth.model.User;
import com.bvvy.sys.auth.service.IUserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by bvvy on 2017/6/10.
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private IUserService userServiceImpl;

    @RequestMapping("/login")
    public AjaxObj login(String username,String password) {
        User user = userServiceImpl.login(username, password);
        return new AjaxObj(ResultCode.SUCCESS.ordinal(), BaseFinalValue.SAVE_SUCCESS, user);
    }

}
