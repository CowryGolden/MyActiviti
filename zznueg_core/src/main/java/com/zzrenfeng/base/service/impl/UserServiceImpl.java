package com.zzrenfeng.base.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zzrenfeng.base.entity.*;
import com.zzrenfeng.base.model.TreeModel;
import com.zzrenfeng.base.service.UserService;
import com.zzrenfeng.base.utils.Constants;
import com.zzrenfeng.base.utils.PageUtil;
import com.zzrenfeng.base.utils.StringUtil;
import com.zzrenfeng.base.utils.UUIDUtils;
import com.zzrenfeng.base.utils.security.Md5Utils;
import com.zzrenfeng.zznueg.dao.TeachStuInfoMapper;
import com.zzrenfeng.zznueg.entity.TeachStuInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Transactional
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    
    //用于后台开户时，开户成功更新教师学生表的开户标志
    @Autowired
    protected TeachStuInfoMapper teachStuInfoMapper;

    @Override
    public List<User> findAll() {
        LOGGER.debug("run the users findall");
        return userMapper.findAll();
    }

    @Override
    public User getUserByName(String name) {

        return userMapper.findByName(name);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean persistenceUser(User user) {
        String userId = Constants.getCurrendUser().getUserId();
        int saveOrUpdateCount = 0;
        
        if (StringUtil.isEmpty(user.getUserId())) {
            BaseDomain.createLog(user, userId);
            user.setPassword(Md5Utils.hash(Constants.DEFAULT_PASSWORD));
            user.setStatus(Constants.PERSISTENCE_STATUS);
            user.setUserId(UUIDUtils.getUUID());
            saveOrUpdateCount = userMapper.insert(user);

            List<Role> rList = roleMapper.findDefaultRole();
            for (Role role : rList) {
                UserRole userRole = new UserRole();
                BaseDomain.createLog(userRole, userId);
                userRole.setUrId(UUIDUtils.getUUID());
                userRole.setUserId(user.getUserId());
                userRole.setRoleId(role.getRoleId());
                userRole.setStatus(Constants.PERSISTENCE_STATUS);
                userRoleMapper.insert(userRole);
            }

        } else {
            BaseDomain.editLog(user, userId);
            
            saveOrUpdateCount = userMapper.updateByPrimaryKeySelective(user);
        }
        if(saveOrUpdateCount == 1) {
        	
        	return true;
        }
        return false;
    }
    
    
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
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public boolean persistenceUserByUserType(User user, TeachStuInfo teachStuInfo, String userType) {
		String userId = Constants.getCurrendUser().getUserId();
        int saveOrUpdateCount = 0;
        
		if(StringUtil.isEmpty(userType)) {
			this.persistenceUser(user);
		} else {
			if (StringUtil.isEmpty(user.getUserId()) && StringUtil.isEmpty(user.getAccount())) {
				return false;	//userId和account有一个为空代表后台开户失败
			}
			BaseDomain.createLog(user, userId);
            user.setPassword(Md5Utils.hash(Constants.DEFAULT_PASSWORD));	//设置默认密码6个1
            saveOrUpdateCount = userMapper.insert(user);
            //设置默认角色权限
            List<Role> rList = roleMapper.findDefaultRole();
            for (Role role : rList) {
                UserRole userRole = new UserRole();
                BaseDomain.createLog(userRole, userId);
                userRole.setUrId(UUIDUtils.getUUID());
                userRole.setUserId(user.getUserId());
                userRole.setRoleId(role.getRoleId());
                userRole.setStatus(Constants.PERSISTENCE_STATUS);
                userRoleMapper.insert(userRole);
            }
		}
		if(Constants.COMM_CONST_INTEGER_1 == saveOrUpdateCount) {
			teachStuInfo.setIsOpenAct(Constants.COMM_CONST_STRING_1);		//将教师学生信息对象中的是否开户设置成：1-已开户			
			if(Constants.COMM_CONST_INTEGER_1 == this.teachStuInfoMapper.updateByPrimaryKeySelective(teachStuInfo)) {
				return true;	//只有两者同时更新成功才返回true
			}        	
        }
		return false;
	}
    

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
    @Override
    public List<User> allUserByPage(Map paramMap) {
        LOGGER.info("开始查找用户信息,分页显示");
        return userMapper.findAllByPage(paramMap);
    }

    @Override
    public boolean delUser(String userId) {
        //删除用户角色映射
        List<UserRole> urList = userRoleMapper.findAllByUserId(userId);
        for (UserRole userRole : urList) {
            userRoleMapper.deleteByPrimaryKey(userRole.getUrId());
        }

        //删除用户权限映射
        List<UserPmsn> upList = userPmsnMapper.findAllByUserId(userId);
        for (UserPmsn userPmsn : upList) {
            userPmsnMapper.deleteByPrimaryKey(userPmsn.getUpmId());
        }

        //删除用户岗位映射
        List<UserPost> userPostList = userPostMapper.findAllByUserId(userId);
        for (UserPost userPost : userPostList) {
            userPostMapper.deleteByPrimaryKey(userPost.getUpId());
        }
        //删除项目组用户映射
        List<PrjUser> prjUserList = prjUserMapper.findAllByUserId(userId);
        for (PrjUser prjUser : prjUserList) {
            prjUserMapper.deleteByPrimaryKey(prjUser.getPuId());
        }
        //删除用户

        User user = userMapper.selectByPrimaryKey(userId);
        user.setStatus(Constants.PERSISTENCE_DELETE_STATUS);
        return userMapper.updateByPrimaryKey(user) > 0;

    }


    /**
     * Description: 获取所有可添加用户的岗位，按树状结构展示
     * stepByStep: 传递过来公司与部门树
     * 1.循环判断该树中哪个节点是部门节点
     * 2.然后查找该节点的下属岗位
     * 3.添加到节点内
     * Name:getPostList
     * Author:zhoujincheng
     * Time:2016/4/27 23:24
     * param:[list]
     * return:java.util.List<com.zzrenfeng.base.model.TreeModel>
     */
    @Override
    public List<TreeModel> getPostList(List<TreeModel> list) {
        //递归处理树结构，添加岗位信息
        postToTree(list);
        return list;
    }

    //添加岗位数据到公司-部门树模型下（第一步处理方法：判断是否有子节点）
    private void postToTree(List<TreeModel> list) {
        for (TreeModel treeModel : list) {
            List<TreeModel> children = treeModel.getChildren();
            //有子节点递归，没有子节点不参与递归，在每个部门节点处理岗位信息的嫁接
            if (children == null || children.size() == 0) {
                addPost(treeModel, children);
            } else {
                List<TreeModel> childList = treeModel.getChildren();
                //内层循环，添加节点
                postToTree(childList);
                addPost(treeModel, children);
            }
        }
    }

    //添加岗位数据到公司-部门树模型下（第二步处理方法：判断是否是部门节点）
    private void addPost(TreeModel treeModel, List<TreeModel> children) {
        //判断是否是部门节点
        if ("DIV".equals(treeModel.getPid())) {
            //获取每个节点的id，即部门id
            String divId = treeModel.getId();
            //获取该部门下属的所有岗位
            List<Post> postList = postMapper.findPostByDiv(divId);
            //把岗位加入部门节点内
            treeModel.setChildren(addPostToDiv(postList, children));
        }
    }

    //添加岗位数据到公司-部门树模型下（第三步处理方法：把岗位加入部门节点内）
    private List<TreeModel> addPostToDiv(List<Post> list, List<TreeModel> childList) {
        if (childList == null) {
            childList = new ArrayList<>();
        }
        for (Post post : list) {
            TreeModel tm = new TreeModel();
            tm.setId(post.getPostId());
            tm.setIconCls(Constants.DEFAULT_ICON);
            tm.setPid("POST");
            tm.setText(post.getPostName());
            tm.setState(Constants.TREE_STATUS_OPEN);
            childList.add(tm);
        }
        return childList;
    }

    /**
     * Description: 按用户ID集合查询用户信息，分页，用于获取岗位、部门、公司的所有用户信息
     * Name:findUserByPage
     * Author:zhoujincheng
     * Time:2016/4/28 10:20
     * param:[pageUtil, idList]
     * return:java.util.List<com.zzrenfeng.base.entity.User>
     */
    @Override
    public List<User> findUserByPage(PageUtil pageUtil, Set<String> idList) {
        return userMapper.findUserByPage(pageUtil, idList);
    }

}
