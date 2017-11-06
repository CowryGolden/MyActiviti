package com.zzrenfeng.zznueg.service;

import java.util.List;

import com.zzrenfeng.zznueg.entity.AcaDepInfo;

/**
 * @功能描述：院系信息服务接口
 * @创  建  者： zhoujincheng
 * @版        本：V1.0.0
 * @创建日期：2017年7月28日 上午11:56:43
 *
 * @修  改  人：
 * @修改日期：
 * @修改描述：
 */
public interface AcaDepInfoService {
	/**
	 * @功能描述：获取所有院系信息
	 * @创  建  者： zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年7月28日 下午2:11:32
	 *
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * @return
	 */
	List<AcaDepInfo> getAllAcaDep() throws Exception;
	
	/**
     * @功能描述：根据父ID获取其一级子项
     * @创  建  者： zhoujincheng
     * @版        本：V1.0.0
     * @创建日期：2017年7月28日 下午2:22:09
     *
     * @修  改  人：
     * @修改日期：
     * @修改描述：
     * @param prntId
     * @return
     */
	List<AcaDepInfo> getAcaDepByPid(String prntId) throws Exception;
	/**
	 * @功能描述：根据父ID获取其一级子项的个数
	 * @创  建  者： zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年7月28日 下午2:37:21
	 *
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * @param prntId
	 * @return
	 */
	Long getAcaDepCountByPid(String prntId) throws Exception;
	/**
	 * @功能描述：根据学院code获取其一级子项（系别）
	 * @创  建  者： zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年7月28日 下午4:16:17
	 *
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * @param pmsnCode
	 * @return
	 */
	List<AcaDepInfo> getChildAcaDepByCode(String pmsnCode) throws Exception;
}
