package com.zzrenfeng.base.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtils {
	/**
	 * 私有构造方法，不需要创建对象
	 */
	private PropertiesUtils() {		
	}
	
	/**
	 * 文件上传路径
	 */
	private static String uploadPath;
	/**
	 * 文件下载路径
	 */
	private static String downloadPath;
	/**
	 * 读取配置文件中的配置属性
	 */
	static {
		Properties prop = new Properties();
		//InputStream in = Object.class.getResourceAsStream("/config.properties");
		InputStream in = PropertiesUtils.class.getClassLoader().getResourceAsStream("config.properties");
		try {
			prop.load(in);
			uploadPath = prop.getProperty("file.upload.dir").trim();
			downloadPath = prop.getProperty("file.download.dir").trim();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * @功能描述：获取配置文件中的文件上传路径 
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月30日 下午2:06:11
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @return
	 */
	public static String getUploadPath() {
		return uploadPath;
	}
	/**
	 * @功能描述：获取配置文件中的文件下载路径 
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年9月30日 下午2:06:33
	 * 
	 * @修  改  人：
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @return
	 */
	public static String getDownloadPath() {
		return downloadPath;
	}
	
	public static void main(String[] args) {
		System.out.println("--------getUploadPath()>>>>" + PropertiesUtils.getDownloadPath());
	}
}
