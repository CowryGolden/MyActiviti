package com.zzrenfeng.zznueg.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zzrenfeng.base.controller.BaseController;
import com.zzrenfeng.base.model.GridModel;
import com.zzrenfeng.base.model.Json;
import com.zzrenfeng.base.utils.Constants;
import com.zzrenfeng.base.utils.FileUploadUtils;
import com.zzrenfeng.base.utils.FileUtil;
import com.zzrenfeng.base.utils.PageUtil;
import com.zzrenfeng.base.utils.PropertiesUtils;
import com.zzrenfeng.base.utils.StringUtil;
import com.zzrenfeng.zznueg.entity.OnlineEvalStuentEvalRecordInfo;
import com.zzrenfeng.zznueg.entity.StuUploadInfo;
import com.zzrenfeng.zznueg.service.StudentPlatformService;
import com.zzrenfeng.zznueg.utils.CommonConstants;
/**
 * @功能描述：学生平台部分Controller
 * @创  建  者：zhoujincheng
 * @版        本：V1.0.0
 * @创建日期：2017年8月8日 下午6:06:34
 * 
 * @修  改  人：
 * @修改日期：
 * @修改描述：
 *
 */
@SessionAttributes("paperId")  //将试卷ID放于session中，以便前端获取
@Controller
@RequestMapping(value = "/zznueg/portal/studentplatform/")
public class StudentPlatformController extends BaseController {

	private final Logger LOGGER = LoggerFactory.getLogger(StudentPlatformController.class);
	
	@Autowired
	private StudentPlatformService studentPlatformService;
	
	/**
	 * @功能描述：学生科目提交主页跳转页面
	 * 			访问路径：zznueg/portal/studentplatform/subjectSubmitMain
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月8日 下午6:14:05
	 * 
	 * @修  改  人：zjc
	 * @版        本：V1.0.1
	 * @修改日期：20170927 14:09
	 * @修改描述：增加评估起止日期两个参数，放于session域，并将结束日期与系统当前日期比较的结果也放于session中；当前日期大返回"1"，否则返回"0"
	 * 
	 * @修  改  人：zjc
	 * @版        本：V1.1.0
	 * @修改日期：20170928 15:18
	 * @修改描述：删除V1.0.1中的设置，将参数设置放于系统登录成功后的统一初始化方法里，不用在各个系统中单独初始化；
	 * 			详情参见：com.zzrenfeng.base.controller.MgrJumpController.main() line:87
	 * 
	 * @return
	 */
	@RequestMapping("subjectSubmitMain")
	public String subjectSubmitMain(HttpServletRequest request) {		
		return "zznueg/portal/student/subjectSubmit";
	}
	
	/**
	 * @功能描述：科目信息修改跳转界面
	 * 			访问路径：zznueg/portal/studentplatform/subjectEditDlg
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月12日 下午4:08:38
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @return
	 */
	@RequestMapping("subjectEditDlg")
	public String subjectEditDlg() {
		return "zznueg/portal/student/subjectEdit";
	}
	
	/**
     * @功能描述：学生成绩跳转页面
	 * 			访问路径：zznueg/portal/studentplatform/studentScores
     * @创  建  者：zhoujincheng
     * @版        本：V1.0.0
     * @创建日期：2017年8月22日 下午2:05:24
     * 
	 * @修  改  人：zjc
     * @版        本：V1.0.1
	 * @修改日期：20170927 15:39
	 * @修改描述：增加评估起止日期两个参数，放于session域，并将结束日期与系统当前日期比较的结果也放于session中；当前日期大返回"1"，否则返回"0"
     * 
	 * @修  改  人：zjc
	 * @版        本：V1.1.0
	 * @修改日期：20170928 15:23
	 * @修改描述：删除V1.0.1中的设置，将参数设置放于系统登录成功后的统一初始化方法里，不用在各个系统中单独初始化；
	 * 			详情参见：com.zzrenfeng.base.controller.MgrJumpController.main() line:87
	 * 
     * @return
     */
    @RequestMapping("studentScores")
    public String studentScores() {
        return "zznueg/portal/student/stuScores";
    }
    
    /**
     * @功能描述：数据统计主页面跳转页面
	 * 			访问路径：zznueg/portal/studentplatform/dataStatMain
     * @创  建  者：zhoujincheng
     * @版        本：V1.0.0
     * @创建日期：2017年8月22日 下午2:05:24
     * 
     * @修  改  人：
     * @修改日期：
     * @修改描述：
     * 
     * @return
     */
    @RequestMapping("dataStatMain")
    public String dataStatisticsMain() {
        return "zznueg/portal/student/stuDataStatisticsMain";
    }
    
    /**
     * @功能描述：数据统计——成绩记录 跳转页面
	 * 			访问路径：zznueg/portal/studentplatform/dataStatScoreTrack
     * @创  建  者：zhoujincheng
     * @版        本：V1.0.0
     * @创建日期：2017年8月25日 下午6:07:49
     * 
     * @修  改  人：
     * @修改日期：
     * @修改描述：
     * 
     * @return
     */
    @RequestMapping("dataStatScoreTrack")
    public String dataStatScoreTrack() {
        return "zznueg/portal/student/stuDataStatScoreTrack";
    }
    
    /**
     * @功能描述：数据统计——成绩比对 跳转页面
	 * 			访问路径：zznueg/portal/studentplatform/dataStatScoreCompare
     * @创  建  者：zhoujincheng
     * @版        本：V1.0.0
     * @创建日期：2017年8月26日 下午4:12:14
     * 
     * @修  改  人：
     * @修改日期：
     * @修改描述：
     * 
     * @return
     */
    @RequestMapping("dataStatScoreCompare")
    public String dataStatScoreCompare() {
        return "zznueg/portal/student/stuDataStatScoreCompare";
    }
    
