package com.bvvy.sys.auth.repo;

import com.bvvy.basic.repo.BaseRepository;
import com.bvvy.sys.auth.model.Acl;
import com.bvvy.sys.auth.repo.custom.AclRepositoryCustom;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by bvvy on 2017/5/29.
 */
public interface AclRepository extends AclRepositoryCustom, BaseRepository<Acl, Integer> {

    /**
     * 根据主体id和主体类型，资源id和资源类型获取ACL对象
     *
     * @param pid
     * @param ptype
     * @param rid
     * @param rtype
     * @return
     */
    @Query("select a from Acl a where a.pid=?1 and a.ptype=?2 and a.rid=?3 and a.rtype=?4")
    public Acl loadAcl(int pid, String ptype, int rid, String rtype);

    @Modifying
    @Transactional
    void deleteByPidAndPtype(int roleId, String ptype);
}
