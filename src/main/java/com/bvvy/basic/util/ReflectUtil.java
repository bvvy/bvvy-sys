package com.bvvy.basic.util;

import com.bvvy.sys.exception.SysException;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.FileFilter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bvvy on 2017/5/24.
 */
public class ReflectUtil {

    @SuppressWarnings("rawtypes")
    public static List<Class> listByClass(String p) {
        try {
            List<Class> clzs = new ArrayList<Class>();
            String packagePath = p.replace(".", "/");
            URL url = Thread.currentThread().getContextClassLoader().getResource(packagePath);
            File file = new File(url.getPath());
            if(!file.exists()) throw new SysException("初始化的包名路径不正确");
            File []fs = file.listFiles(pathname -> pathname.getName().endsWith(".class"));
            for(File f:fs) {
                String cname = p+"."+ FilenameUtils.getBaseName(f.getName());
                Class clz = Class.forName(cname);
                clzs.add(clz);
            }
            return clzs;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static List<Class> listByClassAnnotation(String p,Class an) {
        try {
            List<Class> clzs = new ArrayList<Class>();
            String packagePath = p.replace(".", "/");
            URL url = Thread.currentThread().getContextClassLoader().getResource(packagePath);
            File file = new File(url.getPath());
            if(!file.exists()) throw new SysException("初始化的包名路径不正确");
            File []fs = file.listFiles(pathname -> pathname.getName().endsWith(".class"));
            for(File f:fs) {
                String cname = p+"."+FilenameUtils.getBaseName(f.getName());
                Class clz = Class.forName(cname);
                if(clz.isAnnotationPresent(an))
                    clzs.add(clz);
            }
            return clzs;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
