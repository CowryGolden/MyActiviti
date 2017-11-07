package com.zzrenfeng.zznueg.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zzrenfeng.base.utils.Constants;
import com.zzrenfeng.base.utils.StringUtil;
import com.zzrenfeng.zznueg.dao.LeaderPlatformStatMapper;
import com.zzrenfeng.zznueg.service.LeaderPlatformService;
import com.zzrenfeng.zznueg.utils.CommonConstants;
import com.zzrenfeng.zznueg.utils.Series;

/**
 * @功能描述：领导平台Service接口实现类
 * @创  建  者：zhoujincheng
 * @版        本：V1.0.0
 * @创建日期：2017年8月30日 下午4:01:03
 * 
 * @修  改  人：
 * @修改日期：
 * @修改描述：
 *
 */
@Transactional
@Service("leaderPlatformService")
public class LeaderPlatformServiceImpl implements LeaderPlatformService {
	
	private final Logger LOGGER = LoggerFactory.getLogger(LeaderPlatformServiceImpl.class);

	@Autowired
	protected LeaderPlatformStatMapper leaderPlatformStatMapper;
	
	/**
	 * @功能描述：分页获取全校所有学生基本信息及成绩信息，还可以根据学生学号进行检索信息
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月30日 下午3:56:38
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
	public List<Map> getAllStusInfoAndSubScoresByPage(Map paramMap) throws Exception {
		return this.leaderPlatformStatMapper.getAllStusInfoAndSubScoresByPage(paramMap);
	}

	/**
	 * @功能描述：获取有效学生总数，以供分页使用
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月30日 下午3:57:26
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
	public Long getTotalStusCount(Map paramMap) throws Exception {		
		return this.leaderPlatformStatMapper.getTotalStusCount(paramMap);
	}

	/**
	 * @功能描述：领导平台——数据统计——平均成绩对比——各科平均与单科最高成绩对比图，获取各科平均与单科最高成绩并整合数据
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月1日 上午10:36:17
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
	public Map getAvgSubScoreCompare4SEC(Map paramMap) throws Exception {
		Map rtnMap = new HashMap();
		
		List<String> legendList = new ArrayList<>();//图例名称列
		List xAxisList = new ArrayList<>();	//x轴数据列表
		List yAxisByMaxList = new ArrayList<>();	//各科最高成绩-y轴数据列表
		List yAxisByAvgList = new ArrayList<>();	//各科平均成绩-y轴数据列表
		List<Series> seriesList = new ArrayList<>();//数据列表
		Series series = null;				//echarts每条线（或其他图形）属性序列对象
		
		yAxisByMaxList.add(this.getAvgOrMaxSubScore(paramMap, CommonConstants.SUBJECT_ID_WEIGE, CommonConstants.SCORE_TYPE_MAX));
		yAxisByMaxList.add(this.getAvgOrMaxSubScore(paramMap, CommonConstants.SUBJECT_ID_BANSHU, CommonConstants.SCORE_TYPE_MAX));
		yAxisByMaxList.add(this.getAvgOrMaxSubScore(paramMap, CommonConstants.SUBJECT_ID_JIAOAN, CommonConstants.SCORE_TYPE_MAX));
		yAxisByMaxList.add(this.getAvgOrMaxSubScore(paramMap, CommonConstants.SUBJECT_ID_KEJIAN, CommonConstants.SCORE_TYPE_MAX));
		
		yAxisByAvgList.add(this.getAvgOrMaxSubScore(paramMap, CommonConstants.SUBJECT_ID_WEIGE, CommonConstants.SCORE_TYPE_AVG));
		yAxisByAvgList.add(this.getAvgOrMaxSubScore(paramMap, CommonConstants.SUBJECT_ID_BANSHU, CommonConstants.SCORE_TYPE_AVG));
		yAxisByAvgList.add(this.getAvgOrMaxSubScore(paramMap, CommonConstants.SUBJECT_ID_JIAOAN, CommonConstants.SCORE_TYPE_AVG));
		yAxisByAvgList.add(this.getAvgOrMaxSubScore(paramMap, CommonConstants.SUBJECT_ID_KEJIAN, CommonConstants.SCORE_TYPE_AVG));
		
		if(null != yAxisByMaxList && !yAxisByMaxList.isEmpty()) { 
			xAxisList.add("微格");
			xAxisList.add("板书");
			xAxisList.add("教案");
			xAxisList.add("课件");
			
			series = new Series();
			series.setName("各科最高成绩");
			series.setType(CommonConstants.STATISTICS_ECHARTS_TYPE_BAR);
			series.setData(yAxisByMaxList);

			legendList.add(series.getName());
			seriesList.add(series);
			
			if(null != yAxisByAvgList && !yAxisByAvgList.isEmpty()) { //各科没有最高成绩，也就不存在各科平均成绩，因此放在最高成绩的内层判断
				series = new Series();
				series.setName("各科平均成绩");
				series.setType(CommonConstants.STATISTICS_ECHARTS_TYPE_BAR);
				series.setData(yAxisByAvgList);

				legendList.add(series.getName());
				seriesList.add(series);
			}
		} else {
			LOGGER.debug("获取各科最高成绩为空");
			xAxisList = new ArrayList();  //虚拟x轴
			//增加图例
			legendList = new ArrayList();
			legendList.add("各科最高成绩");
			legendList.add("各科平均成绩");
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
	
	/**
	 * @功能描述：领导平台——数据统计——平均成绩对比——本次与上一次考试学生各科平均成绩对比图，获取本次与上一次考试各科平均成绩并整合数据
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月1日 下午3:26:55
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
	public Map getCurrAndPrevAvgSubScoreCompare4SEC(Map paramMap) throws Exception {
		Map rtnMap = new HashMap();
				
		List xAxisList = null;	//x轴数据列表
		List yAxisByCurrList = null;	//本次考试学生各科平均成绩-y轴数据列表
		List yAxisByPrevList = null;	//上一次考试各科最高成绩-y轴数据列表
		List<String> legendList = new ArrayList<>();//图例名称列
		List<Series> seriesList = new ArrayList<>();//数据列表
		Series series = null;				//echarts每条线（或其他图形）属性序列对象
		
		Integer subMaxUpCount = this.leaderPlatformStatMapper.getMaxUploadCount(paramMap);
		if(subMaxUpCount != null && subMaxUpCount != 0) {
			//初始化x轴序列
			xAxisList = new ArrayList<>();
			xAxisList.add("微格");
			xAxisList.add("板书");
			xAxisList.add("教案");
			xAxisList.add("课件");
			if(subMaxUpCount == 1) { //说明只有一次上传，即没有上一次信息，此时前一次等于本次
				yAxisByCurrList = new ArrayList<>();	//当前次数平均成绩-y轴数据列表
				yAxisByPrevList = new ArrayList<>();	//上一次平均成绩-y轴数据列表
				
				yAxisByCurrList.add(this.getAvgSubScoreBySidCnt(paramMap, CommonConstants.SUBJECT_ID_WEIGE, subMaxUpCount));
				yAxisByPrevList.add(this.getAvgSubScoreBySidCnt(paramMap, CommonConstants.SUBJECT_ID_WEIGE, subMaxUpCount));
				
				yAxisByCurrList.add(this.getAvgSubScoreBySidCnt(paramMap, CommonConstants.SUBJECT_ID_BANSHU, subMaxUpCount));
				yAxisByPrevList.add(this.getAvgSubScoreBySidCnt(paramMap, CommonConstants.SUBJECT_ID_BANSHU, subMaxUpCount));
				
				yAxisByCurrList.add(this.getAvgSubScoreBySidCnt(paramMap, CommonConstants.SUBJECT_ID_JIAOAN, subMaxUpCount));
				yAxisByPrevList.add(this.getAvgSubScoreBySidCnt(paramMap, CommonConstants.SUBJECT_ID_JIAOAN, subMaxUpCount));
				
				yAxisByCurrList.add(this.getAvgSubScoreBySidCnt(paramMap, CommonConstants.SUBJECT_ID_KEJIAN, subMaxUpCount));
				yAxisByPrevList.add(this.getAvgSubScoreBySidCnt(paramMap, CommonConstants.SUBJECT_ID_KEJIAN, subMaxUpCount));
			} else { //说明有上一次，分别记录各次
				yAxisByCurrList = new ArrayList<>();	//当前次数平均成绩-y轴数据列表
				yAxisByPrevList = new ArrayList<>();	//上一次平均成绩-y轴数据列表
				//本次				
				yAxisByCurrList.add(this.getAvgSubScoreBySidCnt(paramMap, CommonConstants.SUBJECT_ID_WEIGE, subMaxUpCount));
				yAxisByCurrList.add(this.getAvgSubScoreBySidCnt(paramMap, CommonConstants.SUBJECT_ID_BANSHU, subMaxUpCount));
				yAxisByCurrList.add(this.getAvgSubScoreBySidCnt(paramMap, CommonConstants.SUBJECT_ID_JIAOAN, subMaxUpCount));
				yAxisByCurrList.add(this.getAvgSubScoreBySidCnt(paramMap, CommonConstants.SUBJECT_ID_KEJIAN, subMaxUpCount));
				
				//上一次
				yAxisByPrevList.add(this.getAvgSubScoreBySidCnt(paramMap, CommonConstants.SUBJECT_ID_WEIGE, subMaxUpCount - 1));
				yAxisByPrevList.add(this.getAvgSubScoreBySidCnt(paramMap, CommonConstants.SUBJECT_ID_BANSHU, subMaxUpCount - 1));
				yAxisByPrevList.add(this.getAvgSubScoreBySidCnt(paramMap, CommonConstants.SUBJECT_ID_JIAOAN, subMaxUpCount - 1));
				yAxisByPrevList.add(this.getAvgSubScoreBySidCnt(paramMap, CommonConstants.SUBJECT_ID_KEJIAN, subMaxUpCount - 1));
			}
						
			if(null != yAxisByCurrList && !yAxisByCurrList.isEmpty()) {
				series = new Series();
				series.setName("本次考试学生各科平均成绩");
				series.setType(CommonConstants.STATISTICS_ECHARTS_TYPE_BAR);
				series.setData(yAxisByCurrList);

				legendList.add(series.getName());
				seriesList.add(series);
			}
			if(null != yAxisByPrevList && !yAxisByPrevList.isEmpty()) {
				series = new Series();
				series.setName("上一次考试学生各科平均成绩");
				series.setType(CommonConstants.STATISTICS_ECHARTS_TYPE_BAR);
				series.setData(yAxisByPrevList);

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
			legendList.add("本次考试学生各科平均成绩");
			legendList.add("上一次考试学生各科平均成绩");
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
	 * @功能描述：根据科目ID和分数类型获取科目的平均成绩或最高成绩
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月1日 上午11:29:53
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param paramMap
	 * @param subjectId
	 * @param scoreType
	 * @return
	 * @throws Exception
	 */
	private Object getAvgOrMaxSubScore(Map paramMap, String subjectId, String scoreType) throws Exception {
		Object rtnObj = null;
		Map maxAndAvgSubScore = new HashMap<>();
		if(paramMap.containsKey("subjectId")) {
			paramMap.remove("subjectId");  //科目ID存在则清除，这里重新设定
		}
		paramMap.put("subjectId", subjectId);
		maxAndAvgSubScore = this.leaderPlatformStatMapper.getAvgAndMaxSubScore(paramMap);
		if(maxAndAvgSubScore != null && !maxAndAvgSubScore.isEmpty()) {
			if(!StringUtil.isEmpty(scoreType)) {
				if(scoreType.equals(CommonConstants.SCORE_TYPE_AVG)) {
					rtnObj = maxAndAvgSubScore.get("avgSubScore") == null ? 0 : maxAndAvgSubScore.get("avgSubScore");
				} else if (scoreType.equals(CommonConstants.SCORE_TYPE_MAX)) {
					rtnObj = maxAndAvgSubScore.get("maxSubScore") == null ? 0 : maxAndAvgSubScore.get("maxSubScore");
				}
			}
		}
		return rtnObj;
	}
	
