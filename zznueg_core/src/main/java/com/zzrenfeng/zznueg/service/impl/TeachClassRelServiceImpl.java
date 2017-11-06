package com.zzrenfeng.zznueg.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zzrenfeng.base.entity.BaseDomain;
import com.zzrenfeng.base.utils.Constants;
import com.zzrenfeng.base.utils.StringUtil;
import com.zzrenfeng.base.utils.UUIDUtils;
import com.zzrenfeng.zznueg.dao.ClassInfoMapper;
import com.zzrenfeng.zznueg.dao.TeachClassRelMapper;
import com.zzrenfeng.zznueg.entity.ClassInfo;
import com.zzrenfeng.zznueg.entity.TeachClassRel;
import com.zzrenfeng.zznueg.service.TeachClassRelService;
import com.zzrenfeng.zznueg.utils.CommonConstants;
/**
 * @功能描述：教师教授班级Service接口实现类
 * @创  建  者：zhoujincheng
 * @版        本：V1.0.0
 * @创建日期：2017年8月7日 下午3:58:21
 * 
 * @修  改  人：
 * @修改日期：
 * @修改描述：
 *
 */
@Transactional
@Service("teachClassRelService")
public class TeachClassRelServiceImpl implements TeachClassRelService {
	private final Logger LOGGER = LoggerFactory.getLogger(TeachClassRelServiceImpl.class);
	
	@Autowired
	protected TeachClassRelMapper teachClassRelMapper;
	@Autowired
	protected ClassInfoMapper classInfoMapper;

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
	@Override
	public List<ClassInfo> getTeachClassRelByTuid(String teachUserId) throws Exception {
		LOGGER.info("获取教师ID为：" + teachUserId + " 的教授班级信息");
		
		List<TeachClassRel> tcrList = teachClassRelMapper.getTeachClassRelByTuid(teachUserId);
		List<ClassInfo> classInfoList = new ArrayList<ClassInfo>();
		
		for(TeachClassRel tcr : tcrList) {
			ClassInfo classInfo = classInfoMapper.selectByPrimaryKey(tcr.getClassId());
			classInfoList.add(classInfo);
		}
		
		return classInfoList;
	}

