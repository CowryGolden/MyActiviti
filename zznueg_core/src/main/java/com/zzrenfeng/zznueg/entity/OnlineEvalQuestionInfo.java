package com.zzrenfeng.zznueg.entity;

import com.zzrenfeng.base.entity.BaseDomain;
/**
 * @功能描述：在线测评试卷题目信息实体类
 * @创  建  者：zhoujincheng
 * @版        本：V1.0.0
 * @创建日期：2017年9月11日 下午2:18:59
 * 
 * @修  改  人：
 * @修改日期：
 * @修改描述：
 *
 */
public class OnlineEvalQuestionInfo extends BaseDomain {
    private String questionId;

    private String paperId;

    private String questionCategory;

    private String questionType;

    private Short questionNum;

    private String questionContent;

    private String questionAnswer;

    private Short questionScore;

    private String remark1;

    private String remark2;

    private String remark3;

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId == null ? null : questionId.trim();
    }

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId == null ? null : paperId.trim();
    }

    public String getQuestionCategory() {
        return questionCategory;
    }

    public void setQuestionCategory(String questionCategory) {
        this.questionCategory = questionCategory == null ? null : questionCategory.trim();
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType == null ? null : questionType.trim();
    }

    public Short getQuestionNum() {
        return questionNum;
    }

    public void setQuestionNum(Short questionNum) {
        this.questionNum = questionNum;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent == null ? null : questionContent.trim();
    }

    public String getQuestionAnswer() {
        return questionAnswer;
    }

    public void setQuestionAnswer(String questionAnswer) {
        this.questionAnswer = questionAnswer == null ? null : questionAnswer.trim();
    }

    public Short getQuestionScore() {
        return questionScore;
    }

    public void setQuestionScore(Short questionScore) {
        this.questionScore = questionScore;
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