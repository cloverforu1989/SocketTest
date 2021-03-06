package com.itvgame.remotecontrol.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Vector;
import javax.microedition.io.Connector;
import javax.microedition.io.ServerSocketConnection;
import javax.microedition.io.SocketConnection;

import com.hst.game.GameScene;
import com.hst.game.KeyObj;
import com.itvgame.remotecontrol.main.RemoteControlMain;
import com.itvgame.remotecontrol.main.RemoteKey;
import com.itvgame.remotecontrol.mina.proto.ProtoBase;
import com.itvgame.remotecontrol.mina.proto.RemoteControl;
import com.sun.midp.io.j2me.serversocket.Socket;

public class Client {
	public static SocketConnection clientSocket = null;
	public static boolean sign2 = true;
	public static final int MAX_CONNECTED_NUM = 2;
	private static int PORT = 11022;
	protected static long time = System.currentTimeMillis();
	protected static long aliveTime = System.currentTimeMillis();
    protected static ClientIoSession session;


	public static ClientHandlerInter handler = new ClientHandlerInter() {

		public void exceptionCaught(ClientIoSession session, Throwable cause) {
			// TODO Auto-generated method stub

		}

		public void inputClosed(ClientIoSession session) {
			// TODO Auto-generated method stub

		}

		public void messageSent(ClientIoSession session, Object message) {
			// TODO Auto-generated method stub

		}

		public void sessionClosed(ClientIoSession session) {
			// TODO Auto-generated method stub

		}

		public void sessionCreated(ClientIoSession session) {
			// TODO Auto-generated method stub

		}

		public void sessionOpened(ClientIoSession session) {
			// TODO Auto-generated method stub

		}

		public void messageReceived(ClientIoSession session, byte[] message) {

			// TODO Auto-generated method stub
         
			Object[] objArr = ProtoBase.decode(message);
			
			if (objArr != null) {
				for (int i = 0; i < objArr.length; i++) {
					Object obj = objArr[i];
					if (obj == null) return;
					if (obj instanceof ProtoBase) {
						ProtoBase protoBase = (ProtoBase) obj;
						if (protoBase != null && protoBase.id == ProtoBase.CLASS_RemoteControl) {
							RemoteControl rc = (RemoteControl) protoBase;
							RemoteControlMain.onKeyEvent(rc);
							try {
								Thread.sleep(1);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						    
							RemoteControlMain.log("msg", RemoteKey.getStr(rc.keyVal()) + " - " + rc.isPress());
						} else {
							RemoteControlMain.logNeeded("messageReceived=1=",protoBase.toString());
						}

					} else    {

							try {
								
								RemoteControlMain.logNeeded("messageReceived=2=", new String((byte[])obj, "utf-8"));
							} catch (UnsupportedEncodingException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

							
						
					}

				}
			}
			
		}
		
		
	};

	public static boolean sign = true;

	public Client() {
		while ((clientSocket = getClentSocket()) == null) {
			RemoteControlMain.log("Client115", "尝试重新连接");
			//GameScene.addMsg("尝试重新连接");
		}
	}

	private static SocketConnection getClentSocket() {
		SocketConnection clientSocket = null;
		try {
			clientSocket = (SocketConnection) Connector.open("socket://"+ RemoteControlMain.getIp() + ":" + PORT);
			clientSocket.setSocketOption(SocketConnection.DELAY, 0);
			clientSocket.setSocketOption(SocketConnection.LINGER, 5);
			clientSocket.setSocketOption(SocketConnection.KEEPALIVE, 0);
			clientSocket.setSocketOption(SocketConnection.RCVBUF, 512);
			clientSocket.setSocketOption(SocketConnection.SNDBUF, 128);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// GameScene.msg.addElement(e.toString());
			RemoteControlMain.log("Client129", "socket 连接失败");
			 ////GameScene.addMsg("socket 连接失败");
		}
		return clientSocket;
	}

	public void start() {

		new Thread() {
			public synchronized void run() {
				session = new ClientIoSession(clientSocket);
				handler.sessionCreated(session);
				//GameScene.addMsg("已经连接服务器。。。");
				RemoteControlMain.log("Client129", "已经连接服务器。。。");
				session.checkMessage();

			};
		}.start();

	}

	public static final void disconnect() throws IOException {
		sign = false;
		synchronized (clientSocket) {

			if (clientSocket != null)
				clientSocket.close();

		}

	}
	
	public static final void sendMsg(byte[] bytes) throws IOException {
		if(session != null) {
			session.sendMsg(bytes);
		}
	} 
}
