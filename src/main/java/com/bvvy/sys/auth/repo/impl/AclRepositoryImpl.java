package com.bvvy.sys.auth.repo.impl;

import com.bvvy.basic.util.Commutil;
import com.bvvy.sys.auth.model.Acl;
import com.bvvy.sys.auth.model.MenuResources;
import com.bvvy.sys.auth.model.Role;
import com.bvvy.sys.auth.model.User;
import com.bvvy.sys.auth.repo.custom.AclRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.*;

/**
 * Created by bvvy on 2017/5/29.
 */
@Repository
public class AclRepositoryImpl implements AclRepositoryCustom {

    @Resource
    private EntityManager entityManager;

    public List<Object[]> listByHql(String hql, Object... objs) {
        Query query = entityManager.createQuery(hql);
        for (int i = 1; i <= objs.length; i++) {
            query.setParameter(i, objs[i-1]);
        }
        return query.getResultList();
    }

    private List<Integer> listOperIdsByResPrin(int rid, String rtype, int pid, String ptype) {
        String hql = "select a.aclState,co.id,co.indexPos from Acl a,ControllerOper co where " +
                "a.rid=co.rid and a.rid=? and a.rtype=? and a.pid=? and a.ptype=?";
        List<Object[]> objs = this.listByHql(hql, rid, rtype, pid, ptype);
        return getOperIds(objs);

    }

    private List<Integer> getOperIds(List<Object[]> objs) {
        List<Integer> ids = new ArrayList<Integer>();
        int aclState = 0;
        int coid = 0;
        int indexPos = 0;
        for (Object[] aobj : objs) {
            aclState = (Integer) aobj[0];
            coid = (Integer) aobj[1];
            indexPos = (Integer) aobj[2];
            if (Acl.checkPermission(indexPos, aclState)) {
                ids.add(coid);
            }
        }
        return ids;
    }

    @Override
    public List<Integer> listRoleOperIdsByRes(Integer rid, String rtype,
                                              Integer roleId) {
        return listOperIdsByResPrin(rid, rtype, roleId, Role.PRINCIPAL_TYPE);
    }

    @Override
    public List<Integer> listUserOperIdsByRes(Integer rid, String rtype,
                                              Integer userId) {
        List<Integer> ids = new ArrayList<Integer>();
        List<Integer> uids = listUserSelfOperIdsByRes(rid, rtype, userId);
        ids.addAll(uids);
        String hql = "select a.aclState,co.id,co.indexPos from Acl a,ControllerOper co,UserRole ur where " +
                "a.rid=co.rid and ur.role.id=a.pid and ur.user.id=? and a.rid=? and a.rtype=? and a.ptype=?";
        List<Object[]> objs = this.listByHql(hql, userId, rid, rtype, Role.PRINCIPAL_TYPE);
        List<Integer> rids = getOperIds(objs);
        Commutil.mergeList(ids, rids);
        return ids;
    }

    @Override
    public List<Integer> listUserSelfOperIdsByRes(Integer rid, String rtype,
                                                  Integer userId) {
        return listOperIdsByResPrin(rid, rtype, userId, User.PRINCIPAL_TYPE);
    }

    private Map<String, List<String>> listAllResAndOperByPrin(Integer pid, String ptype, String rtype) {
        String hql = "select a.aclState,co.indexPos,cr.className,co.methodName from Acl a,ControllerResources cr,ControllerOper co where" +
                "a.rid=cr.id and cr.id=co.rid and a.pid=? and a.ptype=? and a.rtype=?";
        List<Object[]> objs = this.listByHql(hql, pid, ptype, rtype);
        return getResAndOper(objs);
    }

    private Map<String, List<String>> getResAndOper(List<Object[]> objs) {
        int aclState = 0;
        int indexPos = 0;
        String className = null;
        String methodName = null;
        Map<String, List<String>> maps = new HashMap<String, List<String>>();
        for (Object[] aobj : objs) {
            aclState = (Integer) aobj[0];
            indexPos = (Integer) aobj[1];
            className = (String) aobj[2];
            methodName = (String) aobj[3];
            if (Acl.checkPermission(indexPos, aclState)) {
                if (!maps.containsKey(className)) {
                    maps.put(className, new ArrayList<String>());
                    addMethodToList(maps, className, methodName);
                } else {
                    addMethodToList(maps, className, methodName);
                }
            }
        }
        return maps;
    }

    private void addMethodToList(Map<String, List<String>> maps,
                                 String className, String methodName) {
        List<String> ms = maps.get(className);
        String[] mns = methodName.split("|");
        for (String m : mns) {
            if (!ms.contains(m)) ms.add(m);
        }
    }

