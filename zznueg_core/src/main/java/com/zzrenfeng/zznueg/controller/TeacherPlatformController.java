package com.zzrenfeng.zznueg.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.zzrenfeng.base.controller.BaseController;
import com.zzrenfeng.base.enums.DictionaryEnum;
import com.zzrenfeng.base.model.GridModel;
import com.zzrenfeng.base.model.Json;
import com.zzrenfeng.base.utils.Constants;
import com.zzrenfeng.base.utils.DateUtil;
import com.zzrenfeng.base.utils.PageUtil;
import com.zzrenfeng.base.utils.StringUtil;
import com.zzrenfeng.base.utils.SystemBuffer;
import com.zzrenfeng.zznueg.service.TeacherPlatformService;
import com.zzrenfeng.zznueg.utils.CommonConstants;

/**
 * @功能描述：教师平台部分Controller
 * @创  建  者：zhoujincheng
 * @版        本：V1.0.0
 * @创建日期：2017年8月17日 上午10:23:28
 * 
 * @修  改  人：
 * @修改日期：
 * @修改描述：
 *
 */
@Controller
@RequestMapping(value = "/zznueg/portal/teacherplatform/")
public class TeacherPlatformController extends BaseController {
	private final Logger LOGGER = LoggerFactory.getLogger(TeacherPlatformController.class);
	
	@Autowired
	private TeacherPlatformService teacherPlatformService;
	
	/**
	 * @功能描述：学生评估跳转页面（此方法被studentDetail4Teach取代，暂不使用）
	 * 			访问路径：zznueg/portal/teacherplatform/studentEvaluationMain
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月17日 上午10:28:43
	 * 
	 * @修  改  人：zjc
	 * @修改日期：20170822 10:02
	 * @修改描述：为了和学生概览页面点击详情功能通用，因此重新改造了该方法,具体参见studentDetail4Teach()方法
	 * 
	 * @return
	 */
	@RequestMapping("studentEvaluationMain")
	public String studentEvaluationMain() {
		return "zznueg/portal/teacher/studentEvaluation";
	}
	
	/**
	 * @功能描述：教师平台中学生概览页面中点击详情跳转到评估页面并定位学生（此方法取代studentEvaluationMain方法）
	 * 			访问路径：zznueg/portal/teacherplatform/studentDetail
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月22日 上午9:41:13
	 * 
	 * @修  改  人：zjc
	 * @版        本：V1.0.1
	 * @修改日期：20170928 10:29
	 * @修改描述：增加参数pageFlag=fromStuOverview，以此来控制“返回”按钮页面定位
	 * 
	 * @修  改  人：zjc
	 * @版        本：V1.0.2
	 * @修改日期：20170928 14:36
	 * @修改描述：增加评估起止日期两个参数，放于session域，并将结束日期与系统当前日期比较的结果也放于session中；当前日期大返回"1"，否则返回"0"
	 * 
	 * @修  改  人：zjc
	 * @版        本：V1.1.0
	 * @修改日期：20170928 15:25
	 * @修改描述：删除V1.0.2中的设置，将参数设置放于系统登录成功后的统一初始化方法里，不用在各个系统中单独初始化；
	 * 			详情参见：com.zzrenfeng.base.controller.MgrJumpController.main() line:87
	 *  
	 * @param request
	 * @return
	 */
	@RequestMapping("studentDetail")
	public ModelAndView studentDetail4Teach(HttpServletRequest request) {		
		ModelAndView mv = new ModelAndView();
		String stuUserId = request.getParameter("stuUserId").trim();
		String pageFlag = request.getParameter("pageFlag").trim();
		if(StringUtil.isEmpty(stuUserId)) {
			stuUserId = "";
		}
		if(StringUtil.isEmpty(pageFlag)) {
			pageFlag = "";
		}
//System.out.println("###########stuUserId====>>>>" + stuUserId);		
		mv.addObject("stuUserId", stuUserId);
		mv.addObject("pageFlag", pageFlag);		
				
		mv.setViewName("zznueg/portal/teacher/studentEvaluation");
		return mv;
	}
	
