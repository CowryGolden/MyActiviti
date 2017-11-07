/**
 * Title: OrganService.java
 * Package com.zzrenfeng.base.service
 * author zhoujincheng
 * date 2015年10月10日 上午11:28:56
 * version V1.0
 * Copyright (c) 2015,zhoujincheng777@qq.com All Rights Reserved.
 */

package com.zzrenfeng.base.service;

import java.util.List;

import com.zzrenfeng.base.entity.Division;
import com.zzrenfeng.base.model.TreeModel;

/**
 * ClassName: OrganService
 * Description: 组织管理业务类
 * author zhoujincheng
 * date 2015年10月10日 上午11:28:56
 */

public interface DivisionService extends BaseService<Division> {

    /**
     * Title: deleteById
     * Description: 删除某个节点组织(更新状态为I)
     * param   id
     * param return 参数
     * return boolean 返回类型
     * throws
     */
    boolean deleteById(String id);

    /**
     * Title: findSuperOrgan
     * Description: 获取所有可添加子项的组织
     * param return 参数
     * return List<TreeModel> 返回类型
     * throws
     */
    List<TreeModel> findSuperOrgan(String id);

    /**
     * Title: persistenceOrgan
     * Description:持久化处理组织
     * param   permission
     * param return 参数
     * return boolean 返回类型
     * throws
     */
    boolean persistenceOrgan(Division organ);

    /**
     * Description: 根据公司ID查询部门信息
     * Name:findDivByCoId
     * Author:zhoujincheng
     * Time:2016/4/26 14:30
     * param:[id]
     * return:java.util.List<com.zzrenfeng.base.entity.Division>
     */
    List<Division> findDivByCoId(String id);
}
