package com.zzrenfeng.zznueg.dao;

import java.util.List;
import java.util.Map;

import com.zzrenfeng.base.dao.BaseMapper;
import com.zzrenfeng.zznueg.entity.AcademyInfo;
/**
 * @功能描述：学院信息Dao接口
 * @创  建  者：zhoujincheng
 * @版        本：V1.0.0
 * @创建日期：2017年7月29日 下午2:43:28
 * 
 * @修  改  人：
 * @修改日期：
 * @修改描述：
 *
 */
public interface AcademyInfoMapper extends BaseMapper<AcademyInfo> {
/*	
    int deleteByPrimaryKey(String acaId);

    int insert(AcademyInfo record);

    int insertSelective(AcademyInfo record);

    AcademyInfo selectByPrimaryKey(String acaId);

    int updateByPrimaryKeySelective(AcademyInfo record);

    int updateByPrimaryKey(AcademyInfo record);
    */
	
	/**
	 * @功能描述：分页查询所有学院信息
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年7月29日 下午2:43:45
	 * 
	 * @修  改  人：zhoujincheng
	 * @版        本：V1.1.0
	 * @修改日期：20171026 10:29
	 * @修改描述：为了方法通用性，这里将方法findAllByPage(paramMap)的传入参数由PageUtil pageUtil更改为Map paramMap；
	 * 			这样更有利方法传参的扩展；可以和多条件检索功能合并；
	 * 
	 * @param 由pageUtil ->更改为 paramMap
	 * @return
	 */
	List<AcademyInfo> findAllByPage(Map paramMap) throws Exception;
}