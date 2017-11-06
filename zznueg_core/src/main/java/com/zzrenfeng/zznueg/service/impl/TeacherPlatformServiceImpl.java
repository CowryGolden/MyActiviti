package com.zzrenfeng.zznueg.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zzrenfeng.base.entity.BaseDomain;
import com.zzrenfeng.base.utils.Constants;
import com.zzrenfeng.base.utils.StringUtil;
import com.zzrenfeng.zznueg.dao.StuUploadInfoMapper;
import com.zzrenfeng.zznueg.dao.TeacherPlatformStatMapper;
import com.zzrenfeng.zznueg.entity.StuUploadInfo;
import com.zzrenfeng.zznueg.service.TeacherPlatformService;
import com.zzrenfeng.zznueg.utils.CommonConstants;
import com.zzrenfeng.zznueg.utils.Series;

/**
 * @功能描述：教师平台部分Service接口实现类
 * @创  建  者：zhoujincheng
 * @版        本：V1.0.0
 * @创建日期：2017年8月18日 上午10:11:26
 * 
 * @修  改  人：
 * @修改日期：
 * @修改描述：
 *
 */
@Transactional
@Service("teacherPlatformService")
public class TeacherPlatformServiceImpl implements TeacherPlatformService {

	private final Logger LOGGER = LoggerFactory.getLogger(TeacherPlatformServiceImpl.class);
	
	@Autowired
	protected StuUploadInfoMapper stuUploadInfoMapper; 
	@Autowired
	protected TeacherPlatformStatMapper teacherPlatformStatMapper;
	
