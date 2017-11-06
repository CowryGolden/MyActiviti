package com.zzrenfeng.zznueg.entity;

import com.zzrenfeng.base.entity.BaseDomain;
/**
 * @功能描述：教师教授班级关系实体类
 * @创  建  者：zhoujincheng
 * @版        本：V1.0.0
 * @创建日期：2017年8月7日 下午3:54:05
 * 
 * @修  改  人：
 * @修改日期：
 * @修改描述：
 *
 */
public class TeachClassRel extends BaseDomain {
    private String profId;

    private String teachUserId;

    private String classId;

    private String memo;
    
    private String status;

    public String getProfId() {
        return profId;
    }

    public void setProfId(String profId) {
        this.profId = profId == null ? null : profId.trim();
    }

    public String getTeachUserId() {
        return teachUserId;
    }

    public void setTeachUserId(String teachUserId) {
        this.teachUserId = teachUserId == null ? null : teachUserId.trim();
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId == null ? null : classId.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
    
}