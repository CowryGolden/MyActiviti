package com.zzrenfeng.zznueg.dao;

import java.util.List;

import com.zzrenfeng.base.dao.BaseMapper;
import com.zzrenfeng.zznueg.entity.AcaDepInfo;
/**
 * @功能描述：<!-- 说明：由于院系及班级要进行权限控制，并且院系及班级的层级关系和权限表的关系一致；
                                          这里仅使用院系的名称和编号，因此将院系及班级的信息存于权限表中 -->
 * @创  建  者： zhoujincheng
 * @版        本：V1.0.0
 * @创建日期：2017年7月28日 上午11:35:59
 *
 * @修  改  人：
 * @修改日期：
 * @修改描述：
 */
public interface AcaDepInfoMapper extends BaseMapper<AcaDepInfo> {
	/**
	 * @功能描述：获取所有院系信息
	 * @创  建  者： zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年7月28日 上午11:54:10
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
	 * @创建日期：2017年7月28日 下午4:14:28
	 *
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * @param pmsnCode
	 * @return
	 */
	List<AcaDepInfo> getChildAcaDepByCode(String pmsnCode) throws Exception;
}