package com.zzrenfeng.zznueg.controller;

import java.io.FileInputStream;
import java.io.InputStream;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.zzrenfeng.base.controller.BaseController;
import com.zzrenfeng.base.model.GridModel;
import com.zzrenfeng.base.model.Json;
import com.zzrenfeng.base.model.MultiMenu;
import com.zzrenfeng.base.utils.Constants;
import com.zzrenfeng.base.utils.ExcelUtil;
import com.zzrenfeng.base.utils.LogUtils;
import com.zzrenfeng.base.utils.PageUtil;
import com.zzrenfeng.base.utils.StringUtil;
import com.zzrenfeng.zznueg.entity.ClassInfo;
import com.zzrenfeng.zznueg.entity.TeachStuInfo;
import com.zzrenfeng.zznueg.entity.TestImportTeachStuInfo;
import com.zzrenfeng.zznueg.service.TeachClassRelService;
import com.zzrenfeng.zznueg.service.TeachStuInfoService;

import jxl.read.biff.BiffException;
/**
 * @功能描述：教师学生信息Controller
 * @创  建  者：zhoujincheng
 * @版        本：V1.0.0
 * @创建日期：2017年8月2日 上午11:17:24
 * 
 * @修  改  人：
 * @修改日期：
 * @修改描述：
 *
 */
@Controller
@RequestMapping(value = "/zznueg/manage/teastuinfo/")
public class TeachStuInfoController extends BaseController {
	private final Logger LOGGER = LoggerFactory.getLogger(TeachStuInfoController.class);
	
	@Autowired
	private TeachStuInfoService teachStuInfoService;
	@Autowired
	private TeachClassRelService teachClassRelService;
	
	/**
	 * @功能描述：教师主页跳转页面
	 * 			访问路径：zznueg/manage/teastuinfo/teaMain
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月2日 上午11:30:03
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @return
	 */
	@RequestMapping("teaMain")
    public String teaMain() {
        LOGGER.debug("teaMain() is executed!");
        return "zznueg/manage/teastu/teaMain";
    }
	/**
	 * @功能描述：学生主页跳转页面
	 * 			访问路径：zznueg/manage/teastuinfo/stuMain
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月2日 上午11:30:03
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @return
	 */
	@RequestMapping("stuMain")
    public String stuMain() {
        LOGGER.debug("stuMain() is executed!");
        return "zznueg/manage/teastu/stuMain";
    }
	
	/**
	 * @功能描述：教师编辑跳转页面
	 * 			访问路径：zznueg/manage/teastuinfo/teacherEditDlg
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月2日 下午2:31:31
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @return
	 */
	@RequestMapping("teacherEditDlg")
	public String teacherEditDlg() {
		LOGGER.debug("teacherEditDlg() is executed!");
		return "zznueg/manage/teastu/teaEdit";
	}
	
	/**
	 * @功能描述：学生编辑跳转页面
	 * 			访问路径：zznueg/manage/teastuinfo/studentEditDlg
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月2日 下午2:31:31
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @return
	 */
	@RequestMapping("studentEditDlg")
	public String studentEditDlg() {
		LOGGER.debug("studentEditDlg() is executed!");
		return "zznueg/manage/teastu/stuEdit";
	}
	
