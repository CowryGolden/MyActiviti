/**
 * Title: MenuModel.java
 * Package com.zzrenfeng.base.model
 * author zhoujincheng
 * date 2015年9月14日 下午6:26:04
 * version V1.0
 * Copyright (c) 2015,zhoujincheng777@qq.com All Rights Reserved.
 */

package com.zzrenfeng.base.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.zzrenfeng.base.entity.BaseDomain;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: MenuModel
 * Description: 菜单模型类
 * author zhoujincheng
 * date 2015年9月14日 下午6:26:04
 */
public class MenuModel extends BaseDomain {
    private String id;
    private String pid;
    private String name;
    private String iconCls;
    private String url;
    @JsonManagedReference
    private List<MenuModel> child = new ArrayList<MenuModel>();
    /**
     * zjc add;20171101 15:55
     * 菜单类型：M:菜单,O:操作（没有子项，且在菜单中不显示）
     */
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<MenuModel> getChild() {
        return child;
    }

    public void setChild(List<MenuModel> child) {
        this.child = child;
    }

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
    
}
