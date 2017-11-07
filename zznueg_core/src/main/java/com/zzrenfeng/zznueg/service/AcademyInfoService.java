package com.zzrenfeng.zznueg.service;

import java.util.List;
import java.util.Map;

import com.zzrenfeng.base.utils.PageUtil;
import com.zzrenfeng.zznueg.entity.AcademyInfo;

/**
 * @功能描述：学院信息Service接口
 * @创  建  者：zhoujincheng
 * @版        本：V1.0.0
 * @创建日期：2017年7月29日 上午11:35:07
 * 
 * @修  改  人：
 * @修改日期：
 * @修改描述：
 *
 */
public interface AcademyInfoService {
	/**
	 * @功能描述：分页查询所有学院信息
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年7月29日 上午11:36:48
	 * 
	 * @修  改  人：zhoujincheng
	 * @版        本：V1.1.0
	 * @修改日期：20171026 10:29
	 * @修改描述：为了方法通用性，这里将academyInfoService.findAllAcaInfoByPage(paramMap)的传入参数
	 * 			由PageUtil pageUtil更改为Map paramMap；这样更有利方法传参的扩展；可以和多条件检索功能合并；
	 * 
	 * @return
	 */
	List<AcademyInfo> findAllAcaInfoByPage(Map paramMap) throws Exception;
	/**
	 * @功能描述：获取有效记录总条数，以便用于分页和前端展示使用
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年7月29日 下午2:54:54
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param paramMap
	 * @return
	 */
	Long getCount(Map<String, Object> paramMap) throws Exception;
	/**
	 * @功能描述：持久化学院信息
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年7月29日 下午3:20:38
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param academyInfo
	 * @return
	 */
	boolean persistenceAcademyInfo(AcademyInfo academyInfo) throws Exception;
	/**
	 * @功能描述：根据ID删除学院信息（硬删除）
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年7月29日 下午3:40:40
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param acaId
	 * @return
	 */
	boolean delAcaInfoById(String acaId) throws Exception;
	/**
	 * @功能描述：查询所有学院信息
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年7月29日 下午5:40:40
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @return 
	 */
	List<AcademyInfo> findAll() throws Exception;
}
