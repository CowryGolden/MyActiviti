package com.zzrenfeng.zznueg.dao;

import java.util.List;
import java.util.Map;

import com.zzrenfeng.base.dao.BaseMapper;
import com.zzrenfeng.zznueg.entity.DepartmentInfo;
/**
 * @功能描述：系部信息Dao接口，父接口中已经存在的如果子接口中没有特殊要求直接继承即可
 * @创  建  者：zhoujincheng
 * @版        本：V1.0.0
 * @创建日期：2017年7月31日 下午3:31:33
 * 
 * @修  改  人：
 * @修改日期：
 * @修改描述：
 *
 */
public interface DepartmentInfoMapper extends BaseMapper<DepartmentInfo> {
/*	
    int deleteByPrimaryKey(String deptId);

    int insert(DepartmentInfo record);

    int insertSelective(DepartmentInfo record);

    DepartmentInfo selectByPrimaryKey(String deptId);

    int updateByPrimaryKeySelective(DepartmentInfo record);

    int updateByPrimaryKey(DepartmentInfo record);
    */
	/**
	 * @功能描述：根据学院ID获取系部信息
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年7月31日 下午3:32:39
	 * 
	 * @修  改  人：zhoujincheng
	 * @版        本：V1.1.0
	 * @修改日期：20171026 11:13
	 * @修改描述：为了方法通用性，这里将方法getDeptInfoByAcaId(paramMap)的传入参数由String acaId更改为Map paramMap；
	 * 			这样更有利方法传参的扩展；可以和多条件检索功能合并；
	 * 
	 * @param acaId -> paramMap
	 * @return
	 * List<DepartmentInfo> getDeptInfoByAcaId(@Param("acaId") String acaId);
	 */
	List<DepartmentInfo> getDeptInfoByAcaId(Map paramMap) throws Exception;
}