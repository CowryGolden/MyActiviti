package com.zzrenfeng.base.service;

import java.util.List;

import com.zzrenfeng.base.dao.BaseMapper;
import com.zzrenfeng.base.entity.Company;
import com.zzrenfeng.base.model.TreeModel;
import com.zzrenfeng.base.utils.PageUtil;

/**
 * topic
 * author zhoujincheng
 * create 2016/4/4 14:23
 */
public interface CompanyService extends BaseMapper<Company> {

    /**
     * Description: 获取所有的公司名称和ID
     * Name:getAllCoName
     * Author:zhoujincheng
     * Time:2016/4/26 10:19
     * param:[]
     * return:java.util.List<com.zzrenfeng.base.model.DataList>
     */
    List<TreeModel> getAllCoName();

    /**
     * Description: 查找所有的公司信息
     * Name:findComp
     * Author:zhoujincheng
     * Time:2016/4/22 11:59
     * param:[pageUtil]
     * return:java.util.List<com.zzrenfeng.base.entity.Company>
     */
    List<Company> findComp(PageUtil pageUtil);


    /**
     * Description: 根据ID删除公司信息
     * Name:delComp
     * Author:zhoujincheng
     * Time:2016/4/22 11:59
     * param:[compId]
     * return:boolean
     */
    boolean delComp(String compId);


    /**
     * Description: 持久化公司信息，根据ID判断是insert还是update
     * Name:persistenceComp
     * Author:zhoujincheng
     * Time:2016/4/22 11:59
     * param:[company]
     * return:boolean
     */
    boolean persistenceComp(Company company);

    /**
     * Description: 查询某个公司下所有的公司信息，包含下属多级公司
     * Name:AllCoById
     * Author:zhoujincheng
     * Time:2016/4/28 11:10
     * param:[coId]
     * return:java.util.List<com.zzrenfeng.base.entity.Company>
     */
    List<Company> AllCoById(String coId);

    List<Company> findByPid(String pid);
}
