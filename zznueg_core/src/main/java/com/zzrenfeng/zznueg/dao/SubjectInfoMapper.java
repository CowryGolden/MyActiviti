package com.zzrenfeng.zznueg.dao;

import java.util.List;
import java.util.Map;

import com.zzrenfeng.base.dao.BaseMapper;
import com.zzrenfeng.zznueg.entity.SubjectInfo;
/**
 * @功能描述：科目信息Dao接口（子类中仅需定义子类特有的方法即可，通用的从父类中获取）
 * @创  建  者： zhoujincheng
 * @版        本：V1.0.0
 * @创建日期：2017年7月20日 下午2:15:25
 *
 * @修  改  人：
 * @修改日期：
 * @修改描述：
 */
public interface SubjectInfoMapper extends BaseMapper<SubjectInfo> {
/*	//这些方法均在父类中存在，不必再定义；子类中仅需定义子类特有的方法即可，通用的从父类中获取
    int deleteByPrimaryKey(String subjectId);
    int insert(SubjectInfo record);
    int insertSelective(SubjectInfo record);
    SubjectInfo selectByPrimaryKey(String subjectId);
    int updateByPrimaryKeySelective(SubjectInfo record);
    int updateByPrimaryKey(SubjectInfo record);
*/    
	/**
	 * @功能描述：分页查询所有科目信息
	 * @创  建  者： zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年7月21日 上午9:59:18
	 *
	 * @修  改  人：zhoujincheng
	 * @版        本：V1.1.0
	 * @修改日期：20171025 10:39
	 * @修改描述：为了方法通用性，将模糊检索功能合并到该方法中；为了增加检索参数的可扩展性，这里将传入参数由PageUtil pageUtil修改为Map paramMap
	 * 
	 * @param 将pageUtil修改为paramMap
	 * @return
	 */
	List<SubjectInfo> findAllByPage(Map paramMap) throws Exception;
	
	
}