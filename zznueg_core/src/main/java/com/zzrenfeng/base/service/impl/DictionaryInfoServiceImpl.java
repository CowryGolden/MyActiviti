package com.zzrenfeng.base.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zzrenfeng.base.dao.DictionaryInfoMapper;
import com.zzrenfeng.base.entity.DictionaryInfo;
import com.zzrenfeng.base.service.DictionaryInfoService;
/**
 * @功能描述：数据字典信息Service接口实现类
 * @创  建  者：zhoujincheng
 * @版        本：V1.0.0
 * @创建日期：2017年9月13日 上午11:03:37
 * 
 * @修  改  人：
 * @修改日期：
 * @修改描述：
 *
 */
@Transactional
@Service("dictionaryInfoService")
public class DictionaryInfoServiceImpl implements DictionaryInfoService {
	private final Logger LOGGER = LoggerFactory.getLogger(DictionaryInfoServiceImpl.class);
	
	@Autowired
	protected DictionaryInfoMapper dictionaryInfoMapper;
	
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
	@Override
	public Map<String, List<DictionaryInfo>> getAllDictionaryMap() throws Exception {
		Map<String, List<DictionaryInfo>> result = new HashMap<String, List<DictionaryInfo>>();
		List<DictionaryInfo> dictionaryList = this.dictionaryInfoMapper.loadAllDictionaryInfo(null);
		if(null != dictionaryList && !dictionaryList.isEmpty()) {
			List<String> types = this.dictionaryInfoMapper.getAllDictionaryType();
			if(null != types && !types.isEmpty()) {
				for(String type : types) {
					List<DictionaryInfo> list = new ArrayList<DictionaryInfo>();
					for (DictionaryInfo dictionary : dictionaryList) {
						if(dictionary.getType().equals(type)) {
							list.add(dictionary);
						}
					}
					result.put(type, list);
				}
			}
		}
		return result;
	}
	

}
