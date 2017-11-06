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
import com.zzrenfeng.base.utils.PageUtil;
import com.zzrenfeng.base.utils.StringUtil;
import com.zzrenfeng.zznueg.entity.AcademyInfo;
/**
 * @功能描述：学院信息Controller
 * @创  建  者：zhoujincheng
 * @版        本：V1.0.0
 * @创建日期：2017年7月29日 上午11:44:19
 * 
 * @修  改  人：
 * @修改日期：
 * @修改描述：
 *
 */
import com.zzrenfeng.zznueg.service.AcademyInfoService;
/**
 * @功能描述：学院管理Controller
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
@RequestMapping(value = "/zznueg/manage/acainfo/")
public class AcademyInfoController extends BaseController {
	private final Logger LOGGER = LoggerFactory.getLogger(AcademyInfoController.class);
	
	@Autowired
	private AcademyInfoService academyInfoService;
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
	@RequestMapping("acaMain")
    public String acaMain() {
        LOGGER.debug("acaMain() is executed!");
        return "zznueg/manage/acadep/acaMain";
    }
	/**
	 * @功能描述：编辑页面跳转
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年7月29日 下午3:45:47
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @return
	 */
	@RequestMapping("acaEditDlg")
    public String acaEditDlg() {
        LOGGER.debug("acaEditDlg() is executed!");
        return "zznueg/manage/acadep/acaEdit";
    }
	/**
	 * @功能描述：分页查询
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年7月29日 下午3:44:19
	 * 
	 * @修  改  人：zhoujincheng
	 * @版        本：V1.1.0
	 * @修改日期：20171026 10:29
	 * @修改描述：为了方法通用性，这里将academyInfoService.findAllAcaInfoByPage(paramMap)的传入参数
	 * 			由PageUtil pageUtil更改为Map paramMap；这样更有利方法传参的扩展；可以和多条件检索功能合并；
	 * 			增加模糊检索参数：acaName-学院名称；acaLeader-学院负责人；
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
    @RequestMapping(value = "findAllAcaInfoByPage", produces = "application/json;charset=utf-8")
	public GridModel findAllAcaInfoByPage(HttpServletRequest request) {
		Map paramMap = new HashMap<>();
		String acaName = StringUtil.isEmpty(request.getParameter("acaName")) ? null : request.getParameter("acaName").trim();
		String acaLeader = StringUtil.isEmpty(request.getParameter("acaLeader")) ? null : request.getParameter("acaLeader").trim();
		int pageNo = Integer.parseInt(request.getParameter("page"));
		int length = Integer.parseInt(request.getParameter("rows"));
		PageUtil pageUtil = new PageUtil((pageNo -1) * length, length);
		paramMap.put("acaName", acaName);
        paramMap.put("acaLeader", acaLeader);
        paramMap.put("begin", pageUtil.getBegin());
        paramMap.put("end", pageUtil.getEnd());
		
		GridModel gridModel = new GridModel();
		try {
			gridModel.setRows(academyInfoService.findAllAcaInfoByPage(paramMap));
			gridModel.setTotal(academyInfoService.getCount(paramMap));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return gridModel;
	}
	/**
	 * @功能描述：新增或修改学院信息处理
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年7月29日 下午3:49:13
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param academyInfo
	 * @return
	 */
	@ResponseBody
    @RequestMapping(value = "saveOrUpdateAcademy", produces = "application/json;charset=utf-8")
    public String saveOrUpdateAcademy(AcademyInfo academyInfo) {
    	LOGGER.debug("saveOrUpdateAcademy() is executed!");
        Json json = null;
		try {
			json = getMessage(academyInfoService.persistenceAcademyInfo(academyInfo));
		} catch (Exception e) {
			e.printStackTrace();
		}
        return JSONArray.toJSONString(json);
    }
	
	/**
	 * @功能描述：根据ID删除学院信息（硬删除）
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年7月29日 下午4:10:45
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
    @RequestMapping(value = "delAcademy", produces = "application/json;charset=utf-8")
    public String delAcademy(HttpServletRequest request) {
        String acaId = request.getParameter("acaId");

        Json json = new Json();
        boolean flag;
		try {
			flag = academyInfoService.delAcaInfoById(acaId);

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
	 * @功能描述：获取所有学院信息
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年7月29日 下午5:51:42
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @return
	 */
	@ResponseBody
    @RequestMapping(value = "getAllAcaInfo", produces = "application/json;charset=utf-8")
    public List<AcademyInfo> getAllAcaInfo() {
		List<AcademyInfo> allAcaInfo = new ArrayList<AcademyInfo>();
		try {
			allAcaInfo = academyInfoService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return allAcaInfo;
    }

}
