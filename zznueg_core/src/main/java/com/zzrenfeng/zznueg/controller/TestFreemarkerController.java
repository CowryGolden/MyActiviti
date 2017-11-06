package com.zzrenfeng.zznueg.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
/**
 * @功能描述：Freemarker测试
 * @创  建  者：zhoujincheng
 * @版        本：V1.0.0
 * @创建日期：2017年8月11日 上午11:23:04
 * 
 * @修  改  人：
 * @修改日期：
 * @修改描述：
 *
 */
@Controller
@RequestMapping(value = "/zznueg/test/")
public class TestFreemarkerController {

	/**
	 * 模板路径：/WEB-INF/template/ftl/*.ftl
	 * Freemarker模板的Controller
	 * 访问路径： zznueg/test/getFtl
	 */  
    @RequestMapping(value = "getFtl", method = RequestMethod.GET)  
    public ModelAndView getFreemarkerTemplatePage(HttpServletRequest request) {  
        //welcom就是视图的名称（welcom.ftl）  
        ModelAndView mv = new ModelAndView("testFreemarkerTemplate");  
        mv.addObject("name", "My First Spring Mvc and Freemarker! 哈哈哈！！！");  
        return mv;  
    }  
    
    /**
     * 视图路径：/WEB-INF/views/ * / *.jsp
     * jsp模板的Controller
     * 访问路径： zznueg/test/getJsptl
     */  
    @RequestMapping(value = "getJsptl",method = {RequestMethod.GET})  
    public ModelAndView getJspTemplatePage (HttpServletRequest request) {  
        ModelAndView mv = new ModelAndView("testJspFtl");  
        mv.addObject("message1", "切换到jsp模板");  
        mv.addObject("message2", "My First Spring Mvc");  
        mv.setViewName("test/testJspFtl");  
        return mv;  
    }  
	
}
