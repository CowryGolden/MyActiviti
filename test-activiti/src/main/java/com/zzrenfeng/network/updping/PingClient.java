package com.zzrenfeng.network.updping;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * @功能描述：客户端
 * 			####编程实现基于UDP的PING功能，其思想概括如下：####
 * 			# 服务器端PingServer功能：
 * 				1、可以并发的为多个用户请求服务
 * 				2、显示用户通过客户端发送来的消息内容（包含头部和payload信息）
 * 				3、将用户发来的请求在延时一段时间（模拟超时）后返回给客户端，作为收到请求的响应
 * 				4、启动服务器端，初始化一个端口，进行测试
 * 			# 客户端PingClient功能：
 * 				1、模拟发送10个请求，发送一个请求后，最多等待1秒以便接收PingServer返回的相应报文
 * 				2、请求消息的payload中包含关键字PingUDP、序号、时间戳等内容
 * 				3、为每个请求计算折返时间（RTT），统计10个请求的max/min/avg的RTT
 * 				4、通过配置要连接服务器的IP和端口来启动客户端从而连接服务器
 * 
 * @创  建  者：zhoujincheng
 * @版        本：V1.0.0
 * @创建日期：2017年12月7日 上午11:44:23
 * 
 * @修  改  人：
 * @版        本：V1.1.0
 * @修改日期：
 * @修改描述：
 *
 */
public class PingClient {
	
	public static void main(String[] args) throws Exception {
		System.out.println("客户端已启动...");
		
		//接收从系统指定的输入方式输入的数据（默认System.in为键盘输入）
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("请设置连接服务器的IP和端口号（如：localhost 9999）");
		//System.out.println("请输入服务器IP：");
		String host = "localhost"; //scanner.nextLine();	//args[0];	//获取服务器端主机地址
		//System.out.println("请输入服务器端口：");
		int port = 9999; //scanner.nextInt();	//Integer.valueOf(args[1]);	//获取服务器端监听的端口
		
		System.out.println("连接服务器成功，开始发送数据...");
		
		Long[] rtts = new Long[10];	//用于存储rtt，永远最后的统计
		
		for (int i = 0; i < 10; i++) {	//模拟发送10条数据
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS");
			Date sendBefore = new Date();	//记录发送时间
			String sentence = "head:request " + (i + 1)	//模拟用的请求数据
					+ "\n"
					+ "payload:PingUDPSequenceNumber:" + (i + 1) + " TimeStamp:"
					+ sdf.format(sendBefore) + "\n"
					;
			System.out.println("---------------------------------------------");
			System.out.println("客户端发送给服务器端的源数据：" + sentence);
			
			DatagramSocket clientSocket = new DatagramSocket();	//生成客户端DatagramSocket实例
			InetAddress IPAddress = InetAddress.getByName(host);
			
			byte[] buffer = new byte[1024];	//数据包使用的缓冲区
			buffer = sentence.getBytes();	//将请求数据放于缓冲区内
			
			DatagramPacket sendPacket = new DatagramPacket(buffer, buffer.length, IPAddress, port);	//生产发送数据包实例
			clientSocket.send(sendPacket);	//发送到服务器
			
			DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);	//生成接收数据包实例
			
			try {
				clientSocket.receive(receivePacket);
			} catch (IOException e) {
				System.out.println("客户端分组接收异常！");
				e.printStackTrace();
			}
			
			String receiveSentence = new String(receivePacket.getData());	//将数据从缓冲区转换为字符串
			
			Date receiveAfter = new Date();	//记录接收后的时间
			
			rtts[i] = receiveAfter.getTime() - sendBefore.getTime();	//计算rtt
				
			if(rtts[i] > 1000) {
				rtts[i] = 1000L;
				receiveSentence = "Request Timeout,Data Lost!\n";
			}
			System.out.println("客户端从服务器端接收的返回数据：" + receiveSentence);	//显示从Server端返回的数据
			System.out.println("rtt:" + rtts[i]);	//显示rtt
			System.out.println("---------------------------------------------");
			
			clientSocket.close();	//关闭socket
		}
		
		//统计平均rtt，最大rtt和最小rtt
		List<Long> rttList = new ArrayList<Long>();
		rttList = Arrays.asList(rtts);
		Collections.sort(rttList);
		
		long minRtt = Collections.min(rttList);
		long maxRtt = Collections.max(rttList);
		int lostCount = 0;
		long sumRtt = 0L;
		for (Long rtt : rtts) {
			if(rtt == 1000) {
				lostCount++;
			}
			sumRtt += rtt;
		}
		
		long avgRtt = sumRtt / 10;	
		
		BigDecimal lostRate = new BigDecimal(lostCount).divide(new BigDecimal(10), 8, BigDecimal.ROUND_HALF_UP);
		
		double lostRate100 = lostRate.multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		
		System.out.println();
		System.out.println("================Data Statistics==================");
		System.out.println("max rtt: " + maxRtt + "ms");
		System.out.println("min rtt: " + minRtt + "ms");
		System.out.println("avg rtt: " + avgRtt + "ms");
		System.out.println("lost rate: " + (lostRate100) + "%");
	}	
	
}
