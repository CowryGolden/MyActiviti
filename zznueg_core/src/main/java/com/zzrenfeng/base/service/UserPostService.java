package com.zzrenfeng.base.service;

import java.util.List;

import com.zzrenfeng.base.entity.UserPost;

/**
 * Description:
 * author: zhoujincheng
 * date 2016/4/28 10:35
 */
public interface UserPostService extends BaseService<UserPost> {
    /**
     * Description: 根据岗位查询用户ID集合
     * Name:findByPostId
     * Author:zhoujincheng
     * Time:2016/4/28 10:36
     * param:[postId]
     * return:java.util.List<com.zzrenfeng.base.entity.Integer>
     */
    List<String> findByPostId(String postId);

    /**
     * Description: 根据用户ID查询所有的用户角色映射关系，用于预设
     * Name:findByUserId
     * Author:zhoujincheng
     * Time:2016/4/28 22:06
     * param:[userId]
     * return:java.util.List<com.zzrenfeng.base.entity.UserPost>
     */
    List<UserPost> findByUserId(String userId);


    /**
     * 保存分配用户岗位
     * param userId 用户id
     * param checkedIds 岗位ID集合
     * return
     */
    boolean saveUserPost(String userId, String checkedIds);
}
