package com.zzrenfeng.base.dao;

import java.util.List;

import com.zzrenfeng.base.entity.Company;
import com.zzrenfeng.base.utils.PageUtil;

public interface CompanyMapper extends BaseMapper<Company> {
    /**
     * Description: 分页查询公司信息
     * Name:findAllByPage
     * Author:zhoujincheng
     * Time:2016/4/23 10:43
     * param:[pageUtil]
     * return:java.util.List<com.zzrenfeng.base.entity.Company>
     */
    List<Company> findAllByPage(PageUtil pageUtil);

    /**
     * Description:  根据父ID查询公司信息
     * Name:selectByPrntId
     * Author:zhoujincheng
     * Time:2016/4/28 11:13
     * param:[prntId]
     * return:java.util.List<com.zzrenfeng.base.entity.Company>
     */
    List<Company> selectByPrntId(String prntId);

    List<Company> findByPid(String pid);
}