package com.bvvy.sys.auth.model;

import java.util.List;

/**
 * Created by bvvy on 2017/5/24.
 */
public class TreeDto {
    private Integer id;
    private String label;
    private Integer pid;
    private List<TreeDto> children;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public List<TreeDto> getChildren() {
        return children;
    }

    public void setChildren(List<TreeDto> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "TreeDto{" +
                "id=" + id +
                ", label='" + label + '\'' +
                ", pid=" + pid +
                ", children=" + children +
                '}';
    }
}
