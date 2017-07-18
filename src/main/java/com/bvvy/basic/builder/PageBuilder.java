package com.bvvy.basic.builder;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

/**
 * Created by bvvy on 2017/5/18.
 */
public class PageBuilder {

    public static Pageable generate(Integer page, Integer size) {
        return new PageRequest(page-1, size);
    }
}
