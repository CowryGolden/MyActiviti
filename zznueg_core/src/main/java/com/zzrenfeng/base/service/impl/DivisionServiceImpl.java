/**
 * Title: OrganServiceImpl.java
 * Package com.zzrenfeng.base.service.impl
 * author zhoujincheng
 * date 2015年10月10日 上午11:30:17
 * version V1.0
 * Copyright (c) 2015,zhoujincheng777@qq.com All Rights Reserved.
 */

package com.zzrenfeng.base.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zzrenfeng.base.entity.BaseDomain;
import com.zzrenfeng.base.entity.Division;
import com.zzrenfeng.base.entity.Post;
import com.zzrenfeng.base.model.TreeModel;
import com.zzrenfeng.base.service.DivisionService;
import com.zzrenfeng.base.utils.Constants;
import com.zzrenfeng.base.utils.StringUtil;
import com.zzrenfeng.base.utils.UUIDUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * author zhoujincheng
 * ClassName: OrganServiceImpl
 * Description: 组织业务处理实现类
 * date 2015年10月10日 上午11:30:17
 */
@Transactional
@Service("divisionService")
public class DivisionServiceImpl extends BaseServiceImpl<Division> implements
        DivisionService {
    private final Logger LOGGER = LoggerFactory.getLogger(DivisionServiceImpl.class);


    @Override
    public boolean deleteById(String id) {
        //如果部门下面有岗位，不能删除
        List<Post> pList = postMapper.findPostByDiv(id);
        if (pList.size() > 0) {
            return false;
        } else {
            Division divi = divisionMapper.selectByPrimaryKey(id);
            divi.setStatus(Constants.PERSISTENCE_DELETE_STATUS);
            return divisionMapper.updateByPrimaryKey(divi) > 0;
        }
    }

    @Override
    public List<TreeModel> findSuperOrgan(String id) {
        List<Division> organList = divisionMapper.findSuperOrgan(id);
        List<TreeModel> tList = new ArrayList<>();
        for (Division divi : organList) {
            TreeModel treeModel = new TreeModel();
            treeModel.setId(divi.getDivId());
            treeModel.setPid("");
            treeModel.setText(divi.getDivName()); //注意部门管理修改为combotree形式
            treeModel.setIconCls(divi.getIconCls());
            treeModel.setState(Constants.TREE_STATUS_OPEN);
            tList.add(treeModel);
        }

        return tList;
    }

    @Override
    public boolean persistenceOrgan(Division divi) {
        String userId = Constants.getCurrendUser().getUserId();

        if (StringUtil.isEmpty(divi.getDivId())) {

            BaseDomain.createLog(divi, userId);
            divi.setStatus(Constants.PERSISTENCE_STATUS);
            // 部门下级不允许有子部门
            divi.setState(Constants.TREE_STATUS_OPEN);
            divi.setDivId(UUIDUtils.getUUID());
            divi.setIconCls(Constants.DIVISION_ICON);
            divisionMapper.insert(divi);
        } else {
            divi.setState(Constants.TREE_STATUS_OPEN);
            BaseDomain.editLog(divi, userId);
            divi.setIconCls(Constants.DIVISION_ICON);
            divisionMapper.updateByPrimaryKeySelective(divi);
        }
        return true;
    }

    /**
     * Description: 根据公司ID查询部门信息
     * Name:findDivByCoId
     * Author:zhoujincheng
     * Time:2016/4/26 14:30
     * param:[id]
     * return:java.util.List<com.zzrenfeng.base.entity.Division>
     */
    @Override
    public List<Division> findDivByCoId(String id) {
        return divisionMapper.findByCompId(id);
    }
}
