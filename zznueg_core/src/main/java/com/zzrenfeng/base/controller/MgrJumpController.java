package com.zzrenfeng.base.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zzrenfeng.base.enums.DictionaryEnum;
import com.zzrenfeng.base.utils.Constants;
import com.zzrenfeng.base.utils.DateUtil;
import com.zzrenfeng.base.utils.IpUtil;
import com.zzrenfeng.base.utils.SystemBuffer;

/**
 * 后台页面布局控制器
 */
@Controller
@RequestMapping(value = "/manage")
public class MgrJumpController {

    private final Logger LOGGER = LoggerFactory.getLogger(MgrJumpController.class);

    @RequestMapping(value = "/mTop", method = RequestMethod.GET)
    public String mTop() {

        LOGGER.debug("mTop() is executed!");

        return "manage/layout/header";
    }

    @RequestMapping(value = "/mLeft", method = RequestMethod.GET)
    public String mLeft() {

        LOGGER.debug("mLeft() is executed!");

        return "manage/layout/left";
    }

    @RequestMapping(value = "/mFoot", method = RequestMethod.GET)
    public String mFoot() {

        LOGGER.debug("mFoot() is executed!");

        return "manage/layout/footer";
    }

    @RequestMapping(value = "/mCenter", method = RequestMethod.GET)
    public String mCenter() {

        LOGGER.debug("mCenter() is executed!");

        return "manage/layout/center";
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request) {
    	//request.getRemoteHost() 本地访问时使用localhost，获取的为ipv6地址；使用127.0.0.1即可避免
//    	if(IPAddressUtil.isIPv6LiteralAddress(request.getRemoteHost())) {
//System.out.println("###################====>>>>" + IPAddressUtil.textToNumericFormatV4(request.getRemoteHost()));    		
//    	}
    	
        LOGGER.debug("来自IP[" + IpUtil.getIpAddr(request) + "]的访问");
        return "manage/login";
    }

    /**
     * @功能描述：登录成功后主页面跳转
     * @创  建  者：zhoujincheng
     * @版        本：V1.0.0
     * @创建日期：2017年9月28日 下午3:08:06
     * 
     * @修  改  人：zjc
     * @版        本：V1.1.0
     * @修改日期：20170928 15:15
     * @修改描述：增加系统初始化参数，登录成功，跳转到系统主页面时就初始化，不用在各个子系统单独初始化
     * 
     * @param request
     * @return
     */
    @RequestMapping("/main")
    public String main(HttpServletRequest request) {
        LOGGER.debug("main() is executed!");     
        /**********************************************/
        /** 
         * 登录成功后加载系统初始化参数，在此仅初始化一次即可，可以在整个系统中使用，不用在各个子系统（学生平台和教师平台）中分别初始化
         * 增加评估起止日期两个参数，放于session域，并将结束日期与系统当前日期比较的结果也放于session中；当前日期大返回"1"，否则返回"0"
         */
        /**********************************************/
		/*
		 * 评估系统——获取考试起止时间，并将起止日期放于session中
		 */
		String startDateStr = SystemBuffer.getItemValueByDictEnum(DictionaryEnum.EXAMTERM_STARTDATE);
		String endDateStr = SystemBuffer.getItemValueByDictEnum(DictionaryEnum.EXAMTERM_ENDDATE);
		request.getSession(false).setAttribute("startDate", startDateStr);
		request.getSession(false).setAttribute("endDate", endDateStr);	
		/*
		 * 评估系统——获取当前日期，并于结束日期比较，将判断结果也放于session中
		 */
		Date now = new Date();
		String nowStr = DateUtil.DateToStr(now, null);
		Date endDate = DateUtil.StrToDate(endDateStr, null);
		String gtFlag = now.getTime() > endDate.getTime() ? Constants.COMM_CONST_STRING_1 : Constants.COMM_CONST_STRING_0;
		request.getSession(false).setAttribute("now", nowStr);	
		request.getSession(false).setAttribute("gtFlag", gtFlag);

        return "manage/main";
    }

}
