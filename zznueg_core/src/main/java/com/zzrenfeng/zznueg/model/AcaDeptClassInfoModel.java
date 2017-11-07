package com.zzrenfeng.zznueg.model;

/**
 * @功能描述：将院系班级关系转换为层级模型
 * @创  建  者：zhoujincheng
 * @版        本：V1.0.0
 * @创建日期：2017年8月7日 上午9:34:19
 * 
 * @修  改  人：
 * @修改日期：
 * @修改描述：
 *
 */
public class AcaDeptClassInfoModel {
	
	private String id;
	private String name;
	private String prntId;
	private String prntName;
	/**
	 * type具体枚举值如下:
	 * ACA-学院;
	 * DEPT-系部;
	 * CLASS-班级;
	 */
	private String type;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrntId() {
		return prntId;
	}
	public void setPrntId(String prntId) {
		this.prntId = prntId;
	}
	public String getPrntName() {
		return prntName;
	}
	public void setPrntName(String prntName) {
		this.prntName = prntName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}	
	
}
