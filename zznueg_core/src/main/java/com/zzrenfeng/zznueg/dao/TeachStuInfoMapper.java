package com.zzrenfeng.zznueg.dao;

import java.util.List;
import java.util.Map;

import com.zzrenfeng.base.dao.BaseMapper;
import com.zzrenfeng.base.utils.PageUtil;
import com.zzrenfeng.zznueg.entity.TeachStuInfo;
import com.zzrenfeng.zznueg.model.AcaDeptClassInfoModel;
/**
 * @功能描述：教师学生信息Dao接口
 * @创  建  者：zhoujincheng
 * @版        本：V1.0.0
 * @创建日期：2017年8月2日 上午9:49:25
 * 
 * @修  改  人：
 * @修改日期：
 * @修改描述：
 *
 */
public interface TeachStuInfoMapper extends BaseMapper<TeachStuInfo> {
/*	
    int deleteByPrimaryKey(String id);

    int insert(TeachStuInfo record);

    int insertSelective(TeachStuInfo record);

    TeachStuInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TeachStuInfo record);

    int updateByPrimaryKey(TeachStuInfo record);
    */
	
	/**
	 * @功能描述：分页查询所有教师信息
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月2日 上午11:25:39
	 * 
	 * @修  改  人：zjc
	 * @版        本：V1.1.0
	 * @修改日期：20171016 11:17
	 * @修改描述：增加按教师工号或姓名模糊搜索参数和是否开户标志参数；进行按条件检索
	 * 
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
	 * @修  改  人：zjc
	 * @版        本：V1.1.0
	 * @修改日期：20171016 11:17
	 * @修改描述：增加按学生学号或姓名模糊搜索参数和是否开户标志参数；进行按条件检索
	 * 
	 * @param PageUtil pageUtil -> Map paramMap
	 * @return
	 */
	List<TeachStuInfo> findAllStudentInfoByPage(Map paramMap) throws Exception;	

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
	 * @功能描述：通过tsno-学号/工号查询是否存在教师学生记录数
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月2日 下午5:48:29
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param tsno
	 * @return 存在的记录数
	 */
	Integer getTeachStuInfoCountByTsno(String tsno) throws Exception;
	/**
	 * @功能描述：获取所有院系班级层级关系
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月7日 上午9:36:33
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @return
	 */
    List<AcaDeptClassInfoModel> getAllAcaDeptClassInfoTrees() throws Exception;
}