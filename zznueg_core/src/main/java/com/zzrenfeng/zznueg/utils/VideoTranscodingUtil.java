package com.zzrenfeng.zznueg.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VideoTranscodingUtil {
	
	private static String ffmpegPath = "E:\\usr\\local\\ffmpeg\\bin\\ffmpeg.exe";	
	
	/**
	 * 视频转码截图上传 (转码工具 ffmpeg.exe)
	 * @param upFilePath 用于指定要转换格式的原文件路径,要截图的视频原文件路径
	 * @param codcFilePath 格式转换后的的文件保存路径
	 * @param mediaPicPath 截图保存路径
	 * @return
	 */
	public static boolean videoTranscodeUpload(String upFilePath,String codcFilePath, String mediaPicPath,String id){
		// 创建一个List集合来保存转换视频文件为flv格式的命令
		
        List<String> convert = new ArrayList<String>();
        convert.add(ffmpegPath); // 添加转换工具路径
        convert.add("-i"); // 添加参数＂-i＂，该参数指定要转换的文件
        convert.add(upFilePath); // 添加要转换格式的视频文件的路径
        convert.add("-y"); // 添加参数＂-y＂，该参数指定将覆盖已存在的文件
        convert.add("-ab");        //设置音频码率
        convert.add("32");
        convert.add("-ar");        //设置声音的采样频率
        convert.add("22050");
        convert.add("-qscale");     //指定转换的质量 取值0.01-255，约小质量越好
        convert.add("7");
        convert.add("-r");        //设置帧频
        convert.add("15");
        convert.add(codcFilePath);
        
        

        // 创建一个List集合来保存从视频中截取图片的命令
        List<String> cutpic = new ArrayList<String>();
        cutpic.add(ffmpegPath);
        cutpic.add("-y");
        cutpic.add("-ss"); // 添加参数＂-ss＂，该参数指定截取的起始时间
        cutpic.add("00:00:05");
        cutpic.add("-i");
        cutpic.add(upFilePath); // 同上（指定的文件即可以是转换为flv格式之前的文件，也可以是转换的flv文件）
        cutpic.add("-f");
        cutpic.add("image2");
        cutpic.add("-s"); // 添加参数＂-s＂，该参数指定截取的图片大小
        cutpic.add("320*240"); // 添加截取的图片大小为320*240
        cutpic.add(mediaPicPath); // 添加截取的图片的保存路径
        	
        boolean mark = true;
        ProcessBuilder builder = new ProcessBuilder();
        try {
            builder.command(convert);
            builder.redirectErrorStream(true);
            Process p = builder.start();
            doWaitPro(p,id);  
            p.destroy();  
            
            builder.command(cutpic);
            builder.redirectErrorStream(true);
            p = builder.start();
            p.waitFor();
            p.destroy(); 
        } catch (Exception e) {
            mark = false;
            System.out.println(e);
            e.printStackTrace();
        }
        return mark;
	}
	
	
	
	//等待线程处理完成  
    public static void doWaitPro(Process p,String id){  
        try {  
            String errorMsg = readInputStream(p.getErrorStream(), "error",id);  
            String outputMsg = readInputStream(p.getInputStream(), "out",id);   
               int c = p.waitFor();    
               if (c != 0) {// 如果处理进程在等待    
                   System.out.println("处理失败：" + errorMsg);    
               } else {    
                   String size = outputMsg.substring(outputMsg.lastIndexOf("Lsize=")+6, outputMsg.lastIndexOf("kB time=")).trim();
                   size =CommonUtils.sizeToString(Double.valueOf(size));
                   String timeLength = outputMsg.substring(outputMsg.lastIndexOf("time=")+5, outputMsg.lastIndexOf("bitrate=")).trim();
                   timeLength = timeLength.substring(0, timeLength.lastIndexOf(".")).trim();
                   Map<String, Object> hm = new HashMap<>();
                   hm.put("id", id);
                   hm.put("timeLength", timeLength);
                   hm.put("progress", 100);
                   hm.put("transcodingState", "O");
                   hm.put("size", size);
                   remoteUpdateDatabase(hm);
               }    
        } catch (IOException e) {  
            e.printStackTrace();  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }    
    } 
     
    
    
    /** 
     *  
     * @Title: readInputStream  
     * @Description: 完成进度百分比 
     * @param    
     * @return String  
     * @throws 
     */  
     private static String readInputStream(InputStream is, String f,String id) throws IOException {    
    	  int COMPLETE = 0;
    	 // 将进程的输出流封装成缓冲读者对象    
          BufferedReader br = new BufferedReader(new InputStreamReader(is));    
          StringBuffer lines = new StringBuffer();// 构造一个可变字符串    
          long totalTime = 0;    
           
          Map<String, Object> hm = new HashMap<>();
          hm.put("id", id);
          hm.put("transcodingState", "C");
          // 对缓冲读者对象进行每行循环    
          for (String line = br.readLine(); line != null; line = br.readLine()) {    
           lines.append(line);// 将每行信息字符串添加到可变字符串中    
           int positionDuration = line.indexOf("Duration:");// 在当前行中找到第一个"Duration:"的位置    
           int positionTime = line.indexOf("time=");    
           if (positionDuration > 0) {// 如果当前行中有"Duration:"    
	            String dur = line.replace("Duration:", "");// 将当前行中"Duration:"替换为""    
	            dur = dur.trim().substring(0, 8);// 将替换后的字符串去掉首尾空格后截取前8个字符    
	            int h = Integer.parseInt(dur.substring(0, 2));// 封装成小时    
	            int m = Integer.parseInt(dur.substring(3, 5));// 封装成分钟    
	            int s = Integer.parseInt(dur.substring(6, 8));// 封装成秒    
	            totalTime = h * 3600 + m * 60 + s;// 得到总共的时间秒数    
           }    
           if (positionTime > 0) {// 如果所用时间字符串存在    
	            // 截取包含time=的当前所用时间字符串    
	            String time = line.substring(positionTime, line.indexOf("bitrate") - 1);    
	            time = time.substring(time.indexOf("=") + 1, time.indexOf("."));// 截取当前所用时间字符串    
	            int h = Integer.parseInt(time.substring(0, 2));// 封装成小时    
	            int m = Integer.parseInt(time.substring(3, 5));// 封装成分钟    
	            int s = Integer.parseInt(time.substring(6, 8));// 封装成秒    
	            long hasTime = h * 3600 + m * 60 + s;// 得到总共的时间秒数   
	            float t = (float) hasTime / (float) totalTime;// 计算所用时间与总共需要时间的比例    
	            COMPLETE = (int) Math.floor(t * 100);// 计算完成进度百分比    
           }
           hm.put("progress", COMPLETE);
           remoteUpdateDatabase(hm);
          }    
          br.close();// 关闭进程的输出流    
          return lines.toString();    
     }    
    
     
     /**
      * 远程更新数据库
      * @param hm
      */
	private static void remoteUpdateDatabase(Map<String, Object> hm){
       String _url = "http://192.168.1.132:8081/zhsx/adminOffLine/updateVideoRes?id="+ hm.get("id")+"&timeLength="+hm.get("timeLength")+"&progress="+hm.get("progress")+"&transcodingState="+hm.get("transcodingState")+"&size="+hm.get("size");
  	   System.out.println(_url);
       try {
	   	   java.net.URL url = new java.net.URL(_url);
		   java.net.HttpURLConnection conn = (java.net.HttpURLConnection) url.openConnection();
		   conn.setRequestMethod("GET");
		   conn.setDoOutput(true);
		   conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
		   if(conn.getResponseCode()==200){
			   System.out.println("连接成功");
		   }
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
     }
	
	
	
}
