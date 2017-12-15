package com.zzrenfeng.network.tcpip;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @功能描述：模拟TCP/IP连接服务器端（多线程服务器，可接收多个客户端的同时连接）
 * 			实现原理：实现多客户端操作，需要涉及到多线程，只要你把每个接收到的Socket对象单独开一条线程操作，然后在服务器端用一个死循环while(true)去监听端口就行
 * 			# Socket通讯步骤：（简单分为4步）
 * 				1、建立服务端ServerSocket和客户端Socket
 * 				2、打开连接到Socket的输出输入流
 * 				3、按照协议进行读写操作
 * 				4、关闭相应的资源
 * 			# 服务器端功能：
 * 				1、创建ServerSocket对象，绑定并监听端口
 * 				2、通过accept监听客户端的请求
 * 				3、建立连接后，通过输出输入流进行读写操作
 * 				4、关闭相关资源
 * 			注：java.net.ServerSocket 此类实现服务器套接字。服务器套接字等待请求通过网络传入。它基于该请求执行某些操作，然后可能向请求者返回结果。
 * 				服务器套接字的实际工作由 SocketImpl 类的实例执行。应用程序可以更改创建套接字实现的套接字工厂来配置它自身，从而创建适合本地防火墙的套接字。
 * 			# 客户端功能：
 * 				1、创建（客户端）Socket对象，制定服务器端的地址和端口号
 * 				2、建立连接后，通过输出输入流进行读写操作
 * 				3、通过输出输入流获取服务器返回的信息
 * 				4、关闭相关资源
 * 				注：java.net.Socket 此类实现客户端套接字（也可以就叫“套接字”）。套接字是两台机器间通信的端点。 
 * 					套接字的实际工作由 SocketImpl 类的实例执行。应用程序通过更改创建套接字实现的套接字工厂可以配置它自身，以创建适合本地防火墙的套接字。
 * 
 * @创  建  者：zhoujincheng
 * @版        本：V1.0.0
 * @创建日期：2017年12月8日 上午11:25:18
 * 
 * @修  改  人：
 * @版        本：V1.1.0
 * @修改日期：
 * @修改描述：
 *
 */
public class TcpSocketServerMultiThread {

	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(8888);
			System.out.println("服务器端已启动，等待客户端连接...");
			
			while (true) {
				Socket socket = serverSocket.accept();	//侦听并接受到此套接字的连接，返回一个Socket对象
				ServerSocketThread serverSocketThread = new ServerSocketThread(socket);
				
				serverSocketThread.start();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
