package com.zzrenfeng.zznueg.dao;

import java.util.List;
import java.util.Map;

import com.zzrenfeng.base.dao.BaseMapper;
/**
 * @功能描述：学生在线测评记录信息Dao
 * @创  建  者：zhoujincheng
 * @版        本：V1.0.0
 * @创建日期：2017年9月11日 下午2:23:48
 * 
 * @修  改  人：
 * @修改日期：
 * @修改描述：
 *
 */
public interface OnlineEvalStuentEvalRecordInfoMapper extends BaseMapper {
/*	
    int deleteByPrimaryKey(String pid);

    int insert(OnlineEvalStuentEvalRecordInfo record);

    int insertSelective(OnlineEvalStuentEvalRecordInfo record);

    OnlineEvalStuentEvalRecordInfo selectByPrimaryKey(String pid);

    int updateByPrimaryKeySelective(OnlineEvalStuentEvalRecordInfo record);

    int updateByPrimaryKey(OnlineEvalStuentEvalRecordInfo record);
*/    
	
	/**
	 * @功能描述：获取某个学生参与答题的记录数，用于判断该学生是否参与过在线测评
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月12日 下午5:16:56
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	Integer getStuEvalRecordCount(Map paramMap) throws Exception;
	
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
	
    
}