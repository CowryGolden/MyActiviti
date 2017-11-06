package com.zzrenfeng.zznueg.entity;

import com.zzrenfeng.base.entity.BaseDomain;
/**
 * @功能描述：在线测评试卷库信息实体类
 * @创  建  者：zhoujincheng
 * @版        本：V1.0.0
 * @创建日期：2017年9月11日 下午2:16:59
 * 
 * @修  改  人：
 * @修改日期：
 * @修改描述：
 *
 */
public class OnlineEvalPaperlibInfo extends BaseDomain {
    private String paperId;

    private String paperType;

    private String paperTitle;

    private Short totalScore;

    private String isEnable;

    private String remark1;

    private String remark2;

    private String remark3;

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId == null ? null : paperId.trim();
    }

    public String getPaperType() {
        return paperType;
    }

    public void setPaperType(String paperType) {
        this.paperType = paperType == null ? null : paperType.trim();
    }

    public String getPaperTitle() {
        return paperTitle;
    }

    public void setPaperTitle(String paperTitle) {
        this.paperTitle = paperTitle == null ? null : paperTitle.trim();
    }

    public Short getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Short totalScore) {
        this.totalScore = totalScore;
    }

    public String getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(String isEnable) {
        this.isEnable = isEnable == null ? null : isEnable.trim();
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