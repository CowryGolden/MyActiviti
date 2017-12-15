package com.zzrenfeng.network.tcpchat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @功能描述：群聊客户端接收服务器信息的线程类
 * @创  建  者：zhoujincheng
 * @版        本：V1.0.0
 * @创建日期：2017年12月8日 下午2:44:25
 * 
 * @修  改  人：
 * @版        本：V1.1.0
 * @修改日期：
 * @修改描述：
 *
 */
public class ChatClientReceive extends Thread {
	
	private Socket socket;
	//定义时间戳
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	
	public ChatClientReceive(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			//按行接收信息
			String info = "";
			while(null != (info = br.readLine())) {
				System.out.println(sdf.format(new Date()) + "-客户端【信息】：接收到服务器端发来的信息为：" + info);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
}
