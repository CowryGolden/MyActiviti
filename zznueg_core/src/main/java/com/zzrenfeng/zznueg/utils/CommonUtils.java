package com.zzrenfeng.zznueg.utils;

import java.text.DecimalFormat;

/**
 * @功能描述：通用工具类
 * @创  建  者：zhoujincheng
 * @版        本：V1.0.0
 * @创建日期：2017年8月9日 上午10:44:23
 * 
 * @修  改  人：
 * @修改日期：
 * @修改描述：
 *
 */
public class CommonUtils {
	
	/**
	 * @功能描述：文件大小转换
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年8月9日 上午10:45:12
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param length
	 * @return
	 */
	public static String sizeToString(double length) {
		String size = null;
		DecimalFormat df = new DecimalFormat("#.##");
		if (length == 0)
			return "0M";
		else if (length < 1024){
			size = df.format(length) + "B";
		}else if (length < Math.pow(1024, 2)){
			size = df.format(length / 1024) + "K";
		}else if (length < Math.pow(1024, 3)){
			size = df.format(length / Math.pow(1024, 2)) + "M";
		}else{
			size = df.format(length / Math.pow(1024, 3)) + "G";
		}
		return size;
	}
	
	/**
	 * @功能描述：将double类型的数据转为百分数字符串
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月20日 上午11:28:51
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param data
	 * @return
	 */
	public static String double2Percent(double data) {
		DecimalFormat df = new DecimalFormat("#0.00%");
        String perStr = df.format(data);
        return perStr;
	}
	
	
}
