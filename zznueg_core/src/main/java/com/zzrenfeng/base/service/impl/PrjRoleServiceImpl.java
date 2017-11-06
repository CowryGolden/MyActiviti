package com.zzrenfeng.base.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zzrenfeng.base.entity.PrjRole;
import com.zzrenfeng.base.service.PrjRoleService;

import java.util.List;

/**
 * tide
 * author zhoujincheng
 * create 2016/5/2 17:52
 */
@Transactional
@Service("prjRoleService")
public class PrjRoleServiceImpl extends BaseServiceImpl<PrjRole> implements PrjRoleService {
    @Override
    public List<PrjRole> getPrjRoleByPrjId(String prjId) {
        return prjRoleMapper.findAllByPrjId(prjId);
    }
}
