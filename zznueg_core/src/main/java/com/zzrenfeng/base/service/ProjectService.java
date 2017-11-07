package com.zzrenfeng.base.service;

import java.util.List;

import com.zzrenfeng.base.entity.Project;
import com.zzrenfeng.base.entity.User;

/**
 * Description:
 * author: zhoujincheng
 * date 2016/4/29 15:51
 */
public interface ProjectService extends BaseService<Project> {
    /**
     * Description:查询公司所属项目组信息
     * Name:getPrjByCoId
     * Author:zhoujincheng
     * Time:2016/4/29 16:02
     * param:[coId]
     * return:java.util.List<com.zzrenfeng.base.entity.Project>
     */
    List<Project> getPrjByCoId(String coId);

    /**
     * Description: 实例化项目组信息
     * Name:persistencePrj
     * Author:zhoujincheng
     * Time:2016/4/29 16:02
     * param:[prj]
     * return:boolean
     */
    boolean persistencePrj(Project prj);

    /**
     * Description: 删除项目组
     * Name:delPrj
     * Author:zhoujincheng
     * Time:2016/4/29 16:04
     * param:[prjId]
     * return:boolean
     */
    boolean delPrj(String prjId);


    /**
     * Description: 保存分配给项目组的角色
     * Name:savePrjRole
     * Author:zhoujincheng
     * Time:2016/4/29 16:17
     * param:[prjId, checkedIds]
     * return:boolean
     */
    boolean savePrjRole(String prjId, String checkedIds);

    /**
     * Description: 保存分配给项目组的成员
     * Name:savePrjRole
     * Author:zhoujincheng
     * Time:2016/4/29 16:17
     * param:[prjId, checkedIds]
     * return:boolean
     */
    boolean savePrjUsers(String prjId, String checkedIds);


    /**
     * Description: 查找项目组成员
     * Name:searchPrjUser
     * Author:zhoujincheng
     * Time:2016/4/29 16:23
     * param:[prjId]
     * return:java.util.List<com.zzrenfeng.base.entity.User>
     */
    List<User> searchPrjUser(String prjId);
}
