package com.zzrenfeng.zznueg.dao;

import java.util.List;
import java.util.Map;

/**
 * @功能描述：教师平台统计功能DAO
 * @创  建  者：zhoujincheng
 * @版        本：V1.0.0
 * @创建日期：2017年8月28日 下午2:25:54
 * 
 * @修  改  人：
 * @修改日期：
 * @修改描述：
 *
 */
public interface TeacherPlatformStatMapper {
	/**
	 * @功能描述：获取全校所有学生的各科成绩
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月28日 下午2:27:33
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	List<Map> getAllStusSubScores(Map paramMap) throws Exception;
	
	/**
	 * @功能描述：获取某个教师指导的学生的各科成绩
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月28日 下午2:28:17
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	List<Map> getMyStusSubScoresByTuid(Map paramMap) throws Exception;
	
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
	

}


