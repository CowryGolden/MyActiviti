package com.zzrenfeng.base.service;

import java.util.List;

import com.zzrenfeng.base.entity.PostRole;

/**
 * Description:
 * author: zhoujincheng
 * date 2016/4/27 17:27
 */
public interface PostRoleService extends BaseService<PostRole> {

    /**
     * 保存分配岗位角色
     * param postId 岗位id
     * param checkedIds 角色ID集合
     * return
     */
    boolean savePostRole(String postId, String checkedIds);


    List<PostRole> getPostRoleByPostId(String postId);
}
