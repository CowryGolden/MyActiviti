package com.zzrenfeng.zznueg.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.zzrenfeng.base.controller.BaseController;
import com.zzrenfeng.base.model.Json;
import com.zzrenfeng.base.utils.StringUtil;
import com.zzrenfeng.zznueg.entity.DepartmentInfo;
import com.zzrenfeng.zznueg.service.DepartmentInfoService;
/**
 * @功能描述：系部管理Controller
 * @创  建  者：zhoujincheng
 * @版        本：V1.0.0
 * @创建日期：2017年7月29日 下午3:43:26
 * 
 * @修  改  人：
 * @修改日期：
 * @修改描述：
 *
 */
@Controller
@RequestMapping(value = "/zznueg/manage/deptinfo/")
public class DepartmentInfoController extends BaseController {
	private final Logger LOGGER = LoggerFactory.getLogger(DepartmentInfoController.class);
	
	@Autowired
	private DepartmentInfoService departmentInfoService;
	
	/**
	 * @功能描述：主页跳转
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年7月29日 下午3:44:03
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @return
	 */
	@RequestMapping("deptMain")
    public String deptMain() {
        LOGGER.debug("deptMain() is executed!");
        return "zznueg/manage/acadep/deptMain";
    }
	/**
	 * @功能描述：编辑页面跳转
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年7月29日 下午3:45:47
	 * 
	 * @修  改  人：zjc
	 * @修改日期：2017年7月31日 16:11
	 * @修改描述：根据前端点击的不同学院，显示该学院下面的系部，再编辑相应的系部信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "deptEditDlg/acaId={acaId}", method = RequestMethod.GET)
    public ModelAndView deptEditDlg(@PathVariable("acaId") String acaId, HttpServletRequest request) {
        LOGGER.debug("deptEditDlg() is executed!");
        
        request.setAttribute("acaId", acaId);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("zznueg/manage/acadep/deptEdit");
        return mv;
    }
	/**
	 * @功能描述：根据学院ID获取该学院下的所有系部信息
	 * 			访问路径：zznueg/manage/deptinfo/findDeptByAcaId/acaId={acaId}
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年7月31日 下午4:35:49
	 * 
	 * @修  改  人：zhoujincheng
	 * @版        本：V1.1.0
	 * @修改日期：20171026 11:13
	 * @修改描述：增加方法findDeptByAcaId()传递参数：HttpServletRequest request，目的是为了获取前端传递的更多参数；
	 * 			为了方法通用性，这里将departmentInfoService.getDeptInfoByAcaId(paramMap)的传入参数
	 * 			由String acaId更改为Map paramMap；这样更有利方法传参的扩展；可以和多条件检索功能合并；
	 * 			增加模糊检索参数：detpName-系部名称；deptLeader-系部负责人；
	 * 
	 * @param acaId
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "findDeptByAcaId/acaId={acaId}", produces="application/json;charset=utf-8")
	public List<DepartmentInfo> findDeptByAcaId(@PathVariable("acaId") String acaId, HttpServletRequest request) {
		Map paramMap = new HashMap<>();
		String deptName = StringUtil.isEmpty(request.getParameter("deptName")) ? null : request.getParameter("deptName").trim();
		String deptLeader = StringUtil.isEmpty(request.getParameter("deptLeader")) ? null : request.getParameter("deptLeader").trim();
		paramMap.put("acaId", acaId);
		paramMap.put("deptName", deptName);
		paramMap.put("deptLeader", deptLeader);
		
		List<DepartmentInfo> deptList = new ArrayList<DepartmentInfo>();
		try {
			deptList = this.departmentInfoService.getDeptInfoByAcaId(paramMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return deptList;
	}
	
	@ResponseBody
	@RequestMapping(value = "saveOrUpdateDeptInfo", produces = "application/json;charset=utf-8")
	public String saveOrUpdateDeptInfo(DepartmentInfo departmentInfo) {
		Json json = null;
		try {
			json = this.getMessage(this.departmentInfoService.persistenceDepartmentInfo(departmentInfo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONArray.toJSONString(json);
	}
	
}
