package com.bvvy.sys.auth.repo;

import com.bvvy.sys.auth.model.MicroBlog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by bvvy on 2017/7/8.
 */
public interface MicroBlogRepository extends JpaRepository<MicroBlog,Integer> {

}
