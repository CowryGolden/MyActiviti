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
import com.zzrenfeng.base.model.GridModel;
import com.zzrenfeng.base.model.Json;
import com.zzrenfeng.base.model.TreeModel;
import com.zzrenfeng.base.utils.Constants;
import com.zzrenfeng.base.utils.LogUtils;
import com.zzrenfeng.base.utils.PageUtil;
import com.zzrenfeng.base.utils.StringUtil;
import com.zzrenfeng.zznueg.entity.ClassInfo;
import com.zzrenfeng.zznueg.service.ClassInfoService;
/**
 * @功能描述：班级信息Controller
 * @创  建  者：zhoujincheng
 * @版        本：V1.0.0
 * @创建日期：2017年8月1日 上午10:52:56
 * 
 * @修  改  人：
 * @修改日期：
 * @修改描述：
 *
 */
@Controller
@RequestMapping(value = "/zznueg/manage/classinfo/")
public class ClassInfoController extends BaseController {

	private final Logger LOGGER = LoggerFactory.getLogger(ClassInfoController.class);
	
	@Autowired
	private ClassInfoService classInfoService;
	
	/**
	 * @功能描述：班级信息主页跳转页面
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月1日 上午11:07:45
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @return
	 */
	@RequestMapping(value = "classMain")
	public String classMain() {
		LOGGER.debug("classMain() is executed!");
		return "zznueg/manage/class/classMain";
	}
	
	/**
	 * @功能描述：获取学院-系部信息两级树型结构List
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月1日 上午11:05:22
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getAcaDeptTreeList", produces = "application/json;charset=utf-8")
	public List<TreeModel> getAcaDeptTreeList() {
		List<TreeModel> acaDepTreeList = new ArrayList<TreeModel>();
		try {
			acaDepTreeList = classInfoService.getAcaDeptTreeList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return acaDepTreeList;
	}
	
	/**
	 * @功能描述：根据系部ID获取所属的所有班级信息
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月1日 下午3:29:16
	 * 
	 * @修  改  人：zhoujincheng
	 * @版        本：V1.1.0
	 * @修改日期：20171027 11:07
	 * @修改描述：增加方法getClassInfoByDeptId()传递参数：HttpServletRequest request，目的是为了获取前端传递的更多参数；
	 * 			将方法classInfoService.getClassInfoByDeptId()的传递参数由String deptId修改为Map paramMap，
	 * 			这样更有利方法传参的扩展；可以和多条件检索功能合并；
	 * 
	 * @param deptId -> paramMap
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getClassInfoByDeptId/deptId={deptId}", produces = "application/json;charset=utf-8")
	public List<ClassInfo> getClassInfoByDeptId(@PathVariable("deptId") String deptId, HttpServletRequest request) {
		List<ClassInfo> classInfoList = new ArrayList<ClassInfo>();
		String className = StringUtil.isEmpty(request.getParameter("className")) ? null : request.getParameter("className").trim();
		String teacherName = StringUtil.isEmpty(request.getParameter("teacherName")) ? null : request.getParameter("teacherName").trim();
		Map paramMap = new HashMap<>();
		paramMap.put("deptId", deptId);
		paramMap.put("className", className);
		paramMap.put("teacherName", teacherName);
		try {
			classInfoList = classInfoService.getClassInfoByDeptId(paramMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return classInfoList;
	}
	
	/**
	 * @功能描述：分页查询班级信息，可以根据非空条件进行筛选；可以代替上述getClassInfoByDeptId()方法使用
	 * 			访问路径：zznueg/manage/classinfo/getClassInfoByPage
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年10月30日 下午2:24:04
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
	@RequestMapping(value = "getClassInfoByPage", produces = "application/json;charset=utf-8")
	public GridModel getClassInfoByPage(HttpServletRequest request) {
		LOGGER.debug(LogUtils.getCurrExeMethodBriefInfo());
		String deptId = StringUtil.isEmpty(request.getParameter("deptId")) ? null : request.getParameter("deptId").trim();
		String className = StringUtil.isEmpty(request.getParameter("className")) ? null : request.getParameter("className").trim();
		String teacherName = StringUtil.isEmpty(request.getParameter("teacherName")) ? null : request.getParameter("teacherName").trim();
		Map paramMap = new HashMap<>();
		paramMap.put("deptId", deptId);
		paramMap.put("className", className);
		paramMap.put("teacherName", teacherName);
		int pageNo = Integer.parseInt(request.getParameter("page"));
        int length = Integer.parseInt(request.getParameter("rows"));
        PageUtil pageUtil = new PageUtil((pageNo - 1) * length, length);
        paramMap.put("begin", pageUtil.getBegin());
        paramMap.put("end", pageUtil.getEnd());
        
        GridModel gridModel = new GridModel();
        try {
			gridModel.setRows(classInfoService.getClassInfoByPage(paramMap));
			gridModel.setTotal(classInfoService.getClassInfoCount(paramMap));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return gridModel;
	}
	
	
	/**
	 * @功能描述：新增或修改班级信息处理
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月1日 下午5:33:26
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param classInfo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "saveOrUpdateClassInfo", produces = "application/json;charset=utf-8")
	public String saveOrUpdateClassInfo(ClassInfo classInfo) {
		Json json = null;
		try {
			json = this.getMessage(classInfoService.persistenceClassInfo(classInfo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONArray.toJSONString(json);
	}
	
	/**
	 * @功能描述：跳转至编辑页面
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月1日 下午6:24:03
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @return
	 */
	@RequestMapping(value = "classEditDlg", method = RequestMethod.GET)
	public ModelAndView classEditDlg() {
		LOGGER.debug("classEditDlg() is executed!"); 
		ModelAndView mv = new ModelAndView();
		mv.setViewName("zznueg/manage/class/classEdit");
		return mv;
	}
	
