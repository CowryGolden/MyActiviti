package com.zzrenfeng.zznueg.service;

import java.util.List;
import java.util.Map;

import com.zzrenfeng.base.model.TreeModel;
import com.zzrenfeng.zznueg.entity.ClassInfo;
/**
 * @功能描述：班级信息Service接口
 * @创  建  者：zhoujincheng
 * @版        本：V1.0.0
 * @创建日期：2017年8月1日 上午11:01:43
 * 
 * @修  改  人：
 * @修改日期：
 * @修改描述：
 *
 */
public interface ClassInfoService {
	/**
	 * @功能描述：获取学院-系部信息两级树型结构List
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月1日 上午11:02:01
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @return
	 */
	List<TreeModel> getAcaDeptTreeList() throws Exception;
	
	/**
	 * @功能描述：获取学院-系部-班级信息三级树形结构List
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年10月23日 上午11:39:31
	 * 
	 * @修  改  人：
	 * @版        本：V1.1.0
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @return
	 * @throws Exception
	 */
	List<TreeModel> getAcaDeptClassTreeList() throws Exception;

	/**
	 * @功能描述：根据系部ID获取所属的所有班级信息
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月1日 下午3:32:43
	 * 
	 * @修  改  人：zhoujincheng
	 * @版        本：V1.1.0
	 * @修改日期：20171027 11:15
	 * @修改描述：将方法getClassInfoByDeptId()的传递参数由String deptId修改为Map paramMap，
	 * 			这样更有利方法传参的扩展；可以和多条件检索功能合并；
	 * 
	 * @param deptId -> paramMap
	 * @return
	 */
	List<ClassInfo> getClassInfoByDeptId(Map paramMap) throws Exception;

	/**
	 * @功能描述：新增或修改班级信息统一处理类
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月1日 下午5:36:44
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param classInfo
	 * @return
	 */
	boolean persistenceClassInfo(ClassInfo classInfo) throws Exception;

	/**
	 * @功能描述：根据班级ID删除班级信息（硬删除）
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月1日 下午6:54:24
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param classId
	 * @return
	 */
	boolean delClassInfoById(String classId) throws Exception;
	
	/**
	 * @功能描述：分页查询班级信息，可以根据非空条件进行筛选；可以代替上述getClassInfoByDeptId()方法使用
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年10月30日 下午2:18:31
	 * 
	 * @修  改  人：
	 * @版        本：V1.1.0
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	List<ClassInfo> getClassInfoByPage(Map paramMap) throws Exception;
	
	/**
	 * @功能描述：获取满足条件的记录总数，供分页使用
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年10月30日 下午2:55:00
	 * 
	 * @修  改  人：
	 * @版        本：V1.1.0
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param paramMap
	 * @return
	 */
	Long getClassInfoCount(Map<String, Object> paramMap);

}
