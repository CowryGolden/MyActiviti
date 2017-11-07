package com.zzrenfeng.zznueg.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zzrenfeng.base.dao.BaseMapper;
import com.zzrenfeng.base.utils.PageUtil;
import com.zzrenfeng.zznueg.entity.StuUploadInfo;

/**
 * @功能描述：学生上传课件信息Dao
 * @创  建  者：zhoujincheng
 * @版        本：V1.0.0
 * @创建日期：2017年8月8日 上午11:31:20
 * 
 * @修  改  人：
 * @修改日期：
 * @修改描述：
 *
 */
public interface StuUploadInfoMapper extends BaseMapper<StuUploadInfo> {
/*	
    int deleteByPrimaryKey(String pid);

    int insert(StuUploadInfo record);

    int insertSelective(StuUploadInfo record);

    StuUploadInfo selectByPrimaryKey(String pid);

    int updateByPrimaryKeySelective(StuUploadInfo record);

    int updateByPrimaryKey(StuUploadInfo record);
*/    
	/* ############################学生平台部分接口############################	*/
	
    /**
     * @功能描述：学生平台——获取某个学生某个科目已经成功上传的次数（根据学生ID和科目ID联合查询）
     * @创  建  者：zhoujincheng
     * @版        本：V1.0.0
     * @创建日期：2017年8月9日 下午7:21:28
     * 
     * @修  改  人：
     * @修改日期：
     * @修改描述：
     * 
     * @param paramMap
     * @return
     * @throws Exception
     */
	@SuppressWarnings("rawtypes")
	Integer getUploadedCountByStuIdAndSubId(Map paramMap) throws Exception;
	
	/**
	 * @功能描述：学生平台——分页查询所有上传科目信息
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月11日 下午2:09:43
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param pageUtil
	 * @return
	 * @throws Exception
	 */
	List<Map> findAllByPage(PageUtil pageUtil) throws Exception;
	
	/**
	 * @功能描述：学生平台——根据学生ID查询每个学生各自上传的信息
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月16日 下午7:09:40
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	List<Map> findAllByPageByStuUid(Map paramMap) throws Exception;
	
	/**
	 * @功能描述：学生平台——根据科目ID分别查询各个科目的上传信息
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月11日 下午2:11:44
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：修改参数类型为Map
	 * 
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	List<Map> getUploadInfoBySubId(Map paramMap) throws Exception;
	
	/**
	 * @功能描述：学生平台——根据学生ID获取有效记录总数
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月16日 下午7:31:30
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param paramMap
	 * @return
	 */
	Long getCountByStuUid(Map<String, Object> paramMap) throws Exception;
	
	/**
	 * @功能描述：学生平台——根据学生ID获取该学生的基本信息
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月22日 上午11:42:57
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	Map getStuInfoBySuid(Map paramMap) throws Exception;
	
	/**
	 * @功能描述：学生平台——学生关心的是每次上传的科目是否得到最好的成绩，根据最近每科最高成绩定位，即取每科分数最高的
	 * 			特别说明：与教师平台中的getUploadListBySuid4Teach(Map paramMap)方法分离，不再合并使用，因为两者关系的内容不同
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月24日 上午9:49:53
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	List<Map> getUploadListBySuid4Stu(Map paramMap) throws Exception;
	
	
/* ############################教师平台部分接口############################	*/
	
	/**
	 * @功能描述：教师平台——教师查询所教授班级对应的学生列表
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月17日 下午6:46:43
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	List<Map> getStuListByTcid4Teach(Map paramMap) throws Exception;
	
	/**
	 * @功能描述：教师平台——教师根据学生列表的信息点击“上一位”或“下一位”根据学生ID查询学生最近上传的资源列表；(学生平台不再使用该方法，另立新方法)
	 * 			教师平台——教师关心最近上传的成绩是否评分，根据每科最近上传记录定位，即取每科次数最大的
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月17日 下午6:46:50
	 * 
	 * @修  改  人：zjc
	 * @修改日期：20170824 09:44
	 * @修改描述：修改注释：将“学生平台也可以使用此方法获取该学生的成绩信息”删除，增加(学生平台不再使用该方法，另立新方法)，以及新的注释增加
	 * 
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	List<Map> getUploadListBySuid4Teach(Map paramMap) throws Exception;
	
	
	
	/**
	 * @功能描述：教师平台——先根据教师ID查出其教授的班级，过滤无效(没传资料的)的记录后，再根据学生ID过滤单个学生信息及其资源信息
	 * 			(写法太综合，不一定能使用到)
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月17日 下午6:47:08
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	List<Map> getStuUpInfoListByTuidAndSuid4Teach(Map paramMap) throws Exception;
	
	/**
	 * @功能描述：教师平台——教师查询所教授班级对应的学生列表（v1.1：增加科目及评分信息，用于学生概览页信息显示使用，增加分页）
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月21日 下午5:07:55
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	List<Map> getStuOverviewByTuid4Teach(Map paramMap) throws Exception;
	
	/**
	 * @功能描述：教师平台——根据教师ID获取教授的学生总数(以供分页使用)
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月21日 下午5:09:10
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	Long getStuCountByTuid(Map paramMap) throws Exception;
	
	
	
}