	/**
	 * @功能描述：教师查询所教授班级对应的学生列表
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月18日 上午10:08:57
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map> getStuListByTcid4Teach(Map paramMap) throws Exception {
		return this.stuUploadInfoMapper.getStuListByTcid4Teach(paramMap);
	}

	/**
	 * @功能描述：教师根据学生列表的信息点击“上一位”或“下一位”根据学生ID查询学生最近上传的资源列表
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月18日 上午10:09:22
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map> getUploadListBySuid4Teach(Map paramMap) throws Exception {
		return this.stuUploadInfoMapper.getUploadListBySuid4Teach(paramMap);
	}

	/**
	 * @功能描述：持久化教师评分信息
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月21日 下午2:08:51
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean persistenceEvalScore(Map paramMap) throws Exception {
		String teachUserId = Constants.getCurrendUser().getUserId();
		int saveOrUpdateCount = 0;
		StuUploadInfo stuUploadInfo = new StuUploadInfo();
		stuUploadInfo.setPid((String) paramMap.get("pid"));
		stuUploadInfo.setEvalScore(Short.parseShort((String) paramMap.get("evalScore")));
		stuUploadInfo.setTeachUserId(teachUserId);
		stuUploadInfo.setEvalTime(new Date());
//System.out.println("###########stuUploadInfo====>>>>" + stuUploadInfo.toString());		
		//修改学生上传信息中的教师评分内容
		BaseDomain.editLog(stuUploadInfo, teachUserId);
		saveOrUpdateCount = stuUploadInfoMapper.updateByPrimaryKeySelective(stuUploadInfo);
		if(CommonConstants.COMM_CONST_INTEGER_1 == saveOrUpdateCount) {
			return true;
		}
		return false;
	}

	/**
	 * @功能描述：教师查询所教授班级对应的学生列表（v1.1：增加科目及评分信息，用于学生概览页信息显示使用，增加分页）
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月21日 下午5:07:55
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map> getStuOverviewByTuid4Teach(Map paramMap) throws Exception {
		return this.stuUploadInfoMapper.getStuOverviewByTuid4Teach(paramMap);
	}

	/**
	 * @功能描述：根据教师ID获取教授的学生总数(以供分页使用)
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月21日 下午5:09:10
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@Override
	public Long getStuCountByTuid(Map paramMap) throws Exception {
		return this.stuUploadInfoMapper.getStuCountByTuid(paramMap);
	}

	/**
	 * @功能描述：获取全院学生通过率和某个老师指导的学生以及各科目的通过率，并为数据统计表格整合数据
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月28日 下午2:37:05
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@Override
	public Map getPassingRat4DST(Map paramMap) throws Exception {
		Map rtnMap = new HashMap<>();		//返回结果Map
		Map allStusMap = new HashMap<>();	//所有学生结果Map
		Map myStusMap = new HashMap<>();	//我指导的学生结果Map
		List<Map> allStusSubScoresList = new ArrayList<>();	//所有学生的各科成绩列表
		List<Map> myStusSubScoresList = new ArrayList<>();	//某个教师指导的学生的各科成绩列表
		List<Map> allPassStusList = new ArrayList<>();		//所有学生中通过的学生列表
		List<Map> myPassStusList = new ArrayList<>();		//我指导的学生中通过的学生列表
		List<Map> weigePassList = new ArrayList<>();		//我指导的学生中微格科目通过学生的List
		List<Map> banshuPassList = new ArrayList<>();		//我指导的学生中板书科目通过学生的List
		List<Map> jiaoanPassList = new ArrayList<>();		//我指导的学生中教案科目通过学生的List
		List<Map> kejianPassList = new ArrayList<>();		//我指导的学生中课件科目通过学生的List
		
		double allStusPassRate = 0d;	//所有学生的通过率
		double myStusPassRate = 0d;		//我指导的学生的通过率
		double weigePassRate = 0d;		//我指导的学生的微格科目通过率
		double banshuPassRate = 0d;		//我指导的学生的板书科目通过率
		double jiaoanPassRate = 0d;		//我指导的学生的教案科目通过率
		double kejianPassRate = 0d;		//我指导的学生的课件科目通过率
		
		allStusSubScoresList = this.teacherPlatformStatMapper.getAllStusSubScores(paramMap);
		myStusSubScoresList = this.teacherPlatformStatMapper.getMyStusSubScoresByTuid(paramMap);
		//所有学生
		if(null != allStusSubScoresList && allStusSubScoresList.size() > 0) {
			for (Map stuSubScores : allStusSubScoresList) {
				float weige = (stuSubScores.get("weige") == null || (String.valueOf(stuSubScores.get("weige"))).length() == 0)
						? 0f : Float.parseFloat(String.valueOf(stuSubScores.get("weige")));
				float banshu = (stuSubScores.get("banshu") == null || (String.valueOf(stuSubScores.get("banshu"))).length() == 0)
						? 0f : Float.parseFloat(String.valueOf(stuSubScores.get("banshu")));
				float jiaoan = (stuSubScores.get("jiaoan") == null || (String.valueOf(stuSubScores.get("jiaoan"))).length() == 0)
						? 0f : Float.parseFloat(String.valueOf(stuSubScores.get("jiaoan")));
				float kejian = (stuSubScores.get("kejian") == null || (String.valueOf(stuSubScores.get("kejian"))).length() == 0)
						? 0f : Float.parseFloat(String.valueOf(stuSubScores.get("kejian")));
				if((weige >= 60 && weige <=100) && (banshu >= 60 && banshu <=100) && (jiaoan >= 60 && jiaoan <=100) && (kejian >= 60 && kejian <=100)) {
					allPassStusList.add(stuSubScores);
				}				
			}
			if(null != allPassStusList && allPassStusList.size() > 0) {
				BigDecimal pass = new BigDecimal(allPassStusList.size());
				BigDecimal all = new BigDecimal(allStusSubScoresList.size());
				allStusPassRate = pass.divide(all, 4, BigDecimal.ROUND_HALF_UP).doubleValue();
			}
			
			allStusMap.put("allStusSubScores", allStusSubScoresList);
			allStusMap.put("allPassStus", allPassStusList);
			allStusMap.put("allStusPassRate", allStusPassRate);
			
			rtnMap.put("allStus", allStusMap);
		} else {
			LOGGER.debug("获取所有学生的各科成绩列表为空");
		}
		//我指导的学生
		if(null != myStusSubScoresList && myStusSubScoresList.size() > 0) {
			for (Map myStuSubScores : myStusSubScoresList) {
				float weige = (myStuSubScores.get("weige") == null || (String.valueOf(myStuSubScores.get("weige"))).length() == 0)
						? 0f : Float.parseFloat(String.valueOf(myStuSubScores.get("weige")));
				float banshu = (myStuSubScores.get("banshu") == null || (String.valueOf(myStuSubScores.get("banshu"))).length() == 0)
						? 0f : Float.parseFloat(String.valueOf(myStuSubScores.get("banshu")));
				float jiaoan = (myStuSubScores.get("jiaoan") == null || (String.valueOf(myStuSubScores.get("jiaoan"))).length() == 0)
						? 0f : Float.parseFloat(String.valueOf(myStuSubScores.get("jiaoan")));
				float kejian = (myStuSubScores.get("kejian") == null || (String.valueOf(myStuSubScores.get("kejian"))).length() == 0)
						? 0f : Float.parseFloat(String.valueOf(myStuSubScores.get("kejian")));
				if(weige >= 60 && weige <=100) {
					weigePassList.add(myStuSubScores);
				}
				if(banshu >= 60 && banshu <= 100) {
					banshuPassList.add(myStuSubScores);
				}			
				if(jiaoan >= 60 && jiaoan <= 100) {		
					jiaoanPassList.add(myStuSubScores);
				}
				if(kejian >= 60 && kejian <= 100) {
					kejianPassList.add(myStuSubScores);
				}
				if((weige >= 60 && weige <= 100) && (banshu >= 60 && banshu <= 100) && (jiaoan >= 60 && jiaoan <= 100) && (kejian >= 60 && kejian <= 100)) {
					myPassStusList.add(myStuSubScores);
				}								
			}
			if(null != weigePassList && weigePassList.size() > 0) {
				BigDecimal weige = new BigDecimal(weigePassList.size());
				BigDecimal myAll = new BigDecimal(myStusSubScoresList.size());
				weigePassRate =  weige.divide(myAll, 4, BigDecimal.ROUND_HALF_UP).doubleValue();
			}
			if(null != banshuPassList && banshuPassList.size() > 0) {
				BigDecimal banshu = new BigDecimal(banshuPassList.size());
				BigDecimal myAll = new BigDecimal(myStusSubScoresList.size());
				banshuPassRate =  banshu.divide(myAll, 4, BigDecimal.ROUND_HALF_UP).doubleValue();
			}
			if(null != jiaoanPassList && jiaoanPassList.size() > 0) {
				BigDecimal jiaoan = new BigDecimal(jiaoanPassList.size());
				BigDecimal myAll = new BigDecimal(myStusSubScoresList.size());
				jiaoanPassRate =  jiaoan.divide(myAll, 4, BigDecimal.ROUND_HALF_UP).doubleValue();
			}
			if(null != kejianPassList && kejianPassList.size() > 0) {
				BigDecimal kejian = new BigDecimal(kejianPassList.size());
				BigDecimal myAll = new BigDecimal(myStusSubScoresList.size());
				kejianPassRate =  kejian.divide(myAll, 4, BigDecimal.ROUND_HALF_UP).doubleValue();
			}
			if(null != myPassStusList && myPassStusList.size() > 0) {
				BigDecimal myPass = new BigDecimal(myPassStusList.size());
				BigDecimal myAll = new BigDecimal(myStusSubScoresList.size());
				myStusPassRate =  myPass.divide(myAll, 4, BigDecimal.ROUND_HALF_UP).doubleValue();
			}
			
			myStusMap.put("myStusSubScores", myStusSubScoresList);
			myStusMap.put("myPassStus", myPassStusList);
			myStusMap.put("myStusPassRate", myStusPassRate);
			myStusMap.put("weigePassRate", weigePassRate);
			myStusMap.put("banshuPassRate", banshuPassRate);
			myStusMap.put("jiaoanPassRate", jiaoanPassRate);
			myStusMap.put("kejianPassRate", kejianPassRate);
			
			rtnMap.put("myStus", myStusMap);
		} else {
			LOGGER.debug("获取某个教师指导的学生的各科成绩列表为空");
		}
		
		return rtnMap;
	}

	/**
	 * @功能描述：获取全院学生和某个老师指导的学生各个科目不同等级的人数以及占比，为统计饼图整合数据
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月29日 上午10:06:11
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@Override
	public Map getDifferentGrades4SEC(Map paramMap) throws Exception {
		Map rtnMap = new HashMap<>();		//返回结果Map
		List<Map> allStusSubScoresList = new ArrayList<>();	//所有学生的各科成绩列表
		List<Map> myStusSubScoresList = new ArrayList<>();	//某个教师指导的学生的各科成绩列表
		
		Map allStusRstMap = new HashMap<>();	//全体学生饼形图结果Map
		Map myStusRstMap = new HashMap<>();		//我指导的学生饼形图结果Map
		Map weigeRstMap = new HashMap<>();		//全体学生微格科目饼形图结果Map
		Map banshuRstMap = new HashMap<>();		//全体学生板书科目饼形图结果Map
		Map jiaoanRstMap = new HashMap<>();		//全体学生教案科目饼形图结果Map
		Map kejianRstMap = new HashMap<>();		//全体学生课件科目饼形图结果Map
		
		List pieSeriesDataList = null;	//饼图Series Data List
		Map pieSeriesDataMap = null;		//饼图Series Data Map
		List<Series> seriesList = null;
		List<String> legendList = null;
		Series series = null;
		
		List<Map> allStusA = new ArrayList<>();  //所有学生中等级为A的学生（[90,100]）优秀
		List<Map> allStusB = new ArrayList<>();  //所有学生中等级为B的学生（[80, 90)）良好
		List<Map> allStusC = new ArrayList<>();  //所有学生中等级为C的学生（[70, 80)）一般
		List<Map> allStusD = new ArrayList<>();  //所有学生中等级为D的学生（[60, 70)）及格
		List<Map> allStusE = new ArrayList<>();  //所有学生中等级为E的学生（[ 0, 60)）不及格
		int allStusACnt = 0;
		int allStusBCnt = 0;
		int allStusCCnt = 0;
		int allStusDCnt = 0;
		int allStusECnt = 0;
		
		allStusSubScoresList = this.teacherPlatformStatMapper.getAllStusSubScores(paramMap);
		myStusSubScoresList = this.teacherPlatformStatMapper.getMyStusSubScoresByTuid(paramMap);
		//全体学生
		if(null != allStusSubScoresList && allStusSubScoresList.size() > 0) {
			for (Map stuSubScores : allStusSubScoresList) {
				float weige = (stuSubScores.get("weige") == null || (String.valueOf(stuSubScores.get("weige"))).length() == 0)
						? 0f : Float.parseFloat(String.valueOf(stuSubScores.get("weige")));
				float banshu = (stuSubScores.get("banshu") == null || (String.valueOf(stuSubScores.get("banshu"))).length() == 0)
						? 0f : Float.parseFloat(String.valueOf(stuSubScores.get("banshu")));
				float jiaoan = (stuSubScores.get("jiaoan") == null || (String.valueOf(stuSubScores.get("jiaoan"))).length() == 0)
						? 0f : Float.parseFloat(String.valueOf(stuSubScores.get("jiaoan")));
				float kejian = (stuSubScores.get("kejian") == null || (String.valueOf(stuSubScores.get("kejian"))).length() == 0)
						? 0f : Float.parseFloat(String.valueOf(stuSubScores.get("kejian")));
				
				if(weige < 60 || banshu < 60 || jiaoan < 60 || kejian < 60) {
					allStusE.add(stuSubScores);
				}
				if ((weige >= 60 && banshu >= 60 && jiaoan >= 60 && kejian >= 60)
						&& ((weige >= 60 && weige < 70) || (banshu >= 60 && banshu < 70)
								|| (jiaoan >= 60 && jiaoan < 70) || (kejian >= 60 && kejian < 70))) {
					allStusD.add(stuSubScores);
				}
				if ((weige >= 70 && banshu >= 70 && jiaoan >= 70 && kejian >= 70)
						&& ((weige >= 70 && weige < 80) || (banshu >= 70 && banshu < 80)
								|| (jiaoan >= 70 && jiaoan < 80) || (kejian >= 70 && kejian < 80))) {
					allStusC.add(stuSubScores);
				}
				if ((weige >= 80 && banshu >= 80 && jiaoan >= 80 && kejian >= 80)
						&& ((weige >= 80 && weige < 90) || (banshu >= 80 && banshu < 90)
								|| (jiaoan >= 80 && jiaoan < 90) || (kejian >= 80 && kejian < 90))) {
					allStusB.add(stuSubScores);
				}
				if ((weige >= 90 && banshu >= 90 && jiaoan >= 90 && kejian >= 90)
						&& ((weige >= 90 && weige <= 100) || (banshu >= 90 && banshu <= 100)
								|| (jiaoan >= 90 && jiaoan <= 100) || (kejian >= 90 && kejian <= 100))) {
					allStusA.add(stuSubScores);
				}				
			}
			//全体学生各科成绩综合判断的等级各占的人数
			if(null != allStusE && !allStusE.isEmpty()) {
				allStusECnt = allStusE.size();
			}
			if(null != allStusD && !allStusD.isEmpty()) {
				allStusDCnt = allStusD.size();
			}
			if(null != allStusC && !allStusC.isEmpty()) {
				allStusCCnt = allStusC.size();
			}
			if(null != allStusB && !allStusB.isEmpty()) {
				allStusBCnt = allStusB.size();
			}
			if(null != allStusA && !allStusA.isEmpty()) {
				allStusACnt = allStusA.size();
			}
			
			/*
			 * 整合前端ECharts饼图所需的数据
			 */
			//整合全体学生数据
			seriesList = new ArrayList<Series>();
			legendList = new ArrayList<String>();	
			series = new Series();
			
