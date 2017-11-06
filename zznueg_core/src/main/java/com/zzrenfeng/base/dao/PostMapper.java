package com.zzrenfeng.base.dao;

import java.util.List;

import com.zzrenfeng.base.entity.Post;

public interface PostMapper extends BaseMapper<Post> {
    /**
     * Description: 根据部门ID查询该部门下所有的岗位信息
     * Name:findPostByDiv
     * Author:zhoujincheng
     * Time:2016/4/27 8:38
     * param:[divId]
     * return:java.util.List<com.zzrenfeng.base.entity.Post>
     */
    List<Post> findPostByDiv(String divId);
}