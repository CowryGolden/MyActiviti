package com.zzrenfeng.base.dao;

import java.util.List;
import java.util.Map;

import com.zzrenfeng.base.entity.UserRole;

public interface UserRoleMapper extends BaseMapper<UserRole> {
    /**
     * Description: 获取某个用户的所有角色信息
     * Name:findAllByUserId
     * Author:zhoujincheng
     * Time:2016/4/23 10:48
     * param:[userId]
     * return:java.util.List<com.zzrenfeng.base.entity.UserRole>
     */
    List<UserRole> findAllByUserId(String userId);

    List<UserRole> findAllByRoleId(String roleId);
    
    /**
     * @功能描述：分页获取用户-角色对应信息
     * @创  建  者：zhoujincheng
     * @版        本：V1.0.0
     * @创建日期：2017年10月20日 上午10:53:29
     * 
     * @修  改  人：
     * @版        本：V1.1.0
     * @修改日期：
     * @修改描述：
     * 
     * @param paraMap
     * @return
     * @throws Exception
     */
    List<Map> getUserRoleInfo(Map paramMap) throws Exception;
    
    /**
     * @功能描述：获取用户-角色对于关系的数量，用于分页显示 
     * @创  建  者：zhoujincheng
     * @版        本：V1.0.0
     * @创建日期：2017年10月20日 上午10:53:44
     * 
     * @修  改  人：
     * @版        本：V1.1.0
     * @修改日期：
     * @修改描述：
     * 
     * @param paramMap
     * @return
     * @throws Exception
     */
    Long getUserRoleCount(Map<String, Object> paramMap) throws Exception;
    
}