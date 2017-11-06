package com.zzrenfeng.zznueg.service;

import java.util.List;
import java.util.Map;

import com.zzrenfeng.zznueg.entity.ClassInfo;

/**
 * @功能描述：教师教授班级Service接口
 * @创  建  者：zhoujincheng
 * @版        本：V1.0.0
 * @创建日期：2017年8月7日 下午3:55:41
 * 
 * @修  改  人：
 * @修改日期：
 * @修改描述：
 *
 */
public interface TeachClassRelService {

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
	 * @throws Exception 
	 */
	List<ClassInfo> getTeachClassRelByTuid(String teachUserId) throws Exception;
	
	/**
	 * @功能描述：保存教师分配的班级信息，保存到教师班级关系表中
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月7日 下午5:23:23
	 * 
	 * @修  改  人：zjc
	 * @修改日期：2017年8月7日 下午7:02:23
	 * @修改描述：将接口中的分散参数存放于map中，统一传送
	 * 
	 * @param teachUserId 和 checkedClassIds 统一存放到map中
	 * @param paramMap
	 * @return
	 * @throws Exception 
	 */
	//boolean saveTeachClassRel(String teachUserId, String checkedClassIds) throws Exception;
	boolean saveTeachClassRel(Map<String, String> paramMap) throws Exception;
}
