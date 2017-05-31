package com.itvgame.remotecontrol.server;


import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.microedition.io.SocketConnection;
import com.hst.game.GameScene;
import com.itvgame.remotecontrol.server.Server;
import com.sun.midp.io.Properties;

public class IoSession {
  
   public IoSession(SocketConnection socket) {
	   if(socket == null) {
		   throw new NullPointerException("socket is null");
	   }
	   try {
		out = socket.openDataOutputStream();
		in = socket.openDataInputStream();
		ip = socket.getAddress();
		buffer = IoBuffer.allocate(1024, true);
		Server.handler.sessionOpened(this);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   this.socket = socket;
   }
   private String ip;
   private String charset = "UTF-8";
   private SocketConnection socket; 
   private boolean sign = true;
   private DataOutputStream out;
   private DataInputStream in;
   private IoBuffer buffer;
   
   
 /*  public void close() {
	   try {
		socket.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		Server.handler.exceptionCaught(IoSession.this, e);
		closeInOut();
	}finally {
		 Server.clientSocketQuenue.removeElement(IoSession.this);
	}
	  
   }*/
   
   public void checkMessage() {
	   new Thread() {
		  
		public void run() {
			// TODO Auto-generated method stub
			 //GameScene.addMsg("�ͻ��ˣ�" + ip + "   ������Ϣ��ʼ����");
			 ByteArrayOutputStream dos = new ByteArrayOutputStream();
			 while(sign) {
			      try {
			    	  
					   /**��������ͨ��***/
					  
					   byte a =  in.readByte();
					   dos.write(a);
					   buffer.put(a);
					   while((a = in.readByte()) != '\n') {
						   dos.write(a);
						   buffer.put(a);
					   }
					   //����������
					   
					   
					   Server.handler.messageReceived(IoSession.this, dos.toByteArray());
					   dos.reset();
					  
			    	   //sendMsg("1");
					  
					  
					   /**end***/
					 
				   
				/*	ByteArrayOutputStream dos = new ByteArrayOutputStream();
					byte[] b = new byte[1024];
					
					int len = -1;
					while((len = in.read(b)) > 0) {
						dos.write(b, 0, len);
					}
					b = dos.toByteArray();*/
					//if(b.length > 0) {
					  /* in = socket.openDataInputStream();
					   out = socket.openDataOutputStream();*/
					    
					    
					   /* if(in.available() > 0) {
						   byte[] b = new byte[in.available()];
						   in.readFully(b);
						   Server.handler.messageReceived(IoSession.this, b);
						   out.write("ok\n".getBytes());
						   out.flush();
						  
						}else {
							Mtd.sleep(1);
						}*/
					  
					   // out.write(1);//������Ӧ
					   //dos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					 //GameScene.addMsg("�ͻ���" + IoSession.this.getIp() + " �Ͽ�����---");
					/*
					e.printStackTrace();
					 //GameScene.addMsg("�ͻ���" + IoSession.this.getIp() + " �Ͽ�����");
					Mtd.sleep(1000);
					Server.handler.exceptionCaught(IoSession.this, e);
					Server.clientSocketQuenue.removeElement(IoSession.this);
					//closeInOut();
					sign = false;
					*/
					
					closeInOut();
					
					
				}
			   }
		}
	   }.start();
	  
   }
   public SocketConnection getSocket() {
	   return socket;
   }
   protected void closeInOut() {
	   sign = false;
	   if(out != null) {
		   try {
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   }
	   if(in != null) {
		   try {
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   }
	   if(socket != null) {
		   try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			Server.removeClient(this);
			
		}
	   }
   }
public void sendMsg(String msg) {
	try {
		out.writeUTF((msg + "\n"));
		out.flush();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		 ////GameScene.addMsg("�ͻ���" + IoSession.this.getIp() + " �Ͽ����� " + e.getMessage());
		closeInOut();
	}
}
public String getIp() {
	return ip;
}
public void setIp(String ip) {
	this.ip = ip;
}
}