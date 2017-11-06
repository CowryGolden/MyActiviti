package com.zzrenfeng.zznueg.entity;

import com.zzrenfeng.base.entity.BaseDomain;
/**
 * @功能描述：科目信息实体类
 * @创  建  者： zhoujincheng
 * @版        本：V1.0.0
 * @创建日期：2017年7月20日 下午2:13:43
 *
 * @修  改  人：
 * @修改日期：
 * @修改描述：
 */
public class SubjectInfo extends BaseDomain {
    private String subjectId;

    private String subjectName;

    private String subjectCategory;

    private String isValid;

    private String memo;

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId == null ? null : subjectId.trim();
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName == null ? null : subjectName.trim();
    }

    public String getSubjectCategory() {
        return subjectCategory;
    }

    public void setSubjectCategory(String subjectCategory) {
        this.subjectCategory = subjectCategory == null ? null : subjectCategory.trim();
    }

    public String getIsValid() {
        return isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid == null ? null : isValid.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }
}