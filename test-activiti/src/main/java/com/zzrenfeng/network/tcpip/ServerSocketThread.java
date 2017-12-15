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
 * @功能描述：服务器端线程控制类型
 * 			实现多客户端操作，需要涉及到多线程，只要你把每个接收到的Socket对象单独开一条线程操作，然后在服务器端用一个死循环while(true)去监听端口就行
 * 
 * @创  建  者：zhoujincheng
 * @版        本：V1.0.0
 * @创建日期：2017年12月8日 上午11:15:23
 * 
 * @修  改  人：
 * @版        本：V1.1.0
 * @修改日期：
 * @修改描述：
 *
 */
public class ServerSocketThread extends Thread {
	
	private Socket socket;
	private static int count = 0;	//记录连接到服务器的客户端数
	
	public ServerSocketThread(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			//根据输入输出流和客户端连接进行读写
			InputStream inputStream = socket.getInputStream();	//从监听套接字中获得一个输入流，接收客户端传递的信息
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);	//为了提高读入效率，将字节流封装为字符流
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);	//将读入的字符流加入到都入流缓冲区
			
			String temp = null;
			String info = "";			
			
			while (null != (temp = bufferedReader.readLine())) {				
				info += temp;
				System.out.println("-------------------------------------------------");
				count += 1;	//记录连接到服务器的客户端数
				System.out.println("#### 服务器端已接收到第" + count + "个客户端连接！ ####");
				System.out.println("服务器端接收到客户端的信息为：" + info 
						+ "，当前客户端的IP为：" + socket.getInetAddress().getHostAddress()
						+ " 端口号为：" + socket.getPort());
				System.out.println("-------------------------------------------------");
			}
			
			OutputStream outputStream = socket.getOutputStream();	//从监听套接字中获得一个输出流，将接收客户端传递的信息写出到服务器端
			PrintWriter printWriter = new PrintWriter(outputStream);	//将输出流封装成打印流
			printWriter.print("您好，服务器端已经接收到您的信息！您是第" + count + "位登录的用户！");
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
