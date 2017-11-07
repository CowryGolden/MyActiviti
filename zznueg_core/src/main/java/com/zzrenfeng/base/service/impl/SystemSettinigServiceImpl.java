package com.zzrenfeng.base.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zzrenfeng.base.dao.SysSettingMapper;
import com.zzrenfeng.base.entity.BaseDomain;
import com.zzrenfeng.base.entity.SysSetting;
import com.zzrenfeng.base.service.SystemSettinigService;
import com.zzrenfeng.base.utils.Constants;
import com.zzrenfeng.base.utils.StringUtil;
import com.zzrenfeng.base.utils.UUIDUtils;

@Transactional
@Service("systemSettinigService")
public class SystemSettinigServiceImpl implements SystemSettinigService {
	private static final Logger LOGGER = LoggerFactory.getLogger(SystemSettinigServiceImpl.class);
	
	@Autowired
	protected SysSettingMapper sysSettingMapper;
	
	/**
	 * @功能描述：持久化系统环境变量
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月26日 下午4:04:54
	 * 
	 * @修  改  人：zjc
	 * @修改日期：20170927 10:33
	 * @修改描述：控制系统环境变量中设置项itemKey的唯一性，也就是说，数据字典中的一个设置项（dictionaryId=itemKey）只能有一个设置值（itemValue）
	 * 			目前还没有遇到其他场景一个设置项可以设置多个值的情况，因此该方法待商榷，待优化。
	 * 
	 * @param sysSetting
	 * @return
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public boolean persistenceSysSetting(SysSetting sysSetting)  throws Exception {
		String userId = Constants.getCurrendUser().getUserId();
		int saveOrUpdateCount = 0;
		SysSetting ss = this.sysSettingMapper.selectSysSettingByItemKey(sysSetting.getItemKey());
		if(!StringUtil.isEmpty(sysSetting.getItemKey())) {
			if(null == ss) {
				if(StringUtil.isEmpty(sysSetting.getSettingId())) {
					BaseDomain.createLog(sysSetting, userId);
					sysSetting.setSettingId(UUIDUtils.getUpperUUID());
					saveOrUpdateCount = this.sysSettingMapper.insert(sysSetting);
				}
			} else {
				ss.setItemValue(sysSetting.getItemValue());  //将新设置的值放于查出的环境变量对象中
				BaseDomain.editLog(ss, userId);
				saveOrUpdateCount = this.sysSettingMapper.updateByPrimaryKeySelective(ss);
			}
		} else {
			LOGGER.info("设置项键itemKey为空，不能持久化");
			return false;
		}		
		
		if(Constants.COMM_CONST_INTEGER_1 == saveOrUpdateCount) {
			return true;
		}
		return false;
	}

	/**
	 * @功能描述：根据设置项ID获取系统环境变量对象
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月27日 上午9:51:10
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param settingId
	 * @return
	 * @throws Exception
	 */
	@Override
	public SysSetting selectSysSettingByItemKey(String itemKey) throws Exception {
		return this.sysSettingMapper.selectSysSettingByItemKey(itemKey);
	}
	
	
}
