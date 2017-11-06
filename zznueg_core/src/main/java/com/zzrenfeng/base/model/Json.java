/**
 * Title: Json.java
 * Package com.zzrenfeng.base.model
 * author zhoujincheng
 * date 2015年9月21日 下午5:28:56
 * version V1.0
 * Copyright (c) 2015,zhoujincheng777@qq.com All Rights Reserved.
 */

package com.zzrenfeng.base.model;

import com.zzrenfeng.base.entity.BaseDomain;

/**
 * ClassName: Json
 * Description:JSON格式回复信息
 * author zhoujincheng
 * date 2015年9月21日 下午5:28:56
 *
 */

public class Json extends BaseDomain {
    private String title = "提示";
    private String message;
    private boolean status = false;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
