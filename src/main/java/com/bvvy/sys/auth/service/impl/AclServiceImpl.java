package com.bvvy.sys.auth.service.impl;

import com.bvvy.basic.util.Commutil;
import com.bvvy.sys.auth.model.*;
import com.bvvy.sys.auth.repo.AclRepository;
import com.bvvy.sys.auth.service.IAclService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("aclService")
public class AclServiceImpl implements IAclService {
    @Resource
    private AclRepository aclRepository;

    @Override
    public Acl loadAcl(Integer pid, String ptype, Integer rid, String rtype) {
        return aclRepository.loadAcl(pid, ptype, rid, rtype);
    }

    @Override
    public Acl loadAclByRole(Integer roleId, Integer rid, String rtype) {
        return aclRepository.loadAcl(roleId, Role.PRINCIPAL_TYPE, rid, rtype);
    }

    @Override
    public Acl loadAclByUser(Integer userId, Integer rid, String rtype) {
        return aclRepository.loadAcl(userId, User.PRINCIPAL_TYPE, rid, rtype);
    }

    @Override
    public List<Integer> listRoleOperIdsByRes(Integer rid, String rtype,
                                              Integer roleId) {
        return aclRepository.listRoleOperIdsByRes(rid, rtype, roleId);
    }

    @Override
    public List<Integer> listUserOperIdsByRes(Integer rid, String rtype,
                                              Integer userId) {
        return aclRepository.listUserOperIdsByRes(rid, rtype, userId);
    }

    @Override
    public List<Integer> listUserSelfOperIdsByRes(Integer rid, String rtype,
                                                  Integer userId) {
        return aclRepository.listUserSelfOperIdsByRes(rid, rtype, userId);
    }

    @Override
    public Map<String, List<String>> listAllControllerResAndOperByRole(Integer roleId) {
        return aclRepository.listAllResAndOperByRole(roleId, ControllerResources.RES_TYPE);
    }

    @Override
    public Map<String, List<String>> listAllControllerResAndOperByUser(Integer userId) {
        return aclRepository.listAllResAndOperByUser(userId, ControllerResources.RES_TYPE);
    }

    @Override
    public List<String> listMenuSnByRole(Integer roleId) {
        return aclRepository.listMenuSnByRole(roleId);
    }

    @Override
    public List<String> listMenuSnByUser(Integer userId) {
        return aclRepository.listMenuSnByUser(userId);
    }

    @Override
    public List<Integer> listMenuIdByRole(Integer roleId) {
        return aclRepository.listMenuIdByRole(roleId);
    }

    @Override
    public List<Integer> listMenuIdByUser(Integer userId) {
        return aclRepository.listMenuIdByUser(userId);
    }

    @Override
    public void add(int roleId, Integer[] mIds) {
        Acl acl = null;
        aclRepository.deleteByPidAndPtype(roleId,Role.PRINCIPAL_TYPE);
        if (Commutil.isNotEmpty(mIds)) {
            for (int m : mIds) {
                acl = new Acl();
                acl.setPid(roleId);
                acl.setRid(m);
                acl.setRoleType();
                acl.setMenuType();
                acl.setAclState(15);
                aclRepository.save(acl);
            }
        }
    }

    @Override
    public List<Integer> listMenuIdByUserSelf(Integer userId) {
        return aclRepository.listMenuIdByUserSelf(userId);
    }

    @Override
    public void save(Acl acl) {
        aclRepository.save(acl);
    }
}
