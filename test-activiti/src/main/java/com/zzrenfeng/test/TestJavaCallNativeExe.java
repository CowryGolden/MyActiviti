package com.zzrenfeng.test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @功能描述：使用java调用本地.exe文件
 * @创  建  者：zhoujincheng
 * @版        本：V1.0.0
 * @创建日期：2017年12月5日 上午11:20:08
 * 
 * @修  改  人：
 * @版        本：V1.1.0
 * @修改日期：
 * @修改描述：
 *
 */
public class TestJavaCallNativeExe {
	
	/**
	 * @功能描述：使用java调用windows系统的exe文件，比如notepad，calc之类
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年12月5日 上午11:22:21
	 * 
	 * @修  改  人：
	 * @版        本：V1.1.0
	 * @修改日期：
	 * @修改描述：
	 *
	 */
	public static void openWindowsExe(String command) {
		final Runtime runtime = Runtime.getRuntime();
		Process process = null;
		
		try {
			process = runtime.exec(command);
		} catch (final Exception e) {
			System.out.println("Error win exec!");
			e.printStackTrace();
		}
	}
	
	/**
	 * @功能描述：打开其他任意格式的文件，比如txt,word等 
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年12月5日 上午11:42:04
	 * 
	 * @修  改  人：
	 * @版        本：V1.1.0
	 * @修改日期：
	 * @修改描述：
	 *
	 */
	public static void openFile() {  
	    final Runtime runtime = Runtime.getRuntime();  
	    Process process = null;
	    final String cmd = "rundll32 url.dll FileProtocolHandler file://C:\\Users\\Golden Cowry\\Desktop\\ffmpeg命令.txt";
	    //final String cmd = "rundll32 url.dll FileProtocolHandler file://C:\\Users\\Golden Cowry\\Desktop\\5.关于校园多目标跟踪主机的研究项目开发计划书.doc";
	    try {  
	        process = runtime.exec(cmd);  
	    } catch (final Exception e) {  
	        System.out.println("Error exec!");  
	    }  
	}  
	
	/**
	 * @功能描述：调用ffmpeg.exe，并带参数执行
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年12月5日 上午11:39:35
	 * 
	 * @修  改  人：
	 * @版        本：V1.1.0
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param args
	 */
	public static void openFFmpegExe() {
		//视频文件     
        final String videoRealPath = "F:\\ade\\test\\inFile\\videos\\video-demo.mp4";     
        //截图的路径（输出路径）     
        final String imageRealPath ="F:\\ade\\test\\outFile\\images\\video-demo-test.jpg"; 
        
		final Runtime runtime = Runtime.getRuntime();  
	    Process process = null;
		final String cmd = "cmd.exe /c start D:\\ProgramFiles\\ffmpeg\\ffmpeg.exe"
				+ " -i " + videoRealPath
				+ " -y -f image2 -ss 8 -t 0.001 -s 350*240 " + imageRealPath
				;
		InputStream in = null;
		try {
			System.out.println("视频截图开始...");
	        process = runtime.exec(cmd);  
	        in = process.getInputStream();
	        byte[] bytes = new byte[1024];
	        System.out.println("正在截图，请稍后");
	        while(-1 != in.read(bytes)) {
	        	System.out.print(".");
	        }
	        System.out.println();
	        
	        System.out.println("视频截图完成...");
	    } catch (final Exception e) {  
	        System.out.println("Error exec!");  
	    } finally {			
			try {
				if(null != in) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static boolean checkFile(String path) {
		File file = new File(path);
		if(!file.isFile()) {
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		//调用Windows系统本地的exe文件，如notepad，calc
		//openWindowsExe("calc");
		//打开本地文件
		//openFile();
		openFFmpegExe();
	}
	
	

}
