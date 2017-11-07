package com.zzrenfeng.zznueg.service.impl;

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
import com.zzrenfeng.base.entity.DictionaryInfo;
import com.zzrenfeng.base.utils.Constants;
import com.zzrenfeng.base.utils.PageUtil;
import com.zzrenfeng.base.utils.StringUtil;
import com.zzrenfeng.base.utils.SystemBuffer;
import com.zzrenfeng.base.utils.UUIDUtils;
import com.zzrenfeng.zznueg.dao.OnlineEvalStuentEvalRecordInfoMapper;
import com.zzrenfeng.zznueg.dao.StuUploadInfoMapper;
import com.zzrenfeng.zznueg.dao.StudentPlatformStatMapper;
import com.zzrenfeng.zznueg.entity.OnlineEvalStuentEvalRecordInfo;
import com.zzrenfeng.zznueg.entity.StuUploadInfo;
import com.zzrenfeng.zznueg.service.StudentPlatformService;
import com.zzrenfeng.zznueg.utils.CommonConstants;
import com.zzrenfeng.zznueg.utils.Series;

/**
 * @功能描述：学生平台部分Service接口实现类
 * @创  建  者：zhoujincheng
 * @版        本：V1.0.0
 * @创建日期：2017年8月9日 下午6:02:36
 * 
 * @修  改  人：
 * @修改日期：
 * @修改描述：
 *
 */
@Transactional
@Service("studentPlatformService")
public class StudentPlatformServiceImpl implements StudentPlatformService {
	private final Logger LOGGER = LoggerFactory.getLogger(StudentPlatformServiceImpl.class);
	
	@Autowired
	protected StuUploadInfoMapper stuUploadInfoMapper;
	@Autowired
	protected StudentPlatformStatMapper	studentPlatformStatMapper;
	@Autowired
	protected OnlineEvalStuentEvalRecordInfoMapper onlineEvalStuentEvalRecordInfoMapper;
	
