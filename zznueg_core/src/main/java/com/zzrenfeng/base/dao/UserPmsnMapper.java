package com.zzrenfeng.base.dao;

import java.util.List;

import com.zzrenfeng.base.entity.UserPmsn;

public interface UserPmsnMapper extends BaseMapper<UserPmsn> {
    List<UserPmsn> findAllByUserId(String userId);
    
    int delByPmsnId(String pmsnId);
    
    int delByUserId(String userId);
}