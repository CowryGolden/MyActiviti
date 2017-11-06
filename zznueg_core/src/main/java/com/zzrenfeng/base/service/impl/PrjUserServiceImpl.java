package com.zzrenfeng.base.service.impl;

import com.zzrenfeng.base.entity.PrjUser;
import com.zzrenfeng.base.entity.User;
import com.zzrenfeng.base.service.PrjUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * tide
 * author zhoujincheng
 * create 2016/5/2 18:20
 */
@Transactional
@Service("prjUserService")
public class PrjUserServiceImpl extends BaseServiceImpl<PrjUser> implements PrjUserService {
    @Override
    public List<User> getPrjUserByPrjId(String prjId) {
        List<PrjUser> puList = prjUserMapper.getPrjUserByPrjId(prjId);
        Set<String> idList = puList.stream().map(PrjUser::getUserId).collect(Collectors.toSet());
        if (idList.size() > 0) {
            return userMapper.findUserByList(idList);
        } else {
            return null;
        }
    }
}
