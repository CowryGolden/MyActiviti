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
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.zzrenfeng.base.controller.BaseController;
import com.zzrenfeng.base.model.GridModel;
import com.zzrenfeng.base.model.Json;
import com.zzrenfeng.base.utils.Constants;
import com.zzrenfeng.base.utils.PageUtil;
import com.zzrenfeng.base.utils.StringUtil;
import com.zzrenfeng.zznueg.service.LeaderPlatformService;

/**
 * @功能描述：领导平台Controller
 * @创  建  者：zhoujincheng
 * @版        本：V1.0.0
 * @创建日期：2017年8月30日 下午4:13:32
 * 
 * @修  改  人：
 * @修改日期：
 * @修改描述：
 *
 */
@Controller
@RequestMapping(value = "/zznueg/portal/leaderplatform/")
public class LeaderPlatformController extends BaseController {
	
	private final Logger LOGGER = LoggerFactory.getLogger(LeaderPlatformController.class);
	
	@Autowired
	private LeaderPlatformService leaderPlatformService;
	
	/**
	 * @功能描述：领导平台中学生概览跳转页面
	 * 			访问路径：zznueg/portal/leaderplatform/leaderStuOverview
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月30日 下午4:28:39
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @return
	 */
	@RequestMapping("leaderStuOverview")
	public String leaderStuOverview() {
		return "zznueg/portal/leader/leaderStuOverview";
	}
	
	/**
	 * @功能描述：领导平台——数据统计主页面跳转页面
	 * 			访问路径：zznueg/portal/leaderplatform/dataStatisticsMain
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月1日 上午9:58:11
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @return
	 */
	@RequestMapping("dataStatisticsMain")
	public String dataStatisticsMain() {
		return "zznueg/portal/leader/leaderDataStatMain";
	}
	
	/**
	 * @功能描述：领导平台——数据统计——平均成绩对比，跳转页面
	 * 			访问路径：zznueg/portal/leaderplatform/dataStatAvgScoreComp
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月1日 上午10:18:59
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @return
	 */
	@RequestMapping("dataStatAvgScoreComp")
	public String dataStatAvgScoreCompare() {
		return "zznueg/portal/leader/leaderDataStatAvgScoreComp";
	}
	
	/**
	 * @功能描述：领导平台——数据统计——四次考试平均成绩，跳转页面
	 * 			访问路径：zznueg/portal/leaderplatform/dataStatFourCntAvgScoreComp
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月1日 下午5:20:12
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @return
	 */
	@RequestMapping("dataStatFourCntAvgScoreComp")
	public String dataStatFourCntAvgScoreCompare() {
		return "zznueg/portal/leader/leaderDataStatFourCntAvgScoreComp";
	}
	
	/**
	 * @功能描述：领导平台——数据统计——综合分数最高TOP10，跳转页面
	 * 			访问路径：zznueg/portal/leaderplatform/dataStatCompreScoreTop10
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月1日 下午5:32:57
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @return
	 */
	@RequestMapping("dataStatCompreScoreTop10")
	public String dataStatCompreScoreTop10() {
		return "zznueg/portal/leader/leaderDataStatCompreScoreTop10";
	}
	
	/**
	 * @功能描述：领导平台——数据统计——不及格人数对比，跳转页面
	 * 			访问路径：zznueg/portal/leaderplatform/dataStatFailStuCountCompare
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月4日 上午9:52:15
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @return
	 */
	@RequestMapping("dataStatFailStuCountCompare")
	public String dataStatFailStuCountCompare() {
		return "zznueg/portal/leader/leaderDataStatFailStuCountComp";
	}
	
	/**
	 * @功能描述：领导平台——数据统计——不同学院综合平均分对比图，跳转页面
	 * 			访问路径：zznueg/portal/leaderplatform/dataStatCompreAvgScoreComp
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月4日 下午3:24:17
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @return
	 */
	@RequestMapping("dataStatCompreAvgScoreComp")
	public String dataStatCompreAvgScoreCompare() {
		return "zznueg/portal/leader/leaderDataStatCompreAvgScoreComp";
	}
	
