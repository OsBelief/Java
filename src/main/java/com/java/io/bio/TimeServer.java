package com.java.io.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 同步阻塞式IO创建的时间服务器
 * 每请求每线程
 * @author chengzhenhua
 * 
 */
public class TimeServer {
	public static void main(String[] args) {
		int port = 8888;
		ServerSocket server = null;
		try {
			server = new ServerSocket(port);
			Socket socket = null;
			System.out.println("监听开始: ");
			while (true) {
				// 服务器端一个Acceptor线程来监听端口,为每请求创建每线程
				socket = server.accept(); // accept()方法会阻塞直到新的客户端请求
				new Thread(new TimeServerHandler(socket)).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (server != null) {
				try {
					System.out.println("监听结束: ");
					server.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