    /**
     * @功能描述：数据统计——成绩雷达图 跳转页面
	 * 			访问路径：zznueg/portal/studentplatform/dataStatScoreRadar
     * @创  建  者：zhoujincheng
     * @版        本：V1.0.0
     * @创建日期：2017年8月26日 下午4:50:40
     * 
     * @修  改  人：
     * @修改日期：
     * @修改描述：
     * 
     * @return
     */
    @RequestMapping("dataStatScoreRadar")
    public String dataStatScoreRadar() {
        return "zznueg/portal/student/stuDataStatScoreRadar";
    }
    
    /**
     * @功能描述：数据统计——TOP10学生排行图 跳转页面
	 * 			访问路径：zznueg/portal/studentplatform/dataStatStuTop10
     * @创  建  者：zhoujincheng
     * @版        本：V1.0.0
     * @创建日期：2017年8月28日 上午9:23:44
     * 
     * @修  改  人：
     * @修改日期：
     * @修改描述：
     * 
     * @return
     */
    @RequestMapping("dataStatStuTop10")
    public String dataStatStuTop10() {
        return "zznueg/portal/student/stuDataStatStuTop10";
    }
    
    /**
     * @功能描述：学生平台——在线测评主页，跳转页面
	 * 			访问路径：zznueg/portal/studentplatform/stuOnlineSurveyMain
     * @创  建  者：zhoujincheng
     * @版        本：V1.0.0
     * @创建日期：2017年9月8日 上午11:26:13
     * 
     * @修  改  人：
     * @修改日期：
     * @修改描述：
     * 
     * @return
     */
    @RequestMapping("stuOnlineSurveyMain")
    public String stuOnlineSurveyMain() {
        return "zznueg/portal/student/stuOnlineSurveyMain";
    }
    
   /**
    * @功能描述：学生平台——(在线测评)分析结果主页，跳转页面
	* 		       访问路径：zznueg/portal/studentplatform/stuOnlSurAnaResultMain
    * @创  建  者：zhoujincheng
    * @版        本：V1.0.0
    * @创建日期：2017年9月8日 下午2:27:24
    * 
    * @修  改  人：
    * @修改日期：
    * @修改描述：
    * 
    * @return
    */
    @RequestMapping("stuOnlSurAnaResultMain")
    public String stuOnlSurAnaResultMain() {
        return "zznueg/portal/student/stuOnlSurAnaResultMain";
    }
    
    /**
     * @功能描述：学生平台——(在线测评)分析结果——雷达图，跳转页面
 	 * 		          访问路径：zznueg/portal/studentplatform/stuOnlSurAnaResultRadar
     * @创  建  者：zhoujincheng
     * @版        本：V1.0.0
     * @创建日期：2017年9月8日 下午2:27:24
     */
    @RequestMapping("stuOnlSurAnaResultRadar")
    public String stuOnlSurAnaResultRadar() {
        return "zznueg/portal/student/stuOnlSurAnaResultRadar";
    }
    /**
     * @功能描述：学生平台——(在线测评)分析结果——柱状图，跳转页面
 	 * 		          访问路径：zznueg/portal/studentplatform/stuOnlSurAnaResultBar
     * @创  建  者：zhoujincheng
     * @版        本：V1.0.0
     * @创建日期：2017年9月8日 下午2:27:24
     */
    @RequestMapping("stuOnlSurAnaResultBar")
    public String stuOnlSurAnaResultBar() {
        return "zznueg/portal/student/stuOnlSurAnaResultBar";
    }
    /**
     * @功能描述：学生平台——(在线测评)分析结果——折线图，跳转页面
 	 * 		          访问路径：zznueg/portal/studentplatform/stuOnlSurAnaResultLine
     * @创  建  者：zhoujincheng
     * @版        本：V1.0.0
     * @创建日期：2017年9月8日 下午2:27:24
     */
    @RequestMapping("stuOnlSurAnaResultLine")
    public String stuOnlSurAnaResultLine() {
        return "zznueg/portal/student/stuOnlSurAnaResultLine";
    }
    /**
     * @功能描述：学生平台——(在线测评)分析结果——条形图，跳转页面
 	 * 		          访问路径：zznueg/portal/studentplatform/stuOnlSurAnaResultStackBar
     * @创  建  者：zhoujincheng
     * @版        本：V1.0.0
     * @创建日期：2017年9月8日 下午2:27:24
     */
    @RequestMapping("stuOnlSurAnaResultStackBar")
    public String stuOnlSurAnaResultStockBar() {
        return "zznueg/portal/student/stuOnlSurAnaResultStackBar";
    }
	
