package com.zzrenfeng.base.dao;

import java.util.List;

import com.zzrenfeng.base.entity.PostRole;

public interface PostRoleMapper extends BaseMapper<PostRole> {

    List<PostRole> findAllByPostId(String postId);

    List<PostRole> findAllByRoleId(String roleId);

}