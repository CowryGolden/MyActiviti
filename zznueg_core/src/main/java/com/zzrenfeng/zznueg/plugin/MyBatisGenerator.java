package com.zzrenfeng.zznueg.plugin;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.ShellRunner;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

/**
 * @功能描述：MyBatis逆向工程代码生成器
 * 			使用方法：(以MySQL为例)
 * 			使用时对源文件根目录下的generatorConfig.xml进行配置
 * 			配置一：引入jdbc链接数据源jar包
 * 				line：12；mysql-connector-java-5.1.35.jar路径修改成自己的路径
 * 			配置二：配置自己连接的数据库驱动、connectionURL、用户名和密码
 * 				line：27；配置驱动和connectionURL
 * 				line：28；配置用户名和密码
 * 			配置三：配置生成的实体类，所在的包路径，工程目录路径，继承的父类等
 *  			line：37；配置包路径，工程目录路径
 *   			line：42；配置该类继承的父类
 *   		配置四：配置生成的mapxml文件所在的包路径，工程目录路径
 *   			line：46；配置包路径，工程目录路径
 *   		配置五：配置生成的mapxml对应的Dao接口，所在的包路径，工程目录路径，以及继承的父接口等
 *   			line：51；配置包路径，工程目录路径
 *   			line：53；配置该接口所继承的父接口
 *   		配置六：配置对应的数据库中数据表信息，如：schema，表名，生成的实体类名，实体属性等
 * 				line:58-71
 * 
 * 			配置完成后执行本类的main方法即可生成对应文件，刷新工程即可看到对应目录的对应文件。
 *   				
 * @创  建  者：zhoujincheng
 * @版        本：V1.0.0
 * @创建日期：2017年7月29日 上午10:46:52
 * 
 * @修  改  人：zjc
 * @修改日期：20170923 11:33
 * @修改描述：增加 myGenerate() 方法
 *
 */
public class MyBatisGenerator {
	/**
	 * @功能描述：代码生成器1
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月23日 上午11:33:12
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 *
	 */
	public static void generate() {
		String config = MyBatisGenerator.class.getClassLoader().getResource("generatorConfig.xml").getFile();
		String[] arg = { "-configfile", config, "-overwrite" };
		ShellRunner.main(arg);
	}
	/**
	 * @功能描述：代码生成器2
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月23日 上午11:33:31
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 *
	 */
	public static void myGenerate() {
		String fileName = "E:/workspace/ZZNUEvalGraduation/resources/generatorConfig.xml";
        File configFile = new File(fileName);
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        try {
			ConfigurationParser cp = new ConfigurationParser(warnings);
			Configuration config = cp.parseConfiguration(configFile);
			DefaultShellCallback callback = new DefaultShellCallback(overwrite);
			org.mybatis.generator.api.MyBatisGenerator myBatisGenerator = new org.mybatis.generator.api.MyBatisGenerator(config, callback, warnings);
			myBatisGenerator.generate(null);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XMLParserException e) {
			e.printStackTrace();
		} catch (InvalidConfigurationException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		generate();
		//myGenerate();
	}
}
