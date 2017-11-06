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
import com.zzrenfeng.base.model.TreeModel;
import com.zzrenfeng.base.utils.Constants;
import com.zzrenfeng.base.utils.StringUtil;
import com.zzrenfeng.base.utils.UUIDUtils;
import com.zzrenfeng.zznueg.dao.AcademyInfoMapper;
import com.zzrenfeng.zznueg.dao.ClassInfoMapper;
import com.zzrenfeng.zznueg.dao.DepartmentInfoMapper;
import com.zzrenfeng.zznueg.entity.AcademyInfo;
import com.zzrenfeng.zznueg.entity.ClassInfo;
import com.zzrenfeng.zznueg.entity.DepartmentInfo;
import com.zzrenfeng.zznueg.service.ClassInfoService;
/**
 * @功能描述：班级信息Service接口实现类
 * @创  建  者：zhoujincheng
 * @版        本：V1.0.0
 * @创建日期：2017年8月1日 上午11:12:55
 * 
 * @修  改  人：
 * @修改日期：
 * @修改描述：
 *
 */
import com.zzrenfeng.zznueg.utils.CommonConstants;
@Transactional
@Service("classInfoService")
public class ClassInfoServiceImpl implements ClassInfoService {
	private final Logger LOGGER = LoggerFactory.getLogger(ClassInfoServiceImpl.class);
	
	@Autowired
	protected ClassInfoMapper classInfoMapper;
	@Autowired
	protected AcademyInfoMapper academyInfoMapper;
	@Autowired
	protected DepartmentInfoMapper departmentInfoMapper;
	
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
	@Override
	public List<TreeModel> getAcaDeptTreeList()  throws Exception{
		LOGGER.info("获取学院-系部信息两级树型结构List---->>start");
		/*
		 * 获取所有的学院信息，并用TreeModel
		 */
		List<AcademyInfo> acaInfoList = this.academyInfoMapper.findAll();
		//将根节点为"0"的学院递归转换为树型模型（虽然支持无限极学院，业务约束为一级，但通用的为无极模型）
		List<TreeModel> acaTreesList = acaInfoToTreeModel(CommonConstants.COMM_CONST_STRING_0, acaInfoList);
		//将子节点添加到树型结构下；即:将系部信息添加到学院下面
		deptChildrenAddToAcaTree(acaTreesList);
		
		LOGGER.info("获取学院-系部信息两级树型结构List---->>end");
		return acaTreesList;
	}
	
	/**
	 * @功能描述：获取学院-系部-班级三级树形结构List
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
	@Override
	public List<TreeModel> getAcaDeptClassTreeList() throws Exception {
		LOGGER.info("获取学院-系部-班级信息三级树形结构List---->>start");
		List<TreeModel> acaDeptTreeList = getAcaDeptTreeList();
//System.out.println("+++++++++++++++++++++++++acaDeptTreeList---->>>>" + acaDeptTreeList);		
		classChildrenAddToAcaDeptTree(acaDeptTreeList);
		LOGGER.info("获取学院-系部-班级信息三级树形结构List---->>end");
		return acaDeptTreeList;
	}
	
	/**
	 * @功能描述：将子节点添加到树型结构下；即:将系部信息添加到学院下面
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月1日 下午2:23:36
	 * 
	 * @修  改  人：zhoujincheng
	 * @版        本：V1.1.0
	 * @修改日期：20171023 11:11
	 * @修改描述：修改方法名childrenAddToTree为deptChildrenAddToAcaTree
	 * 
	 * @修  改  人：zhoujincheng
	 * @版        本：V1.1.1
	 * @修改日期：20171026 13:51
	 * @修改描述：由于方法departmentInfoMapper.getDeptInfoByAcaId(paramMap)的传递参数由String acaId修改为Map paramMap，这里随之变动性；
	 * 
	 * @param list
	 */
	private void deptChildrenAddToAcaTree(List<TreeModel> acaTreesList)  throws Exception{
//		LOGGER.info("将子节点添加到树型结构下；即:将系部信息添加到学院下面---->>start");
		Map paramMap = null;
		for(TreeModel acaTree : acaTreesList) {
			List<TreeModel> acaInfoChild = acaTree.getChildren();
			if(null == acaInfoChild || acaInfoChild.isEmpty()) {
				//获取每个节点的ID，即学院ID
				String acaId = acaTree.getId();
				paramMap = new HashMap<>();
				paramMap.put("acaId", acaId);
				//获取该学院下属所有的系部
				List<DepartmentInfo> deptList = departmentInfoMapper.getDeptInfoByAcaId(paramMap);
				//把系部信息叶子节点加入到学院树型节点中
				addDeptToAca(deptList, acaInfoChild);
			} else {
				List<TreeModel> childrenList = acaTree.getChildren();
				//内部递归添加孩子节点（学院不是一级的情况）
				deptChildrenAddToAcaTree(childrenList);
				//获取每个节点的ID，即学院ID
				String acaId = acaTree.getId();
				paramMap = new HashMap<>();
				paramMap.put("acaId", acaId);
				//获取该学院下属所有的系部
				List<DepartmentInfo> deptList = departmentInfoMapper.getDeptInfoByAcaId(paramMap);
				//把系部信息叶子节点加入到学院树型节点中
				addDeptToAca(deptList, childrenList);
			}
		}
	}
	