	/**
	 * @功能描述：根据班级ID删除班级信息（硬删除）
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月1日 下午6:50:37
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
    @RequestMapping(value = "delClassById", produces = "application/json;charset=utf-8")
    public String delClassById(HttpServletRequest request) {
		LOGGER.debug("根据班级ID删除班级信息!"); 
		String classId = request.getParameter("classId");
		Json json = new Json();
		boolean flag;
		try {
			flag = classInfoService.delClassInfoById(classId);
			
			if (flag) {
	            json.setStatus(true);
	            json.setMessage(Constants.POST_DATA_SUCCESS);
	        } else {
	        	json.setStatus(false);
	            json.setMessage(Constants.POST_DATA_FAIL);
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return JSONArray.toJSONString(json);
	}
	
	/**
	 * @功能描述：班级-学生视图主页跳转页面
	 * 			访问路径：zznueg/manage/classinfo/classStuMain
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年10月23日 上午10:24:07
	 * 
	 * @修  改  人：
	 * @版        本：V1.1.0
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @return
	 */
	@RequestMapping(value = "classStuMain")
	public String classStuMain() {
		LOGGER.debug("classStuMain() is executed!");
		return "zznueg/manage/classstu/classStuMain";
	}
	
	/**
	 * @功能描述：获取学院-系部-班级信息三级树型结构List
	 * 			访问路径：zznueg/manage/classinfo/getAcaDeptClassTreeList
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年10月23日 下午1:47:00
	 * 
	 * @修  改  人：
	 * @版        本：V1.1.0
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getAcaDeptClassTreeList", produces = "application/json;charset=utf-8")
	public List<TreeModel> getAcaDeptClassTreeList() {
		List<TreeModel> acaDeptClassTreeList = new ArrayList<TreeModel>();
		try {
			acaDeptClassTreeList = classInfoService.getAcaDeptClassTreeList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return acaDeptClassTreeList;
	}
	
	
	
	
}