	/**
	 * @功能描述：学生评估中视频类文件播放跳转页面
	 * 			访问路径：zznueg/portal/teacherplatform/videoPlay
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月17日 上午10:49:26
	 * 
	 * @修  改  人：zjc
	 * @修改日期：20170831 17:04
	 * @修改描述：该方法不能接收参数最好使用下面videoPlay4Preview方法
	 * 
	 * @return
	 */
	@RequestMapping("videoPlay")
	public String videoPlay() {
		return "zznueg/portal/teacher/videoPlay";
	}
	
	/**
	 * @功能描述：预览视频跳转处理
	 * 			访问路径：zznueg/portal/teacherplatform/videoPlay4Preview
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月31日 下午3:25:51
	 * 
	 * @修  改  人：zhoujincheng
	 * @版        本：V1.0.1
	 * @修改日期：20171010 15:49
	 * @修改描述：将上传路径-uploadPath修改为在线预览路径-previewPath
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "videoPlay4Preview", produces = "application/json;charset=utf-8")
	public ModelAndView videoPlay4Preview(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		Map paramMap = new HashMap<>();
		String uploadPid = request.getParameter("uploadPid").trim();
//		String uploadPath = request.getParameter("uploadPath").trim();
		String previewPath = request.getParameter("previewPath").trim();
		String evalScore = request.getParameter("evalScore").trim();
		String stuUserId = request.getParameter("stuUserId").trim();
		String pageFlag = request.getParameter("pageFlag").trim();
		
		if(StringUtil.isEmpty(uploadPid)) {
			uploadPid = "";
		}
		if(StringUtil.isEmpty(previewPath)) {
			previewPath = "";
		}
		if(StringUtil.isEmpty(evalScore)) {
			evalScore = "";
		}
		if(StringUtil.isEmpty(pageFlag)) {
			pageFlag = "";
		}
		
//		mv.addObject("uploadPid", uploadPid);
//		mv.addObject("uploadPath", uploadPath);
//		mv.addObject("evalScore", evalScore);
		
		paramMap.put("uploadPid", uploadPid);
		paramMap.put("previewPath", previewPath);
		paramMap.put("evalScore", evalScore);
		paramMap.put("stuUserId", stuUserId);
		paramMap.put("pageFlag", pageFlag);
		
		mv.addObject("param", paramMap);		
		mv.setViewName("zznueg/portal/teacher/videoPlay");
		return mv;
	}
	
	/**
	 * @功能描述：学生评估中PDF视图查看器跳转页面
	 * 			访问路径：zznueg/portal/teacherplatform/pdfViewer
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月17日 下午2:28:01
	 * 
	 * @修  改  人：zjc
	 * @修改日期：20170831 18:31
	 * @修改描述：该方法不能接收参数最好使用下面pdfViewer4Preview方法
	 * 
	 * @return
	 */
	@RequestMapping("pdfViewer")
	public String pdfViewer() {
		return "zznueg/portal/teacher/pdfViewer";
	}
	
	//访问路径：zznueg/portal/teacherplatform/getFileTest
	@RequestMapping(value = "getFileTest", method = {RequestMethod.GET}, produces = "application/json;charset=utf-8")
	public String getFileTest(HttpServletRequest request) {
		String contextPath = request.getContextPath();  //获取上下文跟
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + contextPath + "/";
		return "redirect: " + basePath + "upload/2017/08/17/c6938aa78a73def82a3987de2b8db322_课件二_20170817.pdf";
	}
	
