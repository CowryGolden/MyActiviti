package com.zzrenfeng.network.tcpip;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @功能描述：模拟TCP/IP连接服务器端（单线程服务器，只能接收一个客户端的连接）
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
 * @创建日期：2017年12月8日 上午9:51:38
 * 
 * @修  改  人：
 * @版        本：V1.1.0
 * @修改日期：
 * @修改描述：
 *
 */
public class TcpSocketServerSingleThread {

	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(8888);
			System.out.println("服务器端已启动，等待客户端连接...");
			Socket socket = serverSocket.accept();	//侦听并接受到此套接字的连接，返回一个Socket对象
			
			//根据输入输出流和客户端连接进行读写
			InputStream inputStream = socket.getInputStream();	//从监听套接字中获得一个输入流，接收客户端传递的信息
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);	//为了提高读入效率，将字节流封装为字符流
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);	//将读入的字符流加入到都入流缓冲区
			
			String temp = null;
			String info = "";
			
			while (null != (temp = bufferedReader.readLine())) {
				info += temp;
				System.out.println("#### 服务器端已接收到一个客户端连接！ ####");
				System.out.println("服务器端接收到客户端的信息为：" + info 
						+ "，当前客户端的IP为：" + socket.getInetAddress().getHostAddress()
						+ " 端口号为：" + socket.getPort());
			}
			
			OutputStream outputStream = socket.getOutputStream();	//从监听套接字中获得一个输出流，将接收客户端传递的信息写出到服务器端
			PrintWriter printWriter = new PrintWriter(outputStream);	//将输出流封装成打印流
			printWriter.print("您好，服务器端已经接收到您的信息！");
			printWriter.flush();	//刷新写出流
			socket.shutdownOutput();	//连接套接字关闭输出流
			socket.shutdownInput();		//将此套接字的输入流置于“流的末尾”。发送到套接字的输入流一侧的任何数据都被确认，然后静默丢弃。
										//如果在套接字上调用此方法后从套接字输入流中读取，则该流的可用方法将返回0，其读取方法将返回-1（流结束）。
			
			//关闭相关资源
			printWriter.close();
			outputStream.close();
			bufferedReader.close();
			inputStreamReader.close();
			inputStream.close();
			socket.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
