package com.zzrenfeng.network.updping;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @功能描述：PingServer处理多用户请求时对各个线程的处理
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
 * @创建日期：2017年12月7日 上午11:20:38
 * 
 * @修  改  人：
 * @版        本：V1.1.0
 * @修改日期：
 * @修改描述：
 *
 */
public class ServerThread extends Thread {
	
	private DatagramSocket serverSocket;	//数据报套接字
	private DatagramPacket receivePacket;	//接收到的数据包分组
	
	public ServerThread(DatagramSocket serverSocket, DatagramPacket receivePacket) {
		this.serverSocket = serverSocket;
		this.receivePacket = receivePacket;
	}

	@Override
	public void run() {
		InetAddress host = receivePacket.getAddress();	//获取客户端的ip地址
		int port = receivePacket.getPort();	//获取客户端的通讯端口

		System.out.println("---------------------------------------------");
		System.out.println("客户端请求的IP为：" + host + " 客户端接收数据端口为：" + port);
		
		byte[] buffer = new byte[4096];	//数据使用的缓冲区
		long randomTime = (long) (Math.random() * 2000);	//到此步说明接收到新连接，再此生成随机数，模拟传输延时
		String sentence = null;	//接收到的数据
		
		try {
			sleep(randomTime);	//睡眠随机时间，用户模拟传输延时
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if(randomTime > 1000) {	//如果随机数大于1000（超过1s），模拟数据包丢失
			sentence = "Request Timeout,Data Lost!\n";	//数据丢失提示信息
		} else {
			sentence = (new String(receivePacket.getData())).substring(0, 100);	//将数据从缓冲区转换为字符串，取前100个字符
		}		
		
		buffer = sentence.getBytes();	//将请求数据转换为byte数组，用户回发给客户端
		
		DatagramPacket sendPacket = new DatagramPacket(buffer, buffer.length, host, port);	//生成回发数据包，发送给具体的客户端地址和端口
		
		try {
			serverSocket.send(sendPacket);	//发送数据给客户端
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("服务器端处理请求后，返回给客户端的结果：" + sentence);
		System.out.println("---------------------------------------------");
	}

	public DatagramSocket getServerSocket() {
		return serverSocket;
	}

	public void setServerSocket(DatagramSocket serverSocket) {
		this.serverSocket = serverSocket;
	}

	public DatagramPacket getReceivePacket() {
		return receivePacket;
	}

	public void setReceivePacket(DatagramPacket receivePacket) {
		this.receivePacket = receivePacket;
	}	

}
