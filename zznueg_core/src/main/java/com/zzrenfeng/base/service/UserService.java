package com.zzrenfeng.base.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.zzrenfeng.base.entity.User;
import com.zzrenfeng.base.model.TreeModel;
import com.zzrenfeng.base.utils.PageUtil;
import com.zzrenfeng.zznueg.entity.TeachStuInfo;

public interface UserService extends BaseService<User> {
    /**
     * 根据名字查询用户,用于shiro权限控制
     * param name
     * return
     */
    User getUserByName(String name);

    /**
     * 持久化用户信息
     * param user
     * return
     */
    boolean persistenceUser(User user);
    
    /**
     * @功能描述：新增方法：根据用户类型持久化用户信息，后台开户成功后同时更新教师学生信息表中的开户标志
     * 			用户类型字段虽然用户信息表中不存在，根据前端送过来的这个值，可以作为持久化用户时的依据
     * @创  建  者：zhoujincheng
     * @版        本：V1.0.0
     * @创建日期：2017年8月3日 下午5:56:24
     * 
     * @修  改  人：
     * @修改日期：
     * @修改描述：
     * 
     * @param user
     * @param teachStuInfo
     * @param userType
     * @return
     */
    boolean persistenceUserByUserType(User user, TeachStuInfo teachStuInfo, String userType);

    /**
     * @功能描述：分页查, String postId询用户信息
     * @创  建  者：zhoujincheng
     * @版        本：V1.0.0
     * @创建日期：2016年04月26日 下午3:19:23
     * 
     * @修  改  人：zjc
     * @版        本：V1.1.0
     * @修改日期：20171016 15:20
     * @修改描述：将传递参数PageUtil pageUtil修改为Map paramMap；
	 * 			目的是为了：增加account-账号，userType-用户类别等（模糊）检索参数；进行按条件检索 
     * 
     * @param PageUtil pageUtil -> Map paramMap
     * @return
     */
    List<User> allUserByPage(Map paramMap);

    /**
     * Description: 按用户ID集合查询用户信息，分页，用于获取岗位、部门、公司的所有用户信息
     * Name:findUserByPage
     * Author:zhoujincheng
     * Time:2016/4/28 10:20
     * param:[pageUtil, idList]
     * return:java.util.List<com.zzrenfeng.base.entity.User>
     */
    List<User> findUserByPage(PageUtil pageUtil, Set<String> idList);


    boolean delUser(String userId);


    /**
     * Description: 获取所有可添加用户的岗位，按树状结构展示
     * Name:getPostList
     * Author:zhoujincheng
     * Time:2016/4/27 23:24
     * param:[list]
     * return:java.util.List<com.zzrenfeng.base.model.TreeModel>
     */
    List<TreeModel> getPostList(List<TreeModel> list);

}