package com.zzrenfeng.network.tcpchat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @功能描述：群聊服务器
 * @创  建  者：zhoujincheng
 * @版        本：V1.0.0
 * @创建日期：2017年12月8日 下午2:24:36
 * 
 * @修  改  人：
 * @版        本：V1.1.0
 * @修改日期：
 * @修改描述：
 *
 */
public class ChatServer {

	public static void main(String[] args) throws IOException {
		//定义时间戳
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		
		List<Socket> socketList = new ArrayList<Socket>();
		//创建服务器端套接字
		ServerSocket serverSocket = new ServerSocket(10010);
		
		System.out.println(sdf.format(new Date()) + "-服务器【信息】：群聊服务器已启动！");		
		while (true) {
			//侦听客户端的连接（侦听并接受到此套接字的连接）
			Socket socket = serverSocket.accept();
			//涉及到多个线程，可能会对集合进行增删的操作，要进行同步的处理
			synchronized (socketList) {
				socketList.add(socket);
			}
			//启动一个新的线程去处理这个客户端的连接交流
			HandleSocketThread socketThread = new HandleSocketThread(socket, socketList);
			socketThread.start();
		}
		/*
		 * 特别注意：因为不知道客户端不知道什么时候发送信息，所以服务器端要一直保持开启状态，不能关闭及相关资源
		 */
	}

	
	
	
}
