package com.zzrenfeng.zznueg.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传
 * @author  田杰熠
 * @version 1.0
 */
public class FilesUploadUtil {

	
	
	/**
	 * 文件上传
	 * @param uploadFile  上传文件
	 * @param uploadPath  保存上传文件的根路径
	 * @return 返回文件路径
	 * @throws Exception 
	 */
	public static String fileUpload(MultipartFile uploadFile,String uploadPath) throws Exception{
		
		if(uploadFile!=null&&uploadFile.getSize()!=0){
			/*
			 * 获得文件名
			 */
			String originaFilename=uploadFile.getOriginalFilename(); 	   
			String fileType = originaFilename.substring(originaFilename.lastIndexOf("."));  
			String newFilename = new Date().getTime() +String.valueOf(originaFilename.hashCode())+fileType;  
			newFilename=newFilename.replaceAll("-", "").trim();
			
			
			
			
			/*
			 * 目录分级处理
			 */
			int rank=2;
			int hashcode = originaFilename.hashCode();
			int a = hashcode & 0xf;
			int b = (a >>> 4) & 0xf;
			int c = (b >>> 4) & 0xf;
			int d = (c >>> 4) & 0xf;
			String randomDirectory ="";
			switch (rank) {
			case 1:
				randomDirectory ="/" + a;
				break;
			case 2:
				randomDirectory ="/" + a + "/" + b;
				break;
			case 3:
				randomDirectory ="/" + a + "/" + b + "/" + c;
				break;
			case 4:
				randomDirectory ="/" + a + "/" + b + "/" + c + "/" + d;
				break;
			default:
				 randomDirectory="";
				break;
			}
			
			
			/*
			 * 获得文件路径
			 * 	如果路径不存在进行创建
			 */
			File filepath=new File(uploadPath, randomDirectory); 	
			if (!filepath.exists()) {
				filepath.mkdirs();
			}
		
			/*
			 *上传文件
			 *   将文件从内存中写入到文件路径
			 */
		
			uploadFile.transferTo(new File(filepath,newFilename));
			
			return randomDirectory+"/"+newFilename;
		}
		return null;
	}
	
	
	/**
	 * 视频转码截图上传 (转码工具 ffmpeg.exe)
	 * @param upFilePath 用于指定要转换格式的文件,要截图的视频源文件
	 * @param ffmpegPath 转码工具的存放路径
	 * @param codcFilePath 格式转换后的的文件保存根路径
	 * @param mediaPicPath 截图保存根路径
	 * @param rank  目录分级级别  可传值(0,1,2,3,4) 分别代表(不做目录分级,1级目录分级,2级目录分级,3级目录分级,4级目录分级,其他值将默认为0)
	 * @return
	 */
	public static boolean videoTranscodeUpload(MultipartFile uploadFile,String ffmpegPath,String codcFilePath, String mediaPicPath,int rank) throws Exception{
		if(uploadFile!=null&&uploadFile.getSize()!=0){
			String originaFilename=uploadFile.getOriginalFilename(); 	  
			String fileType = originaFilename.substring(originaFilename.lastIndexOf("."));  
			String newFilename = new Date().getTime() +String.valueOf(originaFilename.hashCode()); 
			File uploadFilePath = new File(codcFilePath+"/temp/"+newFilename + fileType); 
			if (!uploadFilePath.exists()) {
				uploadFilePath.mkdirs();
			}
			uploadFile.transferTo(uploadFilePath);
			
			/*
			 * 目录分级处理
			 */
			int hashcode = originaFilename.hashCode();
			int a = hashcode & 0xf;
			int b = (a >>> 4) & 0xf;
			int c = (b >>> 4) & 0xf;
			int d = (c >>> 4) & 0xf;
			String randomDirectory ="";
			switch (rank) {
			case 1:
				randomDirectory ="/" + a;
				break;
			case 2:
				randomDirectory ="/" + a + "/" + b;
				break;
			case 3:
				randomDirectory ="/" + a + "/" + b + "/" + c;
				break;
			case 4:
				randomDirectory ="/" + a + "/" + b + "/" + c + "/" + d;
				break;
			default:
				 randomDirectory="";
				break;
			}
			
			String upFilePath=uploadFilePath.getAbsolutePath();
			codcFilePath=codcFilePath+randomDirectory+"/" + newFilename + ".flv";  
			mediaPicPath=mediaPicPath+randomDirectory+"/" + File.separator+newFilename + ".jpg"; 
			boolean flag = videoTranscodeUpload(ffmpegPath, upFilePath, codcFilePath, mediaPicPath);
			return flag;
		}
		return false;
	}
	
	
	
	
	
