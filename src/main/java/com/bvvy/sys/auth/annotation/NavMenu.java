package com.bvvy.sys.auth.annotation;

import com.bvvy.sys.constant.MenuPosition;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface NavMenu {
	public String label();
	/**
	 * 默认使用类名作为菜单sn
	 * @return
	 */
	public String sn() default"";//标识
	
	public MenuPosition menuPos() default MenuPosition.MENU_LEFT_NAV;
	
	public String icon() default "icon-default";
	
	public int orderNum();
	
	public String psn();
	
	public String href() default "";
	
	public int display() default 1;
}