	/**
	 * @功能描述：根据科目ID和上传次数获取该科目在该次数中所有考试学生的平均成绩
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月1日 下午3:24:32
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param paramMap
	 * @param subjectId
	 * @param scoreType
	 * @return
	 * @throws Exception
	 */
	private Object getAvgSubScoreBySidCnt(Map paramMap, String subjectId, Integer uploadCount) throws Exception {
		Object rtnObj = null;
		Map avgSubScore = new HashMap<>();
		if(paramMap.containsKey("subjectId")) {
			paramMap.remove("subjectId");  //科目ID存在则清除，这里重新设定
		}
		if(paramMap.containsKey("uploadCount")) {
			paramMap.remove("uploadCount");  //次数存在则清除，这里重新设定
		}
		paramMap.put("subjectId", subjectId);
		if(null != uploadCount) {
			paramMap.put("uploadCount", uploadCount);
		}
		
		avgSubScore = this.leaderPlatformStatMapper.getAvgSubScoreBySidCnt(paramMap);
		if(avgSubScore != null && !avgSubScore.isEmpty()) {			
			rtnObj = avgSubScore.get("avgSubScore") == null ? 0 : avgSubScore.get("avgSubScore");
		} else {
			rtnObj = 0;
		}
		return rtnObj;
	}
	
