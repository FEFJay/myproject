package com.socket.server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class ServerThread implements Runnable {
	 Socket socket = null;
//	 BufferedReader bReader = null;
	

	public ServerThread(Socket socket) throws IOException{
		System.out.println("server thread");
		this.socket = socket;
//		bReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}

	
	
	@Override
	public void run() {
		System.out.println("run start");
       try {
		    String content = null;
		    while ((content = readFromClient()) != null) {
		    	System.out.println("content2="+content);
				for (Socket socket : MyServer.sockets) {
					DataOutputStream out = new DataOutputStream(socket.getOutputStream());
					out.writeUTF(content);
				}
			}
    	} catch (Exception e) {
		   e.printStackTrace();
	   }

 
       System.out.println("run end");
	}



	//读取客户端数据方法
	private String readFromClient() {
		System.out.println("读取数据readFromClient");
		DataInputStream in = null;
		 try {
			 in = new DataInputStream(socket.getInputStream());
			 String string = in.readUTF();
			 System.out.println("string="+string);
			return string;
		} catch (Exception e) {//如果读取数据有异常，则表示该客户端已经关闭，要从服务器端的客户列表删除
			e.printStackTrace();
			System.out.println("读取数据有异常,删除");
			try {
				socket.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			MyServer.sockets.remove(socket);
		}
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}





























