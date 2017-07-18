package com.bvvy.sys.auth.repo.impl;

import com.bvvy.basic.util.TreeUtil;
import com.bvvy.sys.auth.dto.LeftMenuDto;
import com.bvvy.sys.auth.model.MenuResources;
import com.bvvy.sys.auth.model.TreeDto;
import com.bvvy.sys.auth.repo.custom.MenuResourcesRepositoryCustom;
import com.bvvy.sys.constant.MenuPosition;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by bvvy on 2017/5/24.
 */
@SuppressWarnings("unchecked")
@Repository
public class MenuResourcesRepositoryImpl implements MenuResourcesRepositoryCustom {

    @Resource
    private EntityManager entityManager;


    private String getSelectHql() {
       return "select new MenuResources(m.id,m.label,m.sn,m.menuPos,m.href,m.icon,m.orderNum,m.psn,m.display)";

    }

    @Override
    public TreeDto tree() {
        String sql = "SELECT id,label,pid FROM t_menu_res WHERE display=1 ORDER BY order_num";
        List<Object[]> list = entityManager.createNativeQuery(sql).getResultList();
        TreeDto td = new TreeDto();
        TreeUtil.buildTree(td, list);
        return td;
    }

    @Override
    public List<MenuResources> listTopMenu() {
        String hql = getSelectHql() + " from MenuResources m where m.menuPos=? and m.display=1 order by m.orderNum";
        return entityManager.createQuery(hql, MenuResources.class).setParameter(1, MenuPosition.MENU_TOP_NAV.ordinal()).getResultList();
    }

    @Override
    public List<MenuResources> listModelMenu(String psn, int pos) {
        String hql = getSelectHql() + " from MenuResources m where m.psn=? and m.display=1 and m.menuPos=? order by m.orderNum";
        return entityManager.createQuery(hql, MenuResources.class).setParameter(1, psn).setParameter(2,pos).getResultList();
    }

    @Override
    public List<LeftMenuDto> listLeftMenu() {
        List<LeftMenuDto> lmds = new ArrayList<>();
        String hql = "select m from MenuResources m where m.menuPos=?";

        List<MenuResources> mrs = entityManager.createQuery(hql, MenuResources.class).setParameter(1, MenuPosition.MENU_LEFT_NAV.ordinal()).getResultList();
        LeftMenuDto lmd = null;
        for(MenuResources mr:mrs) {
            if(mr.getParent()!=null&&mr.getParent().getMenuPos()==MenuPosition.MENU_ROOT.ordinal()) {
                //菜单是父节点菜单
                lmd = new LeftMenuDto();
                lmd.setParent(mr);
                lmd.setChilds(new ArrayList<MenuResources>());
                lmds.add(lmd);
            }
        }
        for(MenuResources mr:mrs) {
            MenuResources mp = mr.getParent();
            if(mp!=null&&mp.getMenuPos()==MenuPosition.MENU_LEFT_NAV.ordinal()) {
                LeftMenuDto tmp = new LeftMenuDto(); tmp.setParent(mp);
                if(lmds.contains(tmp)) {
                    lmds.get(lmds.indexOf(tmp)).getChilds().add(mr);
                }
            }
        }

        //排序父节点菜单
        Collections.sort(lmds);

        //排序子菜单
        for(LeftMenuDto md:lmds) {
            Collections.sort(md.getChilds());
        }

        return lmds;
    }


}
