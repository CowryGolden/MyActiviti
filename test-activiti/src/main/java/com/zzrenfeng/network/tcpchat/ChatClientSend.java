package com.zzrenfeng.network.tcpchat;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * @功能描述：群聊客户端向服务器端发送信息的线程类
 * @创  建  者：zhoujincheng
 * @版        本：V1.0.0
 * @创建日期：2017年12月8日 下午2:37:14
 * 
 * @修  改  人：
 * @版        本：V1.1.0
 * @修改日期：
 * @修改描述：
 *
 */
public class ChatClientSend extends Thread {
	
	private Scanner scanner;
	private Socket socket;
	//定义时间戳
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		
	public ChatClientSend(Socket socket) {
		this.socket = socket;
	}
	
	@Override
	public void run() {
		System.out.println(sdf.format(new Date()) + "-客户端【信息】：服务器信息[" + socket.getInetAddress() + ":" + socket.getPort() + "]");
		System.out.println(sdf.format(new Date()) + "-客户端【信息】：本机信息[" + socket.getLocalAddress() + ":" + socket.getLocalPort() + "]");
		System.out.println(sdf.format(new Date()) + "-客户端【信息】：请用输入您要发送的信息，按回车键[Enter]进行发送。开始畅聊吧！");
		System.out.println(sdf.format(new Date()) + "-客户端【信息】：请输入您要发送的内容：");
		scanner = new Scanner(System.in);		
		
		try {
			PrintStream ps = new PrintStream(socket.getOutputStream());
			
			String info = "";
			//阻塞式发送信息
			while(null != (info = scanner.nextLine())) {
				ps.println(info);				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
