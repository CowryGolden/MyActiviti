package com.zzrenfeng.network.tcpchat;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @功能描述：群聊客户端
 * @创  建  者：zhoujincheng
 * @版        本：V1.0.0
 * @创建日期：2017年12月8日 下午2:50:44
 * 
 * @修  改  人：
 * @版        本：V1.1.0
 * @修改日期：
 * @修改描述：
 *
 */
public class ChatClient {

	public static void main(String[] args) {
		try {
			Socket client = new Socket("localhost", 10010);
			//创建客户端发送信息线程
			new ChatClientSend(client).start();
			//创建 客户端接收信息线程
			new ChatClientReceive(client).start();
			
			/*
			 * 特别注意：因为要实现聊天功能，而不是只发送一次信息，所以输入流和输出流都不能关闭
			 */
//			client.shutdownOutput();
//			client.shutdownInput();
//			client.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
