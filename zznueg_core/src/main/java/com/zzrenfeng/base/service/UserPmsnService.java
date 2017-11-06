package com.zzrenfeng.base.service;

import java.util.List;

import com.zzrenfeng.base.entity.UserPmsn;

/**
 * Description:
 * author: zhoujincheng
 * date 2016/4/28 17:22
 */
public interface UserPmsnService extends BaseService<UserPmsn> {

    /**
     * 保存分配用户权限
     * param userId 用户id
     * param checkedIds 岗位ID集合
     * return
     */
    boolean saveUserPmsn(String userId, String checkedIds);


    /**
     * Description:  获取用户的所有直接权限映射信息
     * Name:findByUserId
     * Author:zhoujincheng
     * Time:2016/4/28 22:15
     * param:[userId]
     * return:java.util.List<com.zzrenfeng.base.entity.UserPmsn>
     */
    List<UserPmsn> findByUserId(String userId);
}