	/**
	 * @功能描述：后台开设系统用户跳转页面(学生和教师通用)
	 * 			访问路径：zznueg/manage/teastuinfo/openAccountDlg
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月3日 下午4:33:17
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @return
	 */
	@RequestMapping("openAccountDlg")
	public String openAccountDlg() {
		LOGGER.debug("openAccountDlg() is executed!");
		return "zznueg/manage/teastu/openAct";
	}
	
	
	/**
	 * @功能描述：分页查询所有教师信息
	 * 			访问路径：zznueg/manage/teastuinfo/getAllTeacherByPage
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月2日 上午11:36:06
	 * 
	 * @修  改  人：zjc
	 * @版        本：V1.1.0
	 * @修改日期：20171016 14:14
	 * @修改描述：增加按教师工号或姓名模糊搜索参数和是否开户标志参数；进行按条件检索
	 * 
	 * @修  改  人：zhoujincheng
	 * @版        本：V1.1.0
	 * @修改日期：20171018 15:34
	 * @修改描述：增加模糊搜索参数：tsno-学生学号/教师工号，name-学生姓名/教师姓名；实现一个输入框可以根据学生（教师）学号或姓名进行模糊检索；
	 * 			说明：由于前端学生学号（教师工号）或姓名检索框为同一个输入框，这里将同一个拆分成两个，内容相同；
	 * 				虽然同一个值分拆成两个，但实际只能为一个值；后端判断为空时判断一个即可，使用or连接两者进行检索
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
    @RequestMapping(value = "getAllTeacherByPage", produces = "application/json;charset=utf-8")
	public GridModel getAllTeacherByPage(HttpServletRequest request) {
		LOGGER.debug(LogUtils.getCurrExeMethodBriefInfo());
		Map paramMap = new HashMap<>();
		String tsno = StringUtil.isEmpty(request.getParameter("tsno")) ? null : request.getParameter("tsno").trim();	//获取教师工号
		String name = StringUtil.isEmpty(request.getParameter("name")) ? null : request.getParameter("name").trim();	//获取教师姓名
		String isOpenAct = StringUtil.isEmpty(request.getParameter("isOpenAct")) ? null : request.getParameter("isOpenAct").trim();	//获取是否开户标志
        int pageNo = Integer.parseInt(request.getParameter("page"));
        int length = Integer.parseInt(request.getParameter("rows"));
        PageUtil pageUtil = new PageUtil((pageNo - 1) * length, length);
        paramMap.put("tsno", tsno);
        paramMap.put("name", name);
        paramMap.put("isOpenAct", isOpenAct);
        paramMap.put("begin", pageUtil.getBegin());
        paramMap.put("end", pageUtil.getEnd());
        
        GridModel gridModel = new GridModel();
        try {
			gridModel.setRows(teachStuInfoService.findAllTeacherInfoByPage(paramMap));
			gridModel.setTotal(teachStuInfoService.getTeacherCount(paramMap));
		} catch (Exception e) {
			e.printStackTrace();
		}
        return gridModel;
	}
	
	/**
	 * @功能描述：分页查询所有教师信息
	 * 			访问路径：zznueg/manage/teastuinfo/getAllStudentByPage
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月3日 上午9:31:38
	 * 
	 * @修  改  人：zjc
	 * @版        本：V1.1.0
	 * @修改日期：20171016 11:17
	 * @修改描述：增加按学生学号模糊搜索参数和是否开户标志参数；进行按条件检索
	 * 
	 * @修  改  人：zhoujincheng
	 * @版        本：V1.1.0
	 * @修改日期：20171018 15:30
	 * @修改描述：增加模糊搜索参数：tsno-学生学号/教师工号，name-学生姓名/教师姓名；实现一个输入框可以根据学生（教师）学号或姓名进行模糊检索；
	 * 			说明：由于前端学生学号（教师工号）或姓名检索框为同一个输入框，这里将同一个拆分成两个，内容相同；
	 * 				虽然同一个值分拆成两个，但实际只能为一个值；后端判断为空时判断一个即可，使用or连接两者进行检索
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
    @RequestMapping(value = "getAllStudentByPage", produces = "application/json;charset=utf-8")
	public GridModel getAllStudentByPage(HttpServletRequest request) {
		LOGGER.debug(LogUtils.getCurrExeMethodBriefInfo());
		Map paramMap = new HashMap<>();
		String tsno = StringUtil.isEmpty(request.getParameter("tsno")) ? null : request.getParameter("tsno").trim();	//获取学生学号
		String name = StringUtil.isEmpty(request.getParameter("name")) ? null : request.getParameter("name").trim();	//获取学生姓名
		String isOpenAct = StringUtil.isEmpty(request.getParameter("isOpenAct")) ? null : request.getParameter("isOpenAct").trim();	//获取是否开户标志
		String classId = StringUtil.isEmpty(request.getParameter("classId")) ? null : request.getParameter("classId").trim();	//获取班级ID
		int pageNo = Integer.parseInt(request.getParameter("page"));
        int length = Integer.parseInt(request.getParameter("rows"));
        PageUtil pageUtil = new PageUtil((pageNo - 1) * length, length);
        paramMap.put("tsno", tsno);
        paramMap.put("name", name);
        paramMap.put("isOpenAct", isOpenAct);
        paramMap.put("classId", classId);
        paramMap.put("begin", pageUtil.getBegin());
        paramMap.put("end", pageUtil.getEnd());
        
        GridModel gridModel = new GridModel();
        try {
			gridModel.setRows(teachStuInfoService.findAllStudentInfoByPage(paramMap));
			gridModel.setTotal(teachStuInfoService.getStudentCount(paramMap));
		} catch (Exception e) {
			e.printStackTrace();
		}    
        return gridModel;
	}
	
	/**
	 * @功能描述：新增或修改教师学生信息处理
	 * 			访问路径：zznueg/manage/teastuinfo/saveOrUpdateTeachStu
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月2日 上午11:38:57
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param teachStuInfo
	 * @return
	 */
	@ResponseBody
    @RequestMapping(value = "saveOrUpdateTeachStu", produces = "application/json;charset=utf-8")
    public String saveOrUpdateTeachStu(TeachStuInfo teachStuInfo) {
    	LOGGER.debug("saveOrUpdateSubject() is executed!");
        Json json = null;
		try {
			json = getMessage(teachStuInfoService.persistenceTeachStuInfo(teachStuInfo));
		} catch (Exception e) {
			e.printStackTrace();
		}
        return JSONArray.toJSONString(json);
    }
	