	/**
	 * @功能描述：上传文件成功后处理信息(传入Map对象)
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月9日 下午6:25:50
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param request
	 * @param file
	 * @param result
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean persistenceStuUploadInfo(Map paramMap) throws Exception {
		// 待持久化处理！！！
		return false;
	}
	
	/**
	 * @功能描述：上传文件成功后处理信息(传入StuUploadInfo实体对象)
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月10日 下午6:22:30
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param stuUploadInfo
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean persistenceStuUploadInfo(StuUploadInfo stuUploadInfo) throws Exception {
		String userId = Constants.getCurrendUser().getUserId();
		int saveOrUpdateCount = 0;
		
		if(StringUtil.isEmpty(stuUploadInfo.getPid())) {  //新增上传信息
			BaseDomain.createLog(stuUploadInfo, userId);
			stuUploadInfo.setPid(UUIDUtils.getUpperUUID());
			stuUploadInfo.setStuUserId(userId);
			stuUploadInfo.setIsUploadSucess(CommonConstants.UPLOAD_FILE_SUCESS);
			stuUploadInfo.setUploadTime(new Date());
			
			saveOrUpdateCount = stuUploadInfoMapper.insert(stuUploadInfo);
		} else {  //修改上传信息
			stuUploadInfo.setUploadTime(new Date());
			BaseDomain.editLog(stuUploadInfo, userId);			
			saveOrUpdateCount = stuUploadInfoMapper.updateByPrimaryKeySelective(stuUploadInfo);
		}
		if(CommonConstants.COMM_CONST_INTEGER_1 == saveOrUpdateCount) {
			return true;
		}
		return false;
	}
	
	/**
	 * @功能描述：获取某个学生某个科目已经成功上传的次数（根据学生ID和科目ID联合查询）
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月9日 下午7:23:31
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
	public Integer getUploadedCountByStuIdAndSubId(Map paramMap) throws Exception {
		Integer uploadedCount;
		uploadedCount = stuUploadInfoMapper.getUploadedCountByStuIdAndSubId(paramMap);
		if(uploadedCount == null || uploadedCount == 0) {
			uploadedCount = 0;
		}	
		return uploadedCount;
	}

	/**
	 * @功能描述：分页查询所有上传科目信息(该方法适合后台管理综合查询)
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月11日 下午2:09:43
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param pageUtil
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map> findAllUploadInfoByPage(PageUtil pageUtil) throws Exception {
		List<Map> resultList = new ArrayList<>();
		List<Map> tmpList = new ArrayList<>();
		tmpList = this.stuUploadInfoMapper.findAllByPage(pageUtil);
		//修正几个数据的类型，以供前端显示使用
		for(Map result : tmpList) {
			String uploadPath = (String) result.get("uploadPath");
			String fileName = uploadPath.substring(uploadPath.indexOf("_")+1, uploadPath.length());
			result.put("fileName", fileName);
			
			resultList.add(result);
		}
//System.out.println(resultList.toString());		
		return resultList;
	}

	/**
	 * @功能描述：根据科目ID分别查询各个科目的上传信息
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月11日 下午2:11:44
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：修改参数类型为Map
	 * 
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map> getUploadInfoBySubId(Map paramMap) throws Exception {
		List<Map> resultList = new ArrayList<>();
		List<Map> tmpList = new ArrayList<>();
		tmpList = this.stuUploadInfoMapper.getUploadInfoBySubId(paramMap);
		//修正几个数据的类型，以供前端显示使用
		for(Map result : tmpList) {
			String uploadPath = (String) result.get("uploadPath");
			String fileName = uploadPath.substring(uploadPath.indexOf("_")+1, uploadPath.length());
			result.put("fileName", fileName);
			
			resultList.add(result);
		}
		return resultList;
	}

	/**
	 * @功能描述：获取有效记录总条数，以便用于分页和前端展示使用
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月11日 下午2:24:30
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
	public Long getAllCount(Map<String, Object> paramMap) throws Exception {
		return this.stuUploadInfoMapper.getCount(paramMap);
	}

	/**
	 * @功能描述：根据学生ID查询每个学生各自上传的信息
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月16日 下午7:09:40
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
	public List<Map> findAllByPageByStuUid(Map paramMap) throws Exception {
		List<Map> resultList = new ArrayList<>();
		List<Map> tmpList = new ArrayList<>();
		tmpList = this.stuUploadInfoMapper.findAllByPageByStuUid(paramMap);
		//修正几个数据的类型，以供前端显示使用
		for(Map result : tmpList) {
			String uploadPath = (String) result.get("uploadPath");
			String fileName = uploadPath.substring(uploadPath.indexOf("_")+1, uploadPath.length());
			result.put("fileName", fileName);
			
			resultList.add(result);
		}
		return resultList;
	}

	/**
	 * @功能描述：根据学生ID获取有效记录总数
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月16日 下午7:32:48
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
	public Long getCountByStuUid(Map<String, Object> paramMap) throws Exception {
		return this.stuUploadInfoMapper.getCountByStuUid(paramMap);
	}

	/**
	 * @功能描述：学生平台——根据学生ID获取该学生的基本信息
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月22日 上午11:42:57
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
	public Map getStuInfoBySuid(Map paramMap) throws Exception {
		return this.stuUploadInfoMapper.getStuInfoBySuid(paramMap);
	}

	/**
	 * @功能描述：根据学生ID获取该学生的成绩信息，调用学生平台自己的DAO的getUploadListBySuid4Stu方法
	 * 			学生平台——学生关心的是每次上传的科目是否得到最好的成绩，根据最近每科最高成绩定位，即取每科分数最高的
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月22日 下午3:42:25
	 * 
	 * @修  改  人：zjc
	 * @修改日期：20170824 09:46
	 * @修改描述：修改注释：不再使用“使用教师平台DAO的getUploadListBySuid4Teach方法”，学生平台另立新方法；
	 * 			修改调用方法：调用getUploadListBySuid4Stu(paramMap)方法；
	 * 			特别说明：DAO中与教师平台的getUploadListBySuid4Teach(Map paramMap)方法分离，不再合并使用，因为两者关系的内容不同
	 * 
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map> getStudentScoresBySuid(Map paramMap) throws Exception {
		//return this.stuUploadInfoMapper.getUploadListBySuid4Teach(paramMap);  //不再使用教师平台的方法，教师与学生关系的内容不同，使用学生平台自己的新方法
		return this.stuUploadInfoMapper.getUploadListBySuid4Stu(paramMap);
	}

	/**
	 * @功能描述：获取某个学生的总成绩及总成绩在全校中的排名
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月24日 下午5:12:47
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
	public Map getStuTotalScoreAndRankBySuid(Map paramMap) throws Exception {
		return this.studentPlatformStatMapper.getStuTotalScoreAndRankBySuid(paramMap);
	}

	/**
	 * @功能描述：根据学生ID和科目ID获取该学生该科目的最高成绩及该成绩在全校中的单科的排名
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月24日 下午5:12:50
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
	public Map getSubScoreAndRankBySuidAndSid(Map paramMap) throws Exception {
		return this.studentPlatformStatMapper.getSubScoreAndRankBySuidAndSid(paramMap);
	}
	
	/**
	 * @功能描述：通过学生ID获取该学生各科最高成绩及排名的List集合，调用getSubScoreAndRankBySuidAndSid()方法，整合数据
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月24日 下午5:34:10
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
	public List<Map> getAllSubScoresAndRanksBySuid(Map paramMap) throws Exception {
		Map reqMap = new HashMap<>();
		Map rstMap = new HashMap<>();
		List<Map> rtnList = new ArrayList<>();
		
		String stuUserId = (String)paramMap.get("stuUserId");
		String subjectId = "";
		//获取微格分数及排名
		subjectId = CommonConstants.SUBJECT_ID_WEIGE;
		reqMap.put("stuUserId", stuUserId);
		reqMap.put("subjectId", subjectId);
		rstMap = getSubScoreAndRankBySuidAndSid(reqMap);
		rtnList.add(rstMap);
		//获取板书分数及排名
		reqMap.clear();
		subjectId = CommonConstants.SUBJECT_ID_BANSHU;
		reqMap.put("stuUserId", stuUserId);
		reqMap.put("subjectId", subjectId);
		rstMap = getSubScoreAndRankBySuidAndSid(reqMap);
		rtnList.add(rstMap);
		//获取教案分数及排名
		reqMap.clear();
		subjectId = CommonConstants.SUBJECT_ID_JIAOAN;
		reqMap.put("stuUserId", stuUserId);
		reqMap.put("subjectId", subjectId);
		rstMap = getSubScoreAndRankBySuidAndSid(reqMap);
		rtnList.add(rstMap);
		//获取课件分数及排名
		reqMap.clear();
		subjectId = CommonConstants.SUBJECT_ID_KEJIAN;
		reqMap.put("stuUserId", stuUserId);
		reqMap.put("subjectId", subjectId);
		rstMap = getSubScoreAndRankBySuidAndSid(reqMap);
		rtnList.add(rstMap);
		
		return rtnList;
	}

	/**
	 * @功能描述：根据学生ID查询该学生每次总成绩的统计
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月25日 上午11:05:37
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
	public List<Map> getSumScoresByCountBySuid(Map paramMap) throws Exception {
		return this.studentPlatformStatMapper.getSumScoresByCountBySuid(paramMap);
	}

	/**
	 * @功能描述：根据学生ID和上传次数查询每次上传科目的成绩记录
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月25日 上午11:19:30
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
	public List<Map> getSubScoresBySuidAndUpcnt(Map paramMap) throws Exception {
		return this.studentPlatformStatMapper.getSubScoresBySuidAndUpcnt(paramMap);
	}
	
	/**
	 * @功能描述：根据科目ID查询该科目的总成绩，总考试人数，平均成绩
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月26日 下午2:21:30
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
		return this.studentPlatformStatMapper.getAvgSubScoreBySid(paramMap);
	}

	/**
	 * @功能描述：根据学生ID获取该学生每次上传的科目得到最好的成绩，根据最近每科最高成绩定位，即取每科分数最高的
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月25日 下午2:35:50
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
	public List<Map> getBestSubScoreBySuid(Map paramMap) throws Exception {
		return this.studentPlatformStatMapper.getBestSubScoreBySuid(paramMap);
	}

	/**
	 * @功能描述：获取提升分数最高学生TOP10排名，全体学生最近两次考试成绩差距由高到低排序
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月28日 上午9:45:53
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
	public List<Map> getStuLiftRangeTop10ByGap(Map paramMap) throws Exception {
		return this.studentPlatformStatMapper.getStuLiftRangeTop10ByGap(paramMap);
	}

	/**
	 * @功能描述：根据登录次数获取活跃度Top10学生
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月28日 上午9:45:20
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
	public List<Map> getStuActivityTop10ByLoginCnt(Map paramMap) throws Exception {
		return this.studentPlatformStatMapper.getStuActivityTop10ByLoginCnt(paramMap);
	}
	
	
	/**
	 * @功能描述：学生平台——数据统计——成绩记录——“我的总成绩记录”，获取总成绩数据并为统计图整合数据
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月25日 下午5:18:28
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
	public Map getSumScores4SEC(Map paramMap) throws Exception {
		Map rtnMapData = new HashMap();		//返回的Map数据集
		List<String> legendList = null;		//图例名称列表
		List xAxisList = null;				//x轴数据列表
		List yAxisList = null;				//y轴数据列表
		List<Series> seriesList = null;		//数据列表
		Series series = null;				//echarts每条线（或其他图形）属性序列对象
		
		List<Map> sumScoresList = new ArrayList<>();
		sumScoresList = this.getSumScoresByCountBySuid(paramMap);
		
		if(null != sumScoresList && sumScoresList.size() > 0) {
			legendList = new ArrayList<String>();	// 图例名称列表
			xAxisList = new ArrayList();			// x轴数据列表
			yAxisList = new ArrayList();			// y轴数据列表
			seriesList = new ArrayList<Series>();	// 数据列表
			int cnt = 0;
			//由于上传只允许4次，这里将 x轴序列固定
			for(Map sumScoreMap : sumScoresList) {
				cnt = (int)sumScoreMap.get("uploadCount");
				xAxisList.add("第" + cnt + "次");
				yAxisList.add(sumScoreMap.get("sumScore"));
			}
			
			if(null != yAxisList && yAxisList.size() > 0) {
				series = new Series();
				series.setName("总成绩");
				series.setType(CommonConstants.STATISTICS_ECHARTS_TYPE_BAR);
				series.setData(yAxisList);
				
				legendList.add(series.getName());
				seriesList.add(series);
			}
			rtnMapData.put("axis", xAxisList);
			rtnMapData.put("legend", legendList);
			rtnMapData.put("seriesList", seriesList);
		} else {
			LOGGER.debug("获取总成绩列表为空");
			//虚拟X轴序列，填充近1次成绩序列
			xAxisList = new ArrayList(); 			// x轴数据列表
			//增加图例
			legendList = new ArrayList();
			legendList.add("总成绩");
			//虚拟空序列
			seriesList = new ArrayList();
			for (int i = 0; i < legendList.size(); i++) {
				Series s = new Series();
				s.setName(legendList.get(i));
				s.setType(CommonConstants.STATISTICS_ECHARTS_TYPE_BAR);
				s.setData(new ArrayList());
				seriesList.add(s);
			}
			rtnMapData.put("axis", xAxisList);
			rtnMapData.put("legend", legendList);
			rtnMapData.put("seriesList", seriesList);
		}
		
		return rtnMapData;
	}	
	
	/**
	 * @功能描述：学生平台——数据统计——成绩记录——“我的单科成绩记录”，获取每次上传的单科成绩数据并为统计图整合数据
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月26日 上午9:54:19
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
	public Map getSubScores4SEC(Map paramMap) throws Exception {
		Map rtnMapData = new HashMap();		//返回结果Map
		List<Map> subScores = null;			//每次上传的科目列表
		List<String> legendList = null;		//图例名称列
		List xAxisList = null;				//x轴数据列表
		List yAxisByWgList = null;			//微格-y轴数据列表
		List yAxisByBsList = null;			//板书-y轴数据列表
		List yAxisByJaList = null;			//教案-y轴数据列表
		List yAxisByKjList = null;			//课件-y轴数据列表
		List<Series> seriesList = null;		//数据列表
		Series series = null;				//echarts每条线（或其他图形）属性序列对象
		
		int maxCount = -1;  //记录该学生最大上传次数
		int count = 0;  //记录当前次数，即当前为第几次
		maxCount = this.studentPlatformStatMapper.getMaxUpCountBySuid(paramMap) == null ?
					0 : this.studentPlatformStatMapper.getMaxUpCountBySuid(paramMap);
		
		if(maxCount > 0) {
			legendList = new ArrayList<String>();	// 图例名称列表
			xAxisList = new ArrayList();			// x轴数据列表
			yAxisByWgList = new ArrayList();		// 微格-y轴数据列表
			yAxisByBsList = new ArrayList();		// 板书-y轴数据列表
			yAxisByJaList = new ArrayList();		// 教案-y轴数据列表
			yAxisByKjList = new ArrayList();		// 课件-y轴数据列表
			seriesList = new ArrayList<Series>();	// 数据列表
			for (int i = 0; i < maxCount; i++) {
				count = i + 1;
				if(paramMap.containsKey("uploadCount")) {
					paramMap.remove("uploadCount");  //每次循环更新请求次数参数
				}
				paramMap.put("uploadCount", count);
				subScores = new ArrayList<>();
				subScores = this.getSubScoresBySuidAndUpcnt(paramMap);
				
				xAxisList.add("第" + count + "次");
				for(Map subSco : subScores) {
					if(((String)subSco.get("subjectId")).equals(CommonConstants.SUBJECT_ID_WEIGE)) {
						yAxisByWgList.add(subSco.get("evalScore"));
					} else if (((String)subSco.get("subjectId")).equals(CommonConstants.SUBJECT_ID_BANSHU)) {
						yAxisByBsList.add(subSco.get("evalScore"));
					} else if (((String)subSco.get("subjectId")).equals(CommonConstants.SUBJECT_ID_JIAOAN)) {
						yAxisByJaList.add(subSco.get("evalScore"));
					} else if (((String)subSco.get("subjectId")).equals(CommonConstants.SUBJECT_ID_KEJIAN)) {
						yAxisByKjList.add(subSco.get("evalScore"));
					}					
				}				
			}
			if(null != yAxisByWgList && !yAxisByWgList.isEmpty()) {
				series = new Series();
				series.setName("微格");
				series.setType(CommonConstants.STATISTICS_ECHARTS_TYPE_BAR);
				series.setData(yAxisByWgList);

				legendList.add(series.getName());
				seriesList.add(series);
			}
			if(null != yAxisByBsList && !yAxisByBsList.isEmpty()) {
				series = new Series();
				series.setName("板书");
				series.setType(CommonConstants.STATISTICS_ECHARTS_TYPE_BAR);
				series.setData(yAxisByBsList);

				legendList.add(series.getName());
				seriesList.add(series);
			}
			if(null != yAxisByJaList && !yAxisByJaList.isEmpty()) {
				series = new Series();
				series.setName("教案");
				series.setType(CommonConstants.STATISTICS_ECHARTS_TYPE_BAR);
				series.setData(yAxisByJaList);

				legendList.add(series.getName());
				seriesList.add(series);
			}
			if(null != yAxisByKjList && !yAxisByKjList.isEmpty()) {
				series = new Series();
				series.setName("课件");
				series.setType(CommonConstants.STATISTICS_ECHARTS_TYPE_BAR);
				series.setData(yAxisByKjList);

				legendList.add(series.getName());
				seriesList.add(series);
			}
			
			rtnMapData.put("axis", xAxisList);
			rtnMapData.put("legend", legendList);
			rtnMapData.put("seriesList", seriesList);
		} else {
			LOGGER.debug("获取上传列表为空");
			xAxisList = new ArrayList();  //虚拟x轴
			//增加图例
			legendList = new ArrayList();
			legendList.add("微格");
			legendList.add("板书");
			legendList.add("教案");
			legendList.add("课件");
			//虚拟空序列
			seriesList = new ArrayList();
			for (int i = 0; i < legendList.size(); i++) {
				Series s = new Series();
				s.setName(legendList.get(i));
				s.setType(CommonConstants.STATISTICS_ECHARTS_TYPE_BAR);
				s.setData(new ArrayList());
				seriesList.add(s);
			}
			rtnMapData.put("axis", xAxisList);
			rtnMapData.put("legend", legendList);
			rtnMapData.put("seriesList", seriesList);
		}
		
		return rtnMapData;
	}
	
	/**
	 * @功能描述：学生平台——数据统计——“我的成绩对比(和平均值)”，获取每科最好成绩和该科的平均值并整合数据
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月26日 下午3:23:54
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
	public Map getSubScoreCompareToAvg4SEC(Map paramMap) throws Exception {
		Map rtnMapData = new HashMap();		//返回结果Map
		Map subScore = null;				//单科成绩信息
		List<String> legendList = new ArrayList<>();//图例名称列
		List xAxisList = new ArrayList<>();	//x轴数据列表
		List yAxisBySubList = new ArrayList<>();	//各科成绩-y轴数据列表
		List yAxisByAvgList = new ArrayList<>();	//平均成绩-y轴数据列表
		List<Series> seriesList = new ArrayList<>();//数据列表
		Series series = null;				//echarts每条线（或其他图形）属性序列对象
		
		if(paramMap.containsKey("subjectId")) {
			paramMap.remove("subjectId");  //科目ID存在则清除，这里重新设定
		}
		paramMap.put("subjectId", CommonConstants.SUBJECT_ID_WEIGE);
		subScore = new HashMap<>();
		subScore = this.getSubScoreAndRankBySuidAndSid(paramMap);
		xAxisList.add("微格");
		if(subScore != null && !subScore.isEmpty()) {
			yAxisBySubList.add(subScore.get("evalScore") == null ? 0 : subScore.get("evalScore"));
			yAxisByAvgList.add(subScore.get("avgSubScore") == null ? 0 : subScore.get("avgSubScore"));
		}		
		
		if(paramMap.containsKey("subjectId")) {
			paramMap.remove("subjectId");  //科目ID存在则清除，这里重新设定
		}
		paramMap.put("subjectId", CommonConstants.SUBJECT_ID_BANSHU);
		subScore = new HashMap<>();
		subScore = this.getSubScoreAndRankBySuidAndSid(paramMap);
		xAxisList.add("板书");
		if(subScore != null && !subScore.isEmpty()) {
			yAxisBySubList.add(subScore.get("evalScore") == null ? 0 : subScore.get("evalScore"));
			yAxisByAvgList.add(subScore.get("avgSubScore") == null ? 0 : subScore.get("avgSubScore"));
		}
		
		if(paramMap.containsKey("subjectId")) {
			paramMap.remove("subjectId");  //科目ID存在则清除，这里重新设定
		}
		paramMap.put("subjectId", CommonConstants.SUBJECT_ID_JIAOAN);
		subScore = new HashMap<>();
		subScore = this.getSubScoreAndRankBySuidAndSid(paramMap);
		xAxisList.add("教案");
		if(subScore != null && !subScore.isEmpty()) {
			yAxisBySubList.add(subScore.get("evalScore") == null ? 0 : subScore.get("evalScore"));
			yAxisByAvgList.add(subScore.get("avgSubScore") == null ? 0 : subScore.get("avgSubScore"));
		}
		
		if(paramMap.containsKey("subjectId")) {
			paramMap.remove("subjectId");  //科目ID存在则清除，这里重新设定
		}
		paramMap.put("subjectId", CommonConstants.SUBJECT_ID_KEJIAN);
		subScore = new HashMap<>();
		subScore = this.getSubScoreAndRankBySuidAndSid(paramMap);
		xAxisList.add("课件");
		if(subScore != null && !subScore.isEmpty()) {
			yAxisBySubList.add(subScore.get("evalScore") == null ? 0 : subScore.get("evalScore"));
			yAxisByAvgList.add(subScore.get("avgSubScore") == null ? 0 : subScore.get("avgSubScore"));
		}
		
		if(null != yAxisBySubList && !yAxisBySubList.isEmpty()) {
			series = new Series();
			series.setName("我的成绩");
			series.setType(CommonConstants.STATISTICS_ECHARTS_TYPE_BAR);
			series.setData(yAxisBySubList);

			legendList.add(series.getName());
			seriesList.add(series);
		}
		if(null != yAxisByAvgList && !yAxisByAvgList.isEmpty()) {
			series = new Series();
			series.setName("平均成绩");
			series.setType(CommonConstants.STATISTICS_ECHARTS_TYPE_BAR);
			series.setData(yAxisByAvgList);

			legendList.add(series.getName());
			seriesList.add(series);
		}
		
		rtnMapData.put("axis", xAxisList);
		rtnMapData.put("legend", legendList);
		rtnMapData.put("seriesList", seriesList);
		
		return rtnMapData;
	}
	
	/**
	 * @功能描述：学生平台——数据统计——“成绩雷达图”，获取每科最好成绩和该科的标准成绩(通过线)并整合数据
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月26日 下午4:54:35
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
	public Map getScoresRadar4SEC(Map paramMap) throws Exception {
		Map rtnMapData = new HashMap();		//返回结果Map
		List<Series> seriesList = new ArrayList<Series>();
		List<String> legendList = new ArrayList<String>();
		List radarSeriesDataList = new ArrayList();	//雷达图Series Data List
		Map radarSeriesDataMap = null;		//雷达图Series Data Map
		List seriesDataValueList = null;		//用户存储雷达图上序列的value值，每个序列创建一个List对象
		List radarIndicatorDataList = new ArrayList();	//雷达图维度指标器（indicator） Data List
		Map radarIndicatorDataMap = null;	//雷达图维度指标器（indicator） Data Map
		Series series = null;
		List<Map> bestScoresList = new ArrayList<>();	//每科最好成绩列表
			
		//获取每科最好成绩列表
		bestScoresList = this.getBestSubScoreBySuid(paramMap);
		
		/*
		 * 创建没课标准成绩（即每科及格线）series序列中的data序列
		 */
		radarSeriesDataMap = new HashMap();
		seriesDataValueList = new ArrayList();		//用户存储雷达图上序列的value值，每个序列创建一个List对象
				