	/**
	 * @功能描述：领导平台——数据统计——四次考试平均成绩，获取每次4门科目的平均成绩并整合数据
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月1日 下午4:51:56
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
	public Map getFourSubsAvgScoreByCnt4SEC(Map paramMap) throws Exception {
		Map rtnMap = new HashMap();
		
		List xAxisList = null;	//x轴数据列表
		List yAxisBy1List = new ArrayList<>();	//第一次考试各科平均成绩-y轴数据列表
		List yAxisBy2List = new ArrayList<>();	//第二次考试各科最高成绩-y轴数据列表
		List yAxisBy3List = new ArrayList<>();	//第三次考试各科最高成绩-y轴数据列表
		List yAxisBy4List = new ArrayList<>();	//第四次考试各科最高成绩-y轴数据列表
		List<String> legendList = new ArrayList<>();//图例名称列
		List<Series> seriesList = new ArrayList<>();//数据列表
		Series series = null;				//echarts每条线（或其他图形）属性序列对象
		
		yAxisBy1List.add(this.getAvgSubScoreBySidCnt(paramMap, CommonConstants.SUBJECT_ID_WEIGE, CommonConstants.UPLOAD_COUNT_1));
		yAxisBy1List.add(this.getAvgSubScoreBySidCnt(paramMap, CommonConstants.SUBJECT_ID_BANSHU, CommonConstants.UPLOAD_COUNT_1));
		yAxisBy1List.add(this.getAvgSubScoreBySidCnt(paramMap, CommonConstants.SUBJECT_ID_JIAOAN, CommonConstants.UPLOAD_COUNT_1));
		yAxisBy1List.add(this.getAvgSubScoreBySidCnt(paramMap, CommonConstants.SUBJECT_ID_KEJIAN, CommonConstants.UPLOAD_COUNT_1));
		
		yAxisBy2List.add(this.getAvgSubScoreBySidCnt(paramMap, CommonConstants.SUBJECT_ID_WEIGE, CommonConstants.UPLOAD_COUNT_2));
		yAxisBy2List.add(this.getAvgSubScoreBySidCnt(paramMap, CommonConstants.SUBJECT_ID_BANSHU, CommonConstants.UPLOAD_COUNT_2));
		yAxisBy2List.add(this.getAvgSubScoreBySidCnt(paramMap, CommonConstants.SUBJECT_ID_JIAOAN, CommonConstants.UPLOAD_COUNT_2));
		yAxisBy2List.add(this.getAvgSubScoreBySidCnt(paramMap, CommonConstants.SUBJECT_ID_KEJIAN, CommonConstants.UPLOAD_COUNT_2));
		
		yAxisBy3List.add(this.getAvgSubScoreBySidCnt(paramMap, CommonConstants.SUBJECT_ID_WEIGE, CommonConstants.UPLOAD_COUNT_3));
		yAxisBy3List.add(this.getAvgSubScoreBySidCnt(paramMap, CommonConstants.SUBJECT_ID_BANSHU, CommonConstants.UPLOAD_COUNT_3));
		yAxisBy3List.add(this.getAvgSubScoreBySidCnt(paramMap, CommonConstants.SUBJECT_ID_JIAOAN, CommonConstants.UPLOAD_COUNT_3));
		yAxisBy3List.add(this.getAvgSubScoreBySidCnt(paramMap, CommonConstants.SUBJECT_ID_KEJIAN, CommonConstants.UPLOAD_COUNT_3));
		
		yAxisBy4List.add(this.getAvgSubScoreBySidCnt(paramMap, CommonConstants.SUBJECT_ID_WEIGE, CommonConstants.UPLOAD_COUNT_4));
		yAxisBy4List.add(this.getAvgSubScoreBySidCnt(paramMap, CommonConstants.SUBJECT_ID_BANSHU, CommonConstants.UPLOAD_COUNT_4));
		yAxisBy4List.add(this.getAvgSubScoreBySidCnt(paramMap, CommonConstants.SUBJECT_ID_JIAOAN, CommonConstants.UPLOAD_COUNT_4));
		yAxisBy4List.add(this.getAvgSubScoreBySidCnt(paramMap, CommonConstants.SUBJECT_ID_KEJIAN, CommonConstants.UPLOAD_COUNT_4));
		
		
		if(null != yAxisBy1List && !yAxisBy1List.isEmpty()) { //第一次不为空再能组装数据
			//初始化x轴序列
			xAxisList = new ArrayList<>();
			xAxisList.add("微格");
			xAxisList.add("板书");
			xAxisList.add("教案");
			xAxisList.add("课件");
			
			series = new Series();
			series.setName("第一次考试");
			series.setType(CommonConstants.STATISTICS_ECHARTS_TYPE_BAR);
			series.setData(yAxisBy1List);

			legendList.add(series.getName());
			seriesList.add(series);
			
			if(null != yAxisBy2List && !yAxisBy2List.isEmpty()) {
				series = new Series();
				series.setName("第二次考试");
				series.setType(CommonConstants.STATISTICS_ECHARTS_TYPE_BAR);
				series.setData(yAxisBy2List);

				legendList.add(series.getName());
				seriesList.add(series);
			}
			if(null != yAxisBy3List && !yAxisBy3List.isEmpty()) {
				series = new Series();
				series.setName("第三次考试");
				series.setType(CommonConstants.STATISTICS_ECHARTS_TYPE_BAR);
				series.setData(yAxisBy3List);

				legendList.add(series.getName());
				seriesList.add(series);
			}
			if(null != yAxisBy4List && !yAxisBy4List.isEmpty()) {
				series = new Series();
				series.setName("第四次考试");
				series.setType(CommonConstants.STATISTICS_ECHARTS_TYPE_BAR);
				series.setData(yAxisBy4List);

				legendList.add(series.getName());
				seriesList.add(series);
			}
			
		} else {  //第一次为空说明没有上传过，虚拟空坐标轴序列即可
			//虚拟X轴序列
			xAxisList = new ArrayList(); 
			legendList = new ArrayList();
			series.setName("第一次考试");
			series.setName("第二次考试");
			series.setName("第三次考试");
			series.setName("第四次考试");
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

	/**
	 * @功能描述：领导平台——数据统计——综合分数最高TOP10，获取综合分数TOP10的学生及成绩并整合数据
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月1日 下午5:36:27
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
	public Map getCompreScoreTop104SEC(Map paramMap) throws Exception {
		Map rtnMap = new HashMap<>();
		List<Map> comScoreList = new ArrayList<>();
		
		List xAxisList = null;	//x轴数据列表
		List yAxisList = null;	//y轴数据列表
		List<String> legendList = new ArrayList<>();//图例名称列
		List<Series> seriesList = new ArrayList<>();//数据列表
		Series series = null;				//echarts每条线（或其他图形）属性序列对象
		
		comScoreList = this.leaderPlatformStatMapper.getCompreScoreTop10(paramMap);
		
		if(null != comScoreList && !comScoreList.isEmpty()) {
			xAxisList = new ArrayList<>();
			yAxisList = new ArrayList<>();
			
			for (Map comScore : comScoreList) {
				xAxisList.add(comScore.get("stuName"));
				yAxisList.add(comScore.get("zong") == null ? 0 : comScore.get("zong"));
			}
			
			if(null != yAxisList && !yAxisList.isEmpty()) {
				series = new Series();
				series.setName("综合成绩");
				series.setType(CommonConstants.STATISTICS_ECHARTS_TYPE_BAR);
				series.setData(yAxisList);

				legendList.add(series.getName());
				seriesList.add(series);
			}
			
		} else {
			//虚拟X轴序列
			xAxisList = new ArrayList(); 
			legendList = new ArrayList();
			series.setName("综合成绩");
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
	
	/**
	 * @功能描述：根据科目ID和上传次数获取该科目在该次数中所有考试学生的不及格人数
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月4日 上午9:28:07
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param paramMap
	 * @param subjectId
	 * @param uploadCount
	 * @return
	 * @throws Exception
	 */
	private Object getFailCountBySidCnt(Map paramMap, String subjectId, Integer uploadCount) throws Exception {
		Object rtnObj = null;
		Map failCountMap = new HashMap<>();
		if(paramMap.containsKey("subjectId")) {
			paramMap.remove("subjectId");  //科目ID存在则清除，这里重新设定
		}
		if(paramMap.containsKey("uploadCount")) {
			paramMap.remove("uploadCount");  //次数存在则清除，这里重新设定
		}
		paramMap.put("subjectId", subjectId);
		if(null != uploadCount) {
			paramMap.put("uploadCount", uploadCount);
		}
		
		failCountMap = this.leaderPlatformStatMapper.getFailCountBySidCnt(paramMap);
		if(failCountMap != null && !failCountMap.isEmpty()) {			
			rtnObj = failCountMap.get("failCount") == null ? 0 : failCountMap.get("failCount");
		} else {
			rtnObj = 0;
		}
		return rtnObj;
	}	
	
