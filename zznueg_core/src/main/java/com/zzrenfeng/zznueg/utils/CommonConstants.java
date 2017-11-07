package com.zzrenfeng.zznueg.utils;

/**
 * @功能描述：公共常量类
 * @创  建  者： zhoujincheng
 * @版        本：V1.0.0
 * @创建日期：2017年7月21日 上午9:23:41
 *
 * @修  改  人：
 * @修改日期：
 * @修改描述：
 */
public class CommonConstants {
	
	/**
	 * 统计周期-月
	 */
	public final static String STATISTICS_PERIOD_M = "M";	//统计周期-月
	/**
	 * 统计周期-季
	 */
	public final static String STATISTICS_PERIOD_Q = "Q";	//统计周期-季
	/**
	 * 统计周期-年
	 */
	public final static String STATISTICS_PERIOD_Y = "Y";	//统计周期-年
	
	
	/**
	 * 统计图表类型——柱状图
	 */
	public final static String STATISTICS_ECHARTS_TYPE_BAR = "bar";
	/**
	 * 统计图表类型——折线图
	 */
	public final static String STATISTICS_ECHARTS_TYPE_LINE = "line";
	/**
	 * 统计图表类型——饼图
	 */
	public final static String STATISTICS_ECHARTS_TYPE_PIE = "pie";
	/**
	 * 统计图表类型——雷达图
	 */
	public final static String STATISTICS_ECHARTS_TYPE_RADAR = "radar";
	/**
	 * 统计图表类型——关系图
	 */
	public final static String STATISTICS_ECHARTS_TYPE_GRAPH = "graph";
	/**
	 * 统计图表类型——和弦图
	 */
	public final static String STATISTICS_ECHARTS_TYPE_CHORD = "chord";
	/**
	 * 统计图表类型——力导向图
	 */
	public final static String STATISTICS_ECHARTS_TYPE_FORCE = "force";
	
	/**
	 * 字符串"0"-无效
	 */
	public final static String FLAG_STR_INVALID_0 = "0";
	/**
	 * 字符串"1"-有效
	 */
	public final static String FLAG_STR_VALID_1 = "1";
	/**
	 * 通用整型常量 0
	 */
	public final static Integer COMM_CONST_INTEGER_0 = 0;
	/**
	 * 通用整型常量 1
	 */
	public final static Integer COMM_CONST_INTEGER_1 = 1;
	/**
	 * 通用字符串常量"0"
	 */
	public final static String COMM_CONST_STRING_0 = "0";
	/**
	 * 通用字符串常量"1"
	 */
	public final static String COMM_CONST_STRING_1 = "1";
	/**
	 * 通用字符串常量，空字符串""
	 */
	public final static String COMM_CONST_STRING_EMPTY = "";
	
	/**
	 * 学院标识符常量
	 */
	public final static String COMM_CONST_ACAMEDY_STR = "ACA";
	/**
	 * 系部标识符常量
	 */
	public final static String COMM_CONST_DEPARTMENT_STR = "DEPT";
	/**
	 * 班级标识符常量
	 */
	public final static String COMM_CONST_CLASS_STR = "CLASS";
	
	/**
	 * 上传文件成功标志
	 */
	public final static String UPLOAD_FILE_SUCESS = "S";
	/**
	 * 上传文件失败标志
	 */
	public final static String UPLOAD_FILE_FAILURE = "F";
	
	/**
	 * 每个科目允许上传的次数
	 */
	public final static int COMM_PARAM_ALLOW_UPLOAD_COUNT = 4;
	
	/**
	 * 科目代码：01-微格
	 */
	public final static String SUBJECT_ID_WEIGE = "01";
	/**
	 * 科目代码：02-板书
	 */
	public final static String SUBJECT_ID_BANSHU = "02";
	/**
	 * 科目代码：03-教案
	 */
	public final static String SUBJECT_ID_JIAOAN = "03";
	/**
	 * 科目代码：04-课件
	 */
	public final static String SUBJECT_ID_KEJIAN = "04";
	
	/**
	 * 科目的标准成绩，即分科及格线
	 */
	public final static Integer SUBJECT_STANDARD_SCORE = 60;
	/**
	 * 科目最高成绩
	 */
	public final static Integer SUBJECT_MAX_SCORE = 100;
	
	/**
	 * 科目成绩类型，MAX-科目最高成绩
	 */
	public final static String SCORE_TYPE_MAX = "MAX";
	/**
	 * 科目成绩类型，MIN-科目最低成绩
	 */
	public final static String SCORE_TYPE_MIN = "MIN";
	/**
	 * 科目成绩类型，AVG-科目平均成绩
	 */
	public final static String SCORE_TYPE_AVG = "AVG";
	
	/**
	 * 上传次数，第 1 次
	 */
	public final static Integer UPLOAD_COUNT_1 = 1;
	/**
	 * 上传次数，第 2 次
	 */
	public final static Integer UPLOAD_COUNT_2 = 2;
	/**
	 * 上传次数，第 3 次
	 */
	public final static Integer UPLOAD_COUNT_3 = 3;
	/**
	 * 上传次数，第 4 次
	 */
	public final static Integer UPLOAD_COUNT_4 = 4;
		
	
	
}
