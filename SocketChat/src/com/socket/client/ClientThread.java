package com.socket.client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;


public class ClientThread implements Runnable {
	  Socket socket = null;
	  BufferedReader bReader = null;

	public ClientThread(Socket socket) throws IOException{
		System.out.println("client thread");
		this.socket = socket;
		 bReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}
	

	@Override
	public void run() {
		System.out.println("client run start");
		DataInputStream dis = null;
		try {	
			dis = new DataInputStream(socket.getInputStream());
			while(true){
				//读取服务器端数据  
				String receive = dis.readUTF();   
				System.out.println();
				System.out.println("服务器: " + receive);  
			}
		} catch (SocketException e) {
			System.out.println("连接中断");
//			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	  System.out.println("client run end");
	}
	
	
	

}
