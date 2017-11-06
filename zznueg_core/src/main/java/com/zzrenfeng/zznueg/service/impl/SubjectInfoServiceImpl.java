package com.zzrenfeng.zznueg.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//import com.zzrenfeng.base.utils.Constants;
import com.zzrenfeng.base.utils.PageUtil;
import com.zzrenfeng.base.utils.StringUtil;
import com.zzrenfeng.base.utils.UUIDUtils;
import com.zzrenfeng.zznueg.dao.SubjectInfoMapper;
import com.zzrenfeng.zznueg.entity.SubjectInfo;
import com.zzrenfeng.zznueg.service.SubjectInfoService;
import com.zzrenfeng.zznueg.utils.CommonConstants;
/**
 * @功能描述：科目信息服务接口实现类
 * @创  建  者： zhoujincheng
 * @版        本：V1.0.0
 * @创建日期：2017年7月20日 下午6:22:55
 *
 * @修  改  人：
 * @修改日期：
 * @修改描述：
 */
@Transactional
@Service("subjectInfoService")
public class SubjectInfoServiceImpl implements SubjectInfoService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SubjectInfoServiceImpl.class);
	
	@Autowired
    protected SubjectInfoMapper subjectInfoMapper;
	
	/**
	 * @功能描述：根据科目ID查询科目信息
	 * @创  建  者： zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年7月20日 下午6:05:39
	 *
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * @param subjectId
	 * @return
	 */
	@Override
	public SubjectInfo getSubjectInfoById(String subjectId) throws Exception {
//		this.subjectInfoMapper
		return null;
	}
	
	/**
	 * @功能描述：持久化科目信息（适应前端的新增或修改）
	 * @创  建  者： zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年7月20日 下午6:05:43
	 *
	 * @修  改  人：zjc
	 * @修改日期：20170721
	 * @修改描述：将addSubjectInfo接口修改为persistenceSubjectInfo，以适应前端的新增或修改
	 * @param subjectInfo
	 * @return
	 */
	@Override
	public boolean persistenceSubjectInfo(SubjectInfo subjectInfo) {
//		String currUserId = Constants.getCurrendUser().getUserId();  //获取当前操作的用户ID，若要记录该用户，就用此用户ID
		
		int saveOrUpdateCount = 0;    //新增或修改记录数
		
		if(StringUtil.isEmpty(subjectInfo.getSubjectId())) {  //新增
			subjectInfo.setSubjectId(UUIDUtils.getUpperUUID());
			subjectInfo.setIsValid(CommonConstants.FLAG_STR_VALID_1);
			subjectInfo.setCreated(new Date());
			saveOrUpdateCount = subjectInfoMapper.insert(subjectInfo);
		} else {  //修改
			saveOrUpdateCount = subjectInfoMapper.updateByPrimaryKeySelective(subjectInfo);
		}
		
		if(CommonConstants.COMM_CONST_INTEGER_1 == saveOrUpdateCount) {
			return true;
		}
		return false;
	}

	/**
	 * @功能描述：分页查询所有科目信息
	 * @创  建  者： zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年7月20日 下午6:05:49
	 *
	 * @修  改  人：zhoujincheng
	 * @版        本：V1.1.0
	 * @修改日期：20171025 10:39
	 * @修改描述：为了方法通用性，将模糊检索功能合并到该方法中；为了增加检索参数的可扩展性，这里将传入参数由PageUtil pageUtil修改为Map paramMap
	 * 
	 * @param 将pageUtil修改为paramMap
	 * @return
	 */
	@Override
	public List<SubjectInfo> findAllSubjectInfoByPage(Map paramMap) throws Exception {
		LOGGER.info("开始查找科目信息，分页显示");
		return this.subjectInfoMapper.findAllByPage(paramMap);
	}

	/**
	 * @功能描述：根据科目ID删除科目信息
	 * @创  建  者： zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年7月20日 下午6:05:54
	 *
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * @param subjectId
	 * @return
	 */
	@Override
	public boolean delSubjectInfoById(String subjectId) throws Exception {
		LOGGER.info("根据科目ID删除科目");
		return subjectInfoMapper.deleteByPrimaryKey(subjectId) > 0;		
	}

	/**
	 * @功能描述：获取有效记录总条数，以便用于分页和前端展示使用
	 * @创  建  者： zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年7月27日 下午5:47:04
	 *
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * @param paramMap
	 * @return
	 */
	@Override
	public Long getCount(Map<String, Object> paramMap) throws Exception {
		LOGGER.info("查询有效科目总数");
		return subjectInfoMapper.getCount(paramMap);
	}

	/**
	 * @功能描述：获取所有科目信息
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月9日 下午3:27:46
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @return
	 */
	@Override
	public List<SubjectInfo> findAllSubjectInfo() throws Exception {		
		return subjectInfoMapper.findAll();
	}

}