    @Override
    public Map<String, List<String>> listAllResAndOperByRole(Integer roleId, String rtype) {
        return listAllResAndOperByPrin(roleId, Role.PRINCIPAL_TYPE, rtype);
    }

    @Override
    public Map<String, List<String>> listAllResAndOperByUser(Integer userId, String rtype) {
        Map<String, List<String>> umaps = listAllResAndOperByPrin(userId, User.PRINCIPAL_TYPE, rtype);
        String hql = "select a.aclState,co.indexPos,cr.className,co.methodName from Acl a,ControllerResources cr,ControllerOper co,UserRole ur where" +
                "a.rid=cr.id and cr.id=co.rid and ur.role.id=a.pid and ur.user.id=? and a.ptype=? and a.rtype=?";
        List<Object[]> objs = this.listByHql(hql, userId, Role.PRINCIPAL_TYPE, rtype);
        Map<String, List<String>> rmaps = getResAndOper(objs);
        Set<String> rkeys = rmaps.keySet();
        for (String rk : rkeys) {
            if (umaps.containsKey(rk)) {
                Commutil.mergeList(umaps.get(rk), rmaps.get(rk));
            } else {
                umaps.put(rk, rmaps.get(rkeys));
            }
        }
        return umaps;
    }

    private List<String> listMenuSnByPrin(int pid, String ptype) {
        String hql = "select a.aclState,mr.sn from Acl a,MenuResources mr where a.rid=mr.id and a.pid=? and a.ptype=? and a.rtype=?";
        List<Object[]> objs = this.listByHql(hql, pid, ptype, MenuResources.RES_TYPE);
        return getMenuSn(objs);
    }

    private List<String> getMenuSn(List<Object[]> objs) {
        int aclState = 0;
        String sn = null;
        List<String> sns = new ArrayList<String>();
        for (Object[] aobj : objs) {
            aclState = (Integer) aobj[0];
            sn = (String) aobj[1];
            if (Acl.checkPermission(0, aclState)) {
                sns.add(sn);
            }
        }
        return sns;
    }

    @Override
    public List<String> listMenuSnByRole(Integer roleId) {
        return listMenuSnByPrin(roleId, Role.PRINCIPAL_TYPE);
    }

    @Override
    public List<String> listMenuSnByUser(Integer userId) {
        List<String> usns = listMenuSnByPrin(userId, User.PRINCIPAL_TYPE);
        String hql = "select a.aclState,mr.sn from " +
                "Acl a,MenuResources mr,UserRole ur where a.rid=mr.id and ur.role.id=a.pid and ur.user.id=? and a.ptype=? and a.rtype=?";
        List<Object[]> objs = this.listByHql(hql, userId, Role.PRINCIPAL_TYPE, MenuResources.RES_TYPE);
        List<String> rsns = getMenuSn(objs);
        Commutil.mergeList(usns, rsns);
        return usns;
    }

    private List<Integer> listMenuIdByPrin(int pid, String ptype) {
        String hql = "select a.aclState,a.rid from Acl a where a.pid=? and a.ptype=? and a.rtype=?";
        List<Object[]> objs = this.listByHql(hql, pid, ptype, MenuResources.RES_TYPE);
        return getMenuIds(objs);
    }

    private List<Integer> getMenuIds(List<Object[]> objs) {
        int aclState = 0;
        int id = 0;
        List<Integer> ids = new ArrayList<Integer>();
        for (Object[] aobj : objs) {
            aclState = (Integer) aobj[0];
            id = (Integer) aobj[1];
            if (Acl.checkPermission(0, aclState)) {
                ids.add(id);
            }
        }
        return ids;
    }

    @Override
    public List<Integer> listMenuIdByRole(Integer roleId) {
        return listMenuIdByPrin(roleId, Role.PRINCIPAL_TYPE);
    }

    @Override
    public List<Integer> listMenuIdByUser(Integer userId) {
        List<Integer> uids = listMenuIdByPrin(userId, User.PRINCIPAL_TYPE);
        String hql = "select a.aclState,a.rid from " +
                "Acl a,UserRole ur where ur.role.id=a.pid and ur.user.id=? and a.ptype=? and a.rtype=?";
        List<Object[]> objs = this.listByHql(hql, userId, Role.PRINCIPAL_TYPE, MenuResources.RES_TYPE);
        List<Integer> rids = getMenuIds(objs);
        Commutil.mergeList(uids, rids);
        return uids;
    }

    @Override
    public List<Integer> listMenuIdByUserSelf(Integer userId) {
        return listMenuIdByPrin(userId, User.PRINCIPAL_TYPE);
    }

}

