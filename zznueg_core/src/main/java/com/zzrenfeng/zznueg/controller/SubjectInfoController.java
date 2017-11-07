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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.zzrenfeng.base.controller.BaseController;
import com.zzrenfeng.base.model.GridModel;
import com.zzrenfeng.base.model.Json;
import com.zzrenfeng.base.utils.Constants;
import com.zzrenfeng.base.utils.LogUtils;
import com.zzrenfeng.base.utils.PageUtil;
import com.zzrenfeng.base.utils.StringUtil;
import com.zzrenfeng.zznueg.entity.SubjectInfo;
import com.zzrenfeng.zznueg.service.SubjectInfoService;
/**
 * @功能描述：科目信息Controller
 * @创  建  者： zhoujincheng
 * @版        本：V1.0.0
 * @创建日期：2017年7月24日 上午10:57:02
 *
 * @修  改  人：
 * @修改日期：
 * @修改描述：
 */
@Controller
@RequestMapping(value = "/zznueg/manage/subjectinfo/")
public class SubjectInfoController extends BaseController {
	
	private final Logger LOGGER = LoggerFactory.getLogger(SubjectInfoController.class);

	@Autowired
	private SubjectInfoService subjectInfoService;
	
	/**
	 * @功能描述：科目管理主页面跳转
	 * @创  建  者： zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年7月21日 下午4:54:51
	 *
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * @return
	 */
    @RequestMapping("subjectMain")
    public String main() {
        LOGGER.debug("SubjectInfoController.main() is executed!");
        return "zznueg/manage/subject/subjectMain";
    }
    
    /**
     * @功能描述：科目编辑页面跳转
     * @创  建  者： zhoujincheng
     * @版        本：V1.0.0
     * @创建日期：2017年7月21日 下午4:55:15
     *
     * @修  改  人：
     * @修改日期：
     * @修改描述：
     * @return
     */
    @RequestMapping("subEditDlg")
    public String subEditDlg() {
        LOGGER.debug("SubjectInfoController.subEditDlg() is executed!");
        return "zznueg/manage/subject/subjectEdit";
    }
    
    /**
     * @功能描述：分页查询所有科目信息
     * @创  建  者： zhoujincheng
     * @版        本：V1.0.0
     * @创建日期：2017年7月20日 下午4:55:42
     *
     * @修  改  人：zhoujincheng
     * @版        本：V1.1.0
     * @修改日期：20171025 10:35
     * @修改描述：为了方法通用性，将模糊检索功能合并到该方法中；为了增加检索参数的可扩展性，
     * 			这里将方法subjectInfoService.findAllSubjectInfoByPage(Map paramMap)的传入参数由PageUtil pageUtil修改为Map paramMap
     * 			增加模糊检索参数：subjectName-科目名称；subjectCategory-科目类型；isValid-有效标志
     * @param request
     * @return
     * 
     * ********************************
     * getStackTrace堆栈信息如下：
     * 0--java.lang.Thread.getStackTrace(?) (line:1559)
     * 1--com.zzrenfeng.base.utils.LogUtils.getCurrExeMethodName(?) (line:179)
     * 2--com.zzrenfeng.base.utils.LogUtils.getCurrExeMethodBriefInfo(?) (line:217)
     * 3--com.zzrenfeng.zznueg.controller.SubjectInfoController.findAllSubInfoByPage(?) (line:100)
     * ********************************
     */
    @ResponseBody
    @RequestMapping(value = "findAllSubInfoByPage", produces = "application/json;charset=utf-8")
    public GridModel findAllSubInfoByPage(HttpServletRequest request) {
//      LOGGER.debug("findAllSubInfoByPage() is executed!");
    	LOGGER.debug(LogUtils.getCurrExeMethodBriefInfo());
    	Map paramMap = new HashMap<>();
    	String subjectName = StringUtil.isEmpty(request.getParameter("subjectName")) ? null : request.getParameter("subjectName").trim();
    	String subjectCategory = StringUtil.isEmpty(request.getParameter("subjectCategory")) ? null : request.getParameter("subjectCategory").trim();
    	String isValid = StringUtil.isEmpty(request.getParameter("isValid")) ? null : request.getParameter("isValid").trim();
    	int pageNo = Integer.parseInt(request.getParameter("page"));
        int length = Integer.parseInt(request.getParameter("rows"));
        PageUtil pageUtil = new PageUtil((pageNo - 1) * length, length);
        paramMap.put("subjectName", subjectName);
        paramMap.put("subjectCategory", subjectCategory);
        paramMap.put("isValid", isValid);
        paramMap.put("begin", pageUtil.getBegin());
        paramMap.put("end", pageUtil.getEnd());
        
        GridModel gridModel = new GridModel();
        try {
			gridModel.setRows(subjectInfoService.findAllSubjectInfoByPage(paramMap));
			gridModel.setTotal(subjectInfoService.getCount(paramMap));
		} catch (Exception e) {
			e.printStackTrace();
		}
        return gridModel;
    }
    
    /**
     * @功能描述：新增或修改科目信息处理
     * @创  建  者： zhoujincheng
     * @版        本：V1.0.0
     * @创建日期：2017年7月21日 下午5:00:19
     *
     * @修  改  人：
     * @修改日期：
     * @修改描述：
     * @param subjectInfo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "saveOrUpdateSubject", produces = "application/json;charset=utf-8")
    public String saveOrUpdateSubject(SubjectInfo subjectInfo) {
    	LOGGER.debug("saveOrUpdateSubject() is executed!");
        Json json = null;
		try {
			json = getMessage(subjectInfoService.persistenceSubjectInfo(subjectInfo));
		} catch (Exception e) {
			e.printStackTrace();
		}
        return JSONArray.toJSONString(json);
    }
    
    /**
     * @功能描述：根据科目ID删除科目
     * @创  建  者： zhoujincheng
     * @版        本：V1.0.0
     * @创建日期：2017年7月27日 上午10:09:49
     *
     * @修  改  人：
     * @修改日期：
     * @修改描述：
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "delSubject", produces = "application/json;charset=utf-8")
    public String delSubject(HttpServletRequest request) {
        String subjectId = request.getParameter("subjectId");

        Json json = new Json();
        boolean flag;
		try {
			flag = subjectInfoService.delSubjectInfoById(subjectId);
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
     * @功能描述：获取所有科目信息
     * 			访问路径：zznueg/manage/subjectinfo/findAllSubjects
     * @创  建  者：zhoujincheng
     * @版        本：V1.0.0
     * @创建日期：2017年8月9日 下午3:31:04
     * 
     * @修  改  人：
     * @修改日期：
     * @修改描述：
     * 
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "findAllSubjects", produces = "application/json;charset=utf-8")
    public List<SubjectInfo> findAllSubjects() {
    	List<SubjectInfo> allSubjectsList = new ArrayList<SubjectInfo>();
    	try {
    		allSubjectsList = subjectInfoService.findAllSubjectInfo();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return allSubjectsList;
    }
    
}
