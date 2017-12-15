package com.zzrenfeng.network.updping;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Scanner;

/**
 * @功能描述：服务器端
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
 * @创建日期：2017年12月7日 上午10:39:11
 * 
 * @修  改  人：
 * @版        本：V1.1.0
 * @修改日期：
 * @修改描述：
 *
 */
public class PingServer extends Thread {
	
	private int initPort;	//监听的端口号
	private DatagramSocket serverSocket;	//数据包套接字
	private DatagramPacket receivePacket;	//接收到的数据包分组
	private byte[] buffer = new byte[1024];	//数据包使用的缓冲区
	
	public PingServer() {
		
	}
	
	public PingServer(int initPort) {
		this.initPort = initPort;
	}
	
	public void run() {
		System.out.println("####编程实现基于UDP的PING(java)服务器####");
		System.out.println("====PING SERVER STARTED====");
		System.out.println("请设置服务器监听的端口号，请输入：");
		//接收从系统指定的输入方式输入的数据（默认System.in为键盘输入）
		Scanner scanner = new Scanner(System.in);
		this.initPort = scanner.nextInt();
		//this.initPort = initPort;
		System.out.println("服务器端设置的监听端口号为：" + initPort + "，监听服务开始...");
		
		try {
			//根据输入的监听端口生成Server端的DatagramSocket实例
			serverSocket = new DatagramSocket(initPort);
		} catch (SocketException e) {			
			//捕获到此异常一般为输入的端口非法，或者被占用
			System.out.println("服务器监听端口号：" + initPort + "失败，端口号非法或已被占用，请重新输入新的端口号。");
			e.printStackTrace();
			System.exit(0); //终止退出
		}
		
		//死循环，不断监听是否有请求数据
		while(true) {
			receivePacket = new DatagramPacket(buffer, buffer.length);	//生成接收数据报包实例
			//监听是否有用户发出新的request连接到PingServer
			//程序会陷入到该语句，知道有新的连接产生
			
			try {
				serverSocket.receive(receivePacket);
			} catch (IOException e) {
				System.out.println("服务器分组接收异常");
				e.printStackTrace();
			}
			
			ServerThread thread = new ServerThread(serverSocket, receivePacket);	//到此步说明有新的请求了，此时生成一个新的服务线程
			thread.start();	//启动线程
			
		}		
	}
	
	//回收资源
	@Override
	public void destroy() {
		serverSocket.close();
	}
	
	//main方法
	public static void main(String[] args) throws Exception {
		//PingServer pingServer = new PingServer(Integer.valueOf(args[0]));
		//PingServer pingServer = new PingServer(9999);
		PingServer pingServer = new PingServer();
		pingServer.start();
	}
	
}
