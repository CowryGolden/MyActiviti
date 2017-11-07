package com.zzrenfeng.base.service;

import java.util.List;

import com.zzrenfeng.base.entity.PrjUser;
import com.zzrenfeng.base.entity.User;

/**
 * tide
 * author zhoujincheng
 * create 2016/5/2 18:19
 */
public interface PrjUserService extends BaseService<PrjUser> {
    List<User> getPrjUserByPrjId(String prjId);
}
