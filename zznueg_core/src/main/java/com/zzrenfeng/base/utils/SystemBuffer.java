package com.zzrenfeng.base.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zzrenfeng.base.utils.SpringUtil;
import com.zzrenfeng.base.entity.DictionaryInfo;
import com.zzrenfeng.base.entity.SysSetting;
import com.zzrenfeng.base.enums.DictionaryEnum;
import com.zzrenfeng.base.service.DictionaryInfoService;
import com.zzrenfeng.base.service.SystemSettinigService;

/**
 * @功能描述：系统资源缓冲器，用于系统静态资源缓存和获取
 * @创  建  者：zhoujincheng
 * @版        本：V1.0.0
 * @创建日期：2017年9月13日 上午10:28:30
 * 
 * @修  改  人：
 * @修改日期：
 * @修改描述：
 *
 */
public class SystemBuffer {
	protected static Logger LOGGER = LoggerFactory.getLogger(SystemBuffer.class);
/*	此方法无法进行注入，因为该类不在SpringMVC扫描的包范围之内，因此不能使用注解进行实例化
 *  采用Spring Bean工厂进行单例加载，具体参见init()方法；
	@Autowired
	private static DictionaryInfoService dictionaryInfoService;*/
	
	//数据字典Map对象
	public static Map<String, List<DictionaryInfo>> dictionaryMap = new HashMap<String, List<DictionaryInfo>>();
	/**
	 * 私有构造方法，不需要创建对象
	 */
	private SystemBuffer() {
		
	}	
	
	/**
	 * @功能描述：系统静态资源缓冲加载，该方法在WebContainerListener中被加载，即web容器启动时自动被加载
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月13日 上午10:38:14
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 *
	 */
	public static synchronized void init() {
System.out.println("@@@@@@@@@@@@@@@@@@@@@@====>>>>系统静态资源初始化开始！（web容器启动时加载）<<<<====@@@@@@@@@@@@@@@@@@@@@@");		
		try {
			//获取字典Service实例对象
			DictionaryInfoService dictionaryInfoService = SpringUtil.getBean("dictionaryInfoService");
			dictionaryMap =  dictionaryInfoService.getAllDictionaryMap();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
//System.out.println("################getValue()====>>>>" + SystemBuffer.getSysSettingByDictEnum(DictionaryEnum.EXAMTERM_STARTDATE).getItemValue());	
//System.out.println("################getValue()====>>>>" + SystemBuffer.getItemValueByDictEnum(DictionaryEnum.EXAMTERM_ENDDATE));	
	}
	
	
	
	/**
	 * @功能描述：根据type【数据类型(大类)】和key【数据项键(小类)】获取name【数据项名称】
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月13日 上午10:34:54
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param type
	 * @param key
	 * @return
	 */
	public static String getDictionaryLabelByTypeKey(String type, String key) {
		List<DictionaryInfo> list = dictionaryMap.get(type);
		if (null!=list&&!list.isEmpty()) {
			for (DictionaryInfo d : list) {
				if (key.equals(d.getKey())) {
					return d.getName();
				}
			}
		}
		return "";
	}
	
	/**
	 * @功能描述：根据type【数据类型(大类)】和key【数据项键(小类)】获取字典信息对象
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月13日 上午11:35:50
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param type
	 * @param key
	 * @return
	 */
	public static DictionaryInfo getDictionaryByTypeKey(String type, String key) {
		List<DictionaryInfo> list = dictionaryMap.get(type);
		if (null!=list&&!list.isEmpty()) {
			for (DictionaryInfo d : list) {
				if (key.equals(d.getKey())) {
					return d;
				}
			}
		}
		return null;
	}
	
	/**
	 * @功能描述：根据type【数据类型(大类)】和value【数据项值】获取字典信息对象
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月13日 上午11:37:48
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param type
	 * @param value
	 * @return
	 */
	public static DictionaryInfo getDictionaryByTypeValue(String type, String value) {
		List<DictionaryInfo> list = dictionaryMap.get(type);
		if (null!=list&&!list.isEmpty()) {
			for (DictionaryInfo d : list) {
				if (value.equals(d.getValue())) {
					return d;
				}
			}
		}
		return null;
	}
	
	/**
	 * @功能描述：根据type【数据类型(大类)】和value【数据项值】获取name【数据项名称】
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月13日 上午11:38:59
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param type
	 * @param value
	 * @return
	 */
	public static String getDictionaryLabelByTypeValue(String type, String value) {
		List<DictionaryInfo> list = dictionaryMap.get(type);
		if (null!=list&&!list.isEmpty()) {
			for (DictionaryInfo d : list) {
				if (d.getValue().equals(value)) {
					return d.getName();
				}
			}
		}
		return "";
	}
	
	/**
	 * @功能描述：根据type【数据类型(大类)】和key【数据项键(小类)】获取value【数据项值】
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月13日 上午11:40:24
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param type
	 * @param key
	 * @return
	 */
	public static String getDictionaryValueByTypeKey(String type, String key) {
		List<DictionaryInfo> list = dictionaryMap.get(type);
		if (null!=list&&!list.isEmpty()) {
			for (DictionaryInfo d : list) {
				if (key.equals(d.getKey())) {
					return d.getValue();
				}
			}
		}
		return "";
	}
	
	/**
	 * @功能描述：根据type【数据类型(大类)】获取数据字典对象列表
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月13日 上午11:42:24
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param type
	 * @return
	 */
	public static List<DictionaryInfo> getDictionaryByType(String type) {
		return dictionaryMap.get(type);
	}
	
	/**
	 * @功能描述：根据数据字典枚举类（DictionaryEnum）的字典ID到系统环境变量表（SysSetting）中获取对应项设置的value值；
	 * 			由于系统环境变量表（SysSetting）中的itemKey（设置项键）即为数据字典表中的dictionaryId
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月27日 上午9:39:14
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param dictEnum
	 * @return
	 */
	public static SysSetting getSysSettingByDictEnum(DictionaryEnum dictEnum) {
		SysSetting sysSetting = null;
		SystemSettinigService systemSettinigService = SpringUtil.getBean("systemSettinigService");
		try {
			sysSetting = systemSettinigService.selectSysSettingByItemKey(dictEnum.getDictionary().getDictionaryId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sysSetting;
	}
	
	/**
	 * @功能描述：根据数据字典枚举类（DictionaryEnum）获取该设置项（键-itemKey=dictionaryId）在系统环境变量中设置的值（itemValue）
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月27日 上午10:13:41
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param dictEnum
	 * @return
	 */
	public static String getItemValueByDictEnum(DictionaryEnum dictEnum) {
		return getSysSettingByDictEnum(dictEnum).getItemValue();
	}
	
}
