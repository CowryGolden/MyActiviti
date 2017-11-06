package com.zzrenfeng.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestReadConfigProperties {
	/**
	 * 私有构造方法，不需要创建对象
	 */
	private TestReadConfigProperties() {		
	}
	
	private static String uploadPath;
	private static String downloadPath;
	
	static {
		Properties prop = new Properties();
		InputStream in = Object.class.getResourceAsStream("/config.properties");
		//InputStream in = TestReadConfigProperties.class.getClassLoader().getResourceAsStream("config.properties");
		try {
			prop.load(in);
			uploadPath = prop.getProperty("file.upload.dir").trim();
			downloadPath = prop.getProperty("file.download.dir").trim();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getUploadPath() {
		return uploadPath;
	}
	
	public static String getDownloadPath() {
		return downloadPath;
	}

	public static void main(String[] args) {
		System.out.println("--------getUploadPath()>>>>" + getUploadPath());
		System.out.println("--------getDownloadPath()>>>>" + getDownloadPath());
		System.out.println("user.dir path: " + System. getProperty ("user.dir"));
	}

}
