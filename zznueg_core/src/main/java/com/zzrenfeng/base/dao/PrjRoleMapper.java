package com.zzrenfeng.base.dao;

import java.util.List;

import com.zzrenfeng.base.entity.PrjRole;

public interface PrjRoleMapper extends BaseMapper<PrjRole> {
    List<PrjRole> findAllByPrjId(String prjId);

    List<PrjRole> findAllByRoleId(String roleId);
}