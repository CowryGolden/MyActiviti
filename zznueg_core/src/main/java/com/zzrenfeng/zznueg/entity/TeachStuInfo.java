package com.zzrenfeng.zznueg.entity;

import com.zzrenfeng.base.entity.BaseDomain;
import com.zzrenfeng.base.utils.ExcelVOAttribute;
/**
 * @功能描述：教师学生实体类
 * @创  建  者：zhoujincheng
 * @版        本：V1.0.0
 * @创建日期：2017年8月2日 上午9:43:56
 * 
 * @修  改  人：
 * @修改日期：
 * @修改描述：
 *
 */
public class TeachStuInfo extends BaseDomain {
    private String id;
    
    //学号(S)/工号(T)
    @ExcelVOAttribute(name = "学号(S)/工号(T)", column = "A")
    private String tsno;
    
    @ExcelVOAttribute(name = "姓名", column = "B")
    private String name;
    
    //性别(0-女;1-男)
    @ExcelVOAttribute(name = "性别(0-女;1-男)", column = "C")
    private String sex;

    private String deptId;

    private String classId;
    //用户类别(S-学生;T-教师)
    @ExcelVOAttribute(name = "用户类别(S-学生;T-教师)", column = "D")
    private String userType;
    
    private String status;
    
    /*
     * 新增字段isOpenAct-是否开户(0-未开户，1-已开户)
     */
    private String isOpenAct;

	private String remark1;

    private String remark2;

    private String remark3;

    /*
     * 增加系部名称和班级名称属性，以供查询显示使用，仅仅提供get方法
     */
    private String deptName;
	private String className;
	public String getDeptName() {
		return deptName;
	}
	public String getClassName() {
		return className;
	}
    
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTsno() {
        return tsno;
    }

    public void setTsno(String tsno) {
        this.tsno = tsno == null ? null : tsno.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId == null ? null : deptId.trim();
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId == null ? null : classId.trim();
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType == null ? null : userType.trim();
    }

    public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
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
    

	public String getIsOpenAct() {
		return isOpenAct;
	}
	public void setIsOpenAct(String isOpenAct) {
		this.isOpenAct = isOpenAct;
	}

}