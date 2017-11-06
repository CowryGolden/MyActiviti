package com.zzrenfeng.base.dao;

import org.apache.ibatis.annotations.Param;

import com.zzrenfeng.base.entity.User;
import com.zzrenfeng.base.utils.PageUtil;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface UserMapper extends BaseMapper<User> {

    /**
     * Description: 好名字自己会解释
     * Name:findByName
     * Author:zhoujincheng
     * Time:2016/4/23 10:49
     * param:[name]
     * return:com.zzrenfeng.base.entity.User
     */
    User findByName(String name);

    /**
     * Description: 好名字自己会解释
     * Name:findAllByPage
     * Author:zhoujincheng
     * Time:2016/4/23 10:49
     * param:[pageUtil]
     * return:java.util.List<com.zzrenfeng.base.entity.User>
     * 
     * @修  改  人：zjc
     * @版        本：V1.1.0
     * @修改日期：20171016 15:20
     * @修改描述：将传递参数PageUtil pageUtil修改为Map paramMap；
	 * 			目的是为了：增加account-账号，userType-用户类别等（模糊）检索参数；进行按条件检索 
     * 
     * @param PageUtil pageUtil -> Map paramMap
     * @return
     * 
     */
    List<User> findAllByPage(Map paramMap);

    /**
     * Description: 按用户ID集合查询用户信息，分页，用于获取岗位、部门、公司的所有用户信息
     * Name:findUserByPage
     * Author:zhoujincheng
     * Time:2016/4/28 10:20
     * param:[pageUtil, idList]
     * return:java.util.List<com.zzrenfeng.base.entity.User>
     */
    List<User> findUserByPage(@Param("pageUtil") PageUtil pageUtil, @Param("idSet") Set<String> idSet);


    /**
     * Description: 按用户ID集合查询用户信息，分页，用于获取岗位、部门、公司的所有用户信息
     * Name:findUserByPage
     * Author:zhoujincheng
     * Time:2016/4/28 10:20
     * return:java.util.List<com.zzrenfeng.base.entity.User>
     */
    List<User> findUserByList(@Param("idSet") Set<String> idSet);


}