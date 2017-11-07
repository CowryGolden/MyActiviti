package com.zzrenfeng.base.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zzrenfeng.base.model.GridModel;
import com.zzrenfeng.base.model.TreeModel;
import com.zzrenfeng.base.service.UserRoleService;
import com.zzrenfeng.base.utils.PageUtil;
import com.zzrenfeng.base.utils.StringUtil;

/**
 * topic 用户角色分配处理器 
 * author zhoujincheng
 * create 2016/4/2 18:58
 */
@Controller
@RequestMapping(value = "/manage/userRole")
public class UserRoleController {
    private final Logger LOGGER = LoggerFactory.getLogger(UserRoleController.class);
    
    @Autowired
    private UserRoleService userRoleService;
    
    @RequestMapping("/setRole")
    public String setRole() {
        LOGGER.debug("setRole() is executed!");
        return "manage/userRole/userRoleMain";
    }
    
    /**
     * @功能描述：用户角色管理主界面跳转界面；该页面包含：1、用户-角色：根据用户获取其角色信息；2、角色-用户：根据角色获取相关用户信息；
     * 			访问路径：manage/userRole/userRoleMain
     * @创  建  者：zhoujincheng
     * @版        本：V1.0.0
     * @创建日期：2017年10月19日 下午4:34:50
     * 
     * @修  改  人：
     * @版        本：V1.1.0
     * @修改日期：
     * @修改描述：
     * 
     * @return
     */
    @RequestMapping("/userRoleMain")
    public String userRoleMain() {
        LOGGER.debug("userRoleMain() is executed!");
        return "manage/userrole/userRoleMain";
    }
    
    /**
     * @功能描述：用户-角色对应关系视图页面跳转；
     * 			访问路径：manage/userRole/userRoleOfView
     * @创  建  者：zhoujincheng
     * @版        本：V1.0.0
     * @创建日期：2017年10月20日 上午11:00:55
     * 
     * @修  改  人：
     * @版        本：V1.1.0
     * @修改日期：
     * @修改描述：
     * 
     * @return
     */
    @RequestMapping("/userRoleOfView")
    public String userRoleOfView() {
        return "manage/userrole/userRoleOfView";
    }
    
    /**
     * @功能描述：用户-角色对应关系视图页面跳转；
     * 			访问路径：manage/userRole/roleUserOfView
     * @创  建  者：zhoujincheng
     * @版        本：V1.0.0
     * @创建日期：2017年10月20日 上午16:17:55
     * 
     * @修  改  人：
     * @版        本：V1.1.0
     * @修改日期：
     * @修改描述：
     * 
     * @return
     */
    @RequestMapping("/roleUserOfView")
    public String roleUserOfView() {
        return "manage/userrole/roleUserOfView";
    }
    
    /**
     * @功能描述：分页获取用户-角色对应关系信息
     * 			访问路径：manage/userRole/getUserRoleInfoByPage
     * @创  建  者：zhoujincheng
     * @版        本：V1.0.0
     * @创建日期：2017年10月20日 上午11:02:51
     * 
     * @修  改  人：
     * @版        本：V1.1.0
     * @修改日期：
     * @修改描述：
     * 
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getUserRoleInfoByPage", produces = "application/json;charset=utf-8")
    public GridModel getUserRoleInfoByPage(HttpServletRequest request) {
    	LOGGER.debug("getUserRoleInfoByPage() is executed!");
    	Map paramMap = new HashMap<>();
		String account = StringUtil.isEmpty(request.getParameter("account")) ? null : request.getParameter("account").trim();	//获取用户账号
		String userName = StringUtil.isEmpty(request.getParameter("userName")) ? null : request.getParameter("userName").trim();	//获取用户名称
		String roleId = StringUtil.isEmpty(request.getParameter("roleId")) ? null : request.getParameter("roleId").trim();	//获取角色ID
		int pageNo = Integer.parseInt(request.getParameter("page"));
        int length = Integer.parseInt(request.getParameter("rows"));
        PageUtil pageUtil = new PageUtil((pageNo - 1) * length, length);
        paramMap.put("account", account);
        paramMap.put("userName", userName);
        paramMap.put("roleId", roleId);
        paramMap.put("begin", pageUtil.getBegin());
        paramMap.put("end", pageUtil.getEnd());
        
        GridModel gridModel = new GridModel();
        try {
			gridModel.setRows(userRoleService.getUserRoleInfo(paramMap));
			gridModel.setTotal(userRoleService.getUserRoleCount(paramMap));
		} catch (Exception e) {
			e.printStackTrace();
		}
        return gridModel;    	
    }
    
    /**
     * @功能描述：获取角色树形模型List，虽然角色列表仅有一级，这里转为树形仅仅是方便前端展示
     * 			访问路径：manage/userRole/getRoleTreeList
     * @创  建  者：zhoujincheng
     * @版        本：V1.0.0
     * @创建日期：2017年10月21日 上午11:10:55
     * 
     * @修  改  人：
     * @版        本：V1.1.0
     * @修改日期：
     * @修改描述：
     * 
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getRoleTreeList", produces = "application/json;charset=utf-8")
    public List<TreeModel> getRoleTreeList() {
    	List<TreeModel> roleTreeList = new ArrayList<TreeModel>();
    	try {
			roleTreeList = userRoleService.getRoleTreeModelList();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return roleTreeList;
    }
    
    /**
     * @功能描述：根据角色ID获取用户信息
     * 			访问路径：manage/userRole/getUsersByRole/roleId={roleId}
     * @创  建  者：zhoujincheng
     * @版        本：V1.0.0
     * @创建日期：2017年10月21日 下午1:56:25
     * 
     * @修  改  人：
     * @版        本：V1.1.0
     * @修改日期：
     * @修改描述：
     * 
     * @param roleId
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getUsersByRole/roleId={roleId}", produces = "application/json;charset=utf-8")
    public GridModel getUsersByRole(@PathVariable("roleId") String roleId, HttpServletRequest request) {
        LOGGER.debug("getUsersByRole() is executed!");
        Map paramMap = new HashMap<>();
		String account = StringUtil.isEmpty(request.getParameter("account")) ? null : request.getParameter("account").trim();	//获取用户账号
		String userName = StringUtil.isEmpty(request.getParameter("userName")) ? null : request.getParameter("userName").trim();	//获取用户名称
		int pageNo = Integer.parseInt(request.getParameter("page"));
        int length = Integer.parseInt(request.getParameter("rows"));
        PageUtil pageUtil = new PageUtil((pageNo - 1) * length, length);
        paramMap.put("account", account);
        paramMap.put("userName", userName);
        paramMap.put("roleId", roleId);
        paramMap.put("begin", pageUtil.getBegin());
        paramMap.put("end", pageUtil.getEnd());
        
        GridModel gridModel = new GridModel();
        try {
			gridModel.setRows(userRoleService.getUserRoleInfo(paramMap));
			gridModel.setTotal(userRoleService.getUserRoleCount(paramMap));
		} catch (Exception e) {
			e.printStackTrace();
		}
        return gridModel;
    }
    
    
}