	/**
	 * @功能描述：将班级添加到院系树形结构下
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年10月23日 上午11:44:39
	 * 
	 * @修  改  人：zjc
	 * @版        本：V1.1.0
	 * @修改日期：20171027 11:18
	 * @修改描述：由于方法classInfoMapper.getClassInfoByDeptId()的参数由String deptId修改为Map paramMap，这里联动修改
	 *
	 */
	private void classChildrenAddToAcaDeptTree(List<TreeModel> acaDeptTreeList)  throws Exception{
		Map paramMap = null;
		for(TreeModel acaDeptTree : acaDeptTreeList) {
			List<TreeModel> acaDeptChild = acaDeptTree.getChildren();
			if(null == acaDeptChild || acaDeptChild.isEmpty()) {
				//获取每个节点的ID，即系部ID
				String deptId = acaDeptTree.getId();
				paramMap = new HashMap<>();
				paramMap.put("deptId", deptId);
				//获取该系部下属所有的班级
				List<ClassInfo> classList = classInfoMapper.getClassInfoByDeptId(paramMap);
				//把班级信息叶子节点加入到学院-系部树型节点中
				addClassToAcaDept(classList, acaDeptChild);
			} else {
				List<TreeModel> childrenList = acaDeptTree.getChildren();
				//内部递归添加孩子节点（系部不是一级的情况）
				classChildrenAddToAcaDeptTree(childrenList);
				//获取每个节点的ID，即系部ID
				String deptId = acaDeptTree.getId();
				paramMap = new HashMap<>();
				paramMap.put("deptId", deptId);
				//获取该系部下属所有的班级
				List<ClassInfo> classList = classInfoMapper.getClassInfoByDeptId(paramMap);
				//把班级信息叶子节点加入到学院-西部树型节点中
				addClassToAcaDept(classList, childrenList);
			}
		}
	}
	
	/**
	 * @功能描述：将系部信息叶子节点加入到学院树型节点中
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月1日 下午2:35:05
	 * 
	 * @修  改  人：zhoujincheng
	 * @版        本：V1.1.0
	 * @修改日期：20171023 11:11
	 * @修改描述：
	 * 
	 * @param deptList
	 * @param acaChildrenList
	 */
	private void addDeptToAca(List<DepartmentInfo> deptList, List<TreeModel> acaChildrenList)  throws Exception{
//		LOGGER.info("将系部信息叶子节点加入到学院树型节点中---->>start");
		for(DepartmentInfo dept : deptList) {
			TreeModel tm = new TreeModel();
			tm.setId(dept.getDeptId());
			tm.setPid(dept.getAcaId());
			tm.setText(dept.getDeptName());
			tm.setIconCls(Constants.COLOR_ICON);
			tm.setState(Constants.TREE_STATUS_OPEN);
			tm.setType(CommonConstants.COMM_CONST_DEPARTMENT_STR);
			/*
			 * 暂不考虑系部为多级的情况；这里只考虑系部为一级节点，即：系部下面没有子集；
			 * 这里必须初始化，不然在系部下面增加班级节点时会报“空指针异常”
			 */
			tm.setChildren(new ArrayList<>());  
			
			acaChildrenList.add(tm);
		}
	}
	
