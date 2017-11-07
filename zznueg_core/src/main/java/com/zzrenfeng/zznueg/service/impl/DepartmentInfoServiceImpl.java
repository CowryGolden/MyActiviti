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
import com.zzrenfeng.base.utils.StringUtil;
import com.zzrenfeng.base.utils.UUIDUtils;
import com.zzrenfeng.zznueg.dao.DepartmentInfoMapper;
import com.zzrenfeng.zznueg.entity.DepartmentInfo;
import com.zzrenfeng.zznueg.service.DepartmentInfoService;
import com.zzrenfeng.zznueg.utils.CommonConstants;
/**
 * @功能描述：系部信息接Service接口实现类
 * @创  建  者：zhoujincheng
 * @版        本：V1.0.0
 * @创建日期：2017年7月31日 下午3:39:42
 * 
 * @修  改  人：
 * @修改日期：
 * @修改描述：
 *
 */
@Transactional
@Service("departmentInfoService")
public class DepartmentInfoServiceImpl implements DepartmentInfoService {
	private final Logger LOGGER = LoggerFactory.getLogger(DepartmentInfoServiceImpl.class);
	
	@Autowired
	protected DepartmentInfoMapper departmentInfoMapper;

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
	@Override
	public List<DepartmentInfo> getDeptInfoByAcaId(Map paramMap) throws Exception {
		LOGGER.info("根据学院ID获取系部信息");
		return this.departmentInfoMapper.getDeptInfoByAcaId(paramMap);
	}

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
	@Override
	public boolean persistenceDepartmentInfo(DepartmentInfo departmentInfo) throws Exception {
		String userId = Constants.getCurrendUser().getUserId();
		int saveOrUpdateCount = 0;
		if(StringUtil.isEmpty(departmentInfo.getDeptId())) {
			BaseDomain.createLog(departmentInfo, userId);
			departmentInfo.setDeptId(UUIDUtils.getUpperUUID());
			departmentInfo.setStatus(Constants.PERSISTENCE_STATUS);
			saveOrUpdateCount = this.departmentInfoMapper.insert(departmentInfo);
		} else {
			BaseDomain.editLog(departmentInfo, userId);
			saveOrUpdateCount = this.departmentInfoMapper.updateByPrimaryKeySelective(departmentInfo);
		}
		
		if(CommonConstants.COMM_CONST_INTEGER_1 == saveOrUpdateCount) {
			return true;
		}		
		return false;
	}

}