		for (int i = 0; i < bestScoresList.size(); i++) {
			seriesDataValueList.add(CommonConstants.SUBJECT_STANDARD_SCORE);
		}
		radarSeriesDataMap.put("value", seriesDataValueList);
		radarSeriesDataMap.put("name", "标准成绩");
		radarSeriesDataList.add(radarSeriesDataMap);
		legendList.add("标准成绩");
		/*
		 * 根据获取每科最好成绩列表组装实际成绩series序列中的data序列
		 */
		if(null != bestScoresList && bestScoresList.size() > 0) {
			radarSeriesDataMap = new HashMap();
			seriesDataValueList = new ArrayList();		//用户存储雷达图上序列的value值，每个序列创建一个List对象
			
			for (Map bestScore : bestScoresList) {
				seriesDataValueList.add(bestScore.get("evalScore"));
			}
			radarSeriesDataMap.put("value", seriesDataValueList);
			radarSeriesDataMap.put("name", "实际成绩");
			radarSeriesDataList.add(radarSeriesDataMap);
			legendList.add("实际成绩");
		} else {
			//为空处理
		}
		
		/*
		 * 封装series数据
		 */
		series = new Series();
		series.setName("实际成绩VS标准成绩");
		series.setType(CommonConstants.STATISTICS_ECHARTS_TYPE_RADAR);
		series.setData(radarSeriesDataList);	
		seriesList.add(series);
		
