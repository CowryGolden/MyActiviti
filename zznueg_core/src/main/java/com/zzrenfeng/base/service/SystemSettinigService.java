package com.zzrenfeng.base.service;

import com.zzrenfeng.base.entity.SysSetting;

public interface SystemSettinigService {
	/**
	 * @功能描述：持久化系统环境变量
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月26日 下午4:04:54
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param sysSetting
	 * @return
	 */
	boolean persistenceSysSetting(SysSetting sysSetting) throws Exception;
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
	SysSetting selectSysSettingByItemKey(String itemKey) throws Exception;

}
