package com.zzrenfeng.base.entity;

import java.io.Serializable;
import java.util.Date;
/**
 * @功能描述：系统异常日志信息实体类
 * @创  建  者：zhoujincheng
 * @版        本：V1.0.0
 * @创建日期：2017年9月23日 上午11:32:30
 * 
 * @修  改  人：
 * @修改日期：
 * @修改描述：
 *
 */
public class SysExceptionLoginfo implements Serializable {
    /**
	 * 版本ID
	 */
	private static final long serialVersionUID = 3089144086429120152L;

	/**
     * 主键ID
     */
    private String id;

    /**
     * 类名
     */
    private String className;

    /**
     * 创建日期
     */
    private Date createTime;

    /**
     * 备注
     */
    private String memo;

    /**
     * 类型
     */
    private String type;

    /**
     * 异常类型
     */
    private String exceptionType;
    
    /**
     * 异常明细(堆栈信息)
     */
    private String exceptionDetail;

    /**
     * 方法名
     */
    private String methodName;

    /**
     * 源头(包含行数)
     */
    private String source;

    /**
     * 目标(包含行数)
     */
    private String target;

    /**
     * 操纵用户ID
     */
    private String operatorId;

    /**
     * 操作用户名称
     */
    private String operatorName;

    /**
     * 预留1
     */
    private String remark1;

    /**
     * 预留2
     */
    private String remark2;

    /**
     * 预留3
     */
    private String remark3;


    /**
     * 主键ID
     * @return id 主键ID
     */
    public String getId() {
        return id;
    }

    /**
     * 主键ID
     * @param id 主键ID
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 类名
     * @return className 类名
     */
    public String getClassName() {
        return className;
    }

    /**
     * 类名
     * @param className 类名
     */
    public void setClassName(String className) {
        this.className = className == null ? null : className.trim();
    }

    /**
     * 创建日期
     * @return createTime 创建日期
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建日期
     * @param createTime 创建日期
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 备注
     * @return memo 备注
     */
    public String getMemo() {
        return memo;
    }

    /**
     * 备注
     * @param memo 备注
     */
    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    /**
     * 类型
     * @return type 类型
     */
    public String getType() {
        return type;
    }

    /**
     * 类型
     * @param type 类型
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * 异常类型
     * @return exceptionType 异常类型
     */
    public String getExceptionType() {
        return exceptionType;
    }

    /**
     * 异常类型
     * @param exceptionType 异常类型
     */
    public void setExceptionType(String exceptionType) {
        this.exceptionType = exceptionType == null ? null : exceptionType.trim();
    }

    /**
     * 异常明细(堆栈信息)
     * @return exceptionDetail 异常明细(堆栈信息)
     */
    public String getExceptionDetail() {
        return exceptionDetail;
    }

    /**
     * 异常明细(堆栈信息)
     * @param exceptionDetail 异常明细(堆栈信息)
     */
    public void setExceptionDetail(String exceptionDetail) {
        this.exceptionDetail = exceptionDetail == null ? null : exceptionDetail.trim();
    }

    /**
     * 方法名
     * @return methodName 方法名
     */
    public String getMethodName() {
        return methodName;
    }

    /**
     * 方法名
     * @param methodName 方法名
     */
    public void setMethodName(String methodName) {
        this.methodName = methodName == null ? null : methodName.trim();
    }

    /**
     * 源头(包含行数)
     * @return source 源头(包含行数)
     */
    public String getSource() {
        return source;
    }

    /**
     * 源头(包含行数)
     * @param source 源头(包含行数)
     */
    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    /**
     * 目标(包含行数)
     * @return target 目标(包含行数)
     */
    public String getTarget() {
        return target;
    }

    /**
     * 目标(包含行数)
     * @param target 目标(包含行数)
     */
    public void setTarget(String target) {
        this.target = target == null ? null : target.trim();
    }

    /**
     * 操纵用户ID
     * @return operatorId 操纵用户ID
     */
    public String getOperatorId() {
        return operatorId;
    }

    /**
     * 操纵用户ID
     * @param operatorId 操纵用户ID
     */
    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId == null ? null : operatorId.trim();
    }

    /**
     * 操作用户名称
     * @return operatorName 操作用户名称
     */
    public String getOperatorName() {
        return operatorName;
    }

    /**
     * 操作用户名称
     * @param operatorName 操作用户名称
     */
    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName == null ? null : operatorName.trim();
    }

    /**
     * 预留1
     * @return remark1 预留1
     */
    public String getRemark1() {
        return remark1;
    }

    /**
     * 预留1
     * @param remark1 预留1
     */
    public void setRemark1(String remark1) {
        this.remark1 = remark1 == null ? null : remark1.trim();
    }

    /**
     * 预留2
     * @return remark2 预留2
     */
    public String getRemark2() {
        return remark2;
    }

    /**
     * 预留2
     * @param remark2 预留2
     */
    public void setRemark2(String remark2) {
        this.remark2 = remark2 == null ? null : remark2.trim();
    }

    /**
     * 预留3
     * @return remark3 预留3
     */
    public String getRemark3() {
        return remark3;
    }

    /**
     * 预留3
     * @param remark3 预留3
     */
    public void setRemark3(String remark3) {
        this.remark3 = remark3 == null ? null : remark3.trim();
    }
    
}