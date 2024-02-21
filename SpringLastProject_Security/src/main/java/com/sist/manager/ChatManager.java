package com.sist.manager;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.*;
@ServerEndpoint("/site/chat/chat-ws")
public class ChatManager {
   private static List<Session> users=new ArrayList<Session>();
   
   @OnOpen
   public void onOpen(Session session)
   {
	   users.add(session);
	   System.out.println("Ŭ���̾�Ʈ ����...:"+session.getId());
   }
   @OnMessage
   public void onMessage(String message,Session session) throws Exception
   {
	   System.out.println("���ŵ� �޼���...:"+message+"==> �������:"+session.getId());
	   Iterator<Session> it=users.iterator();
	   while(it.hasNext())
	   {
		   it.next().getBasicRemote().sendText(message);
		   System.out.println(session.getId()+":���� �Ϸ�!!");
	   }
	   
   }
   @OnClose
   public void onClose(Session session)
   {
	   users.remove(session);
	   System.out.println("Ŭ���̾�Ʈ ����...:"+session.getId());
   }
}