	/**
	 * @功能描述：保存教师分配的班级信息，保存到教师班级关系表中
	 * 			说明：这里采用方案一，方案是：将原来的关系记录全部删除后重新分配
	 * 			方案一：将原来的关系记录全部删除后重新分配插入新纪录
	 * 			方案二：先将老的关系记录保存到map，再将老的记录每条的状态设置为I-删除状态
	 * 				再将新送过来的分配班级ID与老的map中的班级ID对比，若存在，将记录状态再修改为E-有效状态
	 * 				比对出一条便从老记录的map中移除一条，若无比对出的即为新增，插入新纪录即可，
	 * 				最后处理完成后，将老map中剩余的没有对应的记录（即被前端移除的关系），从数据库中删除即可
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
	@Override
	public boolean saveTeachClassRel(Map<String, String> paramMap) throws Exception {
		String currUserId = Constants.getCurrendUser().getUserId();
		String teachUserId = paramMap.get("teachUserId");
		String checkedClassIds = paramMap.get("checkedClassIds");
		int delCount = 0;  //记录成功删除的原来教师班级关系记录数
		int addCount = 0;  //记录新增记录成功数
		Map<String, String> delParamMap = null; //用于存放要删除记录的参数：teachId和classId
		
		//盛放没有修改以前的教师与班级的对于关系记录，用于在修改后删除多余记录
		Map<String, TeachClassRel> oldTcrMap = new HashMap<String, TeachClassRel>();
		//获取某个教师ID对应的所有班级
		List<TeachClassRel> tcrList = teachClassRelMapper.getTeachClassRelByTuid(teachUserId);
//System.out.println("################>>>>" + tcrList == null);	//false
//System.out.println("################>>>>" + (tcrList.size() == 0));		//true
		//循环处理这些教师与班级的对应关系记录，逐一存放于map中，然后设置该记录为无效，用于删除（这里直接删除）
		if(null != tcrList && tcrList.size() > CommonConstants.COMM_CONST_INTEGER_0) {
			for(TeachClassRel tcr : tcrList) {
				String teachId = tcr.getTeachUserId();
				String classId = tcr.getClassId();
				//在教师班级关系记录中，对于某个教师来说，班级ID是互斥的，即一个老师不能同时多次教该班级，所以将其作为key处理
				oldTcrMap.put(classId.toString(), tcr);
				
				//组装删除参数
				delParamMap = new HashMap<>();
				delParamMap.put("teachUserId", teachId);
				delParamMap.put("classId", classId);
				//这里直接做硬删除处理，根据教师ID和班级ID联合删除记录
				if(delTeachClassRelByTidAndCid(delParamMap)) {
					delCount++;
				}
			}
		} else { //原来就没有分配班级，直接插入即可
			if (null != checkedClassIds && !"".equals(checkedClassIds)) {
	        	String[] classIds = checkedClassIds.split(",");
	        	for(String classId : classIds) {
	        		if(StringUtil.isEmpty(classId)) {
	        			continue;
	        		}
	        		//原来对应关系记录删除后，重新分配班级，持久化信息
	        		TeachClassRel newTcr = new TeachClassRel();
	        		BaseDomain.createLog(newTcr, currUserId);
	        		newTcr.setProfId(UUIDUtils.getUpperUUID());
	        		newTcr.setTeachUserId(teachUserId);
	        		newTcr.setClassId(classId);
	        		newTcr.setStatus(Constants.PERSISTENCE_STATUS);
	        		if(CommonConstants.COMM_CONST_INTEGER_1 == teachClassRelMapper.insert(newTcr)) {
	        			addCount++;
	        		}
	        	}
	        }
		}
		
		//重新获取某个教师ID对应的所有班级，为空表示删除干净，若原来有记录，判断是否删除干净
		List<TeachClassRel> tcrListTmp = teachClassRelMapper.getTeachClassRelByTuid(teachUserId);
		//开始处理修改后提交的对应数据，checkedClassIds为班级ID集合
		if(null != tcrList && tcrList.size() > 0 && delCount > 0 && (tcrListTmp == null || tcrListTmp.size() == 0)) { 
			if (null != checkedClassIds && !"".equals(checkedClassIds)) {
	        	String[] classIds = checkedClassIds.split(",");
	        	for(String classId : classIds) {
	        		if(StringUtil.isEmpty(classId)) {
	        			continue;
	        		}
	        		//原来对应关系记录删除后，重新分配班级，持久化信息
	        		TeachClassRel newTcr = new TeachClassRel();
	        		BaseDomain.createLog(newTcr, currUserId);
	        		newTcr.setProfId(UUIDUtils.getUpperUUID());
	        		newTcr.setTeachUserId(teachUserId);
	        		newTcr.setClassId(classId);
	        		newTcr.setStatus(Constants.PERSISTENCE_STATUS);
	        		if(CommonConstants.COMM_CONST_INTEGER_1 == teachClassRelMapper.insert(newTcr)) {
	        			addCount++;
	        		}
	        	}
	        }
		} 
		
		//新增成功返回true
		if(addCount > CommonConstants.COMM_CONST_INTEGER_0) {
			return true;  //说明新增成功
		} else if(addCount == CommonConstants.COMM_CONST_INTEGER_0 && delCount > CommonConstants.COMM_CONST_INTEGER_0) {
			return true;  //说明新增为0，删除原来记录
		}
		
		return false;
	}
	
	
	private boolean delTeachClassRelByTidAndCid(Map<String, String> paramMap) throws Exception {
		if(teachClassRelMapper.delTeachClassRelByTidAndCid(paramMap) == CommonConstants.COMM_CONST_INTEGER_1) {
			return true;
		}
		return false;
	}

}
