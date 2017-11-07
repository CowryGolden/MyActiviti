package com.zzrenfeng.zznueg.entity;

import com.zzrenfeng.base.entity.BaseDomain;
/**
 * @功能描述：学生在线测评记录信息实体类
 * @创  建  者：zhoujincheng
 * @版        本：V1.0.0
 * @创建日期：2017年9月11日 下午2:23:05
 * 
 * @修  改  人：
 * @修改日期：
 * @修改描述：
 *
 */
public class OnlineEvalStuentEvalRecordInfo extends BaseDomain {
    private String pid;

    private String stuUserId;

    private String paperId;

    private String questionId;

    private String questionCategory;

    private String studentAnswer;

    private Short stuAnswerScore;

    private String remark1;

    private String remark2;

    private String remark3;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public String getStuUserId() {
        return stuUserId;
    }

    public void setStuUserId(String stuUserId) {
        this.stuUserId = stuUserId == null ? null : stuUserId.trim();
    }

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId == null ? null : paperId.trim();
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId == null ? null : questionId.trim();
    }

    public String getQuestionCategory() {
        return questionCategory;
    }

    public void setQuestionCategory(String questionCategory) {
        this.questionCategory = questionCategory == null ? null : questionCategory.trim();
    }

    public String getStudentAnswer() {
        return studentAnswer;
    }

    public void setStudentAnswer(String studentAnswer) {
        this.studentAnswer = studentAnswer == null ? null : studentAnswer.trim();
    }

    public Short getStuAnswerScore() {
        return stuAnswerScore;
    }

    public void setStuAnswerScore(Short stuAnswerScore) {
        this.stuAnswerScore = stuAnswerScore;
    }

    public String getRemark1() {
        return remark1;
    }

    public void setRemark1(String remark1) {
        this.remark1 = remark1 == null ? null : remark1.trim();
    }

    public String getRemark2() {
        return remark2;
    }

    public void setRemark2(String remark2) {
        this.remark2 = remark2 == null ? null : remark2.trim();
    }

    public String getRemark3() {
        return remark3;
    }

    public void setRemark3(String remark3) {
        this.remark3 = remark3 == null ? null : remark3.trim();
    }
}