package com.bvvy.basic.repo;

import com.bvvy.sys.auth.model.TreeDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Created by bvvy on 2017/5/23.
 */
@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {

    @Transactional
    void delete(ID ids[]);

    List<Object[]> listBySql(String sql, Object... obj);

    List<Object[]> listByHql(String hql, Object... obj);


    <N>List<N> listByHql(String hql, Class<N> clz, Object... objs);

    <N>List<N> listBySql(String hql, Class<N> clz, Object... objs);

}
