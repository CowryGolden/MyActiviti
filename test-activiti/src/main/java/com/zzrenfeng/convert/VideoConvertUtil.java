package com.zzrenfeng.convert;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @功能描述：视频转换工具类，使用FFMpeg或mencoder进行视频打包格式转换，使用FFMpeg进行视频截图
 * @创  建  者：zhoujincheng
 * @版        本：V1.0.0
 * @创建日期：2017年12月5日 下午2:34:26
 * 
 * @修  改  人：
 * @版        本：V1.1.0
 * @修改日期：
 * @修改描述：
 *
 */
public class VideoConvertUtil {
	
	private static final String FILEPATH = "F:\\ade\\test\\inFile\\videos\\video-demo.mp4";
	private static String MENCODERPATH = "D:\\ProgramFiles\\ffmpeg\\mencoder\\mencoder.exe";
	private static String FFMPEGPATH = "D:\\ProgramFiles\\ffmpeg\\ffmpeg.exe";
	private static String OUTVIDEOPATH = "F:\\ade\\test\\outFile\\videos\\";
	private static String OUTIMAGEPATH = "F:\\ade\\test\\outFile\\images\\";
		
	/**
	 * main方法
	 */
	public static void main(String[] args) {
		if(!FileUtil.checkFile(FILEPATH)) {
			System.out.println(FILEPATH + "源文件不存在");
			return;
		}
		
		/*if(processConvert()) {
			System.out.println("视频格式转换并且截图成功！");
		} else {
			System.out.println("视频格式转换并且截图失败！");
		}*/
		
		if(processVideoCutImage(FILEPATH)) {
			System.out.println("====视频截图成功！====");
		} else {
			System.out.println("====视频截图失败！====");
		}
	}
	
	/**
	 * @功能描述：视频转换总入口
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年12月5日 下午4:18:28
	 * 
	 * @修  改  人：
	 * @版        本：V1.1.0
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @return
	 */
	public static boolean processConvert() {
		int type = checkVideoType(FILEPATH);
		boolean status = false;
		if(0 == type) {
			System.out.println("直接将文件转为flv文件");
			status = processConvert2FLV(FILEPATH);	//直接将文件转为flv文件
		} else if(1 == type) {
			String aviFilePath = processConvert2AVI(FILEPATH);	//ffmpeg无法解析的文件格式(wmv9，rm，rmvb等)，直接用工具（mencoder）将文件转为flv文件
			if(null == aviFilePath) {
				return false;	//avi文件没有得到
			}
			status = processConvert2FLV(FILEPATH);	//将avi文件转为flv文件
		} else {
			System.out.println("文件类型未知，无法转换");
			status = false;
		}
		
		return status;
	}

	/**
	 * @功能描述：根据视频文件后缀名判断视频格式类型
	 * 			ffmpeg能解析的格式：asx，asf，mpg，wmv，3gp，mp4，mov，avi，flv等；
	 * 			ffmpeg无法解析的格式：wmv9，rm，rmvb等；
	 * 			注意：对ffmpeg无法解析的文件格式(wmv9，rm，rmvb等)，可以先用别的工具（mencoder）转换为avi(ffmpeg能解析的)格式。
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年12月5日 下午2:56:36
	 * 
	 * @修  改  人：
	 * @版        本：V1.1.0
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param filePath-文件路径+文件名称
	 * 
	 * @return 0-ffmpeg可以处理的格式，1-ffmpeg无法处理的格式，9-未知视频格式；
	 */
	public static int checkVideoType(String filePath) {
		String type = filePath.substring(filePath.lastIndexOf(".") + 1, filePath.length()).toLowerCase();
		
		if(type.equals("avi")) {		//ffmpeg能解析的格式：asx，asf，mpg，wmv，3gp，mp4，mov，avi，flv等；返回：0
			return 0;
		} else if(type.equals("mpg")) {
			return 0;
		} else if(type.equals("wmv")) {
			return 0;
		} else if(type.equals("3gp")) {
			return 0;
		} else if(type.equals("mov")) {
			return 0;
		} else if(type.equals("mp4")) {
			return 0;
		} else if(type.equals("asf")) {
			return 0;
		} else if(type.equals("asx")) {
			return 0;
		} else if(type.equals("flv")) {
			return 0;
		} else if(type.equals("wmv9")) {	//ffmpeg无法解析的格式：wmv9，rm，rmvb等；返回：1
			return 1;
		} else if(type.equals("rm")) {
			return 1;
		} else if(type.equals("rmvb")) {
			return 1;
		}
		
		return 9;
	}
	
