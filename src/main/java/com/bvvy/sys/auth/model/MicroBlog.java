package com.bvvy.sys.auth.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by bvvy on 2017/7/8.
 */
@Entity
@Table(name="t_micro_blog")
public class MicroBlog {

    private Integer id;
    private String content;
    private Date createDate;
    private Integer createUser;


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(name = "create_date")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Column(name="create_user")
    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
