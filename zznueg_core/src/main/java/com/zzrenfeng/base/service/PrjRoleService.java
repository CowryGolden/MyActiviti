package com.zzrenfeng.base.service;

import java.util.List;

import com.zzrenfeng.base.entity.PrjRole;

/**
 * tide
 * author zhoujincheng
 * create 2016/5/2 17:51
 */
public interface PrjRoleService extends BaseService<PrjRole> {

    List<PrjRole> getPrjRoleByPrjId(String prjId);
}
