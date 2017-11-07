/**
 * Title: TreeModel.java
 * Package com.zzrenfeng.base.model
 * author zhoujincheng
 * date 2015年9月23日 上午11:16:46
 * version V1.0
 * Copyright (c) 2015,zhoujincheng777@qq.com All Rights Reserved.
 */

package com.zzrenfeng.base.model;


import java.util.List;

import com.zzrenfeng.base.entity.BaseDomain;

/**
 * ClassName: TreeModel
 * Description:  树展示模型
 * author zhoujincheng
 * date 2015年9月23日 上午11:16:46
 * 
 * @修  改  人：zhoujincheng
 * @版        本：V1.1.0
 * @修改日期：20171023 14:18
 * @修改描述：增加字段：type-节点类型；memo-节点备注；
 */

public class TreeModel extends BaseDomain {
    private String id;		//节点ID
    private String pid;		//节点的父节点ID
    private String text;	//节点名称
    private String iconCls;	//节点图标
    private String state;   //节点开闭状态
    /**** zjc add start;20171023 14:18 ****/
    private String type;	//节点类型
    private String memo;	//节点备注
	/**** zjc add end;20171023 14:18 ****/   
    private List<TreeModel> children;	//节点的孩子节点集合

    public List<TreeModel> getChildren() {
        return children;
    }

    public void setChildren(List<TreeModel> children) {
        this.children = children;
    }

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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
    
}
