package com.zzrenfeng.base.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zzrenfeng.base.service.SystemSettinigService;
import com.zzrenfeng.base.utils.DateUtil;
import com.alibaba.fastjson.JSONArray;
import com.zzrenfeng.base.entity.SysSetting;
import com.zzrenfeng.base.enums.DictionaryEnum;
import com.zzrenfeng.base.model.Json;
import com.zzrenfeng.base.utils.SystemBuffer;

/**
 * @功能描述：系统环境变量设置Controller
 * @创  建  者：zhoujincheng
 * @版        本：V1.0.0
 * @创建日期：2017年9月26日 上午10:22:23
 * 
 * @修  改  人：
 * @修改日期：
 * @修改描述：
 *
 */
@Controller
@RequestMapping(value = "/manage/system/")
public class SystemSettingController extends BaseController {
	
	private final Logger LOGGER = LoggerFactory.getLogger(SystemSettingController.class);
	
	@Autowired
	private SystemSettinigService systemSettinigService;
	
	/**
	 * @功能描述：系统环境变量设置主页面跳转界面
     * 			访问路径：manage/system/sysSettingsMain
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月26日 上午10:26:33
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @return
	 */
	@RequestMapping("sysSettingsMain")
    public String sysSettingsMain() {
        LOGGER.debug("sysSettingsMain() is executed!");
        return "manage/system/sysSettings";
    }
	
	/**
	 * @功能描述：保存或修改系统环境变量
	 * 			访问路径：manage/system/saveOrUpdateSysSetting
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月26日 下午4:14:47
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "saveOrUpdateSysSetting", produces = "application/json;charset=utf-8")
	public String saveOrUpdateSysSetting(HttpServletRequest request) {
		String startDateStr = request.getParameter("startDate").trim();
		String endDateStr = request.getParameter("endDate").trim();
		//String formatstr = "yyyy-MM-dd HH:mm:ss";
		//Date startDate = DateUtil.StrToDate(startDateStr, formatstr);
		//Date endDate = DateUtil.StrToDate(endDateStr, formatstr);
		
		String startDateItemKey = SystemBuffer.getDictionaryByTypeKey(DictionaryEnum.EXAMTERM_STARTDATE.getType(),
				DictionaryEnum.EXAMTERM_STARTDATE.getKey()).getDictionaryId();
		String endDateItemKey = SystemBuffer.getDictionaryByTypeKey(DictionaryEnum.EXAMTERM_ENDDATE.getType(),
				DictionaryEnum.EXAMTERM_ENDDATE.getKey()).getDictionaryId();
		
		SysSetting s1 = new SysSetting();
		s1.setItemKey(startDateItemKey);
		s1.setItemValue(startDateStr);
		SysSetting s2 = new SysSetting();
		s2.setItemKey(endDateItemKey);
		s2.setItemValue(endDateStr);
		
		Json json = new Json();
		try {
			boolean f1 = this.systemSettinigService.persistenceSysSetting(s1);
			boolean f2 = this.systemSettinigService.persistenceSysSetting(s2);
			json = this.getMessage(f1 && f2);
		} catch (Exception e) {
			e.printStackTrace();
		}		

		return JSONArray.toJSONString(json);
	}
	
	
	

}


