package com.bvvy.basic.repo.impl;

import com.bvvy.basic.repo.BaseRepository;
import com.bvvy.sys.auth.model.TreeDto;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

/**
 * Created by bvvy on 2017/5/23.
 */
    @SuppressWarnings("unchecked")
public class BaseRepositoryExtend<T,ID extends Serializable> extends SimpleJpaRepository<T,ID> implements BaseRepository<T,ID> {

    private final EntityManager entityManager;

    public BaseRepositoryExtend(JpaEntityInformation<T,?>  information, EntityManager entityManager) {
        super(information, entityManager);
        this.entityManager = entityManager;
    }


    @Override
    public void delete(ID[] ids) {
        for (ID id : ids) {
            super.delete(id);
        }
    }

    @Override
    public <N>List<N> listByHql(String hql, Class<N> clz, Object... objs) {
        Query query = entityManager.createQuery(hql,clz);
        for(int i=1;i<objs.length;i++) {
            query.setParameter(i, objs);
        }
        return query.getResultList();
    }

    @Override
    public <N>List<N> listBySql(String hql, Class<N> clz ,Object... objs) {
        Query query = entityManager.createNativeQuery(hql,clz);
        for(int i=1;i<objs.length;i++) {
            query.setParameter(i, objs);
        }
        return query.getResultList();
    }

    @Override
    public List<Object[]> listBySql(String sql, Object... objs) {
        return entityManager.createNativeQuery(sql).getResultList();
    }

    @Override
    public List<Object[]> listByHql(String hql, Object... obj) {
        return entityManager.createQuery(hql).getResultList();
    }

}
