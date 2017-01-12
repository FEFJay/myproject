package com.socket.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

import java.net.Socket;
import java.net.UnknownHostException;

public class MyClient {
	
	public static void main(String[] args){

		
		DataOutputStream dos = null;
		BufferedReader br = null;
		try {
//			Socket socket = new Socket("192.168.88.219", 30000);
			Socket socket = new Socket("127.0.0.1", 30000);

			new Thread(new ClientThread(socket)).start();//开启新线程专门接收服务器发来的信息
			
			dos = new DataOutputStream(socket.getOutputStream());//取得输出流,发送到服务器
			br = new BufferedReader(new InputStreamReader(System.in));//键盘录入
			while(true){
				System.out.print("客户端请输入: ");
				String send = br.readLine();  
				
				dos.writeUTF(send); //发送数据 
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