	/**
	 * 视频转码截图上传 (转码工具 ffmpeg.exe)
	 * @param ffmpegPath 转码工具的存放路径
	 * @param upFilePath 用于指定要转换格式的原文件路径,要截图的视频原文件路径
	 * @param codcFilePath 格式转换后的的文件保存路径
	 * @param mediaPicPath 截图保存路径
	 * @return
	 */
	public static boolean videoTranscodeUpload(String ffmpegPath,String upFilePath,String codcFilePath, String mediaPicPath){
		// 创建一个List集合来保存转换视频文件为flv格式的命令
        List<String> convert = new ArrayList<String>();
        convert.add(ffmpegPath); // 添加转换工具路径
        convert.add("-i"); // 添加参数＂-i＂，该参数指定要转换的文件
        convert.add(upFilePath); // 添加要转换格式的视频文件的路径
        convert.add("-qscale");     //指定转换的质量 取值0.01-255，约小质量越好
        convert.add("5");
        //convert.add("-sameq");    //与源文件同质量
        convert.add("-ab");        //设置音频码率
        convert.add("64");
        convert.add("-ac");        //设置声道数
        convert.add("2");
        convert.add("-ar");        //设置声音的采样频率
        convert.add("22050");
        convert.add("-r");        //设置帧频
        convert.add("29.97");
        convert.add("-y"); // 添加参数＂-y＂，该参数指定将覆盖已存在的文件
        convert.add(codcFilePath);

        // 创建一个List集合来保存从视频中截取图片的命令
        List<String> cutpic = new ArrayList<String>();
        cutpic.add(ffmpegPath);
        cutpic.add("-i");
        cutpic.add(upFilePath); // 同上（指定的文件即可以是转换为flv格式之前的文件，也可以是转换的flv文件）
        cutpic.add("-y");
        cutpic.add("-f");
        cutpic.add("image2");
        cutpic.add("-ss"); // 添加参数＂-ss＂，该参数指定截取的起始时间
        cutpic.add("17"); // 添加起始时间为第17秒
        cutpic.add("-t"); // 添加参数＂-t＂，该参数指定持续时间
        cutpic.add("0.001"); // 添加持续时间为1毫秒
        cutpic.add("-s"); // 添加参数＂-s＂，该参数指定截取的图片大小
        cutpic.add("800*600"); // 添加截取的图片大小为320*240
        cutpic.add(mediaPicPath); // 添加截取的图片的保存路径
        	
        boolean mark = true;
        ProcessBuilder builder = new ProcessBuilder();
        try {
            builder.command(convert);
            builder.redirectErrorStream(true);
            builder.start();
            
            builder.command(cutpic);
            builder.redirectErrorStream(true);
            // 如果此属性为 true，则任何由通过此对象的 start() 方法启动的后续子进程生成的错误输出都将与标准输出合并，
            //因此两者均可使用 Process.getInputStream() 方法读取。这使得关联错误消息和相应的输出变得更容易
            builder.start();
        } catch (Exception e) {
            mark = false;
            System.out.println(e);
            e.printStackTrace();
        }
        return mark;
	}
	
	
	
	
	
	
	
	
}
