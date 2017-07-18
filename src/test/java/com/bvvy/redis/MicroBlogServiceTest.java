package com.bvvy.redis;

import com.bvvy.sys.auth.model.MicroBlog;
import com.bvvy.sys.auth.service.IMicroBlogService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.Date;

/**
 * Created by bvvy on 2017/7/8.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MicroBlogServiceTest {

    @Resource
    private IMicroBlogService microBlogService;

    @Test
    public void add() throws Exception {
        MicroBlog microBlog = new MicroBlog();
        microBlog.setContent("你好，大家们号");
        microBlog.setCreateDate(new Date());
        microBlog.setCreateUser(1);
        microBlogService.add(microBlog);
    }

    @Test
    public void update() throws Exception {
    }

    @Test
    public void delete() throws Exception {
    }

    @Test
    public void find() throws Exception {
    }

    @Test
    public void list() throws Exception {
    }

    @Test
    public void get() throws Exception {
    }

}