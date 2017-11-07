package com.zzrenfeng.base.dao;

import com.zzrenfeng.base.entity.SysSetting;
/**
 * @功能描述：系统环境变量Dao接口
 * @创  建  者：zhoujincheng
 * @版        本：V1.0.0
 * @创建日期：2017年9月23日 上午11:42:13
 * 
 * @修  改  人：
 * @修改日期：
 * @修改描述：
 *
 */
public interface SysSettingMapper extends BaseMapper<SysSetting> {
/*    int deleteByPrimaryKey(String settingId);

    int insert(SysSetting record);

    int insertSelective(SysSetting record);

    SysSetting selectByPrimaryKey(String settingId);

    int updateByPrimaryKeySelective(SysSetting record);

    int updateByPrimaryKey(SysSetting record);*/
	
	/**
	 * @功能描述：根据设置项ID获取系统环境变量对象
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月27日 上午10:08:32
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param itemKey
	 * @return
	 * @throws Exception
	 */
	SysSetting selectSysSettingByItemKey(String itemKey) throws Exception;
	
	
    
}