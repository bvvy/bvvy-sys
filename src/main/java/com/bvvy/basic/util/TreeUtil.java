package com.bvvy.basic.util;

import com.bvvy.sys.auth.model.TreeDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bvvy on 2017/5/25.
 */
public class TreeUtil {
    public static void buildTree(TreeDto root, List<Object[]> sourceList) {

        List<TreeDto> childs = new ArrayList<>();
        for (Object[] objs : sourceList) {
            if (Commutil.isEmpty(objs[2])) {
                root.setId((Integer) objs[0]);
                root.setLabel((String) objs[1]);
                root.setPid((Integer) objs[2]);
            } else {
                TreeDto ttd = new TreeDto();
                ttd.setId((Integer) objs[0]);
                ttd.setLabel((String) objs[1]);
                ttd.setPid((Integer) objs[2]);
                childs.add(ttd);
            }
        }
        genTree(root, childs);
    }

    private static void genTree(TreeDto parent, List<TreeDto> childs) {
        List<TreeDto> tempChilds = new ArrayList<>();
        if(parent ==null) return;
        for (TreeDto child : childs) {
            if (child.getPid().equals(parent.getId())) {
                tempChilds.add(child);
            }
        }
        childs.removeAll(tempChilds);
        parent.setChildren(tempChilds);
        for (TreeDto c : tempChilds) {
            genTree(c, childs);
        }
    }
}
