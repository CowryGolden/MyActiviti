package com.zzrenfeng.base.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zzrenfeng.base.entity.Question;
import com.zzrenfeng.base.service.QuestionService;
import com.zzrenfeng.base.utils.PageUtil;

import java.util.List;

/**
 * Description:
 * author: zhoujincheng
 * date 2016/5/13 11:06
 */
@Transactional
@Service("questionService")
public class QuestionServiceImpl extends BaseServiceImpl<Question> implements QuestionService {
    /**
     * Description: 分页查询所有信息
     * Name:findAllByPage
     * Author:zhoujincheng
     * Time:2016/5/13 11:10
     * param:[pageUtil]
     * return:java.util.List<com.zzrenfeng.base.entity.Question>
     *
     * @param pageUtil
     */
    @Override
    public List<Question> findAllByPage(PageUtil pageUtil) {
        return questionMapper.findAllByPage(pageUtil);
    }

    /**
     * Description:查找某个用户的所有问题
     * Name:findAllByUser
     * Author:zhoujincheng
     * Time:2016/5/13 11:04
     * param:[userId]
     * return:java.util.List<com.zzrenfeng.base.entity.Question>
     *
     * @param userId
     */
    @Override
    public List<Question> findAllByUser(String userId) {
        return null;
    }

    /**
     * Description:查找某段时间内的所有问题
     * Name:findAllByTime
     * Author:zhoujincheng
     * Time:2016/5/13 11:04
     * param:[start, end]
     * return:java.util.List<com.zzrenfeng.base.entity.Question>
     *
     * @param start
     * @param end
     */
    @Override
    public List<Question> findAllByTime(String start, String end) {
        return null;
    }

    /**
     * Description:按关键字列查询问题
     * Name:findAllByKey
     * Author:zhoujincheng
     * Time:2016/5/13 11:04
     * param:[key]
     * return:java.util.List<com.zzrenfeng.base.entity.Question>
     *
     * @param key
     */
    @Override
    public List<Question> findAllByKey(String key) {
        return null;
    }

    /**
     * Description:模糊查询
     * Name:findAllByWord
     * Author:zhoujincheng
     * Time:2016/5/13 11:05
     * param:[word]
     * return:java.util.List<com.zzrenfeng.base.entity.Question>
     *
     * @param word
     */
    @Override
    public List<Question> findAllByWord(String word) {
        return null;
    }
}