			pieSeriesDataList = new ArrayList();	//饼图Series Data List
			pieSeriesDataMap = new HashMap();		//饼图Series Data Map			
			
			pieSeriesDataMap.put("value", allStusECnt);
			pieSeriesDataMap.put("name", "不及格");
			pieSeriesDataList.add(pieSeriesDataMap);
			
			pieSeriesDataMap = new HashMap();
			pieSeriesDataMap.put("value", allStusDCnt);
			pieSeriesDataMap.put("name", "及格");
			pieSeriesDataList.add(pieSeriesDataMap);
			
			pieSeriesDataMap = new HashMap();
			pieSeriesDataMap.put("value", allStusCCnt);
			pieSeriesDataMap.put("name", "一般");
			pieSeriesDataList.add(pieSeriesDataMap);
			
			pieSeriesDataMap = new HashMap();
			pieSeriesDataMap.put("value", allStusBCnt);
			pieSeriesDataMap.put("name", "良好");
			pieSeriesDataList.add(pieSeriesDataMap);
			
			pieSeriesDataMap = new HashMap();
			pieSeriesDataMap.put("value", allStusACnt);
			pieSeriesDataMap.put("name", "优秀");
			pieSeriesDataList.add(pieSeriesDataMap);
			
			series.setData(pieSeriesDataList);
			series.setType(CommonConstants.STATISTICS_ECHARTS_TYPE_PIE);
			series.setName("全体学生等级");
			seriesList.add(series);
			legendList.add(series.getName());
			
			allStusRstMap.put("legend", legendList);
			allStusRstMap.put("seriesList", seriesList);
			