		//组装维度指标及维度最大值限制
		radarIndicatorDataMap = new HashMap();	//雷达图维度指标器（indicator） Data Map（每个维度指标器创建一个新对象）					
		radarIndicatorDataMap.put("name", "微格");
		radarIndicatorDataMap.put("max", CommonConstants.SUBJECT_MAX_SCORE);	//本维度指标上限阈值
		radarIndicatorDataList.add(radarIndicatorDataMap);
		radarIndicatorDataMap = new HashMap();	//雷达图维度指标器（indicator） Data Map（每个维度指标器创建一个新对象）					
		radarIndicatorDataMap.put("name", "板书");
		radarIndicatorDataMap.put("max", CommonConstants.SUBJECT_MAX_SCORE);	//本维度指标上限阈值
		radarIndicatorDataList.add(radarIndicatorDataMap);
		radarIndicatorDataMap = new HashMap();	//雷达图维度指标器（indicator） Data Map（每个维度指标器创建一个新对象）					
		radarIndicatorDataMap.put("name", "教案");
		radarIndicatorDataMap.put("max", CommonConstants.SUBJECT_MAX_SCORE);	//本维度指标上限阈值
		radarIndicatorDataList.add(radarIndicatorDataMap);
		radarIndicatorDataMap = new HashMap();	//雷达图维度指标器（indicator） Data Map（每个维度指标器创建一个新对象）					
		radarIndicatorDataMap.put("name", "课件");
		radarIndicatorDataMap.put("max", CommonConstants.SUBJECT_MAX_SCORE);	//本维度指标上限阈值
		radarIndicatorDataList.add(radarIndicatorDataMap);
		
