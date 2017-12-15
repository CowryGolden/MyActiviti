package com.zzrenfeng.network.tcpip;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @功能描述：模拟TCP/IP连接服务器端
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
 * @创建日期：2017年12月8日 上午9:59:20
 * 
 * @修  改  人：
 * @版        本：V1.1.0
 * @修改日期：
 * @修改描述：
 *
 */
public class TcpSocketClient {

	public static void main(String[] args) {		
		
		try {
			//模拟10个客户端
			for (int i = 0; i < 10; i++) {
				//创建（客户端）Socket对象，并将其连接到指定 IP地址的指定端口号（这里制定服务器端的IP和端口号）
				Socket socket = new Socket("localhost", 8888);
				
				//根据输入输出流和服务器端连接
				OutputStream outputStream = socket.getOutputStream();	//从套接字中获得一个输出流，向服务器端发送信息
				PrintWriter printWriter = new PrintWriter(outputStream);	//将输出流封装成打印流
				printWriter.print("服务器您好！I am a hacker" + (i+1) + "!");
				printWriter.flush();
				socket.shutdownOutput();	//关闭输出流
				
				/*************************************************************************/
				/*
					在实际开发中，基于Socket编程，一般传递的并非字符串，很多情况下是对象，我们可以使用ObjectOutputStream将输出流对象序列化。例如：
					OutputStream outputStream = socket.getOutputStream();
					ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
					User user=new User("admin","123456");
					objectOutputStream.writeObject(user);				
				*/
				/*************************************************************************/			
				
				InputStream inputStream = socket.getInputStream();	//从套接字中获取一个输入流，接收服务器端的信息
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream);	//为了提高读入效率，将字节流封装成字符流
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader);	//将读入的字符流加入到缓冲区
				
				String temp = null;
				String info = "";
				
				while (null != (temp = bufferedReader.readLine())) {
					info += temp;
					System.out.println("客户端接收到服务器端的信息为：" + info);
				}
				
				socket.shutdownInput();		//将此套接字的输入流置于“流的末尾”。发送到套接字的输入流一侧的任何数据都被确认，然后静默丢弃。
											//如果在套接字上调用此方法后从套接字输入流中读取，则该流的可用方法将返回0，其读取方法将返回-1（流结束）。
				//关闭相关资源
				printWriter.close();
				outputStream.close();
				bufferedReader.close();
				inputStreamReader.close();
				inputStream.close();
				socket.close();
			}		
			
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
