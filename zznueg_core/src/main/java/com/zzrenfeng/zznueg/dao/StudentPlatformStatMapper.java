package com.zzrenfeng.zznueg.dao;

import java.util.List;
import java.util.Map;

/**
 * @功能描述：学生平台统计功能Dao接口
 * @创  建  者：zhoujincheng
 * @版        本：V1.0.0
 * @创建日期：2017年8月24日 下午2:19:52
 * 
 * @修  改  人：
 * @修改日期：
 * @修改描述：
 *
 */
public interface StudentPlatformStatMapper {
	
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
	Map getStuTotalScoreAndRankBySuid(Map paramMap) throws Exception;
	
	/**
	 * @功能描述：根据学ID和科目ID获取该学生该科目的最高成绩及该成绩在全校中的单科的排名
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月24日 下午5:12:50
	 * 
	 * @修  改  人：zjc
	 * @修改日期：20170825 14:16
	 * @修改描述：新增字段：sumScore-单科所有上传的总成绩；subCount-单科所有上传次数；avgSubScore-单科平均值
	 * 
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	Map getSubScoreAndRankBySuidAndSid(Map paramMap) throws Exception;
	
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
	List<Map> getSumScoresByCountBySuid(Map paramMap) throws Exception;
	
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
	List<Map> getSubScoresBySuidAndUpcnt(Map paramMap) throws Exception;
	
	/**
	 * @功能描述：根据科目ID查询该科目的总成绩，总考试人数，平均成绩
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月25日 下午2:21:30
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	Map getAvgSubScoreBySid(Map paramMap) throws Exception;
	
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
	List<Map> getBestSubScoreBySuid(Map paramMap) throws Exception;
	
	/**
	 * @功能描述：获取提升分数最高学生TOP10排名，全体学生最近两次考试成绩差距由高到低排序
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月25日 下午3:01:36
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	List<Map> getStuLiftRangeTop10ByGap(Map paramMap) throws Exception;
	
	/**
	 * @功能描述：根据登录次数获取活跃度Top10学生
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月25日 下午2:50:29
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	List<Map> getStuActivityTop10ByLoginCnt(Map paramMap) throws Exception;
	
	/**
	 * @功能描述：根据学生ID获取该学生上传的最大次数
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月26日 上午10:08:50
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	Integer getMaxUpCountBySuid(Map paramMap) throws Exception;
	
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
	List<Map> getOnlineEvalPaperInfo(Map paramMap) throws Exception;
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
	Map getOneEnablePaperInfo(Map paramMap) throws Exception;
	
	
}
