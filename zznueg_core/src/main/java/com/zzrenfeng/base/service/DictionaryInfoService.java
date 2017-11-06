package com.zzrenfeng.base.service;

import java.util.List;
import java.util.Map;

import com.zzrenfeng.base.entity.DictionaryInfo;

/**
 * @功能描述：数据字典信息Service接口
 * @创  建  者：zhoujincheng
 * @版        本：V1.0.0
 * @创建日期：2017年9月13日 上午10:59:22
 * 
 * @修  改  人：
 * @修改日期：
 * @修改描述：
 *
 */
public interface DictionaryInfoService {
	/**
	 * @功能描述：按type分组，将type对应的字典队列放入map
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月13日 上午11:01:13
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @return
	 * @throws Exception
	 */
	Map<String, List<DictionaryInfo>> getAllDictionaryMap() throws Exception;

}
