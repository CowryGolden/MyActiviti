package com.zzrenfeng.zznueg.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zzrenfeng.base.entity.BaseDomain;
import com.zzrenfeng.base.model.MultiMenu;
import com.zzrenfeng.base.utils.Constants;
import com.zzrenfeng.base.utils.PageUtil;
import com.zzrenfeng.base.utils.StringUtil;
import com.zzrenfeng.base.utils.UUIDUtils;
import com.zzrenfeng.zznueg.dao.TeachStuInfoMapper;
import com.zzrenfeng.zznueg.dao.TeacherPlatformStatMapper;
import com.zzrenfeng.zznueg.dao.TestImportTeachStuInfoMapper;
import com.zzrenfeng.zznueg.entity.TeachStuInfo;
import com.zzrenfeng.zznueg.entity.TestImportTeachStuInfo;
import com.zzrenfeng.zznueg.model.AcaDeptClassInfoModel;
import com.zzrenfeng.zznueg.service.TeachStuInfoService;
import com.zzrenfeng.zznueg.utils.CommonConstants;

/**
 * @功能描述：教师学生信息Service接口实现类
 * @创  建  者：zhoujincheng
 * @版        本：V1.0.0
 * @创建日期：2017年8月2日 上午11:45:31
 * 
 * @修  改  人：
 * @修改日期：
 * @修改描述：
 *
 */
@Transactional
@Service("teachStuInfoService")
public class TeachStuInfoServiceImpl implements TeachStuInfoService {
	private static final Logger LOGGER = LoggerFactory.getLogger(TeachStuInfoServiceImpl.class);
	
	@Autowired
	protected TeachStuInfoMapper teachStuInfoMapper;
	@Autowired
	protected TestImportTeachStuInfoMapper testImportTeachStuInfoMapper;
	
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
	@Override
	public List<TeachStuInfo> findAllTeacherInfoByPage(Map paramMap) throws Exception {
		return this.teachStuInfoMapper.findAllTeacherInfoByPage(paramMap);
	}

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
	@Override
	public List<TeachStuInfo> findAllStudentInfoByPage(Map paramMap) throws Exception {
		return this.teachStuInfoMapper.findAllStudentInfoByPage(paramMap);
	}

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
	@Override
	public boolean persistenceTeachStuInfo(TeachStuInfo teachStuInfo) throws Exception {
		LOGGER.info("持久化教师学生信息！");
		//获取当前操作的用户ID，若要记录该用户，就用此用户ID
		String currUserId = Constants.getCurrendUser().getUserId(); 
		
		int saveOrUpdateCount = 0;    //新增或修改记录数		
		
		if(StringUtil.isEmpty(teachStuInfo.getId())) {
			if(isExistTeachStuInfoByTsno(teachStuInfo.getTsno())) {
				LOGGER.info("工号/学号已经存在！");
				return false;
			}
			BaseDomain.createLog(teachStuInfo, currUserId);
			teachStuInfo.setId(UUIDUtils.getUpperUUID());
			teachStuInfo.setStatus(Constants.PERSISTENCE_STATUS);
			saveOrUpdateCount = this.teachStuInfoMapper.insert(teachStuInfo);
		} else {
			BaseDomain.editLog(teachStuInfo, currUserId);
			saveOrUpdateCount = this.teachStuInfoMapper.updateByPrimaryKeySelective(teachStuInfo);
		}
		
		if(CommonConstants.COMM_CONST_INTEGER_1 == saveOrUpdateCount) {
			return true;
		}	
		return false;
	}

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
	@Override
	public Long getTeacherCount(Map<String, Object> paramMap) throws Exception {
		return this.teachStuInfoMapper.getTeacherCount(paramMap);
	}

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
	@Override
	public Long getStudentCount(Map<String, Object> paramMap) throws Exception {
		return this.teachStuInfoMapper.getStudentCount(paramMap);
	}

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
	@Override
	public boolean delTeachStuInfoById(String id) throws Exception {
		return this.teachStuInfoMapper.deleteByPrimaryKey(id) > 0;
	}

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
	@Override
	public boolean isExistTeachStuInfoByTsno(String tsno) throws Exception {		
		return this.teachStuInfoMapper.getTeachStuInfoCountByTsno(tsno) > 0;
	}

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
	@Override
	public List<MultiMenu> getADCMultiMenu() throws Exception {
		List<AcaDeptClassInfoModel> adcLists = teachStuInfoMapper.getAllAcaDeptClassInfoTrees();
//System.out.println("adcLists=========>>" + adcLists);		
		return adcToMultiMenu(CommonConstants.COMM_CONST_STRING_0, adcLists);
	}

	/**
	 * @功能描述：将院系班级层级关系转化成菜单模型，支持无限极菜单模型
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月7日 上午9:44:34
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param commConstString0
	 * @param adcLists
	 * @return
	 */
	private List<MultiMenu> adcToMultiMenu(String id, List<AcaDeptClassInfoModel> adcLists) {
		
		List<MultiMenu> menuList = new ArrayList<MultiMenu>();

		adcLists.stream().filter(adcInfo -> id.equals(adcInfo.getPrntId())).forEach(adcInfo -> {
			MultiMenu menu = new MultiMenu();		
			menu.setId(adcInfo.getId());
			menu.setName(adcInfo.getName());
			menu.setPid(CommonConstants.COMM_CONST_STRING_0.equals(adcInfo.getPrntId()) ? CommonConstants.COMM_CONST_STRING_EMPTY : adcInfo.getPrntId());
			menu.setpName(adcInfo.getPrntName());
			menu.setType(adcInfo.getType());
			menu.setChildren(adcToMultiMenu(adcInfo.getId(), adcLists));
/*			
			menu.setIconCls(null);
			menu.setpName(null);
			menu.setMyid(null);
			menu.setSort(null);			
			menu.setDescription(null);
			menu.setIfUsed(null);
*/			
			menuList.add(menu);			
		});
		
		return menuList;
	}

	/**
	 * @功能描述：批量导入教师学生信息
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
	@Override
	//public int batchImportTeachStuInfo(List<TestImportTeachStuInfo> importInfoList) throws Exception {
	public int batchImportTeachStuInfo(List<TeachStuInfo> importInfoList) throws Exception {
		int importCounts = 0;
		//获取当前操作的用户ID，若要记录该用户，就用此用户ID
		String currUserId = Constants.getCurrendUser().getUserId(); 
		
		if(null != importInfoList && !importInfoList.isEmpty()) {
			for(TeachStuInfo ts : importInfoList) {
				ts.setId(UUIDUtils.getUpperUUID());
				//ts.setUserType(Constants.USER_TYPE_STUDENT);  //为了教师和学生信息导入通用性，该标志放于导入文件中
				ts.setStatus(Constants.PERSISTENCE_STATUS);
				ts.setIsOpenAct(Constants.COMM_CONST_STRING_0);
				BaseDomain.createLog(ts, currUserId);
				
				importCounts += this.teachStuInfoMapper.insert(ts);
			}
		} else {
			importCounts = 0;
		}		
		return importCounts;
	}

}
