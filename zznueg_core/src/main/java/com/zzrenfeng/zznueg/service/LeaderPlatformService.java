package com.zzrenfeng.zznueg.service;

import java.util.List;
import java.util.Map;

/**
 * @功能描述：领导平台Service接口
 * @创  建  者：zhoujincheng
 * @版        本：V1.0.0
 * @创建日期：2017年8月30日 下午3:58:48
 * 
 * @修  改  人：
 * @修改日期：
 * @修改描述：
 *
 */
public interface LeaderPlatformService {
	
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
	List<Map> getAllStusInfoAndSubScoresByPage(Map paramMap) throws Exception;

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
	Long getTotalStusCount(Map paramMap) throws Exception;
	
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
	Map getAvgSubScoreCompare4SEC(Map paramMap) throws Exception;
	
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
	Map getCurrAndPrevAvgSubScoreCompare4SEC(Map paramMap) throws Exception;
	
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
	Map getFourSubsAvgScoreByCnt4SEC(Map paramMap) throws Exception;
	
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
	Map getCompreScoreTop104SEC(Map paramMap) throws Exception;

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
	Map getCurrAndPrevFialStuCountCompare4SEC(Map paramMap) throws Exception;
	
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
	Map getPassRateGroupbyAcaId4SEC(Map paramMap) throws Exception;
	
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
	Map getPassRateGroupbyAcaId4DST(Map paramMap) throws Exception;

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
	Map getCompreAvgScoreGroupbyAcaId4SEC(Map paramMap) throws Exception;
		
	/**
	 * @功能描述：获取不同学院的综合平均分
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
	List<Map> getCompreAvgScoreGroupbyAcaId(Map paramMap) throws Exception;
	
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
	List<Map> getPassRateGroupbyAcaId(Map paramMap) throws Exception;
	
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
	List<Map> getAcaInfoList(Map paramMap) throws Exception;
	
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
	List<Map> getAcaExamCountByAidSid(Map paramMap) throws Exception;
	
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
	List<Map> getAcaExamCount4DST(Map paramMap) throws Exception;
	
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
	Map getAcaExamCount4SEC(Map paramMap) throws Exception;
	
	
	
}
