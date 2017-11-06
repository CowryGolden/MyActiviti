package com.zzrenfeng.base.dao;

import java.util.List;

import com.zzrenfeng.base.entity.PrjUser;

public interface PrjUserMapper extends BaseMapper<PrjUser> {
    List<PrjUser> getPrjUserByPrjId(String prjId);

    List<PrjUser> findAllByUserId(String userId);
}