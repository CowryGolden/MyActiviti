package com.zzrenfeng.activiti.test;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
/**
 * @功能描述：测试SpringMVC整合Activiti是否成功
 * 			说明：运行无异常说明Activiti已整合到SpringMVC中。
 * @创  建  者：zhoujincheng
 * @版        本：V1.0.0
 * @创建日期：2017年12月14日 下午2:25:38
 * 
 * @修  改  人：
 * @版        本：V1.1.0
 * @修改日期：
 * @修改描述：
 *
 */
public class TestActiviti1 {

	public static void main(String[] args) {
        // 1.创建Activiti配置对象的实例
        ProcessEngineConfiguration configuration = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();
        // 2.设置数据库连接信息

        // 设置数据库地址
        configuration.setJdbcUrl("jdbc:mysql://localhost:3306/activiti6?createDatabaseIfNotExist&amp;useUnicode=true&amp;characterEncoding=utf8");
        // 数据库驱动
        configuration.setJdbcDriver("com.mysql.jdbc.Driver");
        // 用户名
        configuration.setJdbcUsername("root");
        // 密码
        configuration.setJdbcPassword("r00t");

        // 设置数据库建表策略
        /**
         * DB_SCHEMA_UPDATE_TRUE：如果不存在表就创建表，存在就直接使用
         * DB_SCHEMA_UPDATE_FALSE：如果不存在表就抛出异常
         * DB_SCHEMA_UPDATE_CREATE_DROP：每次都先删除表，再创建新的表
         */
        configuration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);

        // 3.使用配置对象创建流程引擎实例（检查数据库连接等环境信息是否正确）
        ProcessEngine processEngine = configuration.buildProcessEngine();

        System.out.println(processEngine);
    }
	
}