	/**
	 * @功能描述：预览PDF视图查看器跳转页面
	 * 			访问路径：zznueg/portal/teacherplatform/pdfViewer4Preview
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月31日 下午6:47:56
	 * 
	 * @修  改  人：zhoujincheng
	 * @版        本：V1.0.1
	 * @修改日期：20171010 15:44
	 * @修改描述：将上传路径-uploadPath修改为在线预览路径-previewPath
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "pdfViewer4Preview", produces = "application/json;charset=utf-8")
	public ModelAndView pdfViewer4Preview(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		Map paramMap = new HashMap<>();
		String uploadPid = request.getParameter("uploadPid").trim();
//		String uploadPath = request.getParameter("uploadPath").trim();
		String previewPath = request.getParameter("previewPath").trim();
		String evalScore = request.getParameter("evalScore").trim();
		String stuUserId = request.getParameter("stuUserId").trim();
		String pageFlag = request.getParameter("pageFlag").trim();
		
		if(StringUtil.isEmpty(uploadPid)) {
			uploadPid = "";
		}
		if(StringUtil.isEmpty(previewPath)) {
			previewPath = "";
		}
		if(StringUtil.isEmpty(evalScore)) {
			evalScore = "";
		}
		if(StringUtil.isEmpty(pageFlag)) {
			pageFlag = "";
		}
				
		paramMap.put("uploadPid", uploadPid);
		paramMap.put("previewPath", previewPath);
		paramMap.put("evalScore", evalScore);
		paramMap.put("stuUserId", stuUserId);
		paramMap.put("pageFlag", pageFlag);
		
		mv.addObject("param", paramMap);		
		mv.setViewName("zznueg/portal/teacher/pdfViewer");
		return mv;
	}
	
	
	
	/**
	 * @功能描述：教师平台——学生概览跳转页面
	 * 			访问路径：zznueg/portal/teacherplatform/studentOverview
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月21日 下午4:25:32
	 * 
	 * @修  改  人：zjc
	 * @版        本：V1.0.1
	 * @修改日期：20170928 09:36
	 * @修改描述：增加评估起止日期两个参数，放于session域，并将结束日期与系统当前日期比较的结果也放于session中；当前日期大返回"1"，否则返回"0"
	 * 
	 * @修  改  人：zjc
	 * @版        本：V1.0.2
	 * @修改日期：20170928 10:59
	 * @修改描述：增加页面传递参数stuUserId，pageFlag(=fromStudentEvaluation)，以此来控制页面记录定位和页面来源
	 * 
	 * @修  改  人：zjc
	 * @版        本：V1.1.0
	 * @修改日期：20170928 15:26
	 * @修改描述：删除V1.0.1中的设置，将参数设置放于系统登录成功后的统一初始化方法里，不用在各个系统中单独初始化；
	 * 			详情参见：com.zzrenfeng.base.controller.MgrJumpController.main() line:87
	 * 
	 * @return
	 */
	@RequestMapping("studentOverview")
	public String studentOverview(HttpServletRequest request) {
		String stuUserId = StringUtil.isEmpty(request.getParameter("stuUserId")) ? "" : request.getParameter("stuUserId").trim();
		String pageFlag = StringUtil.isEmpty(request.getParameter("pageFlag")) ? "" : request.getParameter("pageFlag").trim();
		//将参数放于request域
		request.setAttribute("stuUserId", stuUserId);
		request.setAttribute("pageFlag", pageFlag);		
		
		return "zznueg/portal/teacher/stuOverview";
	}
	
	/**
	 * @功能描述：教师平台——数据统计主页面跳转页面
	 * 			访问路径：zznueg/portal/teacherplatform/dataStatisticsMain
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月28日 下午3:36:03
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @return
	 */
	@RequestMapping("dataStatisticsMain")
	public String dataStatisticsMain() {
		return "zznueg/portal/teacher/teachDataStatisticsMain";
	}
	
	/**
	 * @功能描述：教师平台——数据统计——不同等级人数占比，跳转页面
	 * 			访问路径：zznueg/portal/teacherplatform/dataStatDiffGradesRate
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月28日 下午4:02:55
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @return
	 */
	@RequestMapping("dataStatDiffGradesRate")
	public String dataStatDiffGradesRate() {
		return "zznueg/portal/teacher/teachDataStatDifferentGrades";
	}
	
	/**
	 * @功能描述：教师平台——数据统计——我指导的学生成绩占比图（柱状图），跳转页面
	 * 			访问路径：zznueg/portal/teacherplatform/dataStatSubScoresRatio
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月28日 下午4:02:55
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @return
	 */
	@RequestMapping("dataStatSubScoresRatio")
	public String dataStatSubScoresRatio() {
		return "zznueg/portal/teacher/teachDataStatSubScoresRatio";
	}
	
