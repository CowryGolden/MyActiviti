/**
 * Title: Attributes.java
 * Package com.zzrenfeng.base.model
 * author zhoujincheng
 * date 2015年9月23日 上午11:17:19
 * version V1.0
 * Copyright (c) 2015,zhoujincheng777@qq.com All Rights Reserved.
 */

package com.zzrenfeng.base.model;

import com.zzrenfeng.base.entity.BaseDomain;

/**
 * ClassName: Attributes
 * Description: 状态属性, TreeModel模型中调用
 * author zhoujincheng
 * date 2015年9月23日 上午11:17:19
 *
 */

public class Attributes extends BaseDomain {
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
