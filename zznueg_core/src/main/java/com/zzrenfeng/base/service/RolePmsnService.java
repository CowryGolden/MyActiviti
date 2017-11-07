package com.zzrenfeng.base.service;

import java.util.List;

import com.zzrenfeng.base.entity.Permission;
import com.zzrenfeng.base.entity.RolePmsn;

/**
 * topic
 * author: zhoujincheng
 * create: 2016/4/8 9:22
 */
public interface RolePmsnService extends BaseService<RolePmsn> {

    /**
     * 好代码自己会说话
     * param roleId
     * return
     */
    List<Permission> findAllByRoleId(String roleId);

    /**
     * 保存分配角色权限
     * param roleId 角色id
     * param checkedIds 菜单权限ID集合
     * return
     */
    boolean savePermission(String roleId, String checkedIds);

    boolean setDefaultRole(String roleId);

}
