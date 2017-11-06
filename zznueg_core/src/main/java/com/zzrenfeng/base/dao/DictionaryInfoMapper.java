package com.zzrenfeng.base.dao;

import java.util.List;
import java.util.Map;

import com.zzrenfeng.base.dao.BaseMapper;
import com.zzrenfeng.base.entity.DictionaryInfo;
/**
 * @功能描述：数据字典Dao
 * @创  建  者：zhoujincheng
 * @版        本：V1.0.0
 * @创建日期：2017年9月11日 下午2:14:53
 * 
 * @修  改  人：
 * @修改日期：
 * @修改描述：
 *
 */
public interface DictionaryInfoMapper extends BaseMapper<DictionaryInfo> {
/*	
    int deleteByPrimaryKey(String dictionaryId);

    int insert(DictionaryInfo record);

    int insertSelective(DictionaryInfo record);

    DictionaryInfo selectByPrimaryKey(String dictionaryId);

    int updateByPrimaryKeySelective(DictionaryInfo record);

    int updateByPrimaryKey(DictionaryInfo record);
*/    
	/**
	 * @功能描述：根据条件参数加载所有字典信息
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月13日 上午10:55:26
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	List<DictionaryInfo> loadAllDictionaryInfo(Map paramMap) throws Exception;
	
	/**
	 * @功能描述：获取数据字典中所有type数据
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月13日 上午11:13:23
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @return
	 * @throws Exception
	 */
	List<String> getAllDictionaryType() throws Exception;
    
}