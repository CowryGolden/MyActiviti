package com.zzrenfeng.zznueg.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zzrenfeng.base.controller.BaseController;
import com.zzrenfeng.base.utils.LogUtils;
import com.zzrenfeng.zznueg.entity.AcaDepInfo;
import com.zzrenfeng.zznueg.service.AcaDepInfoService;
/**
 * @功能描述：院系信息Controller
 * @创  建  者： zhoujincheng
 * @版        本：V1.0.0
 * @创建日期：2017年7月28日 下午2:59:13
 *
 * @修  改  人：zhoujincheng
 * @修改日期：20171023 15:45
 * @修改描述： 废弃，暂不使用！！！！
 */

@Controller
@RequestMapping(value = "/zznueg/manage/acadepinfo/")
public class AcaDepInfoController extends BaseController {

	private final Logger LOGGER = LoggerFactory.getLogger(AcaDepInfoController.class);
	
	@Autowired
	private AcaDepInfoService acaDepInfoService;
	
	/**
	 * @功能描述：获取所有院系信息
	 * @创建者：zhoujincheng
	 * @版本： V1.0.0
	 * @创建日期：2017年7月28日 下午4:48:39
	 * *************************
	 * @修改人：
	 * @修改日期：
	 * @修改描述：
	 * *************************
	 * @return 
	 */
	@ResponseBody
	@RequestMapping(value = "getAllAcaDep", produces = "application/json;charset=utf-8")
	public List<AcaDepInfo> getAllAcaDep() {
		List<AcaDepInfo> acaDepInfoList = new ArrayList<AcaDepInfo>();
		try {
			acaDepInfoList = acaDepInfoService.getAllAcaDep();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return acaDepInfoList;
	}
	
	/**
	 * @功能描述：根据父ID获取其一级子项
	 * @创  建  者： zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年7月28日 下午4:00:37
	 *
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * @param prntId
	 * @return
	 * 访问路径：zznueg/manage/acadepinfo/getAcaDepByPid?pmsnId={pmsnId}
	 */
	@ResponseBody
	@RequestMapping(value = "getAcaDepByPid?pmsnId={pmsnId}", produces = "application/json;charset=utf-8")
	public List<AcaDepInfo> getAcaDepByPid(@PathVariable("pmsnId") String prntId) {
		List<AcaDepInfo> acaDepInfoList = new ArrayList<AcaDepInfo>();
		try {
			acaDepInfoList = acaDepInfoService.getAcaDepByPid(prntId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return acaDepInfoList;
	}
	
	/**
	 * @功能描述：根据学院code获取其一级子项（系别）
	 * @创  建  者： zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年7月28日 下午4:21:21
	 *
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * @param pmsnCode
	 * @return
	 * 访问路径：zznueg/manage/acadepinfo/getChildAcaDepByCode?pmsnCode={pmsnCode}
	 */
	@ResponseBody
	@RequestMapping(value = "getChildAcaDepByCode?pmsnCode={pmsnCode}", produces = "application/json;charset=utf-8")
	public List<AcaDepInfo> getChildAcaDepByCode(@PathVariable("pmsnCode") String pmsnCode) {
		LOGGER.debug(LogUtils.getCurrExeMethodBriefInfo());
		List<AcaDepInfo> acaDepInfoList = new ArrayList<AcaDepInfo>();
		try {
			acaDepInfoList = acaDepInfoService.getChildAcaDepByCode(pmsnCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return acaDepInfoList;
	}

}
