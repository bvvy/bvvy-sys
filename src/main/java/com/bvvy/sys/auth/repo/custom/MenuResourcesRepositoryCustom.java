package com.bvvy.sys.auth.repo.custom;


import com.bvvy.sys.auth.dto.LeftMenuDto;
import com.bvvy.sys.auth.model.MenuResources;
import com.bvvy.sys.auth.model.TreeDto;

import java.util.List;

/**
 * Created by bvvy on 2017/5/24.
 */
public interface MenuResourcesRepositoryCustom {


    TreeDto tree();

    public List<MenuResources> listTopMenu();

    public List<MenuResources> listModelMenu(String psn, int pos);

    public List<LeftMenuDto> listLeftMenu();



}
