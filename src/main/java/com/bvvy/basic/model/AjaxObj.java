package com.bvvy.basic.model;

import com.bvvy.basic.constant.BaseFinalValue;

/**
 * Created by bvvy on 2017/5/18.
 */
public class AjaxObj {

    public static final Integer SUCCESS_CODE = 0;
    public static final Integer ERROR_CODE = 1;

    private Integer code;
    private String message;
    private Object object;


    public AjaxObj() {
    }

    public static AjaxObj success() {
        return new AjaxObj(SUCCESS_CODE, BaseFinalValue.SAVE_SUCCESS);
    }

    public static AjaxObj error() {
        return new AjaxObj(ERROR_CODE, BaseFinalValue.ERROR);
    }

    public AjaxObj(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public AjaxObj(Integer code, String message,Object obj) {
        this.code = code;
        this.message = message;
        this.object = obj;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
