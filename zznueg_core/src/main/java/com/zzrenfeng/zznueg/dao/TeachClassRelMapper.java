package com.zzrenfeng.zznueg.dao;

import java.util.List;
import java.util.Map;

import com.zzrenfeng.base.dao.BaseMapper;
import com.zzrenfeng.zznueg.entity.TeachClassRel;
/**
 * @功能描述：教师教授班级关系表Dao
 * @创  建  者：zhoujincheng
 * @版        本：V1.0.0
 * @创建日期：2017年8月7日 下午3:52:01
 * 
 * @修  改  人：
 * @修改日期：
 * @修改描述：
 *
 */
public interface TeachClassRelMapper extends BaseMapper<TeachClassRel> {
/*	
    int deleteByPrimaryKey(String profId);

    int insert(TeachClassRel record);

    int insertSelective(TeachClassRel record);

    TeachClassRel selectByPrimaryKey(String profId);

    int updateByPrimaryKeySelective(TeachClassRel record);

    int updateByPrimaryKey(TeachClassRel record);
*/  
	/**
	 * @功能描述：根据教师ID查找其分配的班级
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月7日 下午3:52:21
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param teachUserId
	 * @return
	 */
	List<TeachClassRel> getTeachClassRelByTuid(String teachUserId) throws Exception;;
	
	/**
	 * @功能描述：根据教师ID和班级ID联合删除记录
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月7日 下午6:47:59
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	int delTeachClassRelByTidAndCid(Map<String, String> paramMap) throws Exception;
    
}