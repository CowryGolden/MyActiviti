package com.zzrenfeng.base.service;

import java.util.List;

import com.zzrenfeng.base.entity.Question;
import com.zzrenfeng.base.utils.PageUtil;

/**
 * Description:
 * author: zhoujincheng
 * date 2016/5/13 11:00
 */
public interface QuestionService extends BaseService<Question> {

    /**
     * Description: 分页查询所有信息
     * Name:findAllByPage
     * Author:zhoujincheng
     * Time:2016/5/13 11:10
     * param:[pageUtil]
     * return:java.util.List<com.zzrenfeng.base.entity.Question>
     */
    List<Question> findAllByPage(PageUtil pageUtil);

    /**
     * Description:查找某个用户的所有问题
     * Name:findAllByUser
     * Author:zhoujincheng
     * Time:2016/5/13 11:04
     * param:[userId]
     * return:java.util.List<com.zzrenfeng.base.entity.Question>
     */
    List<Question> findAllByUser(String userId);

    /**
     * Description:查找某段时间内的所有问题
     * Name:findAllByTime
     * Author:zhoujincheng
     * Time:2016/5/13 11:04
     * param:[start, end]
     * return:java.util.List<com.zzrenfeng.base.entity.Question>
     */
    List<Question> findAllByTime(String start, String end);

    /**
     * Description:按关键字列查询问题
     * Name:findAllByKey
     * Author:zhoujincheng
     * Time:2016/5/13 11:04
     * param:[key]
     * return:java.util.List<com.zzrenfeng.base.entity.Question>
     */
    List<Question> findAllByKey(String key);

    /**
     * Description:模糊查询
     * Name:findAllByWord
     * Author:zhoujincheng
     * Time:2016/5/13 11:05
     * param:[word]
     * return:java.util.List<com.zzrenfeng.base.entity.Question>
     */
    List<Question> findAllByWord(String word);
}
