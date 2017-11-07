package com.zzrenfeng.zznueg.entity;

import com.zzrenfeng.base.entity.BaseDomain;
import com.zzrenfeng.base.utils.ExcelVOAttribute;

/**
 * @功能描述：测试批量导入教师学生信息的实体类
 * @创  建  者：zhoujincheng
 * @版        本：V1.0.0
 * @创建日期：2017年10月13日 上午11:47:07
 * 
 * @修  改  人：
 * @修改日期：
 * @修改描述：
 *
 */
public class TestImportTeachStuInfo extends BaseDomain {
    /**
     * 主键ID
     */
    private String id;

    /**
     * 学号(S)/工号(T)
     */
    @ExcelVOAttribute(name = "学号(S)/工号(T)", column = "A")
    private String tsno;

    /**
     * 姓名
     */
    @ExcelVOAttribute(name = "姓名", column = "B")
    private String name;

    /**
     * 性别(0-女;1-男)
     */
    @ExcelVOAttribute(name = "性别(0-女;1-男)", column = "C")
    private String sex;

    /**
     * 所属系别ID
     */
    private String deptId;

    /**
     * 所属班级ID
     */
    private String classId;

    /**
     * 用户类别(S-学生;T-教师)
     */
    @ExcelVOAttribute(name = "用户类别(S-学生;T-教师)", column = "D")
    private String userType;

    /**
     * 当前状态,E:有效的,I:无效的
     */
    private String status;

    /**
     * 是否开户(0-未开户，1-已开户)
     */
    private String isOpenAct;

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
     * 学号(S)/工号(T)
     * @return tsno 学号(S)/工号(T)
     */
    public String getTsno() {
        return tsno;
    }

    /**
     * 学号(S)/工号(T)
     * @param tsno 学号(S)/工号(T)
     */
    public void setTsno(String tsno) {
        this.tsno = tsno == null ? null : tsno.trim();
    }

    /**
     * 姓名
     * @return name 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 姓名
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 性别(0-女;1-男)
     * @return sex 性别(0-女;1-男)
     */
    public String getSex() {
        return sex;
    }

    /**
     * 性别(0-女;1-男)
     * @param sex 性别(0-女;1-男)
     */
    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    /**
     * 所属系别ID
     * @return deptId 所属系别ID
     */
    public String getDeptId() {
        return deptId;
    }

    /**
     * 所属系别ID
     * @param deptId 所属系别ID
     */
    public void setDeptId(String deptId) {
        this.deptId = deptId == null ? null : deptId.trim();
    }

    /**
     * 所属班级ID
     * @return classId 所属班级ID
     */
    public String getClassId() {
        return classId;
    }

    /**
     * 所属班级ID
     * @param classId 所属班级ID
     */
    public void setClassId(String classId) {
        this.classId = classId == null ? null : classId.trim();
    }

    /**
     * 用户类别(S-学生;T-教师)
     * @return userType 用户类别(S-学生;T-教师)
     */
    public String getUserType() {
        return userType;
    }

    /**
     * 用户类别(S-学生;T-教师)
     * @param userType 用户类别(S-学生;T-教师)
     */
    public void setUserType(String userType) {
        this.userType = userType == null ? null : userType.trim();
    }

    /**
     * 当前状态,E:有效的,I:无效的
     * @return status 当前状态,E:有效的,I:无效的
     */
    public String getStatus() {
        return status;
    }

    /**
     * 当前状态,E:有效的,I:无效的
     * @param status 当前状态,E:有效的,I:无效的
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * 是否开户(0-未开户，1-已开户)
     * @return isOpenAct 是否开户(0-未开户，1-已开户)
     */
    public String getIsOpenAct() {
        return isOpenAct;
    }

    /**
     * 是否开户(0-未开户，1-已开户)
     * @param isOpenAct 是否开户(0-未开户，1-已开户)
     */
    public void setIsOpenAct(String isOpenAct) {
        this.isOpenAct = isOpenAct == null ? null : isOpenAct.trim();
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