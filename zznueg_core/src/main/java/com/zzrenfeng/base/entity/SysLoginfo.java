package com.zzrenfeng.base.entity;

import java.io.Serializable;
import java.util.Date;
/**
 * @功能描述：系统操作日志信息实体类
 * @创  建  者：zhoujincheng
 * @版        本：V1.0.0
 * @创建日期：2017年9月23日 上午11:39:21
 * 
 * @修  改  人：
 * @修改日期：
 * @修改描述：
 *
 */
public class SysLoginfo implements Serializable {
    /**
	 * 版本ID
	 */
	private static final long serialVersionUID = -6807512190813772092L;

	/**
     * 系统日志ID
     */
    private String logId;

    /**
     * 日志记录时间
     */
    private Date logTime;

    /**
     * 操作用户ID
     */
    private String operatorId;

    /**
     * 操作用户名称
     */
    private String operatorName;

    /**
     * 是否后台操作(0-否,1-是)
     */
    private String isBackground;

    /**
     * 日志类型代码
     */
    private String logType;

    /**
     * 日志事件标题
     */
    private String logEvent;

    /**
     * 操作日志详情
     */
    private String logDetail;

    /**
     * 操作状态(是否成功;0-失败,1-成功)
     */
    private String operState;

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
     * 系统日志ID
     * @return logId 系统日志ID
     */
    public String getLogId() {
        return logId;
    }

    /**
     * 系统日志ID
     * @param logId 系统日志ID
     */
    public void setLogId(String logId) {
        this.logId = logId == null ? null : logId.trim();
    }

    /**
     * 日志记录时间
     * @return logTime 日志记录时间
     */
    public Date getLogTime() {
        return logTime;
    }

    /**
     * 日志记录时间
     * @param logTime 日志记录时间
     */
    public void setLogTime(Date logTime) {
        this.logTime = logTime;
    }

    /**
     * 操作用户ID
     * @return operatorId 操作用户ID
     */
    public String getOperatorId() {
        return operatorId;
    }

    /**
     * 操作用户ID
     * @param operatorId 操作用户ID
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
     * 是否后台操作(0-否,1-是)
     * @return isBackground 是否后台操作(0-否,1-是)
     */
    public String getIsBackground() {
        return isBackground;
    }

    /**
     * 是否后台操作(0-否,1-是)
     * @param isBackground 是否后台操作(0-否,1-是)
     */
    public void setIsBackground(String isBackground) {
        this.isBackground = isBackground == null ? null : isBackground.trim();
    }

    /**
     * 日志类型代码
     * @return logType 日志类型代码
     */
    public String getLogType() {
        return logType;
    }

    /**
     * 日志类型代码
     * @param logType 日志类型代码
     */
    public void setLogType(String logType) {
        this.logType = logType == null ? null : logType.trim();
    }

    /**
     * 日志事件标题
     * @return logEvent 日志事件标题
     */
    public String getLogEvent() {
        return logEvent;
    }

    /**
     * 日志事件标题
     * @param logEvent 日志事件标题
     */
    public void setLogEvent(String logEvent) {
        this.logEvent = logEvent == null ? null : logEvent.trim();
    }

    /**
     * 操作日志详情
     * @return logDetail 操作日志详情
     */
    public String getLogDetail() {
        return logDetail;
    }

    /**
     * 操作日志详情
     * @param logDetail 操作日志详情
     */
    public void setLogDetail(String logDetail) {
        this.logDetail = logDetail == null ? null : logDetail.trim();
    }

    /**
     * 操作状态(是否成功;0-失败,1-成功)
     * @return operState 操作状态(是否成功;0-失败,1-成功)
     */
    public String getOperState() {
        return operState;
    }

    /**
     * 操作状态(是否成功;0-失败,1-成功)
     * @param operState 操作状态(是否成功;0-失败,1-成功)
     */
    public void setOperState(String operState) {
        this.operState = operState == null ? null : operState.trim();
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