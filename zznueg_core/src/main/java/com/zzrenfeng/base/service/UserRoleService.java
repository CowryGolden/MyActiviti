package com.zzrenfeng.base.service;

import java.util.List;
import java.util.Map;

import com.zzrenfeng.base.entity.Role;
import com.zzrenfeng.base.entity.UserRole;
import com.zzrenfeng.base.model.TreeModel;

/**
 * topic
 * author: zhoujincheng
 * create: 2016/4/8 9:22
 */
public interface UserRoleService extends BaseService<UserRole> {

    /**
     * 好代码自己会说话
     * param roleId
     * return
     */
    List<Role> findAllByUserId(String userId);

    /**
     * Description: 根据用户ID获取所有用户角色映射关系
     * Name:findByUserId
     * Author:zhoujincheng
     * Time:2016/4/28 22:13
     * param:[userId]
     * return:java.util.List<com.zzrenfeng.base.entity.UserRole>
     */
    List<UserRole> findByUserId(String userId);

    /**
     * 保存分配角色权限
     * param roleId 角色id
     * param checkedIds 菜单权限ID集合
     * return
     */
    boolean saveRole(String userId, String checkedIds);
    
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
    
    /**
     * @功能描述：获取角色List列表，并将其转换为树形模型（虽然角色列表仅有一级，这里转为树形仅仅是方便前端展示）
     * @创  建  者：zhoujincheng
     * @版        本：V1.0.0
     * @创建日期：2017年10月21日 上午10:19:56
     * 
     * @修  改  人：
     * @版        本：V1.1.0
     * @修改日期：
     * @修改描述：
     * 
     * @return
     * @throws Exception
     */
    List<TreeModel> getRoleTreeModelList() throws Exception;

}