	/**
	 * @功能描述：教师平台——在线测评模板主页，跳转页面
	 * 			访问路径：zznueg/portal/teacherplatform/teachOnlEvalTemplMain
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月8日 上午10:31:28
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @return
	 */
	@RequestMapping("teachOnlEvalTemplMain")
	public String teachOnlineEvaluationTemplateMain() {
		return "zznueg/portal/teacher/teachOnlEvalTemplMain";
	}
	
	/**
	 * @功能描述：教师平台——在线测评题库 主页面，跳转页面
	 * 			访问路径：zznueg/portal/teacherplatform/teachOnlineEvalQuestionLibMain
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月8日 下午3:11:17
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @return
	 */
	@RequestMapping("teachOnlineEvalQuestionLibMain")
	public String teachOnlineEvalQuestionLibMain() {
		return "zznueg/portal/teacher/teachOnlineEvalQuestionLibMain";
	}
	
	/**
	 * @功能描述：教师平台——(在线测评)分析结果主页，跳转页面
	 * 			访问路径：zznueg/portal/teacherplatform/teachOnlSurAnaResultMain
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月8日 下午4:32:28
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @return
	 */
	@RequestMapping("teachOnlSurAnaResultMain")
    public String teachOnlSurAnaResultMain() {
        return "zznueg/portal/teacher/teachOnlSurAnaResultMain";
    }
	
	/**
     * @功能描述：教师平台——(在线测评)分析结果——雷达图，跳转页面
 	 * 		          访问路径：zznueg/portal/teacherplatform/teachOnlSurAnaResultRadar
     * @创  建  者：zhoujincheng
     * @版        本：V1.0.0
     * @创建日期：2017年9月8日 下午2:27:24
     */
    @RequestMapping("teachOnlSurAnaResultRadar")
    public String teachOnlSurAnaResultRadar() {
        return "zznueg/portal/teacher/teachOnlSurAnaResultRadar";
    }
    /**
     * @功能描述：教师平台——(在线测评)分析结果——柱状图，跳转页面
 	 * 		          访问路径：zznueg/portal/teacherplatform/teachOnlSurAnaResultBar
     * @创  建  者：zhoujincheng
     * @版        本：V1.0.0
     * @创建日期：2017年9月8日 下午2:27:24
     */
    @RequestMapping("teachOnlSurAnaResultBar")
    public String teachOnlSurAnaResultBar() {
        return "zznueg/portal/teacher/teachOnlSurAnaResultBar";
    }
    /**
     * @功能描述：教师平台——(在线测评)分析结果——折线图，跳转页面
 	 * 		          访问路径：zznueg/portal/teacherplatform/teachOnlSurAnaResultLine
     * @创  建  者：zhoujincheng
     * @版        本：V1.0.0
     * @创建日期：2017年9月8日 下午2:27:24
     */
    @RequestMapping("teachOnlSurAnaResultLine")
    public String teachOnlSurAnaResultLine() {
        return "zznueg/portal/teacher/teachOnlSurAnaResultLine";
    }
    /**
     * @功能描述：教师平台——(在线测评)分析结果——条形图，跳转页面
 	 * 		          访问路径：zznueg/portal/teacherplatform/teachOnlSurAnaResultStockBar
     * @创  建  者：zhoujincheng
     * @版        本：V1.0.0
     * @创建日期：2017年9月8日 下午2:27:24
     */
    @RequestMapping("teachOnlSurAnaResultStockBar")
    public String teachOnlSurAnaResultStockBar() {
        return "zznueg/portal/teacher/teachOnlSurAnaResultStockBar";
    }
		
		
	