	/**
	 * @功能描述：将班级信息叶子节点加入到学院-系部树形模型节点中
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年10月23日 下午1:38:41
	 * 
	 * @修  改  人：
	 * @版        本：V1.1.0
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param classList
	 * @param acaDeptChildrenList
	 * @throws Exception
	 */
	private void addClassToAcaDept(List<ClassInfo> classList, List<TreeModel> acaDeptChildrenList)  throws Exception{
		for(ClassInfo ci : classList) {
			TreeModel tm = new TreeModel();
			tm.setId(ci.getClassId());
			tm.setIconCls(Constants.POWER_ICON);
			tm.setPid(ci.getDeptId());
			tm.setText(ci.getClassName());
			tm.setState(Constants.TREE_STATUS_OPEN);
			tm.setType(CommonConstants.COMM_CONST_CLASS_STR);
			/*
			 * 暂不考虑班级为多级的情况；这里只考虑班级为一级节点，即：班级下面没有子集
			 * 这里必须初始化，不然在系部下面增加班级节点时会报“空指针异常”
			 */
			tm.setChildren(new ArrayList<>()); 
			
			acaDeptChildrenList.add(tm);
		}
	}
	
	/**
	 * @功能描述：将学院信息从List结构转换成TreeModel结构
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月1日 下午2:03:09
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param id	上级节点ID
	 * @param list	要转换成TreeModel的List对象
	 * @return
	 */
	private List<TreeModel> acaInfoToTreeModel(String id, List<AcademyInfo> list) {
//		LOGGER.info("将学院信息从List结构转换成TreeModel结构---->>start");
		List<TreeModel> menuList = new ArrayList<TreeModel>();
		/*
		 * 使用lambda表达式将list对象迭代转换为TreeModel对象 
		 */
		list.stream().filter(acaInfo -> id.equals(acaInfo.getPrntId())).forEach(acaInfo -> {
			TreeModel menu = new TreeModel();
			menu.setId(acaInfo.getAcaId());
			menu.setPid(CommonConstants.COMM_CONST_STRING_0.equals(acaInfo.getPrntId()) ? CommonConstants.COMM_CONST_STRING_EMPTY : acaInfo.getPrntId());
			menu.setText(acaInfo.getAcaName());
			menu.setIconCls(acaInfo.getAcaIcon());
			menu.setState(Constants.TREE_STATUS_OPEN); //这里必须关闭节点，否则会出现无限节点
			menu.setType(CommonConstants.COMM_CONST_ACAMEDY_STR);
			menu.setChildren(acaInfoToTreeModel(acaInfo.getAcaId(), list));  //递归增加子节点
			
			menuList.add(menu);
		});		
		
		return menuList;
	}

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
	@Override
	public List<ClassInfo> getClassInfoByDeptId(Map paramMap)  throws Exception{
		
		return classInfoMapper.getClassInfoByDeptId(paramMap);
	}

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
	@Override
	public boolean persistenceClassInfo(ClassInfo classInfo)  throws Exception{
		String userId = Constants.getCurrendUser().getUserId();
		int saveOrUpdateCount = 0;
		
		if(StringUtil.isEmpty(classInfo.getClassId())) {
			BaseDomain.createLog(classInfo, userId);
			classInfo.setClassId(UUIDUtils.getUpperUUID());
			classInfo.setStatus(Constants.PERSISTENCE_STATUS);
			
			saveOrUpdateCount = classInfoMapper.insert(classInfo);
		} else {
			BaseDomain.editLog(classInfo, userId);
			saveOrUpdateCount = classInfoMapper.updateByPrimaryKeySelective(classInfo);
		}
		
		if(CommonConstants.COMM_CONST_INTEGER_1 == saveOrUpdateCount) {
			return true;
		}
		return false;
	}

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
	@Override
	public boolean delClassInfoById(String classId)  throws Exception{
		return classInfoMapper.deleteByPrimaryKey(classId) > 0;
	}

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
	@Override
	public List<ClassInfo> getClassInfoByPage(Map paramMap) throws Exception {
		return classInfoMapper.getClassInfoByPage(paramMap);
	}
	
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
	@Override
	public Long getClassInfoCount(Map<String, Object> paramMap) {
		return classInfoMapper.getClassInfoCount(paramMap);
	}

}
