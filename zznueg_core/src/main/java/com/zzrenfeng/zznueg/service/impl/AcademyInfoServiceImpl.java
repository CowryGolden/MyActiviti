package com.zzrenfeng.zznueg.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zzrenfeng.base.entity.BaseDomain;
import com.zzrenfeng.base.utils.Constants;
import com.zzrenfeng.base.utils.PageUtil;
import com.zzrenfeng.base.utils.StringUtil;
import com.zzrenfeng.base.utils.UUIDUtils;
import com.zzrenfeng.zznueg.dao.AcademyInfoMapper;
import com.zzrenfeng.zznueg.entity.AcademyInfo;
import com.zzrenfeng.zznueg.service.AcademyInfoService;
import com.zzrenfeng.zznueg.utils.CommonConstants;
/**
 * @功能描述：学院信息Service接口实现类
 * @创  建  者：zhoujincheng
 * @版        本：V1.0.0
 * @创建日期：2017年7月29日 上午11:35:07
 * 
 * @修  改  人：
 * @修改日期：
 * @修改描述：
 *
 */
@Transactional
@Service("academyInfoService")
public class AcademyInfoServiceImpl implements AcademyInfoService {
	
	private final Logger LOGGER = LoggerFactory.getLogger(AcademyInfoServiceImpl.class);
	
	@Autowired
	protected AcademyInfoMapper academyInfoMapper;
	
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
	@Override
	public List<AcademyInfo> findAllAcaInfoByPage(Map paramMap) throws Exception{
		LOGGER.info("分页查找所有学院信息");
		return this.academyInfoMapper.findAllByPage(paramMap);
	}
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
	@Override
	public Long getCount(Map<String, Object> paramMap) throws Exception {
		return this.academyInfoMapper.getCount(paramMap);
	}
	
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
	@Override
	public boolean persistenceAcademyInfo(AcademyInfo academyInfo) throws Exception {
		String userId = Constants.getCurrendUser().getUserId();
		
		int saveOrUpdateCount = 0;
		if(StringUtil.isEmpty(academyInfo.getAcaId())) {
			BaseDomain.createLog(academyInfo, userId);
			academyInfo.setAcaId(UUIDUtils.getUpperUUID());
			academyInfo.setStatus(Constants.PERSISTENCE_STATUS);
			academyInfo.setAcaIcon(Constants.ACADEMY_ICON);
			academyInfo.setPrntId(CommonConstants.COMM_CONST_STRING_0);  //由于学院没有上级机构，因此将父ID统一设置成"0"
			saveOrUpdateCount = academyInfoMapper.insert(academyInfo);
		} else {
			BaseDomain.editLog(academyInfo, userId);
			saveOrUpdateCount = academyInfoMapper.updateByPrimaryKeySelective(academyInfo);
		}
		if(CommonConstants.COMM_CONST_INTEGER_1 == saveOrUpdateCount) {
			return true;
		}
		return false;
	}
	
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
	@Override
	public boolean delAcaInfoById(String acaId) throws Exception {
		return academyInfoMapper.deleteByPrimaryKey(acaId) > 0;
	}

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
	@Override
	public List<AcademyInfo> findAll() throws Exception {
		return academyInfoMapper.findAll();
	}

}
