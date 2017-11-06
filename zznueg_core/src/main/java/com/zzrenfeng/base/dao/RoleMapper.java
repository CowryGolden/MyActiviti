package com.zzrenfeng.base.dao;

import java.util.List;

import com.zzrenfeng.base.entity.Role;
import com.zzrenfeng.base.utils.PageUtil;

public interface RoleMapper extends BaseMapper<Role> {
    /**
     * Description: 分页获取角色信息
     * Name:findAllByPage
     * Author:zhoujincheng
     * Time:2016/4/23 10:47
     * param:[pageUtil]
     * return:java.util.List<com.zzrenfeng.base.entity.Role>
     */
    List<Role> findAllByPage(PageUtil pageUtil);

    List<Role> findDefaultRole();
}