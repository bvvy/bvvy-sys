package com.bvvy.sys.auth.controller;

import com.bvvy.basic.model.AjaxObj;
import com.bvvy.sys.auth.model.Acl;
import com.bvvy.sys.auth.service.IAclService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by bvvy on 2017/5/29.
 */
@RestController
@RequestMapping("/admin/acl")
public class AclController {

    @Resource
    private IAclService aclServiceImpl;

    @RequestMapping("/add")
    public AjaxObj add(Acl acl) {
        return AjaxObj.success();
    }


}
