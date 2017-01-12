package com.shiyanlou.chat;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import net.sf.json.JSONObject;

/**
 * 聊天服务器类
 * 
 * @author shiyanlou
 * 
 */
@ServerEndpoint("/websocket")
public class ChatServer {
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 日期格式化
	public static Set<Session> sessions = new HashSet<Session>();
	private Session newSession = null;
	private User user = null;

	/*
	 * 1
	 * */
	@OnOpen
	public void open(Session session) {
		// 添加初始化操作
		System.out.println(session.getId()+"已经上线！Client connected");
		this.newSession = session;
		
		// 把所有已经建立的连接都保存起来一起维护。判断是不是新的连接，是则加入到连接组，不是新的则已经加入了不用重复加。
		sessions.add(session);
		System.out.println("当前会话数量=" + sessions.size());
		
	}

	/**2
	 * 接受客户端的消息，并把消息发送给所有连接的会话
	 * 
	 * @param message
	 *            客户端发来的消息
	 * @param session
	 *            客户端的会话
	 * @throws IOException
	 */
	@OnMessage
	public void getMessage(String message, Session session) {
		if (sessions.size()<1) {
			return;
		}
		
		// Print the client message for testing purposes
		System.out.println("msg=" + message);
		
		
		// 把客户端的消息解析为JSON对象
		JSONObject jsonObject = JSONObject.fromObject(message);
		this.user=new User(jsonObject.getString("nickname"), newSession);
		
		jsonObject.put("date", DATE_FORMAT.format(new Date()));// 在消息中添加发送日期
		// 把消息发送给所有连接的会话
		for (Session openSession : sessions) {
			if (openSession.equals(session)) {// 添加本条消息是否为当前会话本身发的标志，获取图标
				jsonObject.put("isSelf", openSession.equals(session));
			} else {// 不是会话本身发送的消息,获取图标
				jsonObject.put("others", openSession.equals(session));
			}

			// 发送JSON格式的消息
			openSession.getAsyncRemote().sendText(jsonObject.toString());
		}

	}

/*
 * 3
 * */
	@OnError
	public void error(Throwable t) {
		// 添加处理错误的操作
		System.out.println("connection error");
		System.out.println(t.getMessage());//答应捕捉到的异常
	}
	
	
 /*
  * 4
  * */
	@OnClose
	public void close() {
		sessions.remove(newSession);//从在线列表中删去已经下线的用户session
		// 添加关闭会话时的操作
		if (user != null) {
			System.out.println(user.getNickName()+"已经下线！Connection closed");
			sendOffLine(newSession);
		}

		System.out.println("error session has been removed!");
	}
	
	
	private  void sendOffLine(Session session) {
		if (user == null || sessions.size()<1) {
			return;
		}
		String message="{'content':'<p>"+user.getNickName()+"已经退出聊天</p>','nickname':'系统消息'}";
		// 把客户端的消息解析为JSON对象
		
		JSONObject jsonObject = JSONObject.fromObject(message);
		jsonObject.put("date", DATE_FORMAT.format(new Date()));// 在消息中添加发送日期
		
		// 把消息发送给所有连接的会话
		for (Session openSession : sessions) {
			jsonObject.put("others", openSession.equals(session));// 不是会话本身发送的消息，获取不同图标
			openSession.getAsyncRemote().sendText(jsonObject.toString());// 发送JSON格式的离线消息
		}
	}
	
	
	
	
	
	
	
	
	
	

}
