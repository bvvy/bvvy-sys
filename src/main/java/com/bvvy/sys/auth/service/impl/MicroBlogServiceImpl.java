package com.bvvy.sys.auth.service.impl;

import com.bvvy.sys.auth.model.MicroBlog;
import com.bvvy.sys.auth.repo.MicroBlogRepository;
import com.bvvy.sys.auth.service.IMicroBlogService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by bvvy on 2017/7/8.
 */
@Service
public class MicroBlogServiceImpl implements IMicroBlogService {

    @Resource
    private MicroBlogRepository microBlogRepository;

    @Override
    public void add(MicroBlog microBlog) {
        microBlogRepository.save(microBlog);
    }

    @Override
    public void update(MicroBlog microBlog) {
        microBlogRepository.save(microBlog);
    }

    @Override
    public void delete(Integer id) {
        microBlogRepository.delete(id);
    }

    @Override
    public Page<MicroBlog> find(Pageable pageable) {
        return microBlogRepository.findAll(pageable);
    }

    @Override
    public List<MicroBlog> list() {
        return microBlogRepository.findAll();
    }

    @Override
    public MicroBlog get(Integer id) {
        return microBlogRepository.findOne(id);
    }
}