	/**
	 * @功能描述：领导平台——数据统计——不同学院成绩通过率，主页面跳转页面
	 * 			访问路径：zznueg/portal/leaderplatform/dataStatAcaPassRateMain
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月4日 下午6:05:36
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @return
	 */
	@RequestMapping("dataStatAcaPassRateMain")
	public String dataStatAcaPassRateMain() {
		return "zznueg/portal/leader/leaderDataStatAcaPassRateMain";
	}
	
	/**
	 * @功能描述：领导平台——数据统计——不同学院综合考核次数对比，主页面跳转页面
	 * 			访问路径：zznueg/portal/leaderplatform/dataStatAcaExamCountComp
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月6日 下午3:19:25
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @return
	 */
	@RequestMapping("dataStatAcaExamCountComp")
	public String dataStatAcaExamCountCompare() {
		return "zznueg/portal/leader/leaderDataStatAcaExamCntComp";
	}
	
	
	/**
	 * @功能描述：获取学生概览数据
	 * 			访问路径：zznueg/portal/leaderplatform/getStudentOverview
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月30日 下午4:49:53
	 * 
	 * @修  改  人：zhoujincheng
	 * @版        本：V1.1.0
	 * @修改日期：20171018 14:10
	 * @修改描述：增加模糊搜索参数：stuName-学生姓名；实现一个输入框可以根据学生学号或姓名进行模糊检索；
	 * 			说明：由于前端学生学号或姓名检索框为同一个输入框，这里将同一个拆分成两个，内容相同；
	 * 				虽然同一个值分拆成两个，但实际只能为一个值；后端判断为空时判断一个即可，使用or连接两者进行检索
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
    @RequestMapping(value = "getStudentOverview", produces = "application/json;charset=utf-8")
    public GridModel getStudentOverviewByPage(HttpServletRequest request) {
		String stuNo = request.getParameter("stuNo");	//获取学生学号
		String stuName = request.getParameter("stuName");	//获取学生姓名
		int pageNo = Integer.parseInt(request.getParameter("page"));
        int length = Integer.parseInt(request.getParameter("rows")); 
        Map paramMap = new HashMap<>();
        PageUtil pageUtil = new PageUtil((pageNo - 1) * length, length);
        paramMap.put("stuNo", stuNo);
        paramMap.put("stuName", stuName);
        paramMap.put("begin", pageUtil.getBegin());
        paramMap.put("end", pageUtil.getEnd());
		
        GridModel gridModel = new GridModel();
        try {
			gridModel.setRows(this.leaderPlatformService.getAllStusInfoAndSubScoresByPage(paramMap));
			gridModel.setTotal(this.leaderPlatformService.getTotalStusCount(paramMap));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return gridModel;
	}
	
	/**
	 * @功能描述：领导平台——数据统计——平均成绩对比——各科平均与单科最高成绩对比图，获取ECharts需要的json字符串数据
	 * 			访问路径：zznueg/portal/leaderplatform/getAvgSubScoreCompare
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月1日 上午10:32:34
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getAvgSubScoreCompare")
	public String getAvgSubScoreCompare4SEC(HttpServletRequest request) {
		Map paramMap = new HashMap<>();
		Map dataMap = new HashMap<>();
		String jsonStr = "";
		
		try {
			dataMap = this.leaderPlatformService.getAvgSubScoreCompare4SEC(paramMap);
			if(dataMap != null && !dataMap.isEmpty()) {
				jsonStr = JSON.toJSONString(dataMap);
			} else {
				LOGGER.debug("获取数据为空！");
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonStr;		
	}
	
	/**
	 * @功能描述：领导平台——数据统计——平均成绩对比——本次与上一次考试学生各科平均成绩对比图，获取ECharts需要的json字符串数据
	 * 			访问路径：zznueg/portal/leaderplatform/getCurrAndPrevAvgSubScoreCompare
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月1日 下午3:35:32
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getCurrAndPrevAvgSubScoreCompare")
	public String getCurrAndPrevAvgSubScoreCompare4SEC(HttpServletRequest request) {
		Map paramMap = new HashMap<>();
		Map dataMap = new HashMap<>();
		String jsonStr = "";
		
		try {
			dataMap = this.leaderPlatformService.getCurrAndPrevAvgSubScoreCompare4SEC(paramMap);
			if(dataMap != null && !dataMap.isEmpty()) {
				jsonStr = JSON.toJSONString(dataMap);
			} else {
				LOGGER.debug("获取数据为空！");
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonStr;		
	}
	
	/**
	 * @功能描述：领导平台——数据统计——平均成绩对比——四次考试平均成绩，获取ECharts需要的json字符串数据
	 * 			访问路径：zznueg/portal/leaderplatform/getFourSubsAvgScoreByCnt
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月1日 下午5:08:24
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getFourSubsAvgScoreByCnt")
	public String getFourSubsAvgScoreByCnt4SEC(HttpServletRequest request) {
		Map paramMap = new HashMap<>();
		Map dataMap = new HashMap<>();
		String jsonStr = "";
		
		try {
			dataMap = this.leaderPlatformService.getFourSubsAvgScoreByCnt4SEC(paramMap);
			if(dataMap != null && !dataMap.isEmpty()) {
				jsonStr = JSON.toJSONString(dataMap);
			} else {
				LOGGER.debug("获取数据为空！");
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonStr;		
	}
	
	/**
	 * @功能描述：领导平台——数据统计——平均成绩对比——综合分数最高TOP10，获取ECharts需要的json字符串数据
	 * 			访问路径：zznueg/portal/leaderplatform/getCompreScoreTop10
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月1日 下午5:35:03
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getCompreScoreTop10")
	public String getCompreScoreTop104SEC(HttpServletRequest request) {
		Map paramMap = new HashMap<>();
		Map dataMap = new HashMap<>();
		String jsonStr = "";
		
		try {
			dataMap = this.leaderPlatformService.getCompreScoreTop104SEC(paramMap);
			if(dataMap != null && !dataMap.isEmpty()) {
				jsonStr = JSON.toJSONString(dataMap);
			} else {
				LOGGER.debug("获取数据为空！");
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonStr;		
	}
	
	/**
	 * @功能描述：领导平台——数据统计——不及格人数对比对比图，获取ECharts需要的json字符串数据
	 * 			访问路径：zznueg/portal/leaderplatform/getCurrAndPrevFialStuCountComp
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月1日 下午3:35:32
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getCurrAndPrevFialStuCountComp")
	public String getCurrAndPrevFialStuCountCompare4SEC(HttpServletRequest request) {
		Map paramMap = new HashMap<>();
		Map dataMap = new HashMap<>();
		String jsonStr = "";
		
		try {
			dataMap = this.leaderPlatformService.getCurrAndPrevFialStuCountCompare4SEC(paramMap);
			if(dataMap != null && !dataMap.isEmpty()) {
				jsonStr = JSON.toJSONString(dataMap);
			} else {
				LOGGER.debug("获取数据为空！");
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonStr;		
	}
	
	/**
	 * @功能描述：领导平台——数据统计——不同学院综合平均分对比对比图，获取ECharts需要的json字符串数据
	 * 			访问路径：zznueg/portal/leaderplatform/getCompreAvgScoreGroupbyAcaId4SEC
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月4日 下午4:26:31
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getCompreAvgScoreGroupbyAcaId4SEC")
	public String getCompreAvgScoreGroupbyAcaId4SEC(HttpServletRequest request) {
		String acaIds = request.getParameter("acaIds").trim();  //选择查询的学院ID
		Map paramMap = new HashMap<>();
		Map dataMap = new HashMap<>();
		String jsonStr = "";
		
		if(!StringUtil.isEmpty(acaIds)) {
			String[] acaIdsArr = acaIds.split(",");
			paramMap.put("acaIds", acaIdsArr);	
		}
		try {
			dataMap = this.leaderPlatformService.getCompreAvgScoreGroupbyAcaId4SEC(paramMap);
			if(dataMap != null && !dataMap.isEmpty()) {
				jsonStr = JSON.toJSONString(dataMap);
			} else {
				LOGGER.debug("获取数据为空！");
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonStr;		
	}
	
	/**
	 * @功能描述：领导平台——数据统计——不同学院综合平均分对比对比图，获取数据统计表所需的数据
	 * 			访问路径：zznueg/portal/leaderplatform/getCompreAvgScoreGroupbyAcaId4DST
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月4日 下午4:48:12
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getCompreAvgScoreGroupbyAcaId4DST")
	public ModelAndView getCompreAvgScoreGroupbyAcaId4DST(HttpServletRequest request) {
		String acaIds = request.getParameter("acaIds").trim();  //选择查询的学院ID
		ModelAndView mv = new ModelAndView();
		Map paramMap = new HashMap<>();
		List rtnList = new ArrayList<>();
		
		if(!StringUtil.isEmpty(acaIds)) {
			String[] acaIdsArr = acaIds.split(",");
			paramMap.put("acaIds", acaIdsArr);	
		}
		try {
			rtnList = this.leaderPlatformService.getCompreAvgScoreGroupbyAcaId(paramMap);
			if(rtnList != null && rtnList.size() > 0) {		
				mv.addObject("rtnList", rtnList);
			} else {
				LOGGER.debug(Constants.MSG_GET_NULL_DATA);
				mv.addObject("msg",Constants.MSG_GET_NULL_DATA);   				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		mv.setViewName("zznueg/portal/leader/leaderDataStatCompreAvgScoreDtl");
		return mv;
	}
	
	/**
	 * @功能描述：领导平台——数据统计——不同学院成绩通过率——学院成绩通过率排名，获取数据统计表所需的数据
	 * 			访问路径：zznueg/portal/leaderplatform/getAcaPassRateSumGroupbyAcaId
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月4日 下午6:02:03
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getAcaPassRateSumGroupbyAcaId")
	public ModelAndView getAcaPassRateSumGroupbyAcaId4DST(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		Map paramMap = new HashMap<>();
		List rtnList = new ArrayList<>();
		
		try {
			rtnList = this.leaderPlatformService.getPassRateGroupbyAcaId(paramMap);
			if(rtnList != null && rtnList.size() > 0) {		
				mv.addObject("rtnList", rtnList);
			} else {
				LOGGER.debug(Constants.MSG_GET_NULL_DATA);
				mv.addObject("msg",Constants.MSG_GET_NULL_DATA);   				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		mv.setViewName("zznueg/portal/leader/leaderDataStatAcaPassRateSum");
		return mv;
	}
	
	/**
	 * @功能描述：获取学院信息列表，用于前端下拉框显示（暂废弃，不使用）
	 * 			访问路径：zznueg/portal/leaderplatform/getAcaInfo4Selection
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月5日 上午9:50:20
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getAcaInfo4Selection", produces = "application/json;charset=utf-8")
	public List<Map> getAcaInfo4Selection() {
		List<Map> rtnList = new ArrayList<>();		
		try {
			//这里从统计结果中获取学院ID和学院名称，如果统计结果为空就不显示下拉列表
			rtnList = this.leaderPlatformService.getPassRateGroupbyAcaId(null); 
			if(null == rtnList && rtnList.isEmpty()) {
				LOGGER.debug(Constants.MSG_GET_NULL_DATA);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return rtnList;
	}
	
	/**
	 * @功能描述：领导平台——数据统计——不同学院成绩通过率——各学院综合及各科目通过率饼形图，获取Echarts所需的数据
	 * 			访问路径：zznueg/portal/leaderplatform/getAcaSubPassRatePie
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月5日 上午11:41:02
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getAcaSubPassRatePie", produces = "application/json;charset=utf-8")
	public String getAcaSubPassRatePie4SEC(HttpServletRequest request) {
		String acaId = request.getParameter("acaId").trim();  //选择查询的学院ID
		String subId = request.getParameter("subId").trim();  //选择查询的科目ID
		Map dataMap = new HashMap<>();
		Map paramMap = new HashMap<>();
		String jsonStr = "";
//System.out.println("%%%%%%%%%%%%%%%%%%%%%====>>>>acaId=" + acaId + ", subId=" +  subId);		
		if(StringUtil.isEmpty(acaId)) {
			LOGGER.debug("传递参数为空！");
			Json json = new Json();
			json.setStatus(false);
			json.setTitle("警告");
			json.setMessage("传递参数为空！");
			jsonStr = JSON.toJSONString(json);
		} else {
			subId = StringUtil.isEmpty(subId) ? acaId : subId;  //科目ID为空时，将其和学院ID置为相同
			paramMap.put("acaId", acaId);
			paramMap.put("subId", subId);			
			try {
				dataMap = this.leaderPlatformService.getPassRateGroupbyAcaId4SEC(paramMap);
				if(dataMap != null && !dataMap.isEmpty()) {
					jsonStr = JSON.toJSONString(dataMap);
				} else {
					LOGGER.debug("获取数据为空！");
				}	
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
		
		return jsonStr;		
	}
	
	/**
	 * @功能描述：领导平台——数据统计——不同学院成绩通过率——各学院综合及各科目通过率数据统计表，获取数据统计表所需的数据
	 * 			访问路径：zznueg/portal/leaderplatform/getAcaSubPassRateDataTable
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月6日 上午10:42:09
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getAcaSubPassRateDataTable")
	public ModelAndView getAcaSubPassRateDataTable4DST(HttpServletRequest request) {
		String acaId = request.getParameter("acaId").trim();  //选择查询的学院ID
		ModelAndView mv = new ModelAndView();
		Map paramMap = new HashMap<>();
		Map passRateMap = new HashMap<>();
		
		try {
			if(StringUtil.isEmpty(acaId)) {
				LOGGER.debug("传递参数为空！");
				mv.addObject("msg", "传递参数为空！"); 
			} else {
				paramMap.put("acaId", acaId);
				passRateMap = this.leaderPlatformService.getPassRateGroupbyAcaId4DST(paramMap);
				if(null != passRateMap && !passRateMap.isEmpty()) {
					mv.addObject("passRate", passRateMap);
				} else {
					LOGGER.debug(Constants.MSG_GET_NULL_DATA);
					mv.addObject("msg",Constants.MSG_GET_NULL_DATA); 
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		mv.setViewName("zznueg/portal/leader/leaderDataStatAcaPassRateSub");
		return mv;
	}
	
	/**
	 * @功能描述：领导平台——数据统计——不同学院综合考核次数对比——数据统计表，获取数据统计表所需的数据
	 * 			访问路径：zznueg/portal/leaderplatform/getAcaExamCountDataTable
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月6日 下午3:12:31
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getAcaExamCountDataTable")
	public ModelAndView getAcaExamCountDataTable4DST(HttpServletRequest request) {
		String acaIds = request.getParameter("acaIds").trim();  //选择查询的学院ID
		ModelAndView mv = new ModelAndView();
		Map paramMap = new HashMap<>();
		List examCountList = new ArrayList<>();
//System.out.println("%%%%%%%%%%%%%%%%%%%%%====>>>>acaIds=" + acaIds);
		if(!StringUtil.isEmpty(acaIds)) {
			String[] acaIdsArr = acaIds.split(",");
			paramMap.put("acaIds", acaIdsArr);	
		}		
		try {
			examCountList = this.leaderPlatformService.getAcaExamCount4DST(paramMap);
			if(null != examCountList && !examCountList.isEmpty()) {
				mv.addObject("rtnList", examCountList);
			} else {
				LOGGER.debug(Constants.MSG_GET_NULL_DATA);
				mv.addObject("msg",Constants.MSG_GET_NULL_DATA); 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		mv.setViewName("zznueg/portal/leader/leaderDataStatAcaExamCntDtl");
		return mv;
	}
	
	/**
	 * @功能描述：领导平台——数据统计——不同学院综合考核次数对比——统计柱状图，获取ECharts所需的数据
	 * 			访问路径：zznueg/portal/leaderplatform/getAcaExamCountBar
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月6日 下午4:16:42
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getAcaExamCountBar", produces = "application/json;charset=utf-8")
	public String getAcaExamCountBar4SEC(HttpServletRequest request) {
		String acaIds = request.getParameter("acaIds").trim();  //选择查询的学院ID
		Map dataMap = new HashMap<>();
		Map paramMap = new HashMap<>();
		String jsonStr = "";
//System.out.println("%%%%%%%%%%%%%%%%%%%%%====>>>>acaIds=" + acaIds);
		if(!StringUtil.isEmpty(acaIds)) {
			String[] acaIdsArr = acaIds.split(",");
			paramMap.put("acaIds", acaIdsArr);	
		}			
		try {
			dataMap = this.leaderPlatformService.getAcaExamCount4SEC(paramMap);
			if(dataMap != null && !dataMap.isEmpty()) {
				jsonStr = JSON.toJSONString(dataMap);
			} else {
				LOGGER.debug("获取数据为空！");
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return jsonStr;		
	}
	
	/**
	 * @功能描述：领导平台——数据统计——不同学院综合考核次数对比——学院选择复选框，获取学院信息的数据
	 * 			访问路径：zznueg/portal/leaderplatform/getAcaCheckboxData4ExamCnt
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月6日 下午4:48:21
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getAcaCheckboxData4ExamCnt")
	public ModelAndView getAcaCheckboxData4ExamCnt(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		Map paramMap = new HashMap<>();
		List<Map> acaInfoList = new ArrayList<>();		
		try {
			acaInfoList = this.leaderPlatformService.getAcaExamCountByAidSid(paramMap);
			if(null != acaInfoList && !acaInfoList.isEmpty()) {
				mv.addObject("rtnList", acaInfoList);
			} else {
				LOGGER.debug(Constants.MSG_GET_NULL_DATA);
				mv.addObject("msg",Constants.MSG_GET_NULL_DATA); 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
		mv.setViewName("zznueg/portal/leader/leaderDataStatAcaExamCntAcaInfo");
		return mv;
	}
	
	/**
	 * @功能描述：领导平台——数据统计——不同学院综合平均分对比图——学院选择复选框，获取学院信息的数据
	 * 			访问路径：zznueg/portal/leaderplatform/getAcaCheckboxData4ComAvg
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月7日 上午10:53:20
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getAcaCheckboxData4ComAvg")
	public ModelAndView getAcaCheckboxData4ComAvg(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		Map paramMap = new HashMap<>();
		List<Map> acaInfoList = new ArrayList<>();		
		try {
			acaInfoList = this.leaderPlatformService.getCompreAvgScoreGroupbyAcaId(paramMap);
			if(null != acaInfoList && !acaInfoList.isEmpty()) {
				mv.addObject("rtnList", acaInfoList);
			} else {
				LOGGER.debug(Constants.MSG_GET_NULL_DATA);
				mv.addObject("msg",Constants.MSG_GET_NULL_DATA); 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
		mv.setViewName("zznueg/portal/leader/leaderDataStatCompreAvgScoreAcaInfo");
		return mv;
	}
	
	
	
	
	
	
	
	
	/**
	 * @功能描述：测试传递参数使用
	 * 			访问路径：zznueg/portal/leaderplatform/testParam
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月6日 下午6:11:01
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "testParam", produces = "application/json;charset=utf-8")
	public String testParam(HttpServletRequest request) {
		String jsonStr = "";
		String acaIds = request.getParameter("acaIds").trim();		
//System.out.println("%%%%%%%%%%%%%%%%%%%%%====>>>>acaIds=" + acaIds);	
		if(!StringUtil.isEmpty(acaIds)) {
			String[] acaIdsArr = acaIds.split(",");		
		} 		
		return jsonStr;
	}
	
	 

}