	/**
	 * @功能描述：根据教师学生ID删除教师学生信息（硬删除）
	 * 			访问路径：zznueg/manage/teastuinfo/delTeachStu
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月2日 上午11:42:36
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
    @RequestMapping(value = "delTeachStu", produces = "application/json;charset=utf-8")
    public String delTeachStu(HttpServletRequest request) {
        String id = request.getParameter("id");

        Json json = new Json();
        boolean flag;
		try {
			flag = teachStuInfoService.delTeachStuInfoById(id);
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
	 * @功能描述：获取院系班级菜单模型，用于给教师分配教授的班级
	 * 			访问路径：zznueg/manage/teastuinfo/getADCMultiMenu
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月7日 上午9:55:20
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @return
	 */
    @ResponseBody
    @RequestMapping(value = "getADCMultiMenu", produces = "application/json;charset=utf-8")
    public List<MultiMenu> getADCMultiMenu() {
    	LOGGER.debug("========>>getADCMultiMenu() is executed!");  	
    	List<MultiMenu> multiMenuList = new ArrayList<MultiMenu>();
    	try {
    		multiMenuList = teachStuInfoService.getADCMultiMenu();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return multiMenuList;
    }
	
    /**
     * @功能描述：根据教师信ID获取对应的教授班级信息
	 * 			访问路径：zznueg/manage/teastuinfo/getClassInfoByTeacher
     * @创  建  者：zhoujincheng
     * @版        本：V1.0.0
     * @创建日期：2017年8月7日 下午4:18:30
     * 
     * @修  改  人：
     * @修改日期：
     * @修改描述：
     * 
     * @param request
     * @return
     * @throws Exception 
     */
    @ResponseBody
    @RequestMapping(value = "getClassInfoByTeacher", produces = "application/json;charset=utf-8")
    public List<ClassInfo> getClassInfoByTeacher(HttpServletRequest request) throws Exception {
    	String teachUserId = request.getParameter("teachUserId");
    	
    	List<ClassInfo> classInfoList = teachClassRelService.getTeachClassRelByTuid(teachUserId);
    	
    	return classInfoList;
    }
    
    /**
     * @功能描述：保存某个教师分配的班级关系信息
	 * 			访问路径：zznueg/manage/teastuinfo/saveTeachClassRel
     * @创  建  者：zhoujincheng
     * @版        本：V1.0.0
     * @创建日期：2017年8月7日 下午5:19:13
     * 
     * @修  改  人：
     * @修改日期：
     * @修改描述：
     * 
     * @param request
     * @return
     * @throws Exception 
     */
    @ResponseBody
    @RequestMapping(value = "saveTeachClassRel", produces = "application/json;charset=utf-8")
    public String saveTeachClassRel(HttpServletRequest request) throws Exception {
    	String teachUserId = request.getParameter("teachUserId");
    	String checkedClassIds = request.getParameter("checkedClassIds");
    	Map<String, String> paramMap = new HashMap<>();
    	paramMap.put("teachUserId", teachUserId);
    	paramMap.put("checkedClassIds", checkedClassIds);
    	
    	Json json = new Json();
    	if(teachClassRelService.saveTeachClassRel(paramMap)) {
    		json.setStatus(true);
            json.setMessage(Constants.POST_DATA_SUCCESS);
        } else {
            json.setMessage(Constants.POST_DATA_FAIL);
        }
    	return JSONArray.toJSONString(json);
    }
    
    /**
     * @功能描述：批量导入学生信息（来自Excel文件）
	 * 			访问路径：zznueg/manage/teastuinfo/batchImportTeachStuInfo
     * @创  建  者：zhoujincheng
     * @版        本：V1.0.0
     * @创建日期：2017年10月13日 下午3:14:00
     * 
     * @修  改  人：
     * @修改日期：
     * @修改描述：
     * 
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "batchImportTeachStuInfo", produces = "application/json;charset=utf-8")
	public String batchImportTeachStuInfo (HttpServletRequest request, @RequestParam("uploadFile") MultipartFile file) {
    	
    	Json json = new Json();
		try {
			InputStream input = file.getInputStream();
			//ExcelUtil<TestImportTeachStuInfo> excelUtil = new ExcelUtil<>(TestImportTeachStuInfo.class);
			//List<TestImportTeachStuInfo> importInfoList = new ArrayList<TestImportTeachStuInfo>();
			ExcelUtil<TeachStuInfo> excelUtil = new ExcelUtil<>(TeachStuInfo.class);
			List<TeachStuInfo> importInfoList = new ArrayList<TeachStuInfo>();
			importInfoList = excelUtil.importExcel(null, input);
//System.out.println("**********************导入结果importInfoList**********************>>>>" + importInfoList);			
			if(null != importInfoList && !importInfoList.isEmpty()) {
				int importCounts = 0;
				importCounts = this.teachStuInfoService.batchImportTeachStuInfo(importInfoList);
				if(importCounts == importInfoList.size()) {
					json.setStatus(true);
					json.setMessage("批量导入【全部成功】！导入条数为：" + importCounts);
				} else if (importCounts < importInfoList.size()) {
					json.setStatus(true);
					json.setMessage("批量导入【部分成功】！导入条数为：" + importCounts);
				} 
				
			} else {
				json.setStatus(false);
				json.setMessage("批量导入数据【失败】！");
			}
		} catch (Exception e) {
			json.setStatus(false);
			json.setMessage("批量导入数据【异常】！");
			e.printStackTrace();
		}
		return JSONArray.toJSONString(json);
    }
    
}