			rtnMap.put("allStus", allStusRstMap);
		} else {
			LOGGER.debug("获取所有学生的各科成绩列表为空");
			
			legendList = new ArrayList();
			legendList.add("全体学生等级");
			//虚拟空序列
			seriesList = new ArrayList();
			for (int i = 0; i < legendList.size(); i++) {
				series = new Series();
				series.setName(legendList.get(i));
				series.setType(CommonConstants.STATISTICS_ECHARTS_TYPE_PIE);
				series.setData(new ArrayList());
				seriesList.add(series);
			}
			allStusRstMap.put("legend", legendList);
			allStusRstMap.put("seriesList", seriesList);
			
			rtnMap.put("allStus", allStusRstMap);
		}
		
		List<Map> myStusA = new ArrayList<>();  //我指导的学生中等级为A的学生（[90,100]）优秀
		List<Map> myStusB = new ArrayList<>();  //我指导的学生中等级为B的学生（[80, 90)）良好
		List<Map> myStusC = new ArrayList<>();  //我指导的学生中等级为C的学生（[70, 80)）一般
		List<Map> myStusD = new ArrayList<>();  //我指导的学生中等级为D的学生（[60, 70)）及格
		List<Map> myStusE = new ArrayList<>();  //我指导的学生中等级为E的学生（[ 0, 60)）不及格
		int myStusACnt = 0;
		int myStusBCnt = 0;
		int myStusCCnt = 0;
		int myStusDCnt = 0;
		int myStusECnt = 0;
		List<Map> weigeA = new ArrayList<>();  //我指导的学生中微格科目等级为A的学生（[90,100]）优秀
		List<Map> weigeB = new ArrayList<>();  //我指导的学生中微格科目等级为B的学生（[80, 90)）良好
		List<Map> weigeC = new ArrayList<>();  //我指导的学生中微格科目等级为C的学生（[70, 80)）一般
		List<Map> weigeD = new ArrayList<>();  //我指导的学生中微格科目等级为D的学生（[60, 70)）及格
		List<Map> weigeE = new ArrayList<>();  //我指导的学生中微格科目等级为E的学生（[ 0, 60)）不及格
		int weigeACnt = 0;
		int weigeBCnt = 0;
		int weigeCCnt = 0;
		int weigeDCnt = 0;
		int weigeECnt = 0;
		List<Map> banshuA = new ArrayList<>();  //我指导的学生中板书科目等级为A的学生（[90,100]）优秀
		List<Map> banshuB = new ArrayList<>();  //我指导的学生中板书科目等级为B的学生（[80, 90)）良好
		List<Map> banshuC = new ArrayList<>();  //我指导的学生中板书科目等级为C的学生（[70, 80)）一般
		List<Map> banshuD = new ArrayList<>();  //我指导的学生中板书科目等级为D的学生（[60, 70)）及格
		List<Map> banshuE = new ArrayList<>();  //我指导的学生中板书科目等级为E的学生（[ 0, 60)）不及格
		int banshuACnt = 0;
		int banshuBCnt = 0;
		int banshuCCnt = 0;
		int banshuDCnt = 0;
		int banshuECnt = 0;
		List<Map> jiaoanA = new ArrayList<>();  //我指导的学生中教案科目等级为A的学生（[90,100]）优秀
		List<Map> jiaoanB = new ArrayList<>();  //我指导的学生中教案科目等级为B的学生（[80, 90)）良好
		List<Map> jiaoanC = new ArrayList<>();  //我指导的学生中教案科目等级为C的学生（[70, 80)）一般
		List<Map> jiaoanD = new ArrayList<>();  //我指导的学生中教案科目等级为D的学生（[60, 70)）及格
		List<Map> jiaoanE = new ArrayList<>();  //我指导的学生中教案科目等级为E的学生（[ 0, 60)）不及格
		int jiaoanACnt = 0;
		int jiaoanBCnt = 0;
		int jiaoanCCnt = 0;
		int jiaoanDCnt = 0;
		int jiaoanECnt = 0;
		List<Map> kejianA = new ArrayList<>();  //我指导的学生中课件科目等级为A的学生（[90,100]）优秀
		List<Map> kejianB = new ArrayList<>();  //我指导的学生中课件科目等级为B的学生（[80, 90)）良好
		List<Map> kejianC = new ArrayList<>();  //我指导的学生中课件科目等级为C的学生（[70, 80)）一般
		List<Map> kejianD = new ArrayList<>();  //我指导的学生中课件科目等级为D的学生（[60, 70)）及格
		List<Map> kejianE = new ArrayList<>();  //我指导的学生中课件科目等级为E的学生（[ 0, 60)）不及格
		int kejianACnt = 0;
		int kejianBCnt = 0;
		int kejianCCnt = 0;
		int kejianDCnt = 0;
		int kejianECnt = 0;
		//我指导的学生
		if(null != myStusSubScoresList && !myStusSubScoresList.isEmpty()) {
			for (Map myStuSubScores : myStusSubScoresList) {
				float weige = (myStuSubScores.get("weige") == null || (String.valueOf(myStuSubScores.get("weige"))).length() == 0)
						? 0f : Float.parseFloat(String.valueOf(myStuSubScores.get("weige")));
				float banshu = (myStuSubScores.get("banshu") == null || (String.valueOf(myStuSubScores.get("banshu"))).length() == 0)
						? 0f : Float.parseFloat(String.valueOf(myStuSubScores.get("banshu")));
				float jiaoan = (myStuSubScores.get("jiaoan") == null || (String.valueOf(myStuSubScores.get("jiaoan"))).length() == 0)
						? 0f : Float.parseFloat(String.valueOf(myStuSubScores.get("jiaoan")));
				float kejian = (myStuSubScores.get("kejian") == null || (String.valueOf(myStuSubScores.get("kejian"))).length() == 0)
						? 0f : Float.parseFloat(String.valueOf(myStuSubScores.get("kejian")));
				//各科成绩综合判断
				if(weige < 60 || banshu < 60 || jiaoan < 60 || kejian < 60) {
					myStusE.add(myStuSubScores);
				}
				if ((weige >= 60 && banshu >= 60 && jiaoan >= 60 && kejian >= 60)
						&& ((weige >= 60 && weige < 70) || (banshu >= 60 && banshu < 70)
								|| (jiaoan >= 60 && jiaoan < 70) || (kejian >= 60 && kejian < 70))) {
					myStusD.add(myStuSubScores);
				}
				if ((weige >= 70 && banshu >= 70 && jiaoan >= 70 && kejian >= 70)
						&& ((weige >= 70 && weige < 80) || (banshu >= 70 && banshu < 80)
								|| (jiaoan >= 70 && jiaoan < 80) || (kejian >= 70 && kejian < 80))) {
					myStusC.add(myStuSubScores);
				}
				if ((weige >= 80 && banshu >= 80 && jiaoan >= 80 && kejian >= 80)
						&& ((weige >= 80 && weige < 90) || (banshu >= 80 && banshu < 90)
								|| (jiaoan >= 80 && jiaoan < 90) || (kejian >= 80 && kejian < 90))) {
					myStusB.add(myStuSubScores);
				}
				if ((weige >= 90 && banshu >= 90 && jiaoan >= 90 && kejian >= 90)
						&& ((weige >= 90 && weige <= 100) || (banshu >= 90 && banshu <= 100)
								|| (jiaoan >= 90 && jiaoan <= 100) || (kejian >= 90 && kejian <= 100))) {
					myStusA.add(myStuSubScores);
				}
				//微格科目判断
				if(weige < 60) {
					weigeE.add(myStuSubScores);
				}
				if(weige >= 60 && weige < 70) {
					weigeD.add(myStuSubScores);
				}
				if(weige >= 70 && weige < 80) {
					weigeC.add(myStuSubScores);
				}
				if(weige >= 80 && weige < 90) {
					weigeB.add(myStuSubScores);
				}
				if(weige >= 90 && weige <= 100) {
					weigeA.add(myStuSubScores);
				}
				//板书科目判断
				if(banshu < 60) {
					banshuE.add(myStuSubScores);
				}
				if(banshu >= 60 && banshu < 70) {
					banshuD.add(myStuSubScores);
				}
				if(banshu >= 70 && banshu < 80) {
					banshuC.add(myStuSubScores);
				}
				if(banshu >= 80 && banshu < 90) {
					banshuB.add(myStuSubScores);
				}
				if(banshu >= 90 && banshu <= 100) {
					banshuA.add(myStuSubScores);
				}
				//教案科目判断
				if(jiaoan < 60) {
					jiaoanE.add(myStuSubScores);
				}
				if(jiaoan >= 60 && jiaoan < 70) {
					jiaoanD.add(myStuSubScores);
				}
				if(jiaoan >= 70 && jiaoan < 80) {
					jiaoanC.add(myStuSubScores);
				}
				if(jiaoan >= 80 && jiaoan < 90) {
					jiaoanB.add(myStuSubScores);
				}
				if(jiaoan >= 90 && jiaoan <= 100) {
					jiaoanA.add(myStuSubScores);
				}
				//课件科目判断
				if(kejian < 60) {
					kejianE.add(myStuSubScores);
				}
				if(kejian >= 60 && kejian < 70) {
					kejianD.add(myStuSubScores);
				}
				if(kejian >= 70 && kejian < 80) {
					kejianC.add(myStuSubScores);
				}
				if(kejian >= 80 && kejian < 90) {
					kejianB.add(myStuSubScores);
				}
				if(kejian >= 90 && kejian <= 100) {
					kejianA.add(myStuSubScores);
				}
			}
			//我指导的学生各科成绩综合判断的等级各占的人数
			if(null != myStusE && !myStusE.isEmpty()) {
				myStusECnt = myStusE.size();
			}
			if(null != myStusD && !myStusD.isEmpty()) {
				myStusDCnt = myStusD.size();
			}
			if(null != myStusC && !myStusC.isEmpty()) {
				myStusCCnt = myStusC.size();
			}
			if(null != myStusB && !myStusB.isEmpty()) {
				myStusBCnt = myStusB.size();
			}
			if(null != myStusA && !myStusA.isEmpty()) {
				myStusACnt = myStusA.size();
			}
			//微格科目各等级所占的人数
			if(null != weigeE && !weigeE.isEmpty()) {
				weigeECnt =weigeE.size();
			}
			if(null != weigeD && !weigeD.isEmpty()) {
				weigeDCnt = weigeD.size();
			}
			if(null != weigeC && !weigeC.isEmpty()) {
				weigeCCnt = weigeC.size();
			}
			if(null != weigeB && !weigeB.isEmpty()) {
				weigeBCnt = weigeB.size();
			}
			if(null != weigeA && !weigeA.isEmpty()) {
				weigeACnt = weigeA.size();
			}
			//板书科目各等级所占的人数
			if(null != banshuE && !banshuE.isEmpty()) {
				banshuECnt =banshuE.size();
			}
			if(null != banshuD && !banshuD.isEmpty()) {
				banshuDCnt = banshuD.size();
			}
			if(null != banshuC && !banshuC.isEmpty()) {
				banshuCCnt = banshuC.size();
			}
			if(null != banshuB && !banshuB.isEmpty()) {
				banshuBCnt = banshuB.size();
			}
			if(null != banshuA && !banshuA.isEmpty()) {
				banshuACnt = banshuA.size();
			}
			//教案科目各等级所占的人数
			if(null != jiaoanE && !jiaoanE.isEmpty()) {
				jiaoanECnt =jiaoanE.size();
			}
			if(null != jiaoanD && !jiaoanD.isEmpty()) {
				jiaoanDCnt = jiaoanD.size();
			}
			if(null != jiaoanC && !jiaoanC.isEmpty()) {
				jiaoanCCnt = jiaoanC.size();
			}
			if(null != jiaoanB && !jiaoanB.isEmpty()) {
				jiaoanBCnt = jiaoanB.size();
			}
			if(null != jiaoanA && !jiaoanA.isEmpty()) {
				jiaoanACnt = jiaoanA.size();
			}
			//课件科目各等级所占的人数
			if(null != kejianE && !kejianE.isEmpty()) {
				kejianECnt =kejianE.size();
			}
			if(null != kejianD && !kejianD.isEmpty()) {
				kejianDCnt = kejianD.size();
			}
			if(null != kejianC && !kejianC.isEmpty()) {
				kejianCCnt = kejianC.size();
			}
			if(null != kejianB && !kejianB.isEmpty()) {
				kejianBCnt = kejianB.size();
			}
			if(null != kejianA && !kejianA.isEmpty()) {
				kejianACnt = kejianA.size();
			}
			
			/*
			 * 整合前端ECharts饼图所需的数据
			 */
			//整合我指导的学生数据
			seriesList = new ArrayList<Series>();
			legendList = new ArrayList<String>();	
			series = new Series();
			
			pieSeriesDataList = new ArrayList();	//饼图Series Data List
			pieSeriesDataMap = new HashMap();		//饼图Series Data Map			
			
			pieSeriesDataMap.put("value", myStusECnt);
			pieSeriesDataMap.put("name", "不及格");
			pieSeriesDataList.add(pieSeriesDataMap);
			
			pieSeriesDataMap = new HashMap();
			pieSeriesDataMap.put("value", myStusDCnt);
			pieSeriesDataMap.put("name", "及格");
			pieSeriesDataList.add(pieSeriesDataMap);
			
			pieSeriesDataMap = new HashMap();
			pieSeriesDataMap.put("value", myStusCCnt);
			pieSeriesDataMap.put("name", "一般");
			pieSeriesDataList.add(pieSeriesDataMap);
			
			pieSeriesDataMap = new HashMap();
			pieSeriesDataMap.put("value", myStusBCnt);
			pieSeriesDataMap.put("name", "良好");
			pieSeriesDataList.add(pieSeriesDataMap);
			
			pieSeriesDataMap = new HashMap();
			pieSeriesDataMap.put("value", myStusACnt);
			pieSeriesDataMap.put("name", "优秀");
			pieSeriesDataList.add(pieSeriesDataMap);
			
			series.setData(pieSeriesDataList);
			series.setType(CommonConstants.STATISTICS_ECHARTS_TYPE_PIE);
			series.setName("我指导的学生等级");
			seriesList.add(series);
			legendList.add(series.getName());
			
			myStusRstMap.put("legend", legendList);
			myStusRstMap.put("seriesList", seriesList);
			
			rtnMap.put("myStus", myStusRstMap);
			
			//整合我指导的学生微格科目数据
			seriesList = new ArrayList<Series>();
			legendList = new ArrayList<String>();	
			series = new Series();
			
			pieSeriesDataList = new ArrayList();	//饼图Series Data List
			pieSeriesDataMap = new HashMap();		//饼图Series Data Map			
			
			pieSeriesDataMap.put("value", weigeECnt);
			pieSeriesDataMap.put("name", "不及格");
			pieSeriesDataList.add(pieSeriesDataMap);
			
			pieSeriesDataMap = new HashMap();
			pieSeriesDataMap.put("value", weigeDCnt);
			pieSeriesDataMap.put("name", "及格");
			pieSeriesDataList.add(pieSeriesDataMap);
			
			pieSeriesDataMap = new HashMap();
			pieSeriesDataMap.put("value", weigeCCnt);
			pieSeriesDataMap.put("name", "一般");
			pieSeriesDataList.add(pieSeriesDataMap);
			
			pieSeriesDataMap = new HashMap();
			pieSeriesDataMap.put("value", weigeBCnt);
			pieSeriesDataMap.put("name", "良好");
			pieSeriesDataList.add(pieSeriesDataMap);
			
			pieSeriesDataMap = new HashMap();
			pieSeriesDataMap.put("value", weigeACnt);
			pieSeriesDataMap.put("name", "优秀");
			pieSeriesDataList.add(pieSeriesDataMap);
			
			series.setData(pieSeriesDataList);
			series.setType(CommonConstants.STATISTICS_ECHARTS_TYPE_PIE);
			series.setName("微格科目");
			seriesList.add(series);
			legendList.add(series.getName());
			
			weigeRstMap.put("legend", legendList);
			weigeRstMap.put("seriesList", seriesList);
			
			rtnMap.put("weige", weigeRstMap);
			
			//整合我指导的学生板书科目数据
			seriesList = new ArrayList<Series>();
			legendList = new ArrayList<String>();	
			series = new Series();
			
			pieSeriesDataList = new ArrayList();	//饼图Series Data List
			pieSeriesDataMap = new HashMap();		//饼图Series Data Map			
			
			pieSeriesDataMap.put("value", banshuECnt);
			pieSeriesDataMap.put("name", "不及格");
			pieSeriesDataList.add(pieSeriesDataMap);
			
			pieSeriesDataMap = new HashMap();
			pieSeriesDataMap.put("value", banshuDCnt);
			pieSeriesDataMap.put("name", "及格");
			pieSeriesDataList.add(pieSeriesDataMap);
			
			pieSeriesDataMap = new HashMap();
			pieSeriesDataMap.put("value", banshuCCnt);
			pieSeriesDataMap.put("name", "一般");
			pieSeriesDataList.add(pieSeriesDataMap);
			
			pieSeriesDataMap = new HashMap();
			pieSeriesDataMap.put("value", banshuBCnt);
			pieSeriesDataMap.put("name", "良好");
			pieSeriesDataList.add(pieSeriesDataMap);
			
			pieSeriesDataMap = new HashMap();
			pieSeriesDataMap.put("value", banshuACnt);
			pieSeriesDataMap.put("name", "优秀");
			pieSeriesDataList.add(pieSeriesDataMap);
			
			series.setData(pieSeriesDataList);
			series.setType(CommonConstants.STATISTICS_ECHARTS_TYPE_PIE);
			series.setName("板书科目");
			seriesList.add(series);
			legendList.add(series.getName());
			
			banshuRstMap.put("legend", legendList);
			banshuRstMap.put("seriesList", seriesList);
			
			rtnMap.put("banshu", banshuRstMap);
			
			//整合我指导的学生教案科目数据
			seriesList = new ArrayList<Series>();
			legendList = new ArrayList<String>();	
			series = new Series();
			
			pieSeriesDataList = new ArrayList();	//饼图Series Data List
			pieSeriesDataMap = new HashMap();		//饼图Series Data Map			
			
			pieSeriesDataMap.put("value", jiaoanECnt);
			pieSeriesDataMap.put("name", "不及格");
			pieSeriesDataList.add(pieSeriesDataMap);
			
			pieSeriesDataMap = new HashMap();
			pieSeriesDataMap.put("value", jiaoanDCnt);
			pieSeriesDataMap.put("name", "及格");
			pieSeriesDataList.add(pieSeriesDataMap);
			
			pieSeriesDataMap = new HashMap();
			pieSeriesDataMap.put("value", jiaoanCCnt);
			pieSeriesDataMap.put("name", "一般");
			pieSeriesDataList.add(pieSeriesDataMap);
			
			pieSeriesDataMap = new HashMap();
			pieSeriesDataMap.put("value", jiaoanBCnt);
			pieSeriesDataMap.put("name", "良好");
			pieSeriesDataList.add(pieSeriesDataMap);
			
			pieSeriesDataMap = new HashMap();
			pieSeriesDataMap.put("value", jiaoanACnt);
			pieSeriesDataMap.put("name", "优秀");
			pieSeriesDataList.add(pieSeriesDataMap);
			
			series.setData(pieSeriesDataList);
			series.setType(CommonConstants.STATISTICS_ECHARTS_TYPE_PIE);
			series.setName("教案科目");
			seriesList.add(series);
			legendList.add(series.getName());
			
			jiaoanRstMap.put("legend", legendList);
			jiaoanRstMap.put("seriesList", seriesList);
			
			rtnMap.put("jiaoan", jiaoanRstMap);
			
			//整合我指导的学生课件科目数据
			seriesList = new ArrayList<Series>();
			legendList = new ArrayList<String>();	
			series = new Series();
			
			pieSeriesDataList = new ArrayList();	//饼图Series Data List
			pieSeriesDataMap = new HashMap();		//饼图Series Data Map			
			
			pieSeriesDataMap.put("value", kejianECnt);
			pieSeriesDataMap.put("name", "不及格");
			pieSeriesDataList.add(pieSeriesDataMap);
			
			pieSeriesDataMap = new HashMap();
			pieSeriesDataMap.put("value", kejianDCnt);
			pieSeriesDataMap.put("name", "及格");
			pieSeriesDataList.add(pieSeriesDataMap);
			
			pieSeriesDataMap = new HashMap();
			pieSeriesDataMap.put("value", kejianCCnt);
			pieSeriesDataMap.put("name", "一般");
			pieSeriesDataList.add(pieSeriesDataMap);
			
			pieSeriesDataMap = new HashMap();
			pieSeriesDataMap.put("value", kejianBCnt);
			pieSeriesDataMap.put("name", "良好");
			pieSeriesDataList.add(pieSeriesDataMap);
			
			pieSeriesDataMap = new HashMap();
			pieSeriesDataMap.put("value", kejianACnt);
			pieSeriesDataMap.put("name", "优秀");
			pieSeriesDataList.add(pieSeriesDataMap);
			
			series.setData(pieSeriesDataList);
			series.setType(CommonConstants.STATISTICS_ECHARTS_TYPE_PIE);
			series.setName("课件科目");
			seriesList.add(series);
			legendList.add(series.getName());
			
			kejianRstMap.put("legend", legendList);
			kejianRstMap.put("seriesList", seriesList);
			
			rtnMap.put("kejian", kejianRstMap);
		} else {
			LOGGER.debug("获取某个教师指导的学生的各科成绩列表为空");
			legendList = new ArrayList();
			legendList.add("科目等级");
			//虚拟空序列
			seriesList = new ArrayList();
			for (int i = 0; i < legendList.size(); i++) {
				series = new Series();
				series.setName(legendList.get(i));
				series.setType(CommonConstants.STATISTICS_ECHARTS_TYPE_PIE);
				series.setData(new ArrayList());
				seriesList.add(series);
			}
			
			myStusRstMap.put("legend", legendList);
			myStusRstMap.put("seriesList", seriesList);
			
			weigeRstMap.put("legend", legendList);
			weigeRstMap.put("seriesList", seriesList);
			
			banshuRstMap.put("legend", legendList);
			banshuRstMap.put("seriesList", seriesList);
			
			jiaoanRstMap.put("legend", legendList);
			jiaoanRstMap.put("seriesList", seriesList);
			
			kejianRstMap.put("legend", legendList);
			kejianRstMap.put("seriesList", seriesList);		
			
			rtnMap.put("myStus", myStusRstMap);			
			rtnMap.put("weige", weigeRstMap);
			rtnMap.put("banshu", banshuRstMap);
			rtnMap.put("jiaoan", jiaoanRstMap);
			rtnMap.put("kejian", kejianRstMap);
		}	
		
		return rtnMap;
	}

	/**
	 * @功能描述：通过教师ID获取某个教师教授学生最大上传的次数
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月29日 下午4:04:08
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@Override
	public Integer getStuMaxUpCountByTuid(Map paramMap) throws Exception {
		return this.teacherPlatformStatMapper.getStuMaxUpCountByTuid(paramMap);
	}

	/**
	 * @功能描述：根据教师ID、科目ID和上传次数查询该科目的总成绩，总考试人数，平均成绩
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月29日 下午4:46:49
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@Override
	public Map getAvgSubScoreByTidSidCnt(Map paramMap) throws Exception {
		return this.teacherPlatformStatMapper.getAvgSubScoreByTidSidCnt(paramMap);
	}

	/**
	 * @功能描述：根据科目ID查询该科目(全院校)的总成绩，总考试人数，平均成绩
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月29日 下午4:47:07
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@Override
	public Map getAvgSubScoreBySid(Map paramMap) throws Exception {
		return this.teacherPlatformStatMapper.getAvgSubScoreBySid(paramMap);
	}
	
	/**
	 * @功能描述：获取某个教师指导的学生各科成绩（平均成绩）全体各科平均成绩，并为统计图表整合数据
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月29日 下午4:56:19
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@Override
	public Map getMyStusSubScoresRatio4SEC(Map paramMap) throws Exception {
		Map rtnMap = new HashMap<>();
		
		List xAxisList = null;	//x轴数据列表
		List yAxisByCurrList = null;	//当前次数平均成绩-y轴数据列表
		List yAxisByPrevList = null;	//上一次平均成绩-y轴数据列表
		List<String> legendList = new ArrayList<>();//图例名称列
		List<Series> seriesList = new ArrayList<>();//数据列表
		Series series = null;				//echarts每条线（或其他图形）属性序列对象		
		
		Integer subMaxUpCount = this.getStuMaxUpCountByTuid(paramMap);
		if(subMaxUpCount != null && subMaxUpCount != 0) {
			//初始化x轴序列
			xAxisList = new ArrayList<>();	//x轴数据列表
			xAxisList.add("微格");
			xAxisList.add("板书");
			xAxisList.add("教案");
			xAxisList.add("课件");
			//获取本次（最大上传次数）我指导学生的各科平均成绩
			if(subMaxUpCount == 1) {//说明只有一次上传，即没有上一次信息，此时前一次等于本次	
				yAxisByCurrList = new ArrayList<>();	//当前次数平均成绩-y轴数据列表
				yAxisByPrevList = new ArrayList<>();	//上一次平均成绩-y轴数据列表
				
				yAxisByCurrList.add(this.getSubAvgScoreByTidSidCnt(paramMap, CommonConstants.SUBJECT_ID_WEIGE, subMaxUpCount));
				yAxisByPrevList.add(this.getSubAvgScoreByTidSidCnt(paramMap, CommonConstants.SUBJECT_ID_WEIGE, subMaxUpCount));
				
				yAxisByCurrList.add(this.getSubAvgScoreByTidSidCnt(paramMap, CommonConstants.SUBJECT_ID_BANSHU, subMaxUpCount));
				yAxisByPrevList.add(this.getSubAvgScoreByTidSidCnt(paramMap, CommonConstants.SUBJECT_ID_BANSHU, subMaxUpCount));
				
				yAxisByCurrList.add(this.getSubAvgScoreByTidSidCnt(paramMap, CommonConstants.SUBJECT_ID_JIAOAN, subMaxUpCount));
				yAxisByPrevList.add(this.getSubAvgScoreByTidSidCnt(paramMap, CommonConstants.SUBJECT_ID_JIAOAN, subMaxUpCount));
				
				yAxisByCurrList.add(this.getSubAvgScoreByTidSidCnt(paramMap, CommonConstants.SUBJECT_ID_KEJIAN, subMaxUpCount));
				yAxisByPrevList.add(this.getSubAvgScoreByTidSidCnt(paramMap, CommonConstants.SUBJECT_ID_KEJIAN, subMaxUpCount));
				
			} else if(subMaxUpCount > 1 && subMaxUpCount <=4) { //说明有上一次，分别记录各次
				yAxisByCurrList = new ArrayList<>();	//当前次数平均成绩-y轴数据列表
				yAxisByPrevList = new ArrayList<>();	//上一次平均成绩-y轴数据列表
				//本次				
				yAxisByCurrList.add(this.getSubAvgScoreByTidSidCnt(paramMap, CommonConstants.SUBJECT_ID_WEIGE, subMaxUpCount));
				yAxisByCurrList.add(this.getSubAvgScoreByTidSidCnt(paramMap, CommonConstants.SUBJECT_ID_BANSHU, subMaxUpCount));
				yAxisByCurrList.add(this.getSubAvgScoreByTidSidCnt(paramMap, CommonConstants.SUBJECT_ID_JIAOAN, subMaxUpCount));
				yAxisByCurrList.add(this.getSubAvgScoreByTidSidCnt(paramMap, CommonConstants.SUBJECT_ID_KEJIAN, subMaxUpCount));
				
				//上一次
				yAxisByPrevList.add(this.getSubAvgScoreByTidSidCnt(paramMap, CommonConstants.SUBJECT_ID_WEIGE, subMaxUpCount - 1));
				yAxisByPrevList.add(this.getSubAvgScoreByTidSidCnt(paramMap, CommonConstants.SUBJECT_ID_BANSHU, subMaxUpCount - 1));
				yAxisByPrevList.add(this.getSubAvgScoreByTidSidCnt(paramMap, CommonConstants.SUBJECT_ID_JIAOAN, subMaxUpCount - 1));
				yAxisByPrevList.add(this.getSubAvgScoreByTidSidCnt(paramMap, CommonConstants.SUBJECT_ID_KEJIAN, subMaxUpCount - 1));
				
			}
			
			if(null != yAxisByPrevList && !yAxisByPrevList.isEmpty()) {
				series = new Series();
				series.setName("上一次考试我指导的学生各科平均成绩");
				series.setType(CommonConstants.STATISTICS_ECHARTS_TYPE_BAR);
				series.setData(yAxisByPrevList);

				legendList.add(series.getName());
				seriesList.add(series);
			}
			if(null != yAxisByCurrList && !yAxisByCurrList.isEmpty()) {
				series = new Series();
				series.setName("本次考试我指导的学生各科平均成绩");
				series.setType(CommonConstants.STATISTICS_ECHARTS_TYPE_BAR);
				series.setData(yAxisByCurrList);

				legendList.add(series.getName());
				seriesList.add(series);
			}
			
			rtnMap.put("axis", xAxisList);
			rtnMap.put("legend", legendList);
			rtnMap.put("seriesList", seriesList);
			
		} else {
			subMaxUpCount = 0;
			//虚拟X轴序列
			xAxisList = new ArrayList(); 
			legendList = new ArrayList();
			legendList.add("上一次考试我指导的学生各科平均成绩");
			legendList.add("本次考试我指导的学生各科平均成绩");
			//虚拟空序列
			seriesList = new ArrayList();
			for (int i = 0; i < legendList.size(); i++) {
				series = new Series();
				series.setName(legendList.get(i));
				series.setType(CommonConstants.STATISTICS_ECHARTS_TYPE_BAR);
				series.setData(new ArrayList());
				seriesList.add(series);
			}
			rtnMap.put("axis", xAxisList);
			rtnMap.put("legend", legendList);
			rtnMap.put("seriesList", seriesList);
		}
		
		return rtnMap;
	}
	
	/**
	 * @功能描述：根据教师ID，科目ID和上传次数获取该教师指导学生某科目在某次考试中的平均成绩
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月30日 上午9:43:56
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param paramMap
	 * @param subjectId
	 * @param upCount
	 * @return avgSubScore 科目平均成绩
	 */
	private Object getSubAvgScoreByTidSidCnt(Map paramMap, String subjectId, Integer upCount) throws Exception {
		Object avgSubScore = null;
		if(paramMap.containsKey("subjectId")) {
			paramMap.remove("subjectId");  //科目ID存在则清除，这里重新设定
		}
		if(paramMap.containsKey("uploadCount")) {
			paramMap.remove("uploadCount");  //次数存在则清除，这里重新设定
		}
		paramMap.put("subjectId", subjectId);
		if(null != upCount) {
			paramMap.put("uploadCount", upCount);
		}
		
		Map currCntMyStusSubMap = new HashMap<>();
		currCntMyStusSubMap = this.getAvgSubScoreByTidSidCnt(paramMap);
		if(currCntMyStusSubMap != null && !currCntMyStusSubMap.isEmpty()) {
			avgSubScore = currCntMyStusSubMap.get("avgSubScore") == null ? 0 : currCntMyStusSubMap.get("avgSubScore");					
		} else {
			avgSubScore = 0;
		}
		return avgSubScore;
	}
	
	/**
	 * @功能描述：根据科目ID获取全校某一科目的平均成绩
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月30日 上午10:23:53
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param paramMap
	 * @param subjectId
	 * @return
	 * @throws Exception
	 */
	private Object getAllSubAvgScoreBySid(Map paramMap, String subjectId) throws Exception {
		Object avgScore = null;
		
		if(paramMap.containsKey("subjectId")) {
			paramMap.remove("subjectId");  //科目ID存在则清除，这里重新设定
		}
		paramMap.put("subjectId", subjectId);
		Map avgScoreMap = new HashMap<>();
		avgScoreMap = this.getAvgSubScoreBySid(paramMap);
		if(null != avgScoreMap && !avgScoreMap.isEmpty()) {
			avgScore = avgScoreMap.get("avgSubScore") == null ? 0 : avgScoreMap.get("avgSubScore");
		} else {
			avgScore = 0;
		}		
		return avgScore;
	}
	
	
	/**
	 * @功能描述：获取我指导学生的平均成绩和全校学生的平均成绩，并为统计柱状图准备数据
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月30日 上午10:10:21
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@Override
	public Map getMyAvgAndAllAvgSubScore4SEC(Map paramMap) throws Exception {
		Map rtnMap = new HashMap();		//返回结果Map
		List<String> legendList = new ArrayList<>();//图例名称列
		List xAxisList = null;	//x轴数据列表
		List yAxisBySubList = new ArrayList<>();	//各科成绩-y轴数据列表
		List yAxisByAvgList = new ArrayList<>();	//平均成绩-y轴数据列表
		List<Series> seriesList = new ArrayList<>();//数据列表
		Series series = null;				//echarts每条线（或其他图形）属性序列对象
		//我指导的学生各科平均成绩y轴序列
		yAxisBySubList.add(this.getSubAvgScoreByTidSidCnt(paramMap, CommonConstants.SUBJECT_ID_WEIGE, null));
		yAxisBySubList.add(this.getSubAvgScoreByTidSidCnt(paramMap, CommonConstants.SUBJECT_ID_BANSHU, null));
		yAxisBySubList.add(this.getSubAvgScoreByTidSidCnt(paramMap, CommonConstants.SUBJECT_ID_JIAOAN, null));
		yAxisBySubList.add(this.getSubAvgScoreByTidSidCnt(paramMap, CommonConstants.SUBJECT_ID_KEJIAN, null));
		//全校学生各科平均成绩y轴序列
		yAxisByAvgList.add(this.getAllSubAvgScoreBySid(paramMap, CommonConstants.SUBJECT_ID_WEIGE));
		yAxisByAvgList.add(this.getAllSubAvgScoreBySid(paramMap, CommonConstants.SUBJECT_ID_BANSHU));
		yAxisByAvgList.add(this.getAllSubAvgScoreBySid(paramMap, CommonConstants.SUBJECT_ID_JIAOAN));
		yAxisByAvgList.add(this.getAllSubAvgScoreBySid(paramMap, CommonConstants.SUBJECT_ID_KEJIAN));
						
		if(null != yAxisByAvgList && !yAxisByAvgList.isEmpty()) {
			//初始化x轴序列
			xAxisList = new ArrayList<>();	//x轴数据列表
			xAxisList.add("微格");
			xAxisList.add("板书");
			xAxisList.add("教案");
			xAxisList.add("课件");
			
			series = new Series();
			series.setName("全校各科平均成绩");
			series.setType(CommonConstants.STATISTICS_ECHARTS_TYPE_BAR);
			series.setData(yAxisByAvgList);

			legendList.add(series.getName());
			seriesList.add(series);
			
			if(null != yAxisBySubList && !yAxisBySubList.isEmpty()) {
				series = new Series();
				series.setName("我指导的学生各科平均成绩");
				series.setType(CommonConstants.STATISTICS_ECHARTS_TYPE_BAR);
				series.setData(yAxisBySubList);

				legendList.add(series.getName());
				seriesList.add(series);
			}
			
		} else {
			LOGGER.debug("获取平均成绩列表为空");
			
			xAxisList = new ArrayList();  //虚拟x轴
			//增加图例
			legendList = new ArrayList();
			legendList.add("全校各科平均成绩");
			legendList.add("我指导的学生各科平均成绩");

			//虚拟空序列
			seriesList = new ArrayList();
			for (int i = 0; i < legendList.size(); i++) {
				series = new Series();
				series.setName(legendList.get(i));
				series.setType(CommonConstants.STATISTICS_ECHARTS_TYPE_BAR);
				series.setData(new ArrayList());
				seriesList.add(series);
			}
		}
		
		rtnMap.put("axis", xAxisList);
		rtnMap.put("legend", legendList);
		rtnMap.put("seriesList", seriesList);
		
		return rtnMap;
	}
	
	
}
