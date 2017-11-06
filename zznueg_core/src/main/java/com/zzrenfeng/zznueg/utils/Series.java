package com.zzrenfeng.zznueg.utils;

import java.util.List;
/**
 * @功能描述：echarts中series属性工具类
 * @创  建  者：zhoujincheng
 * @版        本：V1.0.0
 * @创建日期：2017年8月8日 上午10:21:58
 * 
 * @修  改  人：
 * @修改日期：
 * @修改描述：
 *
 */
public class Series {
	/**
	 * 统计序列名称，或者维度名称
	 */
	private String name;
	/**
	 * 统计图类型
	 */
	private String type;
	/**
	 * 统计维度序列数据（每个维度上的数据集合）
	 */
	private List<?>  data;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<?> getData() {
		return data;
	}
	public void setData(List<?> data) {
		this.data = data;
	}
	
	
}
