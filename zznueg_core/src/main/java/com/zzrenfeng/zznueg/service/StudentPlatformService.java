package com.zzrenfeng.zznueg.service;

import java.util.List;
import java.util.Map;

import com.zzrenfeng.base.utils.PageUtil;
import com.zzrenfeng.zznueg.entity.StuUploadInfo;

/**
 * @功能描述：学生平台部分Service接口
 * @创  建  者：zhoujincheng
 * @版        本：V1.0.0
 * @创建日期：2017年8月9日 下午6:02:36
 * 
 * @修  改  人：
 * @修改日期：
 * @修改描述：
 *
 */
public interface StudentPlatformService {
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
	boolean persistenceStuUploadInfo(Map paramMap) throws Exception;
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
	boolean persistenceStuUploadInfo(StuUploadInfo stuUploadInfo) throws Exception;
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
	Integer getUploadedCountByStuIdAndSubId(Map paramMap) throws Exception;
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
	List<Map> findAllUploadInfoByPage(PageUtil pageUtil) throws Exception;
	
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
	List<Map> findAllByPageByStuUid(Map paramMap) throws Exception;
	
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
	List<Map> getUploadInfoBySubId(Map paramMap) throws Exception;
	
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
	Long getAllCount(Map<String, Object> paramMap) throws Exception;
	
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
	Long getCountByStuUid(Map<String, Object> paramMap) throws Exception;
	
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
	Map getStuInfoBySuid(Map paramMap) throws Exception;
	
	/**
	 * @功能描述：根据学生ID获取该学生的成绩信息，使用教师平台DAO的getUploadListBySuid4Teach方法
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月22日 下午3:42:25
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	List<Map> getStudentScoresBySuid(Map paramMap) throws Exception;
	
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
	Map getSubScoreAndRankBySuidAndSid(Map paramMap) throws Exception;
	
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
	List<Map> getAllSubScoresAndRanksBySuid(Map paramMap) throws Exception;
	
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
	 * @功能描述：学生平台——数据统计——成绩记录——“我的总成绩记录”，获取总成绩数据并为统计图整合数据
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月25日 下午6:15:23
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	Map getSumScores4SEC(Map paramMap) throws Exception;
	
	/**
	 * @功能描述：学生平台——数据统计——成绩记录——“我的单科成绩记录”，获取每次上传的单科成绩数据并为统计图整合数据
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月26日 上午9:57:48
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	Map getSubScores4SEC(Map paramMap) throws Exception;
	
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
	Map getSubScoreCompareToAvg4SEC(Map paramMap) throws Exception;
	
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
	Map getScoresRadar4SEC(Map paramMap) throws Exception;
	
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
	Map getStuTop10ByEnhance4SEC(Map paramMap) throws Exception;
	
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
	Map getStuTop10ByActivity4SEC(Map paramMap) throws Exception;
	
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
	boolean saveStuEvalRecordInfoList(Map paramMap) throws Exception;
	
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
	boolean isTakePartInOnlineEval(Map paramMap) throws Exception;
	
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
	List<Map> getCategorySumScore(Map paramMap) throws Exception;
	
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
	List<Map> getCategorySumScore4DST(Map paramMap) throws Exception;
	
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
	Map getCategorySumScore4SEC(Map paramMap) throws Exception;
	
	
}