		//组装返回结果数据
		rtnMapData.put("legend", legendList);
		rtnMapData.put("indicatorData", radarIndicatorDataList);		
		rtnMapData.put("seriesList", seriesList);
		
		return rtnMapData;
	}
	
	/**
	 * @功能描述：学生平台——数据统计——“TOP10学生排行图”——提升分数最高的学生，根据全体学生最近两次考试成绩的差额判断提升分数最高学生，获取TOP10排名并整合数据
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月28日 上午9:50:05
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
	public Map getStuTop10ByEnhance4SEC(Map paramMap) throws Exception {
		Map rtnMapData = new HashMap();		//返回结果Map
		//***********待补充************
		return rtnMapData;
	}
	
	/**
	 * @功能描述：学生平台——数据统计——“TOP10学生排行图”——活跃度最高的用户TOP10，通过登录次数获取活跃度最高的用户TOP10并整合数据
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月28日 上午9:50:01
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
	public Map getStuTop10ByActivity4SEC(Map paramMap) throws Exception {
		Map rtnMapData = new HashMap();		//返回结果Map
		
		List<String> legendList = null;	//图例名称列表
		List xAxisList = null;			//x轴数据列表
		List yAxisList = null;			//y轴数据列表
		List<Series> seriesList = null;	//数据列表
		Series series = null;			//echarts每条线（或其他图形）属性序列对象
		
		List<Map> stuActiList = new ArrayList<>();
		
		stuActiList = this.getStuActivityTop10ByLoginCnt(paramMap);
		
		if(null != stuActiList && stuActiList.size() > 0) {
			legendList = new ArrayList<String>();
			xAxisList = new ArrayList();
			yAxisList = new ArrayList();			
			
			for (Map stuActi : stuActiList) {
				xAxisList.add(stuActi.get("stuName"));
				yAxisList.add(stuActi.get("loginCount"));
			}
			
			if(null != yAxisList && yAxisList.size() > 0) {
				seriesList = new ArrayList<>();
				series = new Series();
				series.setName("登录次数");
				series.setType(CommonConstants.STATISTICS_ECHARTS_TYPE_LINE);
				series.setData(yAxisList);

				legendList.add(series.getName());
				seriesList.add(series);
			}
			
			rtnMapData.put("axis", xAxisList);
			rtnMapData.put("legend", legendList);
			rtnMapData.put("seriesList", seriesList);
		} else {
			LOGGER.debug("获取登录次数信息为空");
			//虚拟X轴序列
			xAxisList = new ArrayList(); 
			legendList = new ArrayList();
			legendList.add("登录次数");
			//虚拟空序列
			seriesList = new ArrayList();
			for (int i = 0; i < legendList.size(); i++) {
				series = new Series();
				series.setName(legendList.get(i));
				series.setType(CommonConstants.STATISTICS_ECHARTS_TYPE_LINE);
				series.setData(new ArrayList());
				seriesList.add(series);
			}
			rtnMapData.put("axis", xAxisList);
			rtnMapData.put("legend", legendList);
			rtnMapData.put("seriesList", seriesList);
		}
		
		return rtnMapData;
	}

	/**
	 * @功能描述：获取有效的在线测评问卷内容信息
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月11日 下午2:37:34
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
	public List<Map> getOnlineEvalPaperInfo(Map paramMap) throws Exception {
		return this.studentPlatformStatMapper.getOnlineEvalPaperInfo(paramMap);
	}

	/**
	 * @功能描述：获取一个有效的问卷信息
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月11日 下午2:57:24
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
	public Map getOneEnablePaperInfo(Map paramMap) throws Exception {
		return this.studentPlatformStatMapper.getOneEnablePaperInfo(paramMap);
	}
	
	/**
	 * @功能描述：保存学生答题记录信息List
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月12日 下午4:32:26
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
	@Transactional(rollbackFor=Exception.class)
	public boolean saveStuEvalRecordInfoList(Map paramMap) throws Exception {
		int saveOrUpdateCount = 0;    //新增或修改记录数
		String stuUserId = String.valueOf(paramMap.get("stuUserId"));
		List<OnlineEvalStuentEvalRecordInfo> evalRecordList = new ArrayList<>();		
		evalRecordList = (List) paramMap.get("evalRecordList");
		
		for (OnlineEvalStuentEvalRecordInfo evalRecord : evalRecordList) {
			if(StringUtil.isEmpty(evalRecord.getPid())) {  //新增
				BaseDomain.createLog(evalRecord, stuUserId);
				evalRecord.setPid(UUIDUtils.getUpperUUID());
				
				saveOrUpdateCount += this.onlineEvalStuentEvalRecordInfoMapper.insert(evalRecord);
			} else {  //修改，这里不使用
				BaseDomain.editLog(evalRecord, stuUserId);
				saveOrUpdateCount += onlineEvalStuentEvalRecordInfoMapper.updateByPrimaryKeySelective(evalRecord);
			}
		}
		//新增或修改的记录数和列表记录数相同则为全成功
		if(saveOrUpdateCount == evalRecordList.size()) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * @功能描述：获取某个学生参与答题的记录数，用于判断该学生是否参与过在线测评
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月12日 下午5:20:19
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
	public boolean isTakePartInOnlineEval(Map paramMap) throws Exception {
		Integer count = 0;
		count = this.onlineEvalStuentEvalRecordInfoMapper.getStuEvalRecordCount(paramMap);
		if(count != null && count != 0) {
			return true;
		}
		return false;
	}

	/**
	 * @功能描述：根据学生ID和试卷ID获取某学生所答某一试卷不同类别题目分数汇总，以及该类别题目的最高分数汇总
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月13日 上午9:40:17
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
	public List<Map> getCategorySumScore(Map paramMap) throws Exception {
		return this.onlineEvalStuentEvalRecordInfoMapper.getCategorySumScore(paramMap);
	}
	
	/**
	 * @功能描述：获取按问题分类汇总的学生得分和最高分，并为数据统计表准备数据，在原始数据基础上增加问题分类中文名称
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月13日 下午4:39:32
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
	public List<Map> getCategorySumScore4DST(Map paramMap) throws Exception {
		List<Map> cateSumScoreList = new ArrayList<>();
		List<Map> rtnList = new ArrayList<>();
		List<DictionaryInfo> questionCategoryList= new ArrayList<>();
		
		cateSumScoreList = this.getCategorySumScore(paramMap);
		questionCategoryList = SystemBuffer.getDictionaryByType("questionCategory");
		
		for (Map cateSumScoreMap : cateSumScoreList) {
			String questionCategory = String.valueOf(cateSumScoreMap.get("questionCategory"));
			for (DictionaryInfo qcDict : questionCategoryList) {
				if(qcDict.getKey().equals(questionCategory)) {
					cateSumScoreMap.put("questionCategoryName", qcDict.getValue());
				}
			}
			if(!cateSumScoreMap.containsKey("questionCategoryName")) {  //字典中不存在该分类，就将分类中文名置为空字符串
				cateSumScoreMap.put("questionCategoryName", CommonConstants.COMM_CONST_STRING_EMPTY);
			}			
			rtnList.add(cateSumScoreMap);
			
/*			此种方法太冗余，不通用，舍弃
			if(questionCategory.equals(DictionaryEnum.QUESTION_CATEGORY_A00.getKey())) {
				cateSumScoreMap.put("questionCategoryName", DictionaryEnum.QUESTION_CATEGORY_A00.getValue());
				rtnList.add(cateSumScoreMap);
			} else if(questionCategory.equals(DictionaryEnum.QUESTION_CATEGORY_B00.getKey())) {
				cateSumScoreMap.put("questionCategoryName", DictionaryEnum.QUESTION_CATEGORY_B00.getValue());
				rtnList.add(cateSumScoreMap);
			} else if(questionCategory.equals(DictionaryEnum.QUESTION_CATEGORY_C00.getKey())) {
				cateSumScoreMap.put("questionCategoryName", DictionaryEnum.QUESTION_CATEGORY_C00.getValue());
				rtnList.add(cateSumScoreMap);
			} else if(questionCategory.equals(DictionaryEnum.QUESTION_CATEGORY_D00.getKey())) {
				cateSumScoreMap.put("questionCategoryName", DictionaryEnum.QUESTION_CATEGORY_D00.getValue());
				rtnList.add(cateSumScoreMap);
			} else if(questionCategory.equals(DictionaryEnum.QUESTION_CATEGORY_E00.getKey())) {
				cateSumScoreMap.put("questionCategoryName", DictionaryEnum.QUESTION_CATEGORY_E00.getValue());
				rtnList.add(cateSumScoreMap);
			} else if(questionCategory.equals(DictionaryEnum.QUESTION_CATEGORY_F00.getKey())) {
				cateSumScoreMap.put("questionCategoryName", DictionaryEnum.QUESTION_CATEGORY_F00.getValue());
				rtnList.add(cateSumScoreMap);
			} else if(questionCategory.equals(DictionaryEnum.QUESTION_CATEGORY_G00.getKey())) {
				cateSumScoreMap.put("questionCategoryName", DictionaryEnum.QUESTION_CATEGORY_G00.getValue());
				rtnList.add(cateSumScoreMap);
			} else if(questionCategory.equals(DictionaryEnum.QUESTION_CATEGORY_H00.getKey())) {
				cateSumScoreMap.put("questionCategoryName", DictionaryEnum.QUESTION_CATEGORY_H00.getValue());
				rtnList.add(cateSumScoreMap);
			} else {
				cateSumScoreMap.put("questionCategoryName", CommonConstants.COMM_CONST_STRING_EMPTY);
				rtnList.add(cateSumScoreMap);
			}*/
		}
		return rtnList;
	}
	
	/**
	 * @功能描述：获取按问题分类汇总的学生得分和最高分，并为统计图（雷达图、柱状图、折线图、条形图（堆叠柱状图））准备数据
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月14日 上午9:59:43
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
	public Map getCategorySumScore4SEC(Map paramMap) throws Exception {
		Map rtnMap = new HashMap<>();
		List<Map> cateSumScoreList = new ArrayList<>();
		cateSumScoreList = this.getCategorySumScore4DST(paramMap);
		
		rtnMap.put("echartsRadar", getCategorySumScore4Radar(cateSumScoreList));
		rtnMap.put("echartsBar", getCategorySumScore4BarOrLine(cateSumScoreList, CommonConstants.STATISTICS_ECHARTS_TYPE_BAR));
		rtnMap.put("echartsLine", getCategorySumScore4BarOrLine(cateSumScoreList, CommonConstants.STATISTICS_ECHARTS_TYPE_LINE));
		rtnMap.put("echartsStackBar", getCategorySumScore4BarOrLine(cateSumScoreList, CommonConstants.STATISTICS_ECHARTS_TYPE_BAR));
		
		return rtnMap;
	}
	
	/**
	 * @功能描述：获取问题分类汇总得分的雷达图
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月14日 上午10:12:10
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param cateSumScoreList
	 * @return
	 * @throws Exception
	 */
	private Map getCategorySumScore4Radar(List<Map> cateSumScoreList) throws Exception {
		Map rtnRadarMap = new HashMap<>();
		
		List<Series> seriesList = new ArrayList<Series>();
		List<String> legendList = new ArrayList<String>();
		List radarSeriesDataList = new ArrayList();	//雷达图Series Data List
		Map radarSeriesDataMap = null;		//雷达图Series Data Map
		List seriesDataStuScoreValueList = null;		//学生每类问题得分列表，用户存储雷达图上序列的value值，每个序列创建一个List对象
		List seriesDataMaxScoreValueList = null;		//每类问题最高分列表
		List radarIndicatorDataList = new ArrayList();	//雷达图维度指标器（indicator） Data List
		Map radarIndicatorDataMap = null;	//雷达图维度指标器（indicator） Data Map
		Series series = null;
		List<Map> maxScoreList = new ArrayList<>();	//每个维度最大成绩列表
		
		if(null != cateSumScoreList && !cateSumScoreList.isEmpty()) {
			seriesDataStuScoreValueList = new ArrayList();
			seriesDataMaxScoreValueList = new ArrayList();
			for(Map cateSumScore : cateSumScoreList) {
				radarIndicatorDataMap = new HashMap<>();
				radarIndicatorDataMap.put("name", cateSumScore.get("questionCategoryName"));
				radarIndicatorDataMap.put("max", cateSumScore.get("cateMaxTotalScore") == null ? 0 : cateSumScore.get("cateMaxTotalScore"));
				radarIndicatorDataList.add(radarIndicatorDataMap);
				
				seriesDataStuScoreValueList.add(cateSumScore.get("cateStuTotalScore") == null ? 0 : cateSumScore.get("cateStuTotalScore"));
				seriesDataMaxScoreValueList.add(cateSumScore.get("cateMaxTotalScore") == null ? 0 : cateSumScore.get("cateMaxTotalScore"));
			}
			radarSeriesDataMap = new HashMap();
			radarSeriesDataMap.put("value", seriesDataStuScoreValueList);
			radarSeriesDataMap.put("name", "得分");
			radarSeriesDataList.add(radarSeriesDataMap);
			radarSeriesDataMap = new HashMap();
			radarSeriesDataMap.put("value", seriesDataMaxScoreValueList);
			radarSeriesDataMap.put("name", "满分");
			radarSeriesDataList.add(radarSeriesDataMap);
			
			legendList.add("得分");
			legendList.add("满分");
			
			series = new Series();
			series.setName("得分VS满分");
			series.setType(CommonConstants.STATISTICS_ECHARTS_TYPE_RADAR);
			series.setData(radarSeriesDataList);
			seriesList.add(series);
		} else {
			//数据为空处理
		}
		
		//组装返回结果数据
		rtnRadarMap.put("legend", legendList);
		rtnRadarMap.put("indicatorData", radarIndicatorDataList);		
		rtnRadarMap.put("seriesList", seriesList);
		
		return rtnRadarMap;
	}
	
	/**
	 * @功能描述：获取问题分类汇总得分的柱状图（包括堆叠柱状图）或折线图
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月14日 上午11:19:25
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param cateSumScoreList
	 * @param echartsType
	 * @return
	 * @throws Exception
	 */
	private Map getCategorySumScore4BarOrLine(List<Map> cateSumScoreList, String echartsType) throws Exception {
		Map rtnMap = new HashMap<>();
		List<String> legendList = null;		//图例名称列表
		List xAxisList = null;				//x轴数据列表
		List yAxisStuScoreList = null;		//学生问题分类得分-y轴数据列表
		List yAxisMaxScoreList = null;		//问题分类最高得分-y轴数据列表
		List<Series> seriesList = null;		//数据列表
		Series series = null;				//echarts每条线（或其他图形）属性序列对象
		
		if(null != cateSumScoreList && !cateSumScoreList.isEmpty()) {
			legendList = new ArrayList<String>();	// 图例名称列表
			xAxisList = new ArrayList();			// x轴数据列表
			yAxisStuScoreList = new ArrayList();	// 学生问题分类得分-y轴数据列表
			yAxisMaxScoreList = new ArrayList();	//问题分类最高得分-y轴数据列表
			seriesList = new ArrayList<Series>();	// 数据列表
			
			for(Map cateSumScore : cateSumScoreList) {
				xAxisList.add(cateSumScore.get("questionCategoryName"));
				yAxisStuScoreList.add(cateSumScore.get("cateStuTotalScore") == null ? 0 : cateSumScore.get("cateStuTotalScore"));
				yAxisMaxScoreList.add(cateSumScore.get("cateMaxTotalScore") == null ? 0 : cateSumScore.get("cateMaxTotalScore"));
			}
			if(null != yAxisStuScoreList && !yAxisStuScoreList.isEmpty()) {
				series = new Series();
				series.setName("得分");
				if(echartsType.equals(CommonConstants.STATISTICS_ECHARTS_TYPE_BAR)) {
					series.setType(CommonConstants.STATISTICS_ECHARTS_TYPE_BAR);
				} else if(echartsType.equals(CommonConstants.STATISTICS_ECHARTS_TYPE_LINE)) {
					series.setType(CommonConstants.STATISTICS_ECHARTS_TYPE_LINE);
				}				
				series.setData(yAxisStuScoreList);				
				legendList.add(series.getName());
				seriesList.add(series);
			}
			if(null != yAxisMaxScoreList && !yAxisMaxScoreList.isEmpty()) {
				series = new Series();
				series.setName("满分");
				if(echartsType.equals(CommonConstants.STATISTICS_ECHARTS_TYPE_BAR)) {
					series.setType(CommonConstants.STATISTICS_ECHARTS_TYPE_BAR);
				} else if(echartsType.equals(CommonConstants.STATISTICS_ECHARTS_TYPE_LINE)) {
					series.setType(CommonConstants.STATISTICS_ECHARTS_TYPE_LINE);
				}				
				series.setData(yAxisMaxScoreList);				
				legendList.add(series.getName());
				seriesList.add(series);
			}
		} else {
			LOGGER.debug("得分信息为空");
			//虚拟X轴序列
			xAxisList = new ArrayList(); 			// x轴数据列表
			//增加图例
			legendList = new ArrayList();
			legendList.add("得分");
			legendList.add("满分");
			//虚拟空序列
			seriesList = new ArrayList();
			for (int i = 0; i < legendList.size(); i++) {
				series = new Series();
				series.setName(legendList.get(i));
				if(echartsType.equals(CommonConstants.STATISTICS_ECHARTS_TYPE_BAR)) {
					series.setType(CommonConstants.STATISTICS_ECHARTS_TYPE_BAR);
				} else if(echartsType.equals(CommonConstants.STATISTICS_ECHARTS_TYPE_LINE)) {
					series.setType(CommonConstants.STATISTICS_ECHARTS_TYPE_LINE);
				}
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


