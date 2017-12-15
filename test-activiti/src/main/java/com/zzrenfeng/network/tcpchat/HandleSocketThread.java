package com.zzrenfeng.network.tcpchat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @功能描述：处理每个（单个）连接到服务器的客户端的线程
 * @创  建  者：zhoujincheng
 * @版        本：V1.0.0
 * @创建日期：2017年12月8日 下午1:52:39
 * 
 * @修  改  人：
 * @版        本：V1.1.0
 * @修改日期：
 * @修改描述：
 *
 */
public class HandleSocketThread extends Thread {
	
	private Socket socket;
	private List<Socket> socketList;
	
	//定义时间戳
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	
	/**
	 * @功能描述：构造方法
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年12月8日 下午1:54:51
	 * 
	 * @修  改  人：
	 * @版        本：V1.1.0
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param socket		当前连接的客户端
	 * @param socketList	存储已连接客户端的集合
	 */
	public HandleSocketThread(Socket socket, List<Socket> socketList) {
		this.socket = socket;
		this.socketList = socketList;
	}
	
	/**
	 * 重写线程类的run方法
	 */
	@Override
	public void run() {
		InetAddress clientAddress = socket.getInetAddress();	//获取连接到服务器的当前客户端的地址信息
		String clientIP = clientAddress.getHostAddress();	//获取连接到服务器的当前客户端的IP
		int clientPort = socket.getPort();	//获取连接到服务器的当前客户端的端口号
		System.out.println(sdf.format(new Date()) + "-服务器【信息】：" + clientIP + ":" + clientPort + " 上线了！");
		
		if(clientIP.equals("192.168.1.70")) {
			synchronized (socketList) {
				sendToAll("【严重警告】：[" + clientIP + ":" + clientPort + "]由于违规操作，已被拉入黑名单！");
				socketList.remove(socket);
			}
			return;
		}
		
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
			
			String info = "";
			while (null != (info = br.readLine())) {
				String msg = "[" + clientIP + ":" + clientPort + "]：" + info;
				System.out.println(sdf.format(new Date()) + "-服务器【信息】：" + msg);
				//把这个客户端说的话，发送给其他所有客户端
				sendToAll(msg);
			}
		} catch (IOException e) {
			//e.printStackTrace();
			System.out.println(sdf.format(new Date()) + "-服务器【提醒】：" + clientIP + ":" + clientPort + " 下线了！");
			synchronized (socketList) {
				socketList.remove(socket);
			}
		}		
	}
	
	/**
	 * @功能描述：把信息发送给所有的客户端，并将当前socket排除在外
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2017年12月8日 下午2:16:20
	 * 
	 * @修  改  人：
	 * @版        本：V1.1.0
	 * @修改日期：
	 * @修改描述：
	 * 
	 * @param msg	发送的信息
	 */
	private void sendToAll(String msg) {
		synchronized (socketList) {
			for (Socket s : socketList) {
				if(s != socket) {
					try {
						PrintStream ps = new PrintStream(s.getOutputStream());
						ps.println(msg);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
}
