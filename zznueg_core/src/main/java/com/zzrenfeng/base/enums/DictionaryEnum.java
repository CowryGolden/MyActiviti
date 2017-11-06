package com.zzrenfeng.base.enums;

import com.zzrenfeng.base.entity.DictionaryInfo;
import com.zzrenfeng.base.utils.SystemBuffer;

/**
 * @功能描述：数据字典枚举类
 * @创  建  者：zhoujincheng
 * @版        本：V1.0.0
 * @创建日期：2017年9月13日 上午10:10:42
 * 
 * @修  改  人：
 * @修改日期：
 * @修改描述：
 *
 */
public enum DictionaryEnum {
	/*
	 * 考试(评估)期限 
	 */
	EXAMTERM_STARTDATE("examTerm", "examTermStartDate"),
	EXAMTERM_ENDDATE("examTerm", "examTermEndDate"),
	
	/*
	 * 试卷类型
	 */
	PAPER_TYPE_BAS("paperType", "BAS"),  //基本能力在线测评
	PAPER_TYPE_MIX("paperType", "MIX"),  //综合能力在线测评
	
	/*
	 * 题目类型
	 */
	QUESTION_TYPE_S("questionType", "S"),  //单选题
	QUESTION_TYPE_D("questionType", "D"),  //多选题
	QUESTION_TYPE_J("questionType", "J"),  //判断题
	QUESTION_TYPE_Z("questionType", "Z"),  //主观题
	
	/*
	 * 问题分类
	 */
	QUESTION_CATEGORY_A00("questionCategory", "A00"),  //学习能力
	QUESTION_CATEGORY_B00("questionCategory", "B00"),  //沟通能力
	QUESTION_CATEGORY_C00("questionCategory", "C00"),  //亲和力
	QUESTION_CATEGORY_D00("questionCategory", "D00"),  //抗压力
	QUESTION_CATEGORY_E00("questionCategory", "E00"),  //责任心
	QUESTION_CATEGORY_F00("questionCategory", "F00"),  //随机反应能力
	QUESTION_CATEGORY_G00("questionCategory", "G00"),  //数学运算能力
	QUESTION_CATEGORY_H00("questionCategory", "H00"),  //心理承受能力
	;
	
	/**
	 * 数据类型（大类）
	 */
	private String type;
	/**
	 * 数据项键(小类)
	 */
	private String key;
	
	private DictionaryEnum(String type, String key) {
		this.type = type;
		this.key = key;
	}
	
	/**
	 * @功能描述：根据type【数据类型(大类)】和key【数据项键(小类)】获取name【数据项名称】
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月13日 下午2:08:35
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @return
	 */
	public String getLabel() {
		return SystemBuffer.getDictionaryLabelByTypeKey(type, key);
	}
	/**
	 * @功能描述：根据type【数据类型(大类)】和key【数据项键(小类)】获取value【数据项值】
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月13日 下午2:09:43
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @return
	 */
	public String getValue() {
		return SystemBuffer.getDictionaryValueByTypeKey(type, key);
	}
	/**
	 * @功能描述：根据type【数据类型(大类)】和key【数据项键(小类)】获取value【数据项值】，并强制类型转换为int
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月13日 下午2:11:31
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @return
	 */
	public int getIntValue() {
		return Integer.valueOf(this.getValue());
	}
	/**
	 * @功能描述：根据type【数据类型(大类)】和key【数据项键(小类)】获取字典信息对象
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月13日 下午2:12:58
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @return
	 */
	public DictionaryInfo getDictionary() {
		return SystemBuffer.getDictionaryByTypeKey(type, key);
	}
	/**
	 * @功能描述：获取字典type【数据类型(大类)】
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月13日 下午2:13:55
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @return
	 */
	public String getType() {
		return this.type;
	}
	/**
	 * @功能描述：获取字典key【数据项键(小类)】
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月13日 下午2:14:34
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @return
	 */
	public String getKey() {
		return this.key;
	}
	
	
	/**
	 * @功能描述：不能再main方法中测试，在web类中测试
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月13日 上午11:44:45
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
//		System.out.println("################getValue()====>>>>" + DictionaryEnum.QUESTION_CATEGORY_A00.getValue());
	}
	

}
