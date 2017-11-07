package com.zzrenfeng.base.dao;

import java.util.List;

import com.zzrenfeng.base.entity.UserPost;

public interface UserPostMapper extends BaseMapper<UserPost> {

    List<UserPost> findByPostId(String postId);

    List<UserPost> findAllByUserId(String userId);
}