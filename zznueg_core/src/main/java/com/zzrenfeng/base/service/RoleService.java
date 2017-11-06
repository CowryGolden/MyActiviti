package com.zzrenfeng.base.service;

import java.util.List;

import com.zzrenfeng.base.entity.Role;
import com.zzrenfeng.base.utils.PageUtil;

/**
 * topic
 * author: zhoujincheng
 * create: 2016/4/8 9:22
 */
public interface RoleService extends BaseService<Role> {
    /**
     * 分页查询角色信息
     * param pageUtil
     * return
     */
    List<Role> findAllRoleList(PageUtil pageUtil);

    /**
     * 持久化角色信息（包含新增或修改）
     * param role
     * return
     */

    boolean persistenceRole(Role role);

    /**
     * 先判断是否有级联关系，再删除角色信息
     * param id
     * return
     */
    boolean delRole(String id);
}
