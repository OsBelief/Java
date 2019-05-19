package com.java.io.nio;

public class TimeServer {
	public static void main(String[] args) {
		int port = 8888;
		new Thread(new MultiplexerTimeServer(port)).start();
	}
}
