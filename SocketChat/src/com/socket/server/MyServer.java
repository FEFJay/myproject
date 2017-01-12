package com.socket.server;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class MyServer {
	
	public static ArrayList<Socket> sockets = new ArrayList<Socket>();
	
	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(30000);
		while (true) {
			//阻塞，等待有客户端请求连接.新的客户端连接，启动新线程
			Socket socket = server.accept();
			sockets.add(socket);
			
			System.out.println("a new client");
			new Thread(new ServerThread(socket)).start();
		}
	}
	
	
	
	

}
