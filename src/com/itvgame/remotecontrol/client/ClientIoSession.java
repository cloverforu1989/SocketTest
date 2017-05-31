package com.itvgame.remotecontrol.client;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.microedition.io.SocketConnection;

import com.itvgame.remotecontrol.main.RemoteControlMain;
import com.itvgame.remotecontrol.mina.proto.ProtoBase;
import com.itvgame.remotecontrol.mina.proto.STB_Conn;
import com.itvgame.remotecontrol.server.IoBuffer;

public class ClientIoSession {

	public ClientIoSession(SocketConnection socket) {
		if (socket == null) {
			throw new NullPointerException("socket is null");
		}
		try {
			out = socket.openOutputStream();
			in = socket.openDataInputStream();
			ip = socket.getAddress();
			buffer = IoBuffer.allocate(1024, true);
			Client.handler.sessionOpened(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.socket = socket;
	}

	private String ip;
	private SocketConnection socket;
	private boolean sign = true;
	private OutputStream out;
	private DataInputStream in;
	private IoBuffer buffer;
	ProtoBase protobase;


	public void checkMessage() {

		new Thread() {

			public void run() {

				// TODO Auto-generated method stub
				RemoteControlMain.log("Client:", "�ͻ��ˣ�" + ip + "   ������Ϣ��ʼ����" + RemoteControlMain.getProperty("userid"));
				
				protobase = new STB_Conn(RemoteControlMain.getProperty("userid") == null ? "15820467346": RemoteControlMain.getProperty("userid"), "1","1");
				try {
					sendMsg(protobase.toByte());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					RemoteControlMain.log("Client:",ClientIoSession.this.getIp() + " ������id���ʧ��---");
					closeInOut();
					RemoteControlMain.reconnect();
				}
				//RemoteControlMain.log("Client:", ClientIoSession.this.getIp()+ " ������id��Գɹ�---");
				System.out.println("==========�����ӵ�������==========");
				while (sign) {
					try {
						if (System.currentTimeMillis() - Client.time >= 60000) {
							RemoteControlMain.log("AAA", "����");
							Client.time = System.currentTimeMillis();
							sendMsg(new byte[] { 0, 0, 0, 0 });
						}
						byte[] b = new byte[in.available()];
						in.read(b);
						// ����������
						if (b.length > 0) Client.handler.messageReceived(ClientIoSession.this, b);
						b = null;
						try {
							Thread.sleep(1);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					} catch (IOException e) {
						Client.handler.exceptionCaught(ClientIoSession.this, e);
						closeInOut();
						RemoteControlMain.log("ClientIoSession86","��������Ͽ�����,��������������");
						RemoteControlMain.reconnect();

					}
				}
				try {
					RemoteControlMain.disconnect();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}.start();

	}

	public SocketConnection getSocket() {
		return socket;
	}

	protected void closeInOut() {
		sign = false;
		if (out != null) {
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (in != null) {
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (socket != null) {
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void sendMsg(String msg) throws IOException {

		out.write((msg.getBytes()));
		out.flush();

	}

	public void sendMsg(byte[] msg) throws IOException {

		out.write(msg);
		out.flush();

	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

}