	/**
	 * @功能描述：领导平台——数据统计——不及格人数对比对比图，获取本次与上一次考试不及格的人数并整合数据
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月4日 上午9:36:02
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
	public Map getCurrAndPrevFialStuCountCompare4SEC(Map paramMap) throws Exception {
		Map rtnMap = new HashMap();
				
		List xAxisList = null;	//x轴数据列表
		List yAxisByCurrList = null;	//本次考试-y轴数据列表
		List yAxisByPrevList = null;	//上一次考试-y轴数据列表
		List<String> legendList = new ArrayList<>();//图例名称列
		List<Series> seriesList = new ArrayList<>();//数据列表
		Series series = null;				//echarts每条线（或其他图形）属性序列对象
		
		Integer subMaxUpCount = this.leaderPlatformStatMapper.getMaxUploadCount(paramMap);
		if(subMaxUpCount != null && subMaxUpCount != 0) {
			//初始化x轴序列
			xAxisList = new ArrayList<>();
			xAxisList.add("微格");
			xAxisList.add("板书");
			xAxisList.add("教案");
			xAxisList.add("课件");
			if(subMaxUpCount == 1) { //说明只有一次上传，即没有上一次信息，此时前一次等于本次
				yAxisByCurrList = new ArrayList<>();	//当前次数-y轴数据列表
				yAxisByPrevList = new ArrayList<>();	//上一次-y轴数据列表
				
				yAxisByCurrList.add(this.getFailCountBySidCnt(paramMap, CommonConstants.SUBJECT_ID_WEIGE, subMaxUpCount));
				yAxisByPrevList.add(this.getFailCountBySidCnt(paramMap, CommonConstants.SUBJECT_ID_WEIGE, subMaxUpCount));
				
				yAxisByCurrList.add(this.getFailCountBySidCnt(paramMap, CommonConstants.SUBJECT_ID_BANSHU, subMaxUpCount));
				yAxisByPrevList.add(this.getFailCountBySidCnt(paramMap, CommonConstants.SUBJECT_ID_BANSHU, subMaxUpCount));
				
				yAxisByCurrList.add(this.getFailCountBySidCnt(paramMap, CommonConstants.SUBJECT_ID_JIAOAN, subMaxUpCount));
				yAxisByPrevList.add(this.getAvgSubScoreBySidCnt(paramMap, CommonConstants.SUBJECT_ID_JIAOAN, subMaxUpCount));
				
				yAxisByCurrList.add(this.getFailCountBySidCnt(paramMap, CommonConstants.SUBJECT_ID_KEJIAN, subMaxUpCount));
				yAxisByPrevList.add(this.getFailCountBySidCnt(paramMap, CommonConstants.SUBJECT_ID_KEJIAN, subMaxUpCount));
			} else { //说明有上一次，分别记录各次
				yAxisByCurrList = new ArrayList<>();	//当前次数-y轴数据列表
				yAxisByPrevList = new ArrayList<>();	//上一次-y轴数据列表
				//本次				
				yAxisByCurrList.add(this.getFailCountBySidCnt(paramMap, CommonConstants.SUBJECT_ID_WEIGE, subMaxUpCount));
				yAxisByCurrList.add(this.getFailCountBySidCnt(paramMap, CommonConstants.SUBJECT_ID_BANSHU, subMaxUpCount));
				yAxisByCurrList.add(this.getFailCountBySidCnt(paramMap, CommonConstants.SUBJECT_ID_JIAOAN, subMaxUpCount));
				yAxisByCurrList.add(this.getFailCountBySidCnt(paramMap, CommonConstants.SUBJECT_ID_KEJIAN, subMaxUpCount));
				
				//上一次
				yAxisByPrevList.add(this.getFailCountBySidCnt(paramMap, CommonConstants.SUBJECT_ID_WEIGE, subMaxUpCount - 1));
				yAxisByPrevList.add(this.getFailCountBySidCnt(paramMap, CommonConstants.SUBJECT_ID_BANSHU, subMaxUpCount - 1));
				yAxisByPrevList.add(this.getFailCountBySidCnt(paramMap, CommonConstants.SUBJECT_ID_JIAOAN, subMaxUpCount - 1));
				yAxisByPrevList.add(this.getFailCountBySidCnt(paramMap, CommonConstants.SUBJECT_ID_KEJIAN, subMaxUpCount - 1));
			}
						
			if(null != yAxisByCurrList && !yAxisByCurrList.isEmpty()) {
				series = new Series();
				series.setName("本次考试不及格人数");
				series.setType(CommonConstants.STATISTICS_ECHARTS_TYPE_BAR);
				series.setData(yAxisByCurrList);

				legendList.add(series.getName());
				seriesList.add(series);
			}
			if(null != yAxisByPrevList && !yAxisByPrevList.isEmpty()) {
				series = new Series();
				series.setName("上次考试不及格人数");
				series.setType(CommonConstants.STATISTICS_ECHARTS_TYPE_BAR);
				series.setData(yAxisByPrevList);

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
			legendList.add("本次考试不及格人数");
			legendList.add("上次考试不及格人数");
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
	 * @功能描述：领导平台——数据统计——不同学院成绩通过率（通过率统计表），根据学院ID分组获取不同科目不同等级的通过率以及综合通过率并为数据统计表（DST）整合数据
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月6日 上午9:23:41
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
	public Map getPassRateGroupbyAcaId4DST(Map paramMap) throws Exception {
		Map rtnMap = new HashMap();
		List<Map> acaSubPassRateList = new ArrayList<>();
		Map acaSubPassRateMap = new HashMap<>();
		
		if(null != paramMap && !paramMap.isEmpty()) {
			//String subjectId = String.valueOf(paramMap.get("subId"));  //获取科目ID			
			acaSubPassRateList = this.getPassRateGroupbyAcaId(paramMap);
			
			if(null != acaSubPassRateList && !acaSubPassRateList.isEmpty()) {
				if(acaSubPassRateList.size() >= CommonConstants.COMM_CONST_INTEGER_1) { //List长度大于或等于1说明返回记录多于1条，取第一条即可
					acaSubPassRateMap = acaSubPassRateList.get(CommonConstants.COMM_CONST_INTEGER_0);	
					if(null != acaSubPassRateMap && !acaSubPassRateMap.isEmpty()) {
						rtnMap = acaSubPassRateMap;
					}
				}
			} else {
				LOGGER.debug(Constants.MSG_GET_NULL_DATA);
			}
		} else {
			LOGGER.debug("传递参数为空；paramMap=" + paramMap);
		}
		
		return rtnMap;
	}	
	
	/**
	 * @功能描述：将double类型数据转换为百分数
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月6日 上午10:08:10
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param data
	 * @return
	 */
	private String double2Percent(double data) {
		DecimalFormat df = new DecimalFormat("#0.00%");
        String perStr = df.format(data);
        return perStr;
	}
	
	
	/**
	 * @功能描述：领导平台——数据统计——不同学院成绩通过率（通过率饼形图），根据学院ID分组获取不同科目不同等级的通过率以及综合通过率并为统计图（SEC）整合数据
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月4日 下午2:36:32
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
	public Map getPassRateGroupbyAcaId4SEC(Map paramMap) throws Exception {
		Map rtnMap = new HashMap<>();
		List<Map> acaSubPassRateList = new ArrayList<>();
		Map acaSubPassRateMap = new HashMap<>();			
		
		Map acaSumPassRateMap = new HashMap<>();	//学院综合通过率饼形图结果Map
		Map acaSubWgPassRateMap = new HashMap<>();	//学院中微格科目通过率饼形图结果Map
		Map acaSubBsPassRateMap = new HashMap<>();	//学院中板书科目通过率饼形图结果Map
		Map acaSubJaPassRateMap = new HashMap<>();	//学院中教案科目通过率饼形图结果Map
		Map acaSubKjPassRateMap = new HashMap<>();	//学院中课件科目通过率饼形图结果Map
				
		if(null != paramMap && !paramMap.isEmpty()) {
			String subId = String.valueOf(paramMap.get("subId"));  //获取科目ID
			
			acaSubPassRateList = this.getPassRateGroupbyAcaId(paramMap);
			
			if(null != acaSubPassRateList && !acaSubPassRateList.isEmpty()) {
				if(acaSubPassRateList.size() >= CommonConstants.COMM_CONST_INTEGER_1) { //List长度大于或等于1说明返回记录多于1条，取第一条即可
					acaSubPassRateMap = acaSubPassRateList.get(CommonConstants.COMM_CONST_INTEGER_0);	
					if(null != acaSubPassRateMap && !acaSubPassRateMap.isEmpty()) {
						if(subId.equals(CommonConstants.SUBJECT_ID_WEIGE)) {
							double weigeARate = this.convertObject2Double(acaSubPassRateMap, "weigeARate");
							double weigeBRate = this.convertObject2Double(acaSubPassRateMap, "weigeBRate");
							double weigeCRate = this.convertObject2Double(acaSubPassRateMap, "weigeCRate");
							double weigeDRate = this.convertObject2Double(acaSubPassRateMap, "weigeDRate");
							double weigeERate = this.convertObject2Double(acaSubPassRateMap, "weigeERate");
							
							acaSubWgPassRateMap = this.assemblePieData(weigeARate, weigeBRate, weigeCRate, weigeDRate, weigeERate, "微格科目通过率");
							rtnMap.put("weige", acaSubWgPassRateMap);		
						} else if(subId.equals(CommonConstants.SUBJECT_ID_BANSHU)) {
							double banshuARate = this.convertObject2Double(acaSubPassRateMap, "banshuARate");
							double banshuBRate = this.convertObject2Double(acaSubPassRateMap, "banshuBRate");
							double banshuCRate = this.convertObject2Double(acaSubPassRateMap, "banshuCRate");
							double banshuDRate = this.convertObject2Double(acaSubPassRateMap, "banshuDRate");
							double banshuERate = this.convertObject2Double(acaSubPassRateMap, "banshuERate");
							
							acaSubBsPassRateMap = this.assemblePieData(banshuARate, banshuBRate, banshuCRate, banshuDRate, banshuERate, "板书科目通过率");
							rtnMap.put("banshu", acaSubBsPassRateMap);
						} else if(subId.equals(CommonConstants.SUBJECT_ID_JIAOAN)) {
							double jiaoanARate = this.convertObject2Double(acaSubPassRateMap, "jiaoanARate");
							double jiaoanBRate = this.convertObject2Double(acaSubPassRateMap, "jiaoanBRate");
							double jiaoanCRate = this.convertObject2Double(acaSubPassRateMap, "jiaoanCRate");
							double jiaoanDRate = this.convertObject2Double(acaSubPassRateMap, "jiaoanDRate");
							double jiaoanERate = this.convertObject2Double(acaSubPassRateMap, "jiaoanERate");
							
							acaSubJaPassRateMap = this.assemblePieData(jiaoanARate, jiaoanBRate, jiaoanCRate, jiaoanDRate, jiaoanERate, "教案科目通过率");
							rtnMap.put("jiaoan", acaSubJaPassRateMap);
						} else if(subId.equals(CommonConstants.SUBJECT_ID_KEJIAN)) {
							double kejianARate = this.convertObject2Double(acaSubPassRateMap, "kejianARate");
							double kejianBRate = this.convertObject2Double(acaSubPassRateMap, "kejianBRate");
							double kejianCRate = this.convertObject2Double(acaSubPassRateMap, "kejianCRate");
							double kejianDRate = this.convertObject2Double(acaSubPassRateMap, "kejianDRate");
							double kejianERate = this.convertObject2Double(acaSubPassRateMap, "kejianERate");
							
							acaSubKjPassRateMap = this.assemblePieData(kejianARate, kejianBRate, kejianCRate, kejianDRate, kejianERate, "课件科目通过率");
							rtnMap.put("kejian", acaSubKjPassRateMap);
						} else {  //科目ID为学院ID或其他数据的都设置为学院综合通过率
							double zongARate = this.convertObject2Double(acaSubPassRateMap, "zongARate");
							double zongBRate = this.convertObject2Double(acaSubPassRateMap, "zongBRate");
							double zongCRate = this.convertObject2Double(acaSubPassRateMap, "zongCRate");
							double zongDRate = this.convertObject2Double(acaSubPassRateMap, "zongDRate");
							double zongERate = this.convertObject2Double(acaSubPassRateMap, "zongERate");
							
							acaSumPassRateMap = this.assemblePieData(zongARate, zongBRate, zongCRate, zongDRate, zongERate, "学院综合通过率");
							rtnMap.put("zong", acaSumPassRateMap);
						}
					}
				}
			} else {
				LOGGER.debug(Constants.MSG_GET_NULL_DATA);
			}
		} else {
			LOGGER.debug("传递参数为空；paramMap=" + paramMap);
		}
		
		return rtnMap;
	}
	
	/**
	 * @功能描述：将Map中的指定对象转换为Float类型的数据
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月5日 下午4:47:25
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param objMap
	 * @param key
	 * @return
	 */
	private double convertObject2Double(Map objMap, String key) {
		double rtnDouble = 0d;
		rtnDouble = (objMap.get(key) == null || String.valueOf(objMap.get(key)).length() == 0) ? 0d
				: Double.parseDouble(String.valueOf(objMap.get(key)));
		return rtnDouble;
	}
	
