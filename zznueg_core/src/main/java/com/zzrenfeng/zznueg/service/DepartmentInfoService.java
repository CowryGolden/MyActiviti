package com.zzrenfeng.zznueg.service;

import java.util.List;
import java.util.Map;

import com.zzrenfeng.zznueg.entity.DepartmentInfo;

/**
 * @功能描述：系部信息接Service接口
 * @创  建  者：zhoujincheng
 * @版        本：V1.0.0
 * @创建日期：2017年7月31日 下午3:35:20
 * 
 * @修  改  人：
 * @修改日期：
 * @修改描述：
 *
 */
public interface DepartmentInfoService {
	/**
	 * @功能描述：根据学院ID获取系部信息
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年7月31日 下午3:36:28
	 * 
	 * @修  改  人：zhoujincheng
	 * @版        本：V1.1.0
	 * @修改日期：20171026 11:13
	 * @修改描述：为了方法通用性，这里将departmentInfoService.getDeptInfoByAcaId(paramMap)的传入参数
	 * 			由String acaId更改为Map paramMap；这样更有利方法传参的扩展；可以和多条件检索功能合并；
	 * 
	 * @param acaId -> paramMap
	 * @return
	 */
	List<DepartmentInfo> getDeptInfoByAcaId(Map paramMap) throws Exception;
	
	/**
	 * @功能描述：持久化系部信息，以供前端新增或修改使用
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年7月31日 下午4:44:56
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param departmentInfo
	 * @return
	 */
	boolean persistenceDepartmentInfo(DepartmentInfo departmentInfo) throws Exception;

}
