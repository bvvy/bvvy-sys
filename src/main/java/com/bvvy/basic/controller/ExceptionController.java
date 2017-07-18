package com.bvvy.basic.controller;

import com.bvvy.basic.constant.ResultCode;
import com.bvvy.basic.model.AjaxObj;
import com.bvvy.sys.exception.SysException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by bvvy on 2017/5/23.
 */
@ControllerAdvice
@RestController
public class ExceptionController {

    @ExceptionHandler({
            SysException.class
    })
    public AjaxObj sysExceptionHandler(SysException e) {
        return new AjaxObj(ResultCode.ERROR.ordinal(), e.getMessage());
    }
}
