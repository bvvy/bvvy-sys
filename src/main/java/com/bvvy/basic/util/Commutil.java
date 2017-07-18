package com.bvvy.basic.util;

import java.util.Collection;
import java.util.List;

/**
 * Created by bvvy on 2017/5/18.
 */
public class Commutil {
    @SuppressWarnings("rawtypes")
    public static boolean isEmpty(Object obj) {
        if(obj==null) return true;
        if(obj instanceof String) {
            if("".equals(obj)) return true;
        }
        if(obj instanceof Collection<?>) {
            if(((Collection) obj).size()>0) return true;
        }
        return false;
    }

    public static boolean isNotEmpty(Object object) {
        return !isEmpty(object);
    }

    public static <N extends Object>void mergeList(List<N> baseList,List<N> mergeList) {
        for(N o:mergeList) {
            if(!baseList.contains(o)) {
                baseList.add(o);
            }
        }
    }

}

