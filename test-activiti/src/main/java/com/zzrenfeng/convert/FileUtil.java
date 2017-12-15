package com.zzrenfeng.convert;

import java.io.File;
import java.io.IOException;

/**
 * @功能描述：文件工具类
 * @创  建  者：zhoujincheng
 * @版        本：V1.0.0
 * @创建日期：2017年12月5日 下午2:52:49
 * 
 * @修  改  人：
 * @版        本：V1.1.0
 * @修改日期：
 * @修改描述：
 *
 */
public class FileUtil {
	/**
	 * @功能描述：检查文件是否存在，若不存在，则创建新文件
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年12月5日 下午2:35:25
	 * 
	 * @修  改  人：
	 * @版        本：V1.1.0
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param path
	 * @return
	 */
	public static boolean checkFile(String filePath) {
		File file = new File(filePath);
/*		
		if(!file.exists()) {
			System.out.println("文件不存在，需要新创建...");
			try {
				System.out.println("创建新文件开始...");
				file.createNewFile();
				System.out.println("创建新文件成功...");
			} catch (IOException e) {
				System.out.println("创建新文件失败...");
				e.printStackTrace();
			}
			return false;
		}
		System.out.println("文件存在，不需要新创建...");
*/		
		if(!file.exists()) {
			return false;
		}
		return true;
	}
	
	/**
	 * @功能描述：检查目录是否存在，若不存在，则创建相应的目录
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年12月5日 下午2:43:14
	 * 
	 * @修  改  人：
	 * @版        本：V1.1.0
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param dirPah
	 * @return
	 */
	public static boolean checkDir(String dirPath) {
		File file = new File(dirPath);
		if(!file.exists() && !file.isDirectory()) {
			System.out.println("目录不存在，需要新创建...");
			try {
				System.out.println("创目录目录开始...");
				file.mkdir();
				System.out.println("创目录目录成功...");
			} catch (Exception e) {
				System.out.println("创目录目录失败...");
				e.printStackTrace();
			}
			return false;
		}
		System.out.println("目录存在，不需要新创建...");
		return true;
	}
}