	/**
	 * @功能描述：文件上传，处理步骤：首先将本地文件上传到服务器，上传成功后再将上传信息保存到数据库
	 * 			访问路径：zznueg/portal/studentplatform/uploadFile
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月9日 下午5:59:53
	 * 
	 * @修  改  人：zjc
	 * @版        本：V1.0.1
	 * @修改日期：20170810 14:37
	 * @修改描述：将上传文件和保存上传信息到数据库两过程分离；分别放在两个方法里面，上传成功后再持久化信息
	 * 
	 * @修  改  人：zjc
	 * @版        本：V1.1.0
	 * @修改日期：20171010 16:13
	 * @修改描述：文件上传和修改时，分别向服务器文件系统的指定上传目录和web容器指定目录存放该文件；
	 * 			分别为uploadPath-文件上传路径，previewPath-在线预览路径；
	 * 			uploadPath-文件上传路径：用于文件持久存储和下载使用；
	 * 			previewPath-在线预览路径：用于文件在线预览；
	 * 			该版本没有解决问题：当重新部署应用或重启web容器时可能会导致在线预览文件丢失；另，若需要预览的文件过多会导致web容器所占的存储资源就越多；
	 * 
	 * @param request
	 * @param response
	 * @param file
	 * @return
	 */
	@ResponseBody
    @RequestMapping(value = "uploadFile", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public String uploadFile(HttpServletRequest request, HttpServletResponse response, @RequestParam("uploadFile") MultipartFile file) {
		String currUserId = Constants.getCurrendUser().getUserId();
		String pid = request.getParameter("pid");
		String subjectId = request.getParameter("subjectId");
		String optMethod = request.getParameter("optMethod");
//System.out.println("******************optMethod====" + optMethod + " ****subjectId====" + subjectId + " ****pid====" + pid);		
		Json json = new Json();
//System.out.println("******************请求成功*****************");
//System.out.println("******************file====" + file);
		Map resultMap = new HashMap<>();
		Map paramMap = new HashMap<>();		
		paramMap.put("stuUserId", currUserId);
		paramMap.put("subjectId", subjectId);
		
		int uploadCount = 0; //上传次数
		try {
			//获取某个学生某个科目已经成功上传的次数（根据学生ID和科目ID联合查询）			
			int uploadedCount = studentPlatformService.getUploadedCountByStuIdAndSubId(paramMap);  //已经上传次数
			
			if(optMethod.equals("mod")) {  //若为修改，次数不变				
				/*
				 * 上传文件，上传成功后返回文件上传路径（目录+文件名），上传至Web容器对应应用的根目录下（上下文根目录）
				 * 增加previewPath-在线预览路径
				 */
				String previewPath = FileUploadUtils.upload(request, file, null, true);
								
				if(!StringUtil.isEmpty(previewPath)) {
					resultMap.put("previewPath", previewPath);	
					//文件上传至上下文根目录成功后，再将该文件拷贝到文件系统指定的目录（下载文件目录）
					String uploadPath = FileUploadUtils.copyUploadFileToFSDir(request, previewPath);
System.out.println("**************************uploadPath**************************====>>>>" + uploadPath);	
					resultMap.put("uploadPath", uploadPath);	
					json.setStatus(true);
		            json.setMessage("上传成功！"); 					
				} else { //这里的处理方式不是太优雅，后续可以优化
					json.setStatus(false);
		            json.setMessage("上传失败！");
				}
			} else {
				if(uploadedCount == CommonConstants.COMM_PARAM_ALLOW_UPLOAD_COUNT) {
					json.setStatus(false);
		            json.setMessage("该科目您已经上传4次不允许再次上传！");
		            //return JSONArray.toJSONString(json);
				} else if (uploadedCount < CommonConstants.COMM_PARAM_ALLOW_UPLOAD_COUNT) {
					uploadCount = uploadedCount + 1;				
					resultMap.put("uploadCount", uploadCount);
					
					/*
					 * 上传文件，上传成功后返回文件上传路径（目录+文件名），上传至Web容器对应应用的根目录下（上下文根目录）
					 * 增加previewPath-在线预览路径
					 */
					String previewPath = FileUploadUtils.upload(request, file, null, true);
					
					if(!StringUtil.isEmpty(previewPath)) {
						resultMap.put("previewPath", previewPath);	
						//文件上传至上下文根目录成功后，再将该文件拷贝到文件系统指定的目录（下载文件目录）
						String uploadPath = FileUploadUtils.copyUploadFileToFSDir(request, previewPath);
System.out.println("**************************uploadPath**************************====>>>>" + uploadPath);	
						resultMap.put("uploadPath", uploadPath);
						json.setStatus(true);
			            json.setMessage("上传成功！");        
			            
					} else { //这里的处理方式不是太优雅，后续可以优化
						json.setStatus(false);
			            json.setMessage("上传失败！");
					}
				}
			}			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		resultMap.put("jsonMsg", json);
		return JSONArray.toJSONString(resultMap);
	}
	
	/**
	 * @功能描述：上传成功后调用此方法持久化信息(修改成功后也用此法，底层通用，也已将该方法修改为传入实体对象，更通用)
	 * 			访问路径：zznueg/portal/studentplatform/saveUploadInfo
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月10日 下午2:59:11
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
    @RequestMapping(value = "saveUploadInfo", produces = "application/json;charset=utf-8")
//	public String saveUploadInfo(HttpServletRequest request, HttpServletResponse response) {
	public String saveUploadInfo(StuUploadInfo stuUploadInfo) {
/*		//获取当前操作用户
		String currUserId = Constants.getCurrendUser().getUserId();
		String subjectId = request.getParameter("subjectId");
		String subjectName = request.getParameter("subjectName");
		String fileSize = request.getParameter("fileSize");
		String uploadPath = request.getParameter("uploadPath");
		String uploadCount = request.getParameter("uploadCount");
		Map paramMap = new HashMap();
		
		paramMap.put("stuUserId", currUserId);
		paramMap.put("subjectId", subjectId);
		paramMap.put("subjectName", subjectName);
		paramMap.put("fileSize", fileSize);	
		paramMap.put("uploadPath", uploadPath);
		paramMap.put("uploadCount", uploadCount);
		paramMap.put("isUploadSucess", CommonConstants.UPLOAD_FILE_SUCESS);
					
		Json json = new Json();
		
		try {
			//上传成功后将信息保存到数据库中				
			if(studentPlatformService.saveUploadFileInfo(paramMap)) {
				json.setStatus(true);
	            json.setMessage(Constants.POST_DATA_SUCCESS);
	        } else {
	        	json.setStatus(false);
	            json.setMessage(Constants.POST_DATA_FAIL);
	        }	
		} catch (Exception e) {
			e.printStackTrace();
		}*/	
//System.out.println("##################stuUploadInfo====>>" + stuUploadInfo.toString());		
		Json json = new Json();
		try {
			json = getMessage(studentPlatformService.persistenceStuUploadInfo(stuUploadInfo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return JSONArray.toJSONString(json);
	}
	
	/**
	 * @功能描述：分页查询所有上传科目信息(返回GridModel对象)(该方法适合后台管理综合查询)
	 * 			访问路径：zznueg/portal/studentplatform/findAllUploadInfoByPage
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月11日 下午2:27:50
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
    @RequestMapping(value = "findAllUploadInfoByPage", produces = "application/json;charset=utf-8")
    public GridModel findAllUploadInfoByPage(HttpServletRequest request) {
		int pageNo = Integer.parseInt(request.getParameter("page"));
		int length = Integer.parseInt(request.getParameter("rows"));
		PageUtil pageUtil = new PageUtil((pageNo - 1) * length, length);
		GridModel gridModel = new GridModel();
		try {
			gridModel.setRows(studentPlatformService.findAllUploadInfoByPage(pageUtil));
			gridModel.setTotal(studentPlatformService.getAllCount(null));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return gridModel;
	}
	
	/**
	 * @功能描述：根据科目ID分别查询各个科目的上传信息（返回List对象）
	 * 			访问路径：zznueg/portal/studentplatform/getUploadInfoBySubId
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月11日 下午2:38:15
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
    @RequestMapping(value = "getUploadInfoBySubId", produces = "application/json;charset=utf-8")
	public List<Map> getUploadInfoBySubId(HttpServletRequest request) {
		String stuUserId = Constants.getCurrendUser().getUserId();  //获取当前登录用户实体类的用户ID
		String subjectId = request.getParameter("subjectId").trim();
		Map paramMap = new HashMap<>();
		List<Map> uploadInfoList = new ArrayList<>();
		paramMap.put("stuUserId", stuUserId);
		paramMap.put("subjectId", subjectId);
		try {
			uploadInfoList = this.studentPlatformService.getUploadInfoBySubId(paramMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return uploadInfoList;
	}
	
	/**
	 * @功能描述：分页查询所有上传科目信息并返回GridModel对象到页面(该方法适合后台管理综合查询)
	 * 			访问路径：zznueg/portal/studentplatform/getAllUpInfoByPage
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月11日 下午3:00:26
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：此方法留给后台领导使用，学生用户不使用该方法
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getAllUpInfoByPage")  
    public ModelAndView getAllUploadInfoByPage(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		Map rtnMap = new HashMap<>();
		GridModel gmData = new GridModel();
		String flag = "all";	//用于前端区分请求及数据显示
		try {
			//从上面的返回GridModel对象的方法中直接调用获取分页数据方法
			gmData = findAllUploadInfoByPage(request);
			//将返回结果数据塞到结果map中以供页面使用
			rtnMap.put("gmData", gmData);
			if(null != rtnMap && rtnMap.size() > 0) {
				mv.addObject("flag", flag);
				mv.addObject("rtnMap", rtnMap);
				mv.setViewName("zznueg/portal/student/subjectInfo");  //此路径需要修改，待优化
			} else {
				LOGGER.debug("获取数据为空！");
				mv.addObject("msg",Constants.MSG_GET_NULL_DATA);	
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage() + Constants.EXCEPTION_MSG_GET_DATA);
			e.printStackTrace();
		}	
		
		return mv;
	}
	
	/**
	 * @功能描述：根据学生ID查询每个学生各自上传的信息(学生用户使用)
	 * 			访问路径：zznueg/portal/studentplatform/getAllUploadInfoByStuUid
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月16日 下午7:20:03
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getAllUploadInfoByStuUid")  
    public ModelAndView getAllUploadInfoByStuUid(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		Map paramMap = new HashMap<>();
		Map rtnMap = new HashMap<>();
		String stuUserId = Constants.getCurrendUser().getUserId();  //获取当前登录用户实体类的用户ID
		int pageNo = Integer.parseInt(request.getParameter("page"));
		int length = Integer.parseInt(request.getParameter("rows"));
		PageUtil pageUtil = new PageUtil((pageNo - 1) * length, length);
		paramMap.put("stuUserId", stuUserId);
		paramMap.put("begin", pageUtil.getBegin());
		paramMap.put("end", pageUtil.getEnd());
		String flag = "all";	//用于前端区分请求及数据显示
		
		/*
		 * 获取考试起止时间
		 */
//		String startDate = SystemBuffer.getItemValueByDictEnum(DictionaryEnum.EXAMTERM_STARTDATE);
//		String endDate = SystemBuffer.getItemValueByDictEnum(DictionaryEnum.EXAMTERM_ENDDATE);
//		mv.addObject("startDate", startDate);
//		mv.addObject("endDate", endDate);
//		request.getSession(false).setAttribute("startDate", startDate);
//		request.getSession(false).setAttribute("endDate", endDate);
		
		GridModel gridModel = new GridModel();
		try {
			gridModel.setRows(studentPlatformService.findAllByPageByStuUid(paramMap));
			gridModel.setTotal(studentPlatformService.getCountByStuUid(paramMap));
			//将返回结果数据塞到结果map中以供页面使用
			rtnMap.put("gmData", gridModel);
			if(null != rtnMap && rtnMap.size() > 0) {
				mv.addObject("flag", flag);
				mv.addObject("rtnMap", rtnMap);
				mv.setViewName("zznueg/portal/student/subjectInfo");  
			} else {
				LOGGER.debug("获取数据为空！");
				mv.addObject("msg",Constants.MSG_GET_NULL_DATA);	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mv;
	}
	
	
	/**
	 * @功能描述：根据科目ID获取上传的科目列表
	 * 			访问路径：zznueg/portal/studentplatform/getUpInfoBySubId
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月12日 上午11:35:08
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getUpInfoBySubId")
	public ModelAndView getUpInfoBySubId(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		Map rtnMap = new HashMap<>();
		List<Map> listData = new ArrayList<>();
		
		try {
			listData = getUploadInfoBySubId(request);
			rtnMap.put("listData", listData);
			if(null != rtnMap && rtnMap.size() > 0) {
				mv.addObject("flag", "detail");
				mv.addObject("rtnMap", rtnMap);
				mv.setViewName("zznueg/portal/student/subjectInfo");
			} else {
				LOGGER.debug("获取数据为空！");
				mv.addObject("msg",Constants.MSG_GET_NULL_DATA);	
			}
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage() + Constants.EXCEPTION_MSG_GET_DATA);
			e.printStackTrace();
		}
		
		return mv;
	}
	
	/**
	 * @功能描述：根据学生ID获取该学生的基本信息
	 * 			访问路径：zznueg/portal/studentplatform/getStuInfoBySuid
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月22日 下午2:16:09
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getStuInfoBySuid", produces = "application/json;charset=utf-8")
	public ModelAndView getStuInfoBySuid(HttpServletRequest request) {
		String stuUserId = Constants.getCurrendUser().getUserId();  //获取当前登录用户实体类的用户ID
		ModelAndView mv =  new ModelAndView();
		Map paramMap = new HashMap<>();
		Map rtnMap = new HashMap<>();
		paramMap.put("stuUserId", stuUserId);
		try {
			rtnMap = this.studentPlatformService.getStuInfoBySuid(paramMap);
			if(null != rtnMap && rtnMap.size() > 0) {
				mv.addObject("rtnMap", rtnMap);				
			} else {
				LOGGER.debug("获取数据为空！");
				mv.addObject("msg",Constants.MSG_GET_NULL_DATA);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
		mv.setViewName("zznueg/portal/student/stuInfo");
		return mv;
	}
	
	/**
	 * @功能描述：根据学生ID获取该学生的评分信息
	 * 			访问路径：zznueg/portal/studentplatform/getStudentScores4Stu
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月22日 下午3:48:44
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getStudentScores4Stu")
	public ModelAndView getStudentScores4Stu(HttpServletRequest request) {
		String stuUserId = Constants.getCurrendUser().getUserId();
		ModelAndView mv = new ModelAndView();
		Map paramMap = new HashMap<>();
		List<Map> listData = new ArrayList<>();
		paramMap.put("stuUserId", stuUserId);
		
		try {
			listData = this.studentPlatformService.getStudentScoresBySuid(paramMap);
			if(null != listData && listData.size() > 0) {
				mv.addObject("listData", listData);				
			} else {
				LOGGER.debug("获取数据为空！");
				mv.addObject("msg",Constants.MSG_GET_NULL_DATA);	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
		mv.setViewName("zznueg/portal/student/stuScoresDtl");
		return mv;
	}
	
	/**
	 * @功能描述：学生平台中学生在线预览视频跳转页面处理
	 * 			访问路径：zznueg/portal/studentplatform/videoPlay4Preview
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月21日 上午10:03:43
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "videoPlay4Preview", produces = "application/json;charset=utf-8")
	public ModelAndView videoPlay4Preview(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		Map paramMap = new HashMap<>();
		String uploadPath = request.getParameter("uploadPath").trim();
		String stuUserId = request.getParameter("stuUserId").trim();
		
		if(StringUtil.isEmpty(uploadPath)) {
			uploadPath = "";
		}
		
		paramMap.put("uploadPath", uploadPath);
		paramMap.put("stuUserId", stuUserId);
		
		mv.addObject("param", paramMap);		
		mv.setViewName("zznueg/portal/student/videoPlay");
		return mv;
	}
	
	/**
	 * @功能描述：学生平台中学生在线预览PDF文档跳转页面处理
	 * 			访问路径：zznueg/portal/studentplatform/pdfViewer4Preview
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月21日 上午10:39:13
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "pdfViewer4Preview", produces = "application/json;charset=utf-8")
	public ModelAndView pdfViewer4Preview(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		Map paramMap = new HashMap<>();
		String uploadPath = request.getParameter("uploadPath").trim();
		String stuUserId = request.getParameter("stuUserId").trim();

		if(StringUtil.isEmpty(uploadPath)) {
			uploadPath = "";
		}
		paramMap.put("uploadPath", uploadPath);
		paramMap.put("stuUserId", stuUserId);
		
		mv.addObject("param", paramMap);		
		mv.setViewName("zznueg/portal/student/pdfViewer");
		return mv;
	}
	
	/**
	 * @功能描述：学生平台数据统计——获取某学生成绩及排名的汇总信息
	 * 			访问路径：zznueg/portal/studentplatform/getStuScoresAndRanksSum4Stat
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月24日 下午6:13:11
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getStuScoresAndRanksSum4Stat")
	public ModelAndView getStudentScoresAndRanksSum4DataStat(HttpServletRequest request) {
		String stuUserId = Constants.getCurrendUser().getUserId();
		ModelAndView mv = new ModelAndView();
		Map rtnMap = new HashMap<>();
		Map paramMap = new HashMap<>();
		Map totalMapData = new HashMap<>();		
		List<Map> subListData = new ArrayList<>();
		paramMap.put("stuUserId", stuUserId);
		
		try {
			totalMapData = this.studentPlatformService.getStuTotalScoreAndRankBySuid(paramMap);
			subListData = this.studentPlatformService.getAllSubScoresAndRanksBySuid(paramMap);
			if(null != subListData && subListData.size() > 0) {	
				rtnMap.put("subListData", subListData);
				if(null != totalMapData && totalMapData.size() > 0) {
					rtnMap.put("totalMapData", totalMapData);
					mv.addObject("rtnMap", rtnMap);
				} else {
					LOGGER.debug("获取汇总成绩数据为空！");
					mv.addObject("msg",Constants.MSG_GET_NULL_DATA);
				}
			} else {
				LOGGER.debug("获取科目成绩数据为空！");
				mv.addObject("msg",Constants.MSG_GET_NULL_DATA);	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		mv.setViewName("zznueg/portal/student/stuDataStatSum");
		return mv;
	}
	
	/**
	 * @功能描述：学生平台——数据统计——成绩记录——“我的总成绩记录”，获取ECharts需要的json字符串数据
	 * 			访问路径：zznueg/portal/studentplatform/getSumScores4SEC
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月25日 下午6:13:13
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getSumScores4SEC")
	public String getSumScores4SEC(HttpServletRequest request) {
		String stuUserId = Constants.getCurrendUser().getUserId();
		Map dataMap = new HashMap<>();
		Map paramMap = new HashMap<>();
		paramMap.put("stuUserId", stuUserId);
		
		String sumScoresJsonStr = "";
		
		try {
			dataMap = this.studentPlatformService.getSumScores4SEC(paramMap);
			if(dataMap != null && !dataMap.isEmpty()) {
				sumScoresJsonStr = JSON.toJSONString(dataMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return sumScoresJsonStr;
	}
	
	/**
	 * @功能描述：学生平台——数据统计——成绩记录——“我的单科成绩记录”，获取ECharts需要的json字符串数据
	 * 			访问路径：zznueg/portal/studentplatform/getSubScores4SEC
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月26日 上午11:20:43
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getSubScores4SEC")
	public String getSubScores4SEC(HttpServletRequest request) {
		String stuUserId = Constants.getCurrendUser().getUserId();
		Map dataMap = new HashMap<>();
		Map paramMap = new HashMap<>();
		paramMap.put("stuUserId", stuUserId);
		
		String subScoresJsonStr = "";
		
		try {
			dataMap = this.studentPlatformService.getSubScores4SEC(paramMap);
			if(dataMap != null && !dataMap.isEmpty()) {
				subScoresJsonStr = JSON.toJSONString(dataMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return subScoresJsonStr;
	}
	
	/**
	 * @功能描述：学生平台——数据统计——“我的成绩对比(和平均值)”，获取ECharts需要的json字符串数据
	 * 			访问路径：zznueg/portal/studentplatform/getSubScoreCompare
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月26日 下午4:01:13
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getSubScoreCompare")
	public String getSubScoreCompareToAvg4SEC(HttpServletRequest request) {
		String stuUserId = Constants.getCurrendUser().getUserId();
		Map dataMap = new HashMap<>();
		Map paramMap = new HashMap<>();
		paramMap.put("stuUserId", stuUserId);
		
		String jsonStr = "";
		
		try {
			dataMap = this.studentPlatformService.getSubScoreCompareToAvg4SEC(paramMap);
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
	 * @功能描述：学生平台——数据统计——“成绩雷达图”，获取ECharts需要的json字符串数据
	 * 			访问路径：zznueg/portal/studentplatform/getScoresRadar
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月26日 下午4:58:13
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getScoresRadar")
	public String getScoresRadar4SEC(HttpServletRequest request) {
		String stuUserId = Constants.getCurrendUser().getUserId();
		Map dataMap = new HashMap<>();
		Map paramMap = new HashMap<>();
		paramMap.put("stuUserId", stuUserId);
		
		String jsonStr = "";
		
		try {
			dataMap = this.studentPlatformService.getScoresRadar4SEC(paramMap);
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
	 * @功能描述：学生平台——数据统计——“TOP10学生排行图”——提升分数最高的学生，获取ECharts需要的json字符串数据
	 * 			访问路径：zznueg/portal/studentplatform/getStuTop10ByEnhance
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月28日 上午9:38:47
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getStuTop10ByEnhance")
	public String getStuTop10ByEnhance4SEC(HttpServletRequest request) {
		String stuUserId = Constants.getCurrendUser().getUserId();
		Map dataMap = new HashMap<>();
		Map paramMap = new HashMap<>();
		paramMap.put("stuUserId", stuUserId);
		
		String jsonStr = "";
		
		try {
			//**********待补充**********
			//dataMap = this.studentPlatformService.getStuTop10ByEnhance4SEC(paramMap);
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
	 * @功能描述：学生平台——数据统计——“TOP10学生排行图”——活跃度最高的用户TOP10，获取ECharts需要的json字符串数据
	 * 			访问路径：zznueg/portal/studentplatform/getStuTop10ByActivity
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月28日 上午9:42:08
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getStuTop10ByActivity")
	public String getStuTop10ByActivity4SEC(HttpServletRequest request) {
		//String stuUserId = Constants.getCurrendUser().getUserId();
		Map dataMap = new HashMap<>();
		Map paramMap = new HashMap<>();
		
		String jsonStr = "";
		
		try {
			dataMap = this.studentPlatformService.getStuTop10ByActivity4SEC(paramMap);
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
	 * @功能描述：学生平台——在线测评调查问卷内容
	 * 			访问路径：zznueg/portal/studentplatform/getOnlineEvalPaperInfo
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月11日 下午3:06:57
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getOnlineEvalPaperInfo")
	public ModelAndView getOnlineEvalPaperInfo(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		Map paramMap = new HashMap<>();
		Map paperInfoMap = new HashMap<>();
		List<Map> paperDtlInfoList = new ArrayList<>();
		
		try {
			paperInfoMap = this.studentPlatformService.getOneEnablePaperInfo(paramMap);
			if(null != paperInfoMap && !paperInfoMap.isEmpty()) {
				mv.addObject("mapData", paperInfoMap);
				String paperId = String.valueOf(paperInfoMap.get("paperId"));
				paramMap.put("paperId", paperId);
				request.getSession().setAttribute("paperId", paperId);  //将问卷ID放到session域中，以便前端获取
				paperDtlInfoList = this.studentPlatformService.getOnlineEvalPaperInfo(paramMap);				
				if(null != paperDtlInfoList && !paperDtlInfoList.isEmpty()) {
					mv.addObject("listData", paperDtlInfoList);
				} else {
					LOGGER.debug("获取试卷内容信息为空！");
				}				
			} else {
				LOGGER.debug("获取有效的试卷信息为空！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		mv.setViewName("zznueg/portal/student/stuOnlineSurveyPaperDtl");
		return mv;
	}
	
	/**
	 * @功能描述：保存学生提交的测评答题记录信息
	 * 			访问路径：zznueg/portal/studentplatform/saveStuEvalRecordInfo
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月12日 下午3:26:14
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
    @RequestMapping(value = "saveStuEvalRecordInfo", produces = "application/json;charset=utf-8")
	public String saveStuEvalRecordInfo(HttpServletRequest request, HttpServletResponse response) {
		//获取当前操作用户
		String currUserId = Constants.getCurrendUser().getUserId();
		String paperId = request.getParameter("paperId");
		String formData = request.getParameter("formData");
		
		List<OnlineEvalStuentEvalRecordInfo> evalRecordList = new ArrayList<>();
		Map paramMap = new HashMap();		
		paramMap.put("stuUserId", currUserId);
		paramMap.put("paperId", paperId);
		//将前端传送的json字符串转换为json对象数组，再进行遍历
		JSONArray jsonObjArr = JSONArray.parseArray(formData);
//System.out.println("##################formData====>>" + formData);		
		for (int i = 0; i < jsonObjArr.size(); i++) {
			JSONObject jsonObj = (JSONObject)jsonObjArr.get(i);
//System.out.println("##################jsonObj("+i+")====>>" + jsonObj.getString("questionId") + ", " + jsonObj.getString("questionCategory") + ", " + jsonObj.getString("studentAnswer") + ", " + jsonObj.getFloatValue("stuAnswerScore"));
			OnlineEvalStuentEvalRecordInfo evalRecord = new OnlineEvalStuentEvalRecordInfo();
			evalRecord.setStuUserId(currUserId);
			evalRecord.setPaperId(paperId);
			evalRecord.setQuestionId(jsonObj.getString("questionId"));
			evalRecord.setQuestionCategory(jsonObj.getString("questionCategory"));
			evalRecord.setStudentAnswer(jsonObj.getString("studentAnswer"));
			evalRecord.setStuAnswerScore(jsonObj.getShortValue("stuAnswerScore"));
			
			evalRecordList.add(evalRecord);
		}
		paramMap.put("evalRecordList", evalRecordList);
		Json json = new Json();		
		try {
			if(!studentPlatformService.isTakePartInOnlineEval(paramMap)){
				if(studentPlatformService.saveStuEvalRecordInfoList(paramMap)) {
					json.setStatus(true);
		            json.setMessage(Constants.POST_DATA_SUCCESS);
		        } else {
		        	json.setStatus(false);
		            json.setMessage(Constants.POST_DATA_FAIL);
		        }
			} else {
				json.setStatus(false);
	            json.setMessage("您已经参与过该测评！");
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		return JSONArray.toJSONString(json);
	}
	
	/**
	 * @功能描述：学生平台——分析结果——得分结果分析表
	 * 			访问路径：zznueg/portal/studentplatform/getCategorySumScore4DST
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月13日 下午5:09:48
	 * 
	 * @修  改  人：zjc
	 * @修改日期：20170914 09:24
	 * @修改描述：前端从session中获取试卷ID的方式不妥，若试卷ID没有被初始化就获取默认值的方式不妥，
	 * 			除非将试卷ID放于系统静态资源初始化模块，在web容器启动时就初始化试卷ID，这样就保证数据的控制范围；
	 * 			因此这里采用从数据库中获取有效试卷ID方式获取方案，更能保证数据有效性和准确性，就是需要每次请求，每次现获取
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getCategorySumScore4DST")
	public ModelAndView getCategorySumScore4DST(HttpServletRequest request) {
		String currUserId = Constants.getCurrendUser().getUserId();
		//String paperId = request.getParameter("paperId");  //这里不再从前端请求的session中获取，改由从数据库实时获取
		
		ModelAndView mv = new ModelAndView();
		Map paramMap = new HashMap<>();
		Map paperInfoMap = new HashMap<>();
		List<Map> cateSumScoreList = new ArrayList<>();
		paramMap.put("stuUserId", currUserId);
		try {
			paperInfoMap = this.studentPlatformService.getOneEnablePaperInfo(paramMap);
			if(null != paperInfoMap && !paperInfoMap.isEmpty()) {
				String paperId = String.valueOf(paperInfoMap.get("paperId"));
				paramMap.put("paperId", paperId);
				
				cateSumScoreList = this.studentPlatformService.getCategorySumScore4DST(paramMap);
				if(null != cateSumScoreList && !cateSumScoreList.isEmpty()) {
					mv.addObject("rtnList", cateSumScoreList);
				} else {
					LOGGER.debug(Constants.MSG_GET_NULL_DATA);
					mv.addObject("msg",Constants.MSG_GET_NULL_DATA); 
				}
			} else {
				LOGGER.debug("获取试卷信息为空！还没有初始化试卷！");
				mv.addObject("msg","暂无有效的在线调查问卷");
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		mv.setViewName("zznueg/portal/student/stuOnlSurAnaResultStatTable");
		return mv;
	}
	
	/**
	 * @功能描述：学生平台——分析结果——分析结果统计图（雷达图、柱状图、折线图、条形图（堆叠柱状图））
	 * 			访问路径：zznueg/portal/studentplatform/getAnalysisResult4SEC
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月14日 上午9:44:24
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getAnalysisResult4SEC")
	public String getAnalysisResult4SEC(HttpServletRequest request) {
		String stuUserId = Constants.getCurrendUser().getUserId();
		Map echartsMap = new HashMap<>();
		Map paramMap = new HashMap<>();		
		Map paperInfoMap = new HashMap<>();		
		paramMap.put("stuUserId", stuUserId);
		
		String jsonStr = "";		
		try {			
			paperInfoMap = this.studentPlatformService.getOneEnablePaperInfo(paramMap);
			if(null != paperInfoMap && !paperInfoMap.isEmpty()) {
				String paperId = String.valueOf(paperInfoMap.get("paperId"));
				paramMap.put("paperId", paperId);				
				echartsMap = this.studentPlatformService.getCategorySumScore4SEC(paramMap);
				if(null != echartsMap && !echartsMap.isEmpty()) {
					jsonStr = JSON.toJSONString(echartsMap);
				} else {
					LOGGER.debug(Constants.MSG_GET_NULL_DATA);					
				}
			} else {
				LOGGER.debug("获取试卷信息为空！还没有初始化试卷！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return jsonStr;
	}
	
	/**
	 * @功能描述：文件下载
	 * 			访问路径：zznueg/portal/studentplatform/downloadFile
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月30日 下午3:57:07
	 * 
	 * @修  改  人：zjc
	 * @版        本：V1.0.1
	 * @修改日期：20171009 11:40
	 * @修改描述：在jsp页面使用超链接下载文件时，如有返回值则下载成功后会在/WEB-INF/views/下找返回json字符串对应的视图，
	 * 			报错{"message":"文件下载成功","status":true,"title":"提示"}.jsp找不到；改为void返回类型可以解决此问题；
	 * 			带json字符串返回值的目前还没有找到更好的解决方案；待优化！！！
	 * 
	 * @修  改  人：zjc
	 * @版        本：V1.1.0
	 * @修改日期：20171010 09:43
	 * @修改描述：在方法前增加@ResponseBody注解，解决{"message":"文件下载成功","status":true,"title":"提示"}.jsp视图找不到问题；
	 * 			不用改为void返回类型，可以返回给前端json字符串以供前端页面进行判断处理；
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "downloadFile")
	public String downloadFile(HttpServletRequest request, HttpServletResponse response) {
		String filePath = request.getParameter("filePath");
		Json json = new Json();
		if(StringUtil.isEmpty(filePath)) {
			json.setStatus(false);
			json.setMessage("文件路径为空");
		} else {
			try {
//				if(FileUtil.copyTo(filePath, PropertiesUtils.getDownloadPath())){	//将文件先拷贝到下载目录再进行文件下载；这里没有此需求，暂不用
					FileUtil.downFile(filePath, response);
					json.setStatus(true);
					json.setMessage("文件下载成功");
//				}
			} catch (FileNotFoundException fne) {
				json.setStatus(false);
				json.setMessage("下载的文件资源不存在");
				fne.printStackTrace();
			} catch (IOException e) {
				json.setStatus(false);
				json.setMessage("下载文件出错");
				e.printStackTrace();
			}
		}
System.out.println("####com.zzrenfeng.zznueg.controller.StudentPlatformController.downloadFile---->>>>" + JSONArray.toJSONString(json));		
		return JSONArray.toJSONString(json);
	}
	
	/**
	 * @功能描述：判断要下载的文件是否存在；下载前先判断，如不存在则不进入downloadFile方法，存在时再执行下载方法
	 * 			访问路径：zznueg/portal/studentplatform/doesFileExist
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年10月11日 下午5:02:34
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
    @RequestMapping(value = "doesFileExist", produces = "application/json;charset=utf-8")
	public String doesFileExist(HttpServletRequest request, HttpServletResponse response) {
		String filePath = request.getParameter("filePath");
		Json json = new Json();
		try {
			if(FileUtil.isExists(filePath)) {
				json.setStatus(true);
				json.setMessage("文件存在，可以下载！");
			} else {
				json.setStatus(false);
				json.setMessage("您要下载的文件不存在！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONArray.toJSONString(json);
	}
	
}
