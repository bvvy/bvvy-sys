package com.bvvy.sys.auth.service;

import com.bvvy.sys.auth.model.MicroBlog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by bvvy on 2017/7/8.
 */
public interface IMicroBlogService {


    void add(MicroBlog microBlog);

    void update(MicroBlog microBlog);

    void delete(Integer id);

    Page<MicroBlog> find(Pageable pageable);

    List<MicroBlog> list();

    MicroBlog get(Integer id);

}
