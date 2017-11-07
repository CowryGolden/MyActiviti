package com.zzrenfeng.zznueg.entity;

import com.zzrenfeng.base.entity.BaseDomain;
import java.util.Date;

/**
 * @功能描述：学生上传课件实体类
 * @创  建  者：zhoujincheng
 * @版        本：V1.0.0
 * @创建日期：2017年8月8日 上午11:32:21
 * 
 * @修  改  人：zjc
 * @版        本：V1.0.1
 * @修改日期：20170809
 * @修改描述：新增文件大小字段fileSize
 * 
 * @修  改  人：zjc
 * @版        本：V1.1.0
 * @修改日期：20171010 14:46
 * @修改描述：新增"在线预览路径"字段previewPath，保存的为文件资源在web容器中的相对路径，文件在线预览时使用；
 * 			说明：uploadPath-文件上传至服务器的文件系统的绝对路径；也是文件下载时所需的路径；
 *
 */
public class StuUploadInfo extends BaseDomain {
    private String pid;

    private String stuUserId;

    private String subjectId;

    private String subjectName;

    private String isUploadSucess;
    /**
     * 文件上传至服务器的文件系统的绝对路径；也是文件下载时所需的路径；在线预览文件的路径参见previewPath
     */
    private String uploadPath;

    private Short uploadCount;

    private Date uploadTime;
    
    //新增文件大小字段
    private String fileSize;

    private Short evalScore;

    private String teachUserId;

    private Date evalTime;

    private String memo;
    
    /**
     * zjc 20171010 14:44 新增字段
     * 在线预览路径
     */
    private String previewPath;
    
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

    public String getIsUploadSucess() {
        return isUploadSucess;
    }

    public void setIsUploadSucess(String isUploadSucess) {
        this.isUploadSucess = isUploadSucess == null ? null : isUploadSucess.trim();
    }

    public String getUploadPath() {
        return uploadPath;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath == null ? null : uploadPath.trim();
    }

    public Short getUploadCount() {
        return uploadCount;
    }

    public void setUploadCount(Short uploadCount) {
        this.uploadCount = uploadCount;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public Short getEvalScore() {
        return evalScore;
    }

    public void setEvalScore(Short evalScore) {
        this.evalScore = evalScore;
    }

    public String getTeachUserId() {
        return teachUserId;
    }

    public void setTeachUserId(String teachUserId) {
        this.teachUserId = teachUserId == null ? null : teachUserId.trim();
    }

    public Date getEvalTime() {
        return evalTime;
    }

    public void setEvalTime(Date evalTime) {
        this.evalTime = evalTime;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	
    public String getPreviewPath() {
		return previewPath;
	}

	public void setPreviewPath(String previewPath) {
		this.previewPath = previewPath;
	}


}