	/**
	 * @功能描述：组装学院综合及各科目各等级的通过率饼形图所需的数据
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月5日 下午4:47:36
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param ARate
	 * @param BRate
	 * @param CRate
	 * @param DRate
	 * @param ERate
	 * @param seriesName
	 * @return
	 */
	private Map assemblePieData(Object ARate,Object BRate,Object CRate,Object DRate,Object ERate,String seriesName) {
		Map rtnMap = new HashMap<>();
		List pieSeriesDataList = new ArrayList<>();	//饼图Series Data List
		Map pieSeriesDataMap = null;		//饼图Series Data Map
		List<Series> seriesList = new ArrayList<>();
		List<String> legendList = new ArrayList<>();
		Series series = new Series();
				
		pieSeriesDataMap = new HashMap();
		pieSeriesDataMap.put("value", ARate);
		pieSeriesDataMap.put("name", "优秀");
		pieSeriesDataList.add(pieSeriesDataMap);
		
		pieSeriesDataMap = new HashMap();
		pieSeriesDataMap.put("value", BRate);
		pieSeriesDataMap.put("name", "良好");
		pieSeriesDataList.add(pieSeriesDataMap);
		
		pieSeriesDataMap = new HashMap();
		pieSeriesDataMap.put("value", CRate);
		pieSeriesDataMap.put("name", "一般");
		pieSeriesDataList.add(pieSeriesDataMap);
		
		pieSeriesDataMap = new HashMap();
		pieSeriesDataMap.put("value", DRate);
		pieSeriesDataMap.put("name", "及格");
		pieSeriesDataList.add(pieSeriesDataMap);
		
		pieSeriesDataMap = new HashMap();
		pieSeriesDataMap.put("value", ERate);
		pieSeriesDataMap.put("name", "不及格");
		pieSeriesDataList.add(pieSeriesDataMap);
		
		series.setData(pieSeriesDataList);
		series.setType(CommonConstants.STATISTICS_ECHARTS_TYPE_PIE);
		series.setName(seriesName);
		seriesList.add(series);
		legendList.add(series.getName());
		
		rtnMap.put("legend", legendList);
		rtnMap.put("seriesList", seriesList);
		
		return rtnMap;
	}
	
	
	/**
	 * @功能描述：领导平台——数据统计——不同学院综合平均分对比对比图，按学院分组获取各科目的平均分以及综合平均分并整合数据
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月4日 下午2:36:36
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
	public Map getCompreAvgScoreGroupbyAcaId4SEC(Map paramMap) throws Exception {
		Map rtnMap = new HashMap<>();
		List<Map> compreAvgScoreList = new ArrayList<>();
		List xAxisByWgList = null;	//微格-x轴数据列表
		List xAxisByBsList = null;	//板书-x轴数据列表
		List xAxisByJaList = null;	//教案-x轴数据列表
		List xAxisByKjList = null;	//课件-x轴数据列表
		List yAxisList = null;	//y轴数据列表
		List<String> legendList = null;//图例名称列
		List<Series> seriesList = null;//数据列表
		Series series = null;	//echarts每条线（或其他图形）属性序列对象
		
		compreAvgScoreList = this.getCompreAvgScoreGroupbyAcaId(paramMap);
		
		if(null != compreAvgScoreList && !compreAvgScoreList.isEmpty()) {
			legendList = new ArrayList<>();
			seriesList = new ArrayList<>();
			
			yAxisList = new ArrayList<>();
			xAxisByWgList = new ArrayList<>();
			xAxisByBsList = new ArrayList<>();
			xAxisByJaList = new ArrayList<>();
			xAxisByKjList = new ArrayList<>();
			for (Map compreAvgScore : compreAvgScoreList) {				
				yAxisList.add(compreAvgScore.get("acaName"));				
								
				xAxisByWgList.add(compreAvgScore.get("weigeAvg") == null ? 0 : compreAvgScore.get("weigeAvg"));
				xAxisByBsList.add(compreAvgScore.get("banshuAvg") == null ? 0 : compreAvgScore.get("banshuAvg"));
				xAxisByJaList.add(compreAvgScore.get("jiaoanAvg") == null ? 0 : compreAvgScore.get("jiaoanAvg"));
				xAxisByKjList.add(compreAvgScore.get("kejianAvg") == null ? 0 : compreAvgScore.get("kejianAvg"));							
			}
			
			if(null != xAxisByWgList && !xAxisByWgList.isEmpty()) {
				series = new Series();
				series.setName("微格");
				series.setType(CommonConstants.STATISTICS_ECHARTS_TYPE_BAR);
				series.setData(xAxisByWgList);

				legendList.add(series.getName());
				seriesList.add(series);
			}
			if(null != xAxisByBsList && !xAxisByBsList.isEmpty()) {
				series = new Series();
				series.setName("板书");
				series.setType(CommonConstants.STATISTICS_ECHARTS_TYPE_BAR);
				series.setData(xAxisByBsList);

				legendList.add(series.getName());
				seriesList.add(series);
			}
			if(null != xAxisByJaList && !xAxisByJaList.isEmpty()) {
				series = new Series();
				series.setName("教案");
				series.setType(CommonConstants.STATISTICS_ECHARTS_TYPE_BAR);
				series.setData(xAxisByJaList);

				legendList.add(series.getName());
				seriesList.add(series);
			}
			if(null != xAxisByKjList && !xAxisByKjList.isEmpty()) {
				series = new Series();
				series.setName("课件");
				series.setType(CommonConstants.STATISTICS_ECHARTS_TYPE_BAR);
				series.setData(xAxisByKjList);

				legendList.add(series.getName());
				seriesList.add(series);
			}
			
			rtnMap.put("axis", yAxisList);
			rtnMap.put("legend", legendList);
			rtnMap.put("seriesList", seriesList);
		} else {
			//虚拟Y轴序列
			yAxisList = new ArrayList(); 
			legendList = new ArrayList();
			legendList.add("微格");
			legendList.add("板书");
			legendList.add("教案");
			legendList.add("课件");
			//虚拟空序列
			seriesList = new ArrayList();
			for (int i = 0; i < legendList.size(); i++) {
				series = new Series();
				series.setName(legendList.get(i));
				series.setType(CommonConstants.STATISTICS_ECHARTS_TYPE_BAR);
				series.setData(new ArrayList());
				seriesList.add(series);
			}
			rtnMap.put("axis", yAxisList);
			rtnMap.put("legend", legendList);
			rtnMap.put("seriesList", seriesList);
		}		
		
		return rtnMap;
	}

	/**
	 * @功能描述：获取不同学院的综合平均分，为前端表格数据显示准备数据
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月4日 下午3:47:04
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
	public List<Map> getCompreAvgScoreGroupbyAcaId(Map paramMap) throws Exception {
		return this.leaderPlatformStatMapper.getCompreAvgScoreGroupbyAcaId(paramMap);
	}
	
	/**
	 * @功能描述：获取不同学院的各科通过率及综合通过率
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月4日 下午5:59:36
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
	public List<Map> getPassRateGroupbyAcaId(Map paramMap) throws Exception {
		return this.leaderPlatformStatMapper.getPassRateGroupbyAcaId(paramMap);
	}

	/**
	 * @功能描述：获取学院信息列表，用于前端下拉框显示（暂废弃，不使用）
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月5日 上午9:44:50
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
	public List<Map> getAcaInfoList(Map paramMap) throws Exception {
		return this.leaderPlatformStatMapper.getAcaInfoList(paramMap);
	}

	/**
	 * @功能描述：获取不同学院各科目的最大考核次数
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月6日 下午2:38:25
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
	public List<Map> getAcaExamCountByAidSid(Map paramMap) throws Exception {
		return this.leaderPlatformStatMapper.getAcaExamCountByAidSid(paramMap);
	}

	/**
	 * @功能描述：领导平台——数据统计——不同学院综合考核次数对比——数据统计表，按学院和科目分组获取各学院各科目的最大上传次数并整合数据
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月6日 下午2:43:02
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
	public List<Map> getAcaExamCount4DST(Map paramMap) throws Exception {
		return this.getAcaExamCountByAidSid(paramMap);
	}

	/**
	 * @功能描述：领导平台——数据统计——不同学院综合考核次数对比——统计图表，按学院和科目分组获取各学院各科目的最大上传次数并整合数据
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月6日 下午2:43:10
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
	public Map getAcaExamCount4SEC(Map paramMap) throws Exception {
		Map rtnMap = new HashMap<>();
		List<Map> acaExamCntList = new ArrayList<>();
		
		List xAxisList = null;	//x轴数据列表
		List yAxisByWgList = null;	//微格-y轴数据列表
		List yAxisByBsList = null;	//板书-y轴数据列表
		List yAxisByJaList = null;	//教案-y轴数据列表
		List yAxisByKjList = null;	//课件-y轴数据列表
		List<String> legendList = null;//图例名称列
		List<Series> seriesList = null;//数据列表
		Series series = null;		//echarts每条线（或其他图形）属性序列对象
		
		acaExamCntList = this.getAcaExamCountByAidSid(paramMap);
		
		if(null != acaExamCntList && !acaExamCntList.isEmpty()) {
			xAxisList = new ArrayList<>();	
			yAxisByWgList = new ArrayList<>();
			yAxisByBsList = new ArrayList<>();
			yAxisByJaList = new ArrayList<>();
			yAxisByKjList = new ArrayList<>();
			legendList = new ArrayList<>();
			seriesList = new ArrayList<>();
			
			for (Map acaExamCnt : acaExamCntList) {				
				xAxisList.add(acaExamCnt.get("acaName"));
				yAxisByWgList.add(acaExamCnt.get("weige") == null ? 0 : acaExamCnt.get("weige"));
				yAxisByBsList.add(acaExamCnt.get("banshu") == null ? 0 : acaExamCnt.get("banshu"));
				yAxisByJaList.add(acaExamCnt.get("jiaoan") == null ? 0 : acaExamCnt.get("jiaoan"));
				yAxisByKjList.add(acaExamCnt.get("kejian") == null ? 0 : acaExamCnt.get("kejian"));
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
			
		} else {
			LOGGER.debug("获取考核次数列表为空");
			
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