	/**
	 * @功能描述：将视频文件转化为avi格式的文件
	 * 			对ffmpeg无法解析的文件格式(wmv9，rm，rmvb等)，可以先用别的工具（mencoder）转换为avi(ffmpeg能解析的)格式
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年12月5日 下午3:20:52
	 * 
	 * @修  改  人：
	 * @版        本：V1.1.0
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param type-视频类型
	 * @return 转换成功后的avi文件名（路径+文件名称）
	 */
	public static String processConvert2AVI(String filePath) {
		if(!FileUtil.checkFile(filePath)) {
			System.out.println(filePath + "源文件不存在");
			return null;
		}
		String outFilePath = OUTVIDEOPATH + "video-demo-test2avi.avi";
		List<String> command = new ArrayList<String>();
		command.add(MENCODERPATH);
		command.add(filePath);
		command.add("-oac");
		command.add("lavc");
		command.add("-lavcopts");
		command.add("acodec=mp3:abitrate=64");
		command.add("-ovc");
		command.add("xvid");
		command.add("-xvidencopts");
		command.add("bitrate=600");
		command.add("-of");
		command.add("avi");
		command.add("-o");
		command.add(outFilePath);
		
		try {
			ProcessBuilder builder = new ProcessBuilder();
			builder.command(command);
			builder.start();
			return outFilePath;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * @功能描述：ffmpeg能解析的文件格式（asx，asf，mpg，wmv，3gp，mp4，mov，avi，flv等），直接将其转换为flv格式的文件，并进行截图
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年12月5日 下午3:42:50
	 * 
	 * @修  改  人：
	 * @版        本：V1.1.0
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param filePath-源文件路径
	 * @return
	 */
	public static boolean processConvert2FLV(String filePath) {
		if(!FileUtil.checkFile(filePath)) {
			System.out.println(filePath + "源文件不存在");
			return false;
		}
				
		//输出文件命名
		Calendar c = Calendar.getInstance();
		String savename = String.valueOf(c.getTimeInMillis() + Math.round(Math.random() * 100000));
		String outVideoFile = OUTVIDEOPATH + "video-demo-test2flv_" + savename + ".flv";
		String outImageFile = OUTIMAGEPATH + "video-demo-testcutimage_" + savename + ".jpg";
		
		List<String> command = new ArrayList<String>();
		command.add(FFMPEGPATH);
		command.add("-i");
		command.add(filePath);
		command.add("-ab");
		command.add("56");
		command.add("-ar");
		command.add("22050");
		command.add("-qscale");
		command.add("8");
		command.add("-r");
		command.add("15");
		command.add("-s");
		command.add("600*500");
		command.add(outVideoFile);
		
		try {
			Runtime runtime = Runtime.getRuntime();
			Process process = null;
			//视频截图命令
			String cutCmd = FFMPEGPATH + " -i " + filePath
					+ " -y -f image2 -ss 8 -t 0.001 -s 600*500 "
					+ outImageFile
					;
			process = runtime.exec(cutCmd);	//进行视频截图（如下processVideoCutImage()方法截图也可以）
			//进行视频格式转换（以下两种方式均可）
//			ProcessBuilder builder = new ProcessBuilder(); 
//			builder.command(command);
			ProcessBuilder builder = new ProcessBuilder(command); 
			builder.command();
			builder.start();
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * @功能描述：
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年12月5日 下午4:47:06
	 * 
	 * @修  改  人：
	 * @版        本：V1.1.0
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @return
	 */
	public static boolean processVideoCutImage(String filePath) {
		if(!FileUtil.checkFile(filePath)) {
			System.out.println(filePath + "源文件不存在");
			return false;
		}
		
		//输出文件命名
		Calendar c = Calendar.getInstance();
		String savename = String.valueOf(c.getTimeInMillis() + Math.round(Math.random() * 100000));
		String outImageFile = OUTIMAGEPATH + "video-demo-testcutimage_" + savename + ".jpg";
		
		List<String> command = new ArrayList<String>();
		command.add(FFMPEGPATH);
		command.add("-i");
		command.add(filePath);
		command.add("-y");
		command.add("-f");
		command.add("image2");
		command.add("-ss");
		command.add("7");
		command.add("-t");
		command.add("0.001");
		command.add("-s");
		command.add("930*380");
		command.add(outImageFile);
		
		InputStream in = null;
		
		try {
			ProcessBuilder builder = new ProcessBuilder();
			builder.command(command);
			/*
			 * 如果该属性为true，那么随后由此对象的start()方法启动的子进程生成的任何错误输出将与标准输出合并，
			 * 以便可以使用Process.getInputStream()方法读取这两个输出。 这使得将错误消息与相应的输出相关联变得更容易。
			 * 初始值是false。
			 */
			builder.redirectErrorStream(true);
			
			System.out.println("视频截图开始...");
			Process process = builder.start();
			in = process.getInputStream();
			byte[] bytes = new byte[1024];
			System.out.println("正在进行视频截，请稍后");
			while (-1 != in.read(bytes)) {
				System.out.print(".");
			}
			System.out.println();
			if(null != in) {
				in.close();
			}
			System.out.println("视频截图完成...");
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("视频截图失败！");
			return false;
		}
	}

}
