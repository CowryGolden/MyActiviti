package com.zzrenfeng.base.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zzrenfeng.base.entity.BaseDomain;
import com.zzrenfeng.base.entity.Role;
import com.zzrenfeng.base.entity.UserRole;
import com.zzrenfeng.base.model.TreeModel;
import com.zzrenfeng.base.service.UserRoleService;
import com.zzrenfeng.base.utils.Constants;
import com.zzrenfeng.base.utils.StringUtil;
import com.zzrenfeng.base.utils.UUIDUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * topic
 * author: zhoujincheng
 * create: 2016/4/13 9:27
 */
@Transactional
@Service("userRoleService")
public class UserRoleServiceImpl extends BaseServiceImpl<UserRole> implements UserRoleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRoleServiceImpl.class);

    /**
     * 好代码自己会说话
     * param roleId
     * return
     *
     * @param userId
     */
    @Override
    public List<Role> findAllByUserId(String userId) {
        LOGGER.info("开始读取id为" + userId + "的用户信息");
        List<Role> rList = new ArrayList<>();
        List<UserRole> urList = userRoleMapper.findAllByUserId(userId);
        for (UserRole i : urList) {
            Role role = new Role();
            role.setRoleId(i.getRoleId());
            rList.add(role);
        }
        return rList;
    }

    /**
     * 保存分配角色权限
     * 处理逻辑：根据用户查找所有的已有角色信息，然后全部删除，最后赋予新角色
     * param roleId 角色id
     * param checkedIds 菜单权限ID集合
     * return
     * param userId
     * param checkedIds
     */
    @Override
    public boolean saveRole(String userId, String checkedIds) {

        String currentUserId = Constants.getCurrendUser().getUserId();

        //盛放没有修改以前的对应记录，用于在修改后删除多余的记录
        Map<String, UserRole> map = new HashMap<>();

        //获取ID对应的所有权限
        List<UserRole> urList = userRoleMapper.findAllByUserId(userId);

        //循环处理这些对应记录，逐一放入map中，然后设置该记录为过期，用于标记删除
        for (UserRole userRole : urList) {
            //对于该对应记录来说，互斥的ID当成key处理
            map.put(userRole.getRoleId(), userRole);
            //设置所有记录过期
            updUserRole(currentUserId, userRole, Constants.PERSISTENCE_DELETE_STATUS);
        }


        //开始处理修改后提交的对应数据，checkedIds为权限集合
        if (null != checkedIds && !"".equals(checkedIds)) {
            String[] ids = checkedIds.split(",");
            for (String id : ids) {
                if (StringUtil.isEmpty(id)) {
                    continue;
                }
                //然后看这些ID是否在map中
                UserRole userRole = map.get(id);
                if (userRole != null) {
                    //如果在map中，说明在数据库中有记录，把状态改成正常
                    updUserRole(userId, userRole, Constants.PERSISTENCE_STATUS);
                } else {
                    //如果不在msp中，说明该对应记录在数据库中没有，要新增
                    userRole = new UserRole();
                    BaseDomain.createLog(userRole, userId);
                    userRole.setStatus(Constants.PERSISTENCE_STATUS);
                    //循环处理的ID
                    userRole.setRoleId(id);
                    //传递过来的Id
                    userRole.setUserId(userId);
                    userRole.setUrId(UUIDUtils.getUUID());
                    userRoleMapper.insert(userRole);
                }
                //同时删除已经处理过的map值
                map.remove(id);
            }
        }
        //当所有值都处理完毕以后，剩下的map值就是：原来有对应关系，修改后没有对应关系，删除之
        for (Map.Entry<String, UserRole> entry : map.entrySet()) {
            userRoleMapper.deleteByPrimaryKey(entry.getValue().getUrId());
        }

        return true;
    }

    private void updUserRole(String userId, UserRole userRole, String status) {
        BaseDomain.editLog(userRole, userId);
        userRole.setStatus(status);
        userRoleMapper.updateByPrimaryKeySelective(userRole);
    }

    /**
     * Description: 根据用户ID获取所有用户角色映射关系
     * Name:findByUserId
     * Author:zhoujincheng
     * Time:2016/4/28 22:13
     * param:[userId]
     * return:java.util.List<com.zzrenfeng.base.entity.UserRole>
     *
     * @param userId
     */
    @Override
    public List<UserRole> findByUserId(String userId) {
        return userRoleMapper.findAllByUserId(userId);
    }

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
	@Override
	public List<Map> getUserRoleInfo(Map paramMap) throws Exception {
		return userRoleMapper.getUserRoleInfo(paramMap);
	}

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
	@Override
	public Long getUserRoleCount(Map<String, Object> paramMap) throws Exception {
		return userRoleMapper.getUserRoleCount(paramMap) == null ? 0L : userRoleMapper.getUserRoleCount(paramMap);
	}

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
	@Override
	public List<TreeModel> getRoleTreeModelList() throws Exception {
		List<Role> roleList = new ArrayList<Role>();
		roleList = roleMapper.findAll();
		return roleToTreeModelList(roleList);
	}
	
	/**
	 * @功能描述：将roleList转为树形模型List；由于角色只有一级节点，将其父节点统一设置为“0”
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年10月21日 上午10:50:38
	 * 
	 * @版        本：V1.1.0
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param id
	 * @param roleList
	 * @return
	 * @throws Exception
	 */
	private List<TreeModel> roleToTreeModelList(List<Role> roleList) throws Exception {
		List<TreeModel> roleTreeModelList = new ArrayList<TreeModel>();
		TreeModel roleMenu = null;
		for(Role role : roleList) {
			roleMenu = new TreeModel();
			roleMenu.setId(role.getRoleId());
			roleMenu.setPid(Constants.COMM_CONST_STRING_0);  //由于角色只有一级节点，将其父节点统一设置为“0”
			roleMenu.setText(role.getRoleName());
			roleMenu.setIconCls(Constants.STAR_ICON);
			roleMenu.setState(Constants.TREE_STATUS_OPEN);
			roleMenu.setChildren(null);
			
			roleTreeModelList.add(roleMenu);
		}
		return roleTreeModelList;
	}
	
	
}
