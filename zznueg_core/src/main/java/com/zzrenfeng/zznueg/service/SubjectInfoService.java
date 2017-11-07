package com.zzrenfeng.zznueg.service;

import java.util.List;
import java.util.Map;

import com.zzrenfeng.base.utils.PageUtil;
import com.zzrenfeng.zznueg.entity.SubjectInfo;

/** 
 * @功能描述：科目信息服务接口
 * @创  建  者： zhoujincheng
 * @版        本：V1.0.0
 * @创建日期：2017年7月20日 下午5:36:11
 *
 * @修  改  人：
 * @修改日期：
 * @修改描述：
 */
public interface SubjectInfoService {
	/**
	 * @功能描述：根据科目ID查询科目信息
	 * @创  建  者： zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年7月20日 下午6:05:39
	 *
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * @param subjectId
	 * @return
	 */
	SubjectInfo getSubjectInfoById(String subjectId) throws Exception;
	/**
	 * @功能描述：持久化科目信息（适应前端的新增或修改）
	 * @创  建  者： zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年7月20日 下午6:05:43
	 *
	 * @修  改  人：zjc
	 * @修改日期：20170721
	 * @修改描述：将addSubjectInfo接口修改为persistenceSubjectInfo，以适应前端的新增或修改
	 * @param subjectInfo
	 * @return
	 */
	boolean persistenceSubjectInfo(SubjectInfo subjectInfo) throws Exception;
	/**
	 * @功能描述：分页查询所有科目信息
	 * @创  建  者： zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年7月20日 下午6:05:49
	 *
	 * @修  改  人：zhoujincheng
	 * @版        本：V1.1.0
	 * @修改日期：20171025 10:39
	 * @修改描述：为了方法通用性，将模糊检索功能合并到该方法中；为了增加检索参数的可扩展性，这里将传入参数由PageUtil pageUtil修改为Map paramMap
	 * 
	 * @param 将pageUtil修改为paramMap
	 * @return
	 */
	List<SubjectInfo> findAllSubjectInfoByPage(Map paramMap) throws Exception;
	/**
	 * @功能描述：根据科目ID删除科目信息
	 * @创  建  者： zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年7月20日 下午6:05:54
	 *
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * @param subjectId
	 * @return
	 */
	boolean delSubjectInfoById(String subjectId) throws Exception;
	/**
	 * @功能描述：获取有效记录总条数，以便用于分页和前端展示使用
	 * @创  建  者： zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年7月27日 下午5:47:04
	 *
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * @param paramMap
	 * @return
	 */
	Long getCount(Map<String, Object> paramMap) throws Exception;
	
	/**
	 * @功能描述：获取所有科目信息
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月9日 下午3:27:46
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @return
	 */
	List<SubjectInfo> findAllSubjectInfo() throws Exception;
		
}
