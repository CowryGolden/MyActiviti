package com.zzrenfeng.zznueg.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zzrenfeng.zznueg.dao.AcaDepInfoMapper;
import com.zzrenfeng.zznueg.entity.AcaDepInfo;
import com.zzrenfeng.zznueg.service.AcaDepInfoService;
/**
 * @功能描述：院系信息服务接口实现类
 * @创  建  者： zhoujincheng
 * @版        本：V1.0.0
 * @创建日期：2017年7月28日 下午2:42:17
 *
 * @修  改  人：
 * @修改日期：
 * @修改描述：
 */
@Transactional
@Service("acaDepInfoService")
public class AcaDepInfoServiceImpl implements AcaDepInfoService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AcaDepInfoServiceImpl.class);
	
	@Autowired
	protected AcaDepInfoMapper acaDepInfoMapper;
	
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
	@Override
	public List<AcaDepInfo> getAllAcaDep() throws Exception {
		return acaDepInfoMapper.getAllAcaDep();
	}
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
	@Override
	public List<AcaDepInfo> getAcaDepByPid(String prntId) throws Exception {
		return acaDepInfoMapper.getAcaDepByPid(prntId);
	}
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
	@Override
	public Long getAcaDepCountByPid(String prntId) throws Exception {
		return acaDepInfoMapper.getAcaDepCountByPid(prntId);
	}
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
	@Override
	public List<AcaDepInfo> getChildAcaDepByCode(String pmsnCode) throws Exception {
		LOGGER.info("根据学院CODE获取该学院下的所有系别");
		return acaDepInfoMapper.getChildAcaDepByCode(pmsnCode);
	}

}
