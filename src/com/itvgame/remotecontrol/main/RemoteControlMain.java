package com.itvgame.remotecontrol.main;

import java.io.IOException;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;
import com.itvgame.remotecontrol.client.Client;
import com.itvgame.remotecontrol.mina.proto.RemoteControl;
import com.itvgame.remotecontrol.mina.proto.StbNotify;

/**
 * Զ�̿��ƽӿ���
 * 
 * @author Clover4U
 * 
 */
public final class RemoteControlMain {
	private static MIDlet midlet = null;// �û�id
	private static RemoteControlListener rcListener;
	private static Client client;
	private static boolean isInit = false;
	private static boolean debug = false;
	private static Canvas currentCanvas = null;// canvas
	private static RemoteControl rc = new RemoteControl();
    private static final String TAG = "RemoteControlMain";
	private static String IP = "219.134.132.150";

	public static String getIp() {
		return IP;
	}

	public static final String getProperty(String key) {
		if (midlet == null)
			return null;
		return midlet.getAppProperty(key);

	}

	/**
	 * ��ʼ��
	 * 
	 * @param remoteControlListener
	 *   ��������������
	 * @param midlet ��midlet
	 */
	public static synchronized void init(RemoteControlListener rcListener, MIDlet midlet) {
		
		if (!isInit && rcListener != null && midlet != null) {
			RemoteControlMain.rcListener = rcListener;
			RemoteControlMain.midlet = midlet;
			RemoteControlMain.currentCanvas = (Canvas) Display.getDisplay(midlet).getCurrent();
			IP = midlet.getAppProperty("rcIp");
			client = new Client();
			client.start();
			isInit = true;
		} else {
			if (isInit)
				logNeeded(TAG, "�Ѿ���ʼ�����ˣ�");
			else if (rcListener == null)
				logNeeded(TAG, "rcListener����Ϊ��");
			else if (midlet == null)
				logNeeded(TAG, "midlet����Ϊ��");
		}
	}

	public static final void onKeyEvent(RemoteControl r) {
		byte key = RemoteKey.swapRemoteKeyToSetBoxKey(r.keyVal());
		
		if(r.isPress()) {
			rcListener.keyPressedRemote(key); 
		}else {
			rcListener.keyRleasedRemote(key); 
		}
	}

	public static void log(String TAG, Object object) {
		if (debug) {
			System.out.println(TAG + "==== " + String.valueOf(object));
		}
	}
	public static void logNeeded(String TAG, Object object) {
		System.out.println(TAG + "==== " + String.valueOf(object));
		
	}

	public static boolean isDebug() {
		return debug;
	}

	public static synchronized void setDebug(boolean debug) {
		RemoteControlMain.debug = debug;
	}

	/**
	 * �Ͽ�����
	 * 
	 * @throws IOException
	 */
	public static synchronized void disconnect() throws IOException {
		synchronized (client) {
			if (client != null) {
				client.disconnect();
			}
		}

	}

	/**
	 * ����
	 */
	public static void reconnect() {
		synchronized (client) {
			if (client != null) {
				try {
					client.disconnect();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				client = new Client();
				client.start();
			}
		}

	}
	
	public static void exitGame(IListener listener){
		StbNotify stbn = new StbNotify(-1, "exitGame");
		try {
			Client.sendMsg(stbn.toByte());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	    }finally {
			
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(listener != null) {
				System.out.println("~~~~~~~~~~~~~����11~~~~~~~~~~~~~~~");
				Client.sign = false;
				listener.callback();
				
			}else {
				System.out.println("~~~~~~~~~~~~~����222~~~~~~~~~~~~~~~");
				Client.sign = false;
				if(midlet != null)
				   midlet.notifyDestroyed();
				
				
			}
			
			
		}
	}
	
	
}