	/**
	 * @功能描述：教师查询所教授班级对应的学生列表
	 * 			访问路径：zznueg/portal/teacherplatform/getStuListByTcid4Teach
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月18日 上午10:51:12
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
    @RequestMapping(value = "getStuListByTcid4Teach", produces = "application/json;charset=utf-8")
	public List<Map> getStuListByTcid4Teach(HttpServletRequest request) {
		String teachUserId = Constants.getCurrendUser().getUserId();  //获取当前登录用户实体类的用户ID
		Map paramMap = new HashMap<>();
		paramMap.put("teachUserId", teachUserId);
		List<Map> stuList = new ArrayList<>();
		
		try {
			stuList = this.teacherPlatformService.getStuListByTcid4Teach(paramMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return stuList;
	}

	/**
	 * @功能描述：教师根据学生列表的信息点击“上一位”或“下一位”根据学生ID查询学生最近上传的资源列表
	 * 			访问路径：zznueg/portal/teacherplatform/getUploadListBySuid4Teach
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月18日 下午3:12:54
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getUploadListBySuid4Teach")
	public ModelAndView getUploadListBySuid4Teach(HttpServletRequest request) {
		String stuUserId = request.getParameter("stuUserId");
		ModelAndView mv = new ModelAndView();
		Map paramMap = new HashMap<>();
		List rtnList = new ArrayList<>();
		paramMap.put("stuUserId", stuUserId);
		
		try {
			rtnList = this.teacherPlatformService.getUploadListBySuid4Teach(paramMap);
			if(rtnList != null && rtnList.size() > 0) {
//System.out.println("###########====>>>>" + rtnList.toString());				
				mv.addObject("rtnList", rtnList);	
				//mv.addObject("msg",Constants.MSG_GET_DATA_SUCESS);
			} else {
				LOGGER.debug(Constants.MSG_GET_NULL_DATA);
				mv.addObject("msg",Constants.MSG_GET_NULL_DATA);   				
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage() + Constants.EXCEPTION_MSG_GET_DATA);
			e.printStackTrace();
		}
		mv.setViewName("zznueg/portal/teacher/upInfoDetail");
		return mv;
	}
	
	/**
	 * @功能描述：保存教师评分
	 * 			访问路径：zznueg/portal/teacherplatform/saveEvalScore
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月21日 上午11:13:52
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "saveEvalScore", produces = "application/json;charset=utf-8")
	public String persistenceEvalScore(HttpServletRequest request) {
		Json json = new Json();
		String pid = request.getParameter("pid").trim();
		String evalScore = request.getParameter("evalScore").trim();
//System.out.println("##################====>>>>pid=" + pid + " evalScore=" + evalScore);
		Map paramMap = new HashMap<>();
		paramMap.put("pid", pid);
		paramMap.put("evalScore", evalScore);
		
		try {
			if(StringUtil.isEmpty(pid)) {
				 json.setStatus(false);
		         json.setMessage("评分主键pid为空！");
			}
			json = getMessage(this.teacherPlatformService.persistenceEvalScore(paramMap));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return JSONArray.toJSONString(json);
	}
	
	/**
	 * @功能描述：分页获取学生概览信息
	 * 			访问路径：zznueg/portal/teacherplatform/getStudentOverview
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月21日 下午5:22:15
	 * 
	 * @修  改  人：zhoujincheng
	 * @版        本：V1.1.0
	 * @修改日期：20171018 15:02
	 * @修改描述：增加模糊搜索参数：stuNo-学生学号，stuName-学生姓名；实现一个输入框可以根据学生学号或姓名进行模糊检索；
	 * 			说明：由于前端学生学号或姓名检索框为同一个输入框，这里将同一个拆分成两个，内容相同；
	 * 				虽然同一个值分拆成两个，但实际只能为一个值；后端判断为空时判断一个即可，使用or连接两者进行检索
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
    @RequestMapping(value = "getStudentOverview", produces = "application/json;charset=utf-8")
    public GridModel getStudentOverviewByPage(HttpServletRequest request) {
		String teachUserId = Constants.getCurrendUser().getUserId();  //获取当前登录用户实体类的用户ID
		String stuNo = request.getParameter("stuNo");	//获取学生学号
		String stuName = request.getParameter("stuName");	//获取学生姓名
		int pageNo = Integer.parseInt(request.getParameter("page"));
        int length = Integer.parseInt(request.getParameter("rows"));  
        Map paramMap = new HashMap<>();
        PageUtil pageUtil = new PageUtil((pageNo - 1) * length, length);
        paramMap.put("teachUserId", teachUserId);
        paramMap.put("stuNo", stuNo);
        paramMap.put("stuName", stuName);
        paramMap.put("begin", pageUtil.getBegin());
        paramMap.put("end", pageUtil.getEnd());
        GridModel gridModel = new GridModel();
        try {
			gridModel.setRows(this.teacherPlatformService.getStuOverviewByTuid4Teach(paramMap));
			gridModel.setTotal(this.teacherPlatformService.getStuCountByTuid(paramMap));
		} catch (Exception e) {
			e.printStackTrace();
		}
        return gridModel;
	}
	
	/**
	 * @功能描述：教师平台——数据统计——不同等级人数占比，获取数据统计表格(DST)所需的数据
	 * 			访问路径：zznueg/portal/teacherplatform/getDiffGradesPassRate4DST
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月28日 下午4:40:28
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
    @RequestMapping(value = "getDiffGradesPassRate4DST", produces = "application/json;charset=utf-8")
    public String getDifferentGradesPassRate4DST(HttpServletRequest request) {
		String teachUserId = Constants.getCurrendUser().getUserId();
		Map paramMap = new HashMap<>();
		Map mapData = new HashMap<>();
		paramMap.put("teachUserId", teachUserId);
		String jsonData = "";
		try {
			mapData = this.teacherPlatformService.getPassingRat4DST(paramMap);
			if(null != mapData && !mapData.isEmpty()) {
				jsonData = JSON.toJSONString(mapData);
			} else {
				LOGGER.debug("获取数据为空！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return jsonData;
	}
	
	/**
	 * @功能描述：教师平台——数据统计——不同等级人数占比（饼形图），获取统计图表(SEC)不同等级饼形图所需的json数据
	 * 			访问路径：zznueg/portal/teacherplatform/getDifferentGrades
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月29日 下午2:41:21
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getDifferentGrades")
	public String getDifferentGrades4SEC(HttpServletRequest request) {
		String teachUserId = Constants.getCurrendUser().getUserId();
		Map paramMap = new HashMap<>();
		Map mapData = new HashMap<>();
		paramMap.put("teachUserId", teachUserId);
		String jsonData = "";
		
		try {
			mapData = this.teacherPlatformService.getDifferentGrades4SEC(paramMap);
			if(mapData != null && !mapData.isEmpty()) {
				jsonData = JSON.toJSONString(mapData);
			} else {
				LOGGER.debug("获取数据为空！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonData;
	}
	
	/**
	 * @功能描述：教师平台——数据统计——我指导的学生成绩占比图（柱状图）——我指导的学生本次和上次平均成绩对比，获取统计图表(SEC)本次和上次平均成绩柱状图所需的json数据
	 * 			访问路径：zznueg/portal/teacherplatform/getMyStusSubScoresRatio
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月29日 下午6:41:11
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getMyStusSubScoresRatio")
	public String getMyStusSubScoresRatio4SEC(HttpServletRequest request) {
		String teachUserId = Constants.getCurrendUser().getUserId();
		Map paramMap = new HashMap<>();
		Map mapData = new HashMap<>();
		paramMap.put("teachUserId", teachUserId);
		String jsonData = "";
		
		try {
			mapData = this.teacherPlatformService.getMyStusSubScoresRatio4SEC(paramMap);
			if(mapData != null && !mapData.isEmpty()) {
				jsonData = JSON.toJSONString(mapData);
			} else {
				LOGGER.debug("获取数据为空！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonData;
	}
	
	/**
	 * @功能描述：教师平台——数据统计——我指导的学生成绩占比图（柱状图）——我指导的学生各科平均成绩和全校各科平均成绩对比，获取统计图表(SEC)平均成绩柱状图所需的json数据
	 * 			访问路径：zznueg/portal/teacherplatform/getMyAndAllAvgSubScoreCompare
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月30日 上午11:01:28
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getMyAndAllAvgSubScoreCompare")
	public String getMyAvgAndAllAvgSubScoreCompare4SEC(HttpServletRequest request) {
		String teachUserId = Constants.getCurrendUser().getUserId();
		Map paramMap = new HashMap<>();
		Map mapData = new HashMap<>();
		paramMap.put("teachUserId", teachUserId);
		String jsonData = "";
		
		try {
			mapData = this.teacherPlatformService.getMyAvgAndAllAvgSubScore4SEC(paramMap);
			if(mapData != null && !mapData.isEmpty()) {
				jsonData = JSON.toJSONString(mapData);
			} else {
				LOGGER.debug("获取数据为空！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonData;
	}
	
	
}
