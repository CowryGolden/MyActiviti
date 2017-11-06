package com.zzrenfeng.zznueg.service;

import java.util.List;
import java.util.Map;

/**
 * @功能描述：教师平台部分Service接口
 * @创  建  者：zhoujincheng
 * @版        本：V1.0.0
 * @创建日期：2017年8月18日 上午10:08:09
 * 
 * @修  改  人：
 * @修改日期：
 * @修改描述：
 *
 */
public interface TeacherPlatformService {
	
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
	List<Map> getStuListByTcid4Teach(Map paramMap) throws Exception;
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
	List<Map> getUploadListBySuid4Teach(Map paramMap) throws Exception;
		
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
	boolean persistenceEvalScore(Map paramMap) throws Exception;

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
	List<Map> getStuOverviewByTuid4Teach(Map paramMap) throws Exception;
	
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
	Long getStuCountByTuid(Map paramMap) throws Exception;
	
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
	Map getPassingRat4DST(Map paramMap) throws Exception;
	
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
	Map getDifferentGrades4SEC(Map paramMap) throws Exception;
	
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
	Integer getStuMaxUpCountByTuid(Map paramMap) throws Exception;
	
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
	Map getAvgSubScoreByTidSidCnt(Map paramMap) throws Exception;
	
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
	Map getAvgSubScoreBySid(Map paramMap) throws Exception;
	
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
	Map getMyStusSubScoresRatio4SEC(Map paramMap) throws Exception;
	
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
	Map getMyAvgAndAllAvgSubScore4SEC(Map paramMap) throws Exception;
	
	
}
