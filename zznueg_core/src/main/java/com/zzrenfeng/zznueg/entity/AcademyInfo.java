package com.zzrenfeng.zznueg.entity;

import com.zzrenfeng.base.entity.BaseDomain;
/**
 * @功能描述：学院信息实体类（增加：父学院ID和父学院名称两个字段）
 * @创  建  者：zhoujincheng
 * @版        本：V1.0.0
 * @创建日期：2017年7月31日 上午11:47:59
 * 
 * @修  改  人：
 * @修改日期：
 * @修改描述：
 *
 */
public class AcademyInfo extends BaseDomain {
    private String acaId;

    private String acaName;

    private String acaIcon;

    private String acaLeader;

    private String contact;

    private String status;

    private String memo;
    //新增父学院ID
    private String prntId;    
	//新增父学院名称
    private String prntName;

    public String getAcaId() {
        return acaId;
    }

    public void setAcaId(String acaId) {
        this.acaId = acaId == null ? null : acaId.trim();
    }

    public String getAcaName() {
        return acaName;
    }

    public void setAcaName(String acaName) {
        this.acaName = acaName == null ? null : acaName.trim();
    }

    public String getAcaIcon() {
        return acaIcon;
    }

    public void setAcaIcon(String acaIcon) {
        this.acaIcon = acaIcon == null ? null : acaIcon.trim();
    }

    public String getAcaLeader() {
        return acaLeader;
    }

    public void setAcaLeader(String acaLeader) {
        this.acaLeader = acaLeader == null ? null : acaLeader.trim();
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
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

}