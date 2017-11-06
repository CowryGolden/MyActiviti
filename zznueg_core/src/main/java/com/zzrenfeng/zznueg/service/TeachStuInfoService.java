package com.zzrenfeng.zznueg.service;

import java.util.List;
import java.util.Map;

import com.zzrenfeng.base.model.MultiMenu;
import com.zzrenfeng.base.utils.PageUtil;
import com.zzrenfeng.zznueg.entity.TeachStuInfo;
import com.zzrenfeng.zznueg.entity.TestImportTeachStuInfo;

/**
 * @功能描述：教师学生信息Service接口
 * @创  建  者：zhoujincheng
 * @版        本：V1.0.0
 * @创建日期：2017年8月2日 上午11:25:20
 * 
 * @修  改  人：
 * @修改日期：
 * @修改描述：
 *
 */
public interface TeachStuInfoService {
	/**
	 * @功能描述：分页查询所有教师信息
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月2日 上午11:25:39
	 * 
	 * @修  改  人：zhoujincheng
	 * @版        本：V1.1.0
	 * @修改日期：20171016 14:15
	 * @修改描述：将传递参数PageUtil pageUtil修改为Map paramMap；
	 * 			目的是为了：增加按教师工号或姓名模糊搜索参数和是否开户标志参数；进行按条件检索 
	 * @param PageUtil pageUtil -> Map paramMap
	 * @return
	 */
	List<TeachStuInfo> findAllTeacherInfoByPage(Map paramMap) throws Exception;	
	/**
	 * @功能描述：分页查询所有学生信息
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月2日 上午11:25:53
	 * 
	 * @修  改  人：zhoujincheng
	 * @版        本：V1.1.0
	 * @修改日期：20171016 11:20
	 * @修改描述：将传递参数PageUtil pageUtil修改为Map paramMap；
	 * 			目的是为了：增加按学生学号或姓名模糊搜索参数和是否开户标志参数；进行按条件检索 
	 * @param PageUtil pageUtil -> Map paramMap
	 * @return
	 */
	List<TeachStuInfo> findAllStudentInfoByPage(Map paramMap) throws Exception;	
	/**
	 * @功能描述：新增或修改教师或学生信息持久化处理
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月2日 上午11:26:24
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param teachStuInfo
	 * @return
	 */
	boolean persistenceTeachStuInfo(TeachStuInfo teachStuInfo) throws Exception;
	/**
	 * @功能描述：获取教师总数
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月2日 上午11:27:29
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param paramMap
	 * @return
	 */
	Long getTeacherCount(Map<String, Object> paramMap) throws Exception;
	/**
	 * @功能描述：获取学生总数
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月2日 上午11:27:45
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param paramMap
	 * @return
	 */
	Long getStudentCount(Map<String, Object> paramMap) throws Exception;
	
	/**
	 * @功能描述：根据教师学生ID删除教师学生信息
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月2日 上午11:42:04
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param id
	 * @return
	 */
	boolean delTeachStuInfoById(String id) throws Exception;
	
	/**
	 * @功能描述：通过tsno-学号/工号查询是否存在教师学生记录
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月2日 下午5:57:16
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param tsno
	 * @return 存在返回true，不存在返回false
	 */
	boolean isExistTeachStuInfoByTsno(String tsno) throws Exception;
	
	/**
	 * @功能描述：获取所有院系班级层级关系
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月7日 上午9:40:28
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @return
	 */
	List<MultiMenu> getADCMultiMenu() throws Exception;
	
	/**
	 * @功能描述：批量导入学生信息
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年10月13日 下午4:25:21
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param importInfoList
	 * @return 返回导入成功的条数
	 * @throws Exception
	 */
	//int batchImportTeachStuInfo(List<TestImportTeachStuInfo> importInfoList) throws Exception;
	int batchImportTeachStuInfo(List<TeachStuInfo> importInfoList) throws Exception;

}
