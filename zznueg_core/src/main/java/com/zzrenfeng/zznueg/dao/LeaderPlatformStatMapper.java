package com.zzrenfeng.zznueg.dao;

import java.util.List;
import java.util.Map;

/**
 * @功能描述：领导平台统计功能DAO
 * @创  建  者：zhoujincheng
 * @版        本：V1.0.0
 * @创建日期：2017年8月30日 下午3:36:12
 * 
 * @修  改  人：
 * @修改日期：
 * @修改描述：
 *
 */
public interface LeaderPlatformStatMapper {
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
	 * @功能描述：获取各科平均与单科最高成绩
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月1日 上午11:10:39
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	Map getAvgAndMaxSubScore(Map paramMap) throws Exception;
	
	/**
	 * @功能描述：根据科目ID和上传次数获取该科目在该次数中所有考试学生的平均成绩
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月1日 下午3:17:16
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	Map getAvgSubScoreBySidCnt(Map paramMap) throws Exception;
	
	/**
	 * @功能描述：获取全体学生所有科目中最大上传的次数
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月1日 下午4:08:10
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	Integer getMaxUploadCount(Map paramMap) throws Exception;
	
	/**
	 * @功能描述：获取综合分数TOP10的学生及成绩
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
	List<Map> getCompreScoreTop10(Map paramMap) throws Exception;
	
	/**
	 * @功能描述：根据科目ID和上传次数获取该科目在该次数中所有考试学生的不及格人数
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月4日 上午9:26:56
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	Map getFailCountBySidCnt(Map paramMap) throws Exception;
	
	/**
	 * @功能描述：获取不同学院的各科通过率及综合通过率
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月4日 下午2:30:46
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
	 * @功能描述：获取不同学院的综合平均分
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月4日 下午2:30:19
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
	
	
}


