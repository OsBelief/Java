package com.java.concurrent;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class TaskExecutionWebServer {
	private static final int NTHREADS = 100;
	private static final Executor exec = Executors.newFixedThreadPool(NTHREADS);
	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(80);
		while(true) {
			Socket connection = server.accept();	// 接受连接
			Runnable task = new Runnable() {
				@Override
				public void run() {
					// 处理请求
				}
			};
			exec.execute(task);
		}
	}
}
