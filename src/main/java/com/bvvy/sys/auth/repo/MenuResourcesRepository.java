package com.bvvy.sys.auth.repo;

import com.bvvy.basic.repo.BaseRepository;
import com.bvvy.sys.auth.model.MenuResources;
import com.bvvy.sys.auth.model.TreeDto;
import com.bvvy.sys.auth.repo.custom.MenuResourcesRepositoryCustom;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by bvvy on 2017/5/24.
 */

public interface MenuResourcesRepository extends BaseRepository<MenuResources,Integer>,MenuResourcesRepositoryCustom {